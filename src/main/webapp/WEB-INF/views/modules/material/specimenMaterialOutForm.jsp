<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物料领取管理</title>
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
		<li><a href="${ctx}/material/specimenMaterialOut/">物料领取列表</a></li>
		<li class="active"><a href="${ctx}/material/specimenMaterialOut/form?id=${specimenMaterialOut.id}">物料领取<shiro:hasPermission name="material:specimenMaterialOut:edit">${not empty specimenMaterialOut.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="material:specimenMaterialOut:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="specimenMaterialOut" action="${ctx}/material/specimenMaterialOut/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:input path="pic" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">领取明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>编码</th>
								<th>样品编码</th>
								<th>样品类型</th>
								<th>数量</th>
								<th>度量</th>
								<shiro:hasPermission name="material:specimenMaterialOut:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="specimenMaterialOutItemList">
						</tbody>
						<shiro:hasPermission name="material:specimenMaterialOut:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#specimenMaterialOutItemList', specimenMaterialOutItemRowIdx, specimenMaterialOutItemTpl);specimenMaterialOutItemRowIdx = specimenMaterialOutItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="specimenMaterialOutItemTpl">//<!--
						<tr id="specimenMaterialOutItemList{{idx}}">
							<td class="hide">
								<input id="specimenMaterialOutItemList{{idx}}_id" name="specimenMaterialOutItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="specimenMaterialOutItemList{{idx}}_delFlag" name="specimenMaterialOutItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="specimenMaterialOutItemList{{idx}}_code" name="specimenMaterialOutItemList[{{idx}}].code" type="text" value="{{row.code}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="specimenMaterialOutItemList{{idx}}_clientCode" name="specimenMaterialOutItemList[{{idx}}].clientCode" type="text" value="{{row.clientCode}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="specimenMaterialOutItemList{{idx}}_materialType" name="specimenMaterialOutItemList[{{idx}}].materialType" type="text" value="{{row.materialType}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<input id="specimenMaterialOutItemList{{idx}}_qty" name="specimenMaterialOutItemList[{{idx}}].qty" type="text" value="{{row.qty}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<input id="specimenMaterialOutItemList{{idx}}_measure" name="specimenMaterialOutItemList[{{idx}}].measure" type="text" value="{{row.measure}}" maxlength="255" class="input-small "/>
							</td>
							<shiro:hasPermission name="material:specimenMaterialOut:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#specimenMaterialOutItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var specimenMaterialOutItemRowIdx = 0, specimenMaterialOutItemTpl = $("#specimenMaterialOutItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(specimenMaterialOut.specimenMaterialOutItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#specimenMaterialOutItemList', specimenMaterialOutItemRowIdx, specimenMaterialOutItemTpl, data[i]);
								specimenMaterialOutItemRowIdx = specimenMaterialOutItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="material:specimenMaterialOut:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>