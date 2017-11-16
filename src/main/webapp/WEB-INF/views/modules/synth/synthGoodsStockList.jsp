<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品库存管理</title>
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
		<li class="active"><a href="${ctx}/synth/synthGoodsStock/">药品库存列表</a></li>
		<shiro:hasPermission name="synth:synthGoodsStock:edit"><li><a href="${ctx}/synth/synthGoodsStock/form">药品库存添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="synthGoodsStock" action="${ctx}/synth/synthGoodsStock/" method="post" class="breadcrumb form-search">
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
				<th>耗材名称</th>
				<th>批号（序列号）</th>
				<th>生产厂家</th>
				<th>数量</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="synth:synthGoodsStock:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="synthGoodsStock">
			<tr>
				<td>
					${synthGoodsStock.goods.name}
				</td>
				<td><a href="${ctx}/synth/synthGoodsStock/form?id=${synthGoodsStock.id}">
					${synthGoodsStock.batchNumber}
				</a></td>
				<td>
					${synthGoodsStock.manufacturer}
				</td>
				<td>
					${synthGoodsStock.qty}
				</td>
				<td>
					<fmt:formatDate value="${synthGoodsStock.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${synthGoodsStock.remarks}
				</td>
				<shiro:hasPermission name="synth:synthGoodsStock:edit"><td>
    				<a href="${ctx}/synth/synthGoodsStock/form?id=${synthGoodsStock.id}">修改</a>
					<a href="${ctx}/synth/synthGoodsStock/delete?id=${synthGoodsStock.id}" onclick="return confirmx('确认要删除该药品库存吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>