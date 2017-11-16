<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>DNA试验管理</title>
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
		<li class="active"><a href="${ctx}/dna/dnaExperiment/">DNA试验列表</a></li>
		<shiro:hasPermission name="dna:dnaExperiment:edit"><li><a href="${ctx}/dna/dnaExperiment/form">DNA试验添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaExperiment" action="${ctx}/dna/dnaExperiment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>基因盒</th>
				<th>实验室</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>状态</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="dna:dnaExperiment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaExperiment">
			<tr>
				<td><a href="${ctx}/dna/dnaExperiment/form?id=${dnaExperiment.id}">
					${dnaExperiment.code}
				</a></td>
				<td>
					${dnaExperiment.cassette.id}
				</td>
				<td>
					${dnaExperiment.lab.id}
				</td>
				<td>
					<fmt:formatDate value="${dnaExperiment.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${dnaExperiment.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaExperiment.status}
				</td>
				<td>
					<fmt:formatDate value="${dnaExperiment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaExperiment.remarks}
				</td>
				<shiro:hasPermission name="dna:dnaExperiment:edit"><td>
    				<a href="${ctx}/dna/dnaExperiment/form?id=${dnaExperiment.id}">修改</a>
					<a href="${ctx}/dna/dnaExperiment/delete?id=${dnaExperiment.id}" onclick="return confirmx('确认要删除该dna试验吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>