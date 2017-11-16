<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>鉴定意见管理</title>
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
		<li class="active"><a href="${ctx}/entrust/entrustExpertOpinion/">鉴定意见列表</a></li>
		<shiro:hasPermission name="entrust:entrustExpertOpinion:edit"><li><a href="${ctx}/entrust/entrustExpertOpinion/form">鉴定意见添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="entrustExpertOpinion" action="${ctx}/entrust/entrustExpertOpinion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>角色名称：</label>
				<form:input path="roleName" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>版本：</label>
				<form:input path="version" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>鉴定人：</label>
				<form:input path="appraiser.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>角色名称</th>
				<th>意见</th>
				<th>版本</th>
				<th>鉴定人</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="entrust:entrustExpertOpinion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entrustExpertOpinion">
			<tr>
				<td><a href="${ctx}/entrust/entrustExpertOpinion/form?id=${entrustExpertOpinion.id}">
					${entrustExpertOpinion.roleName}
				</a></td>
				<td>
					${entrustExpertOpinion.opinion}
				</td>
				<td>
					${entrustExpertOpinion.version}
				</td>
				<td>
					${entrustExpertOpinion.appraiser.id}
				</td>
				<td>
					<fmt:formatDate value="${entrustExpertOpinion.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${entrustExpertOpinion.remarks}
				</td>
				<shiro:hasPermission name="entrust:entrustExpertOpinion:edit"><td>
    				<a href="${ctx}/entrust/entrustExpertOpinion/form?id=${entrustExpertOpinion.id}">修改</a>
					<a href="${ctx}/entrust/entrustExpertOpinion/delete?id=${entrustExpertOpinion.id}" onclick="return confirmx('确认要删除该鉴定意见吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>