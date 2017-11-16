<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>dna日常记录管理</title>
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
		<li class="active"><a href="${ctx}/dna/dnaDaily/">dna日常记录列表</a></li>
		<shiro:hasPermission name="dna:dnaDaily:edit"><li><a href="${ctx}/dna/dnaDaily/form">dna日常记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaDaily" action="${ctx}/dna/dnaDaily/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建者：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dnaDaily.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>创建者</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="dna:dnaDaily:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaDaily">
			<tr>
				<td><a href="${ctx}/dna/dnaDaily/form?id=${dnaDaily.id}">
					${dnaDaily.operator.name}
				</a></td>
				<td>
					<fmt:formatDate value="${dnaDaily.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaDaily.remarks}
				</td>
				<shiro:hasPermission name="dna:dnaDaily:edit"><td>
    				<a href="${ctx}/dna/dnaDaily/form?id=${dnaDaily.id}">修改</a>
					<a href="${ctx}/dna/dnaDaily/delete?id=${dnaDaily.id}" onclick="return confirmx('确认要删除该dna日常记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>