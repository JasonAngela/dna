<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>等位基因频率管理</title>
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
		<li class="active"><a href="${ctx}/dna/dnaGeneFrequency/">等位基因频率列表</a></li>
		<shiro:hasPermission name="dna:dnaGeneFrequency:edit"><li><a href="${ctx}/dna/dnaGeneFrequency/form">等位基因频率添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaGeneFrequency" action="${ctx}/dna/dnaGeneFrequency/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		     <li><label>名称：</label>
				<form:input path="loci.name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>基因座</th>
				<th>值</th>
				<th>概率</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="dna:dnaGeneFrequency:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaGeneFrequency">
			<tr>
				<td>
					${dnaGeneFrequency.loci.name}
				</td>
				<td>
					${dnaGeneFrequency.value} 
				</td>
				<td>
					${dnaGeneFrequency.probability}
				</td>
				<td>
					<fmt:formatDate value="${dnaGeneFrequency.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaGeneFrequency.remarks}
				</td>
				<shiro:hasPermission name="dna:dnaGeneFrequency:edit"><td>
    				<a href="${ctx}/dna/dnaGeneFrequency/form?id=${dnaGeneFrequency.id}">修改</a>
					<a href="${ctx}/dna/dnaGeneFrequency/delete?id=${dnaGeneFrequency.id}" onclick="return confirmx('确认要删除该等位基因频率吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>