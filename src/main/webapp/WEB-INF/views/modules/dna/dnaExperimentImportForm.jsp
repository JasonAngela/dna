<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>DNA导入试验记录</title>
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
		<li><a href="${ctx}/dna/dnaExperiment/">DNA试验列表</a></li>
		<li class="active"><a href="${ctx}/dna/dnaExperiment/form?id=${dnaExperiment.id}">DNA试验<shiro:hasPermission name="dna:dnaExperiment:edit">${not empty dnaExperiment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaExperiment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaExperiment" action="${ctx}/dna/dnaExperiment/import" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden path="act.businessId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">鉴定编码：</label>
			<div class="controls">
				 ${dnaExperiment.code}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">基因盒：</label>
			<div class="controls">
				<form:select path="cassette.id">
					<form:options itemLabel="name" itemValue="id" items="${cassetteList}"/> 
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验室：</label>
			<div class="controls">
				<form:select path="lab.id">
					<form:options items="${labList}" itemLabel="name" itemValue="id"/>
				</form:select>
			</div>
		</div>
		 
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>  
		</div>
		<div class="control-group">
			<label class="control-label">导入试验记录：</label>
			<div class="controls">
				<input id="importDataAddress" name="importDataAddress" type="hidden" value="" maxlength="255"/>
				<sys:ckfinder input="importDataAddress" type="files" uploadPath="/dnaExperiment/import" selectMultiple="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaExperiment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>