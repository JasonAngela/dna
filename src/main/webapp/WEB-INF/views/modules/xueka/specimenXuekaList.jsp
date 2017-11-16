<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>血卡管理</title>
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
		<li class="active"><a href="${ctx}/xueka/specimenXueka/">血卡列表</a></li>
		<shiro:hasPermission name="xueka:specimenXueka:edit"><li><a href="${ctx}/xueka/specimenXueka/form">血卡添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="specimenXueka" action="${ctx}/xueka/specimenXueka/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>xueka_id：</label>
				<form:input path="xuekaId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>material_register_item_id：</label>
				<form:input path="materialRegisterItemId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>xueka_id</th>
				<th>备注</th>
				<shiro:hasPermission name="xueka:specimenXueka:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="specimenXueka">
			<tr>
				<td><a href="${ctx}/xueka/specimenXueka/form?id=${specimenXueka.id}">
					${specimenXueka.xuekaId}
				</a></td>
				<td>
					${specimenXueka.remarks}
				</td>
				<shiro:hasPermission name="xueka:specimenXueka:edit"><td>
    				<a href="${ctx}/xueka/specimenXueka/form?id=${specimenXueka.id}">修改</a>
					<a href="${ctx}/xueka/specimenXueka/delete?id=${specimenXueka.id}" onclick="return confirmx('确认要删除该血卡吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>