<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理管理</title>
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
		<li class="active"><a href="${ctx}/synth/synthEquipment/">设备管理列表</a></li>
		<shiro:hasPermission name="synth:synthEquipment:edit"><li><a href="${ctx}/synth/synthEquipment/form">设备管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="synthEquipment" action="${ctx}/synth/synthEquipment/" method="post" class="breadcrumb form-search">
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
				<th>所属实验室</th>
				<th>编码</th>
				<th>名称</th>
				<th>采购时间</th>
				<th>生产厂家</th>
				<th>经销商</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="synth:synthEquipment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="synthEquipment">
			<tr>
				<td><a href="${ctx}/synth/synthEquipment/form?id=${synthEquipment.id}">
					${synthEquipment.lab.name}
				</a></td>
				<td>
					${synthEquipment.code}
				</td>
				<td>
					${synthEquipment.name}
				</td>
				<td>
					<fmt:formatDate value="${synthEquipment.purchaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${synthEquipment.manufacturer}
				</td>
				<td>
					${synthEquipment.dealer}
				</td>
				<td>
					<fmt:formatDate value="${synthEquipment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${synthEquipment.remarks}
				</td>
				<shiro:hasPermission name="synth:synthEquipment:edit"><td>
    				<a href="${ctx}/synth/synthEquipment/form?id=${synthEquipment.id}">修改</a>
					<a href="${ctx}/synth/synthEquipment/delete?id=${synthEquipment.id}" onclick="return confirmx('确认要删除该设备管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>