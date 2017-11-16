<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电泳室管理</title>
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
		<li class="active"><a href="${ctx}/dna/dnaElectrophoresisParting/">电泳室列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="dnaElectrophoresisParting" action="${ctx}/dna/dnaElectrophoresisParting/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>实验室：</label>
				<form:input path="lab.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>阴性对照：</label>
				<form:input path="negativeControl" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dnaElectrophoresisParting.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>实验室</th>
				<th>阴性对照</th>
				<th>创建时间</th>
				<td>使用人</td>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dnaElectrophoresisParting">
			<tr>
				<td><a href="${ctx}/dna/dnaElectrophoresisParting/form?id=${dnaElectrophoresisParting.id}">
					${dnaElectrophoresisParting.lab.name}
				</a></td>
				<td>
					${dnaElectrophoresisParting.negativeControl}
				</td>
				<td>
					<fmt:formatDate value="${dnaElectrophoresisParting.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dnaElectrophoresisParting.operator.name}
				</td>
				<td>
					${dnaElectrophoresisParting.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>