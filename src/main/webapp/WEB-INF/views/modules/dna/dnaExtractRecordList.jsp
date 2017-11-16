<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存成功管理</title>
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
		<li class="active"><a href="${ctx}/dna/dnaExtractRecord/">保存成功列表</a></li>
		<shiro:hasPermission name="dna:dnaExtractRecord:edit"><li><a href="${ctx}/dna/dnaExtractRecord/form">保存成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaExtractRecord" action="${ctx}/dna/dnaExtractRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>提取室：</label>
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
				<th>提取室</th>
				<th>修改时间</th>
				<th>备注</th>
				<th>使用人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaExtractRecord">
			<tr>
				<td><a href="${ctx}/dna/dnaExtractRecord/form?id=${dnaExtractRecord.id}">
					${dnaExtractRecord.lab.name}
				</a></td>
				<td>
					<fmt:formatDate value="${dnaExtractRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaExtractRecord.remarks}
				</td>
				<td>
					${dnaExtractRecord.operator.name}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>