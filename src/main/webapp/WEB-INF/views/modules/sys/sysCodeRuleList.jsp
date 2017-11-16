<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>编码规则管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysCodeRule/">编码规则列表</a></li>
		<shiro:hasPermission name="sys:sysCodeRule:edit"><li><a href="${ctx}/sys/sysCodeRule/form">编码规则添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysCodeRule" action="${ctx}/sys/sysCodeRule/" method="post" class="breadcrumb form-search">
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
				<th>编码</th>
				<th>名称</th>
				<th>创建者</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="sys:sysCodeRule:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysCodeRule">
			<tr>
				<td><a href="${ctx}/sys/sysCodeRule/form?id=${sysCodeRule.id}">
					${sysCodeRule.code}
				</a></td>
				<td>
					${sysCodeRule.name}
				</td>
				<td>
					${sysCodeRule.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${sysCodeRule.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysCodeRule.remarks}
				</td>
				<shiro:hasPermission name="sys:sysCodeRule:edit"><td>
    				<a href="${ctx}/sys/sysCodeRule/form?id=${sysCodeRule.id}">修改</a>
					<a href="${ctx}/sys/sysCodeRule/delete?id=${sysCodeRule.id}" onclick="return confirmx('确认要删除该编码规则吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>