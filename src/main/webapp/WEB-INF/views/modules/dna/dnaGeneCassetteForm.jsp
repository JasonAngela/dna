<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基因盒管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dna/dnaGeneCassette/">基因盒列表</a></li>
		<li class="active"><a href="${ctx}/dna/dnaGeneCassette/form?id=${dnaGeneCassette.id}">基因盒<shiro:hasPermission name="dna:dnaGeneCassette:edit">${not empty dnaGeneCassette.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaGeneCassette:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaGeneCassette" action="${ctx}/dna/dnaGeneCassette/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="seq" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">基因盒明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>基因座</th>
								<th>排序</th>
								<shiro:hasPermission name="dna:dnaGeneCassette:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dnaGeneCassetteItemList">
						</tbody>
						<shiro:hasPermission name="dna:dnaGeneCassette:edit"><tfoot>
							<tr><td colspan="3"><a href="javascript:" onclick="addRow('#dnaGeneCassetteItemList', dnaGeneCassetteItemRowIdx, dnaGeneCassetteItemTpl);dnaGeneCassetteItemRowIdx = dnaGeneCassetteItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dnaGeneCassetteItemTpl">//<!--
						<tr id="dnaGeneCassetteItemList{{idx}}">
							<td class="hide">
								<input id="dnaGeneCassetteItemList{{idx}}_id" name="dnaGeneCassetteItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaGeneCassetteItemList{{idx}}_id" name="dnaGeneCassetteItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaGeneCassetteItemList{{idx}}_cassette" name="dnaGeneCassetteItemList[{{idx}}].cassette.id" type="hidden" value="{{row.cassette.id}}"/>
							</td>
							<td>
								<select id="dnaGeneCassetteItemList{{idx}}_loci" name="dnaGeneCassetteItemList[{{idx}}].loci.id" data-value="{{row.loci.id}}" class="input-small required">
									<c:forEach items="${lociList}" var="loci">
										<option value="${loci.id}">${loci.name}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="dnaGeneCassetteItemList{{idx}}_seq" name="dnaGeneCassetteItemList[{{idx}}].loci.id" data-value="{{row.loci.id}}" class="input-small required"  type="number"/>
							</td>
							<shiro:hasPermission name="dna:dnaGeneCassette:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dnaGeneCassetteItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dnaGeneCassetteItemRowIdx = 0, dnaGeneCassetteItemTpl = $("#dnaGeneCassetteItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dnaGeneCassette.dnaGeneCassetteItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#dnaGeneCassetteItemList', dnaGeneCassetteItemRowIdx, dnaGeneCassetteItemTpl, data[i]);
								dnaGeneCassetteItemRowIdx = dnaGeneCassetteItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaGeneCassette:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>