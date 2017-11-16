<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实验室使用记录管理</title>
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
		<li class="active"><a href="${ctx}/synth/synthLabUsageRecord/">实验室使用记录列表</a></li>
		<shiro:hasPermission name="synth:synthLabUsageRecord:edit"><li><a href="${ctx}/synth/synthLabUsageRecord/form">实验室使用记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="synthLabUsageRecord" action="${ctx}/synth/synthLabUsageRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>实验室</th>
				<th>使用人</th>
				<th>使用时间</th>
				<shiro:hasPermission name="synth:synthLabUsageRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="synthLabUsageRecord">
			<tr>
				<td><a href="${ctx}/synth/synthLabUsageRecord/form?id=${synthLabUsageRecord.id}">
					${synthLabUsageRecord.lab.name}
				</a></td>
				<td>
					${synthLabUsageRecord.operator.name}
				</td>
				<td>
					<fmt:formatDate value="${synthLabUsageRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="synth:synthLabUsageRecord:edit"><td>
    				<a href="${ctx}/synth/synthLabUsageRecord/form?id=${synthLabUsageRecord.id}">修改</a>
					<a href="${ctx}/synth/synthLabUsageRecord/delete?id=${synthLabUsageRecord.id}" onclick="return confirmx('确认要删除该实验室使用记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>