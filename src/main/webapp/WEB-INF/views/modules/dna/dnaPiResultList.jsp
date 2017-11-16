<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>二联体管理</title>
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
		<li class="active"><a href="${ctx}/dna/dnaPiResult/">二联体列表</a></li>
		<shiro:hasPermission name="dna:dnaPiResult:edit"><li><a href="${ctx}/dna/dnaPiResult/form">二联体添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaPiResult" action="${ctx}/dna/dnaPiResult/" method="post" class="breadcrumb form-search">
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
				<th>父编码</th>
				<th>子编码</th>
				<th>委托id</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="dna:dnaPiResult:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaPiResult">
			<tr>
				<td><a href="${ctx}/dna/dnaPiResult/form?id=${dnaPiResult.id}">
					${dnaPiResult.parentCode}
				</a></td>
				<td>
					${dnaPiResult.childCode}
				</td>
				<td>
					${dnaPiResult.register.id}
				</td>
				<td>
					<fmt:formatDate value="${dnaPiResult.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaPiResult.remarks}
				</td>
				<shiro:hasPermission name="dna:dnaPiResult:edit"><td>
    				<a href="${ctx}/dna/dnaPiResult/form?id=${dnaPiResult.id}">修改</a>
					<a href="${ctx}/dna/dnaPiResult/delete?id=${dnaPiResult.id}" onclick="return confirmx('确认要删除该二联体吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>