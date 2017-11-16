<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>str导入记录管理</title>
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
		<li><a href="${ctx}/dna/dnaStrImport/">str导入记录列表</a></li>
		<li class="active"><a href="${ctx}/dna/dnaStrImport/form?id=${dnaStrImport.id}">str导入记录<shiro:hasPermission name="dna:dnaStrImport:edit">${not empty dnaStrImport.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaStrImport:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaStrImport" action="${ctx}/dna/dnaStrImport/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden path="act.businessId"/>
		<form:hidden path="register.id"/>
		<sys:message content="${message}"/>		
		 
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">str导入明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>gene_loci</th>
								<th>material_code</th>
								<th>value</th>
								<shiro:hasPermission name="dna:dnaStrImport:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dnaStrImportItemList">
						</tbody>
						<shiro:hasPermission name="dna:dnaStrImport:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#dnaStrImportItemList', dnaStrImportItemRowIdx, dnaStrImportItemTpl);dnaStrImportItemRowIdx = dnaStrImportItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dnaStrImportItemTpl">//<!--
						<tr id="dnaStrImportItemList{{idx}}">
							<td class="hide">
								<input id="dnaStrImportItemList{{idx}}_id" name="dnaStrImportItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaStrImportItemList{{idx}}_delFlag" name="dnaStrImportItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dnaStrImportItemList{{idx}}_geneLoci" name="dnaStrImportItemList[{{idx}}].geneLoci" type="text" value="{{row.geneLoci}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaStrImportItemList{{idx}}_materialCode" name="dnaStrImportItemList[{{idx}}].materialCode" type="text" value="{{row.materialCode}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaStrImportItemList{{idx}}_value" name="dnaStrImportItemList[{{idx}}].value" type="text" value="{{row.value}}" maxlength="255" class="input-small "/>
							</td>
							<shiro:hasPermission name="dna:dnaStrImport:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dnaStrImportItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dnaStrImportItemRowIdx = 0, dnaStrImportItemTpl = $("#dnaStrImportItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dnaStrImport.dnaStrImportItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#dnaStrImportItemList', dnaStrImportItemRowIdx, dnaStrImportItemTpl, data[i]);
								dnaStrImportItemRowIdx = dnaStrImportItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaStrImport:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>