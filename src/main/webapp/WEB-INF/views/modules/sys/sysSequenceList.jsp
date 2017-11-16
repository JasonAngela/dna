<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>序列管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysSequence/">序列列表</a></li>
		<shiro:hasPermission name="sys:sysSequence:edit"><li><a href="${ctx}/sys/sysSequence/form">序列添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysSequence" action="${ctx}/sys/sysSequence/" method="post" class="breadcrumb form-search">
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
				<th>开始</th>
				<th>当前值</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="sys:sysSequence:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysSequence">
			<tr>
				<td><a href="${ctx}/sys/sysSequence/form?id=${sysSequence.id}">
					${sysSequence.name}
				</a></td>
				<td>
					${sysSequence.start}
				</td>
				<td>
					${sysSequence.current}
				</td>
				<td>
					<fmt:formatDate value="${sysSequence.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysSequence.remarks}
				</td>
				<shiro:hasPermission name="sys:sysSequence:edit"><td>
    				<a href="${ctx}/sys/sysSequence/form?id=${sysSequence.id}">修改</a>
					<a href="${ctx}/sys/sysSequence/delete?id=${sysSequence.id}" onclick="return confirmx('确认要删除该序列吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>