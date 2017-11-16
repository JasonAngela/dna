<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电泳分型板管理</title>
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
		<li class="active"><a href="${ctx}/dna/dnaBoard/">电泳分型板列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaBoard" action="${ctx}/dna/dnaBoard/" method="post" class="breadcrumb form-search">
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
	 			<th>编号</th>		
				<th>修改时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaBoard">
			<tr>
				<td><a href="${ctx}/dna/dnaBoard/form?id=${dnaBoard.id}">
				  ${dnaBoard.code}
				</a>
				</td>		
			
				<td>
					<fmt:formatDate value="${dnaBoard.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${dnaBoard.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>