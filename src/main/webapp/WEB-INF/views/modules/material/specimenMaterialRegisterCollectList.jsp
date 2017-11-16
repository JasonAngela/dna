<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>样品领取</title>
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
		<li><a href="${ctx}/material/specimenMaterialOut/">物料领取列表</a></li>
		<li class="active"><a href="${ctx}/material/specimenMaterialOut/form?id=${specimenMaterialOut.id}">物料领取<shiro:hasPermission name="material:specimenMaterialOut:edit">${not empty specimenMaterialOut.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="material:specimenMaterialOut:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="collectForm"  action="${ctx}/material/specimenMaterialOut/save" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确认选择" onsubmit="onFormSubmit"/></li>
			<li class="clearfix"></li>
		</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择<th>
				<th>编码</th>
				<th>样品类型</th>
				<th>数量</th>
				<th>度量</th>
				<th>图片</th>
				<th>数量</th>
				<th>入库数量</th>
				<th>出库数量</th>
				<th>剩余数量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="specimenMaterialRegisterItem">
			<tr>
				<td>
					<input type="checkbox" name="ids" value="${specimenMaterialRegisterItem.id}"/>
				</td>
				<td><a href="${ctx}/material/specimenMaterialRegisterItem/form?id=${specimenMaterialRegisterItem.id}">
					${specimenMaterialRegisterItem.code}
				</a>
				</td>
				<td>
					${specimenMaterialRegisterItem.materialType}
				</td>
				<td>
					${specimenMaterialRegisterItem.qty}
				</td>
				<td>
					${specimenMaterialRegisterItem.measure}
				</td>
				<td>
					${specimenMaterialRegisterItem.pic}
				</td>
				<td>
					${specimenMaterialRegisterItem.qty}
				</td>
				<td>
					${specimenMaterialRegisterItem.inQty}
				</td>
				<td>
					${specimenMaterialRegisterItem.outQty}
				</td>
				<td>
					${specimenMaterialRegisterItem.leftQty}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form:form>
	<div class="pagination">${page}</div>
</body>
</html>