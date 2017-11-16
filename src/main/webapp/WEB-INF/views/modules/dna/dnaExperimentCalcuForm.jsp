<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>DNA导入试验记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function CheckedNo(){ 
			$(':checkbox').prop('checked',''); 
			}  
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>选择进行计算</li>
		<input type="button" value="全不选" onclick="CheckedNo()">
	</ul><br/>	<form:form id="inputForm" modelAttribute="dnaExperiment" action="${ctx}/dna/dnaExperiment/calcu" method="post" class="form-horizontal">
	<form:hidden path="id"/>
		<table>
		<tr>
			<tr>
				<td width="60px">选择</td>
				<td width="60px;">编码</td>
			</tr>
	  	</tr>	
			<c:forEach items="${dnaExperiment.dnaExperimentSpecimenList}" var="specimen" varStatus="kl">
				<tr>
					<td><input type="checkbox" name="dnaSpeIteams[${kl.index}].specimen" value="${specimen.specimenCode}"></td>
					<td>${specimen.specimenCode}</td>
					<td>
						<input type="checkbox" name="dnaSpeIteams[${kl.index}].parrens" value="F">父亲
						<input type="checkbox" name="dnaSpeIteams[${kl.index}].parrens" value="C">小孩
						<input type="checkbox" name="dnaSpeIteams[${kl.index}].parrens" value="M">母亲
					</td>
				</tr>
			</c:forEach>
		</table>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
	</form:form>
</body>
</html>