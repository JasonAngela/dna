<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>等位基因频率管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dna/dnaGeneFrequency/">等位基因频率列表</a></li>
		<li class="active"><a href="${ctx}/dna/dnaGeneFrequency/form?id=${dnaGeneFrequency.id}">等位基因频率<shiro:hasPermission name="dna:dnaGeneFrequency:edit">${not empty dnaGeneFrequency.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaGeneFrequency:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaGeneFrequency" action="${ctx}/dna/dnaGeneFrequency/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">基因座：</label>
			<div class="controls">
			
			
			<form:select path="loci.id" class="input-xlarge required">
			<c:forEach items="${dnaGeneLocis}" var="dnaGeneLocis">
				<form:option value="${dnaGeneLocis.id}">
				 
				${dnaGeneLocis.name}
				</form:option>
			</c:forEach>
				</form:select>
			
			   
			    
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">值：</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">概率：</label>
			<div class="controls">
				<form:input path="probability" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaGeneFrequency:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>