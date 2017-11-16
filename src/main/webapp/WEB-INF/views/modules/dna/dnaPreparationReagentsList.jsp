<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试剂配制记录表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dna/dnaPreparationReagents/">试剂配制记录表列表</a></li>
		<shiro:hasPermission name="dna:dnaPreparationReagents:edit"><li><a href="${ctx}/dna/dnaPreparationReagents/form">试剂配制记录表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaPreparationReagents" action="${ctx}/dna/dnaPreparationReagents/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>实验室id：</label>
				<form:input path="lab.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>实验室</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>备注</th>
				<th>使用人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaPreparationReagents">
			<tr>
				<td><a href="${ctx}/dna/dnaPreparationReagents/form?id=${dnaPreparationReagents.id}">
					${dnaPreparationReagents.lab.name}
				</a></td>
				<td>
					<fmt:formatDate value="${dnaPreparationReagents.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${dnaPreparationReagents.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaPreparationReagents.remarks}
				</td>
				<td>
					${dnaPreparationReagents.operator.name}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>