<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>样品入库管理</title>
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
		<li class="active"><a href="${ctx}/material/specimenMaterialIn/">样品入库列表</a></li>
		<shiro:hasPermission name="material:specimenMaterialIn:edit"><li><a href="${ctx}/material/specimenMaterialIn/form">样品入库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="specimenMaterialIn" action="${ctx}/material/specimenMaterialIn/" method="post" class="breadcrumb form-search">
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
				<th>子项数量</th>
				<th>总量</th>
				<th>状态</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="material:specimenMaterialIn:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="specimenMaterialIn">
			<tr>
				<td><a href="${ctx}/material/specimenMaterialIn/form?id=${specimenMaterialIn.id}">
					${specimenMaterialIn.code}
				</a></td>
				<td>
					${specimenMaterialIn.itemCount}
				</td>
				<td>
					${specimenMaterialIn.totalQty}
				</td>
				<td>
					${specimenMaterialIn.status}
				</td>
				<td>
					<fmt:formatDate value="${specimenMaterialIn.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${specimenMaterialIn.remarks}
				</td>
				<shiro:hasPermission name="material:specimenMaterialIn:edit"><td>
    				<a href="${ctx}/material/specimenMaterialIn/form?id=${specimenMaterialIn.id}">修改</a>
					<a href="${ctx}/material/specimenMaterialIn/delete?id=${specimenMaterialIn.id}" onclick="return confirmx('确认要删除该样品入库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>