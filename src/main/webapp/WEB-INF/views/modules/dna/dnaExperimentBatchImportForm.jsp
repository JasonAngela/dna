<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>DNA导入试验记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	
	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>批量导入</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaExperiment" action="${ctx}/dna/dnaExperiment/batchImport" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">基因盒：</label>
			<div class="controls">
				<form:select path="cassette.id">
					<form:options itemLabel="name" itemValue="id" items="${cassetteList}"/> 
				</form:select>
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