<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Dna实验管理</title>
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
		<li><a href="${ctx}/oneself/oneself/">Dna实验列表</a></li>
		<li class="active"><a href="${ctx}/oneself/oneself/form?id=${oneself.id}">Dna实验<shiro:hasPermission name="oneself:oneself:edit">${not empty oneself.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oneself:oneself:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oneself" action="${ctx}/oneself/oneself/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">onesel_experimental：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>样品编号</th>
								<th>备注</th>
								<shiro:hasPermission name="oneself:oneself:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="oneselExperimentalList">
						</tbody>
						<shiro:hasPermission name="oneself:oneself:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#oneselExperimentalList', oneselExperimentalRowIdx, oneselExperimentalTpl);oneselExperimentalRowIdx = oneselExperimentalRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="oneselExperimentalTpl">//<!--
						<tr id="oneselExperimentalList{{idx}}">
							<td class="hide">
								<input id="oneselExperimentalList{{idx}}_id" name="oneselExperimentalList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="oneselExperimentalList{{idx}}_delFlag" name="oneselExperimentalList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="oneselExperimentalList{{idx}}_experimentalId" name="oneselExperimentalList[{{idx}}].experimentalId" type="text" value="{{row.experimentalId}}" maxlength="64" class="input-small required"/>
							</td>
							
							<td>
								<textarea id="oneselExperimentalList{{idx}}_remarks" name="oneselExperimentalList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="oneself:oneself:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#oneselExperimentalList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var oneselExperimentalRowIdx = 0, oneselExperimentalTpl = $("#oneselExperimentalTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(oneself.oneselExperimentalList)};
							for (var i=0; i<data.length; i++){
								addRow('#oneselExperimentalList', oneselExperimentalRowIdx, oneselExperimentalTpl, data[i]);
								oneselExperimentalRowIdx = oneselExperimentalRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="oneself:oneself:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>