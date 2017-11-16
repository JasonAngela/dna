<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基因盒管理</title>
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
		<li class="active"><a href="${ctx}/dna/dnaGeneCassette/">基因盒列表</a></li>
		<shiro:hasPermission name="dna:dnaGeneCassette:edit"><li><a href="${ctx}/dna/dnaGeneCassette/form">基因盒添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaGeneCassette" action="${ctx}/dna/dnaGeneCassette/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>排序</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="dna:dnaGeneCassette:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaGeneCassette">
			<tr>
				<td><a href="${ctx}/dna/dnaGeneCassette/form?id=${dnaGeneCassette.id}">
					${dnaGeneCassette.name}
				</a></td>
				<td>
					${dnaGeneCassette.seq}
				</td>
				<td>
					<fmt:formatDate value="${dnaGeneCassette.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaGeneCassette.remarks}
				</td>
				<shiro:hasPermission name="dna:dnaGeneCassette:edit"><td>
    				<a href="${ctx}/dna/dnaGeneCassette/form?id=${dnaGeneCassette.id}">修改</a>
					<a href="${ctx}/dna/dnaGeneCassette/delete?id=${dnaGeneCassette.id}" onclick="return confirmx('确认要删除该基因盒吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>