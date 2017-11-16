<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实验室检查记录管理</title>
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
		<li class="active"><a href="${ctx}/synth/synthLabInspectRecord/">实验室检查记录列表</a></li>
		<shiro:hasPermission name="synth:synthLabInspectRecord:edit"><li><a href="${ctx}/synth/synthLabInspectRecord/form">实验室检查记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="synthLabInspectRecord" action="${ctx}/synth/synthLabInspectRecord/" method="post" class="breadcrumb form-search">
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
				<th>实验室</th>
				<th>温度</th>
				<th>湿度</th>
				<th>检查时间</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="synth:synthLabInspectRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="synthLabInspectRecord">
			<tr>
				<td><a href="${ctx}/synth/synthLabInspectRecord/form?id=${synthLabInspectRecord.id}">
					${synthLabInspectRecord.lab.name}		
					</a>
				</td>
				<td>
					${synthLabInspectRecord.temperature}
				</td>
				<td>
					${synthLabInspectRecord.wetness}
				</td>
				<td>
					<fmt:formatDate value="${synthLabInspectRecord.inspectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${synthLabInspectRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${synthLabInspectRecord.remarks}
				</td>
				<shiro:hasPermission name="synth:synthLabInspectRecord:edit"><td>
    				<a href="${ctx}/synth/synthLabInspectRecord/form?id=${synthLabInspectRecord.id}">修改</a>
					<a href="${ctx}/synth/synthLabInspectRecord/delete?id=${synthLabInspectRecord.id}" onclick="return confirmx('确认要删除该实验室检查记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>