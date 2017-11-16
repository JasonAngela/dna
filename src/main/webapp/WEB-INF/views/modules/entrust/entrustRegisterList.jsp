<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存成功管理</title>
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
		<li class="active"><a href="${ctx}/entrust/entrustRegister/">保存成功列表</a></li>
		<shiro:hasPermission name="entrust:entrustRegister:edit"><li><a href="${ctx}/entrust/entrustRegister/form">保存成功添加</a></li></shiro:hasPermission>
	</ul>
<form:form id="searchForm" modelAttribute="entrustRegister" action="${ctx}/entrust/entrustRegister/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>委托人：</label>
				<form:input path="clientName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>受理人：</label>
				<form:input path="serverName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('entrust_statusCode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${entrustRegister.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${entrustRegister.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<a href="${ctx}/entrust/entrustRegister/readcard"><input id="btnSubmit1" class="btn btn-primary" type="button" value="读卡"/>
				</a>
				<a href="${ctx}/entrust/entrustRegister/export"><input id="btnSubmit2" class="btn btn-primary" type="button" value="导出表格"/>
				</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编码</th>
				<th>案件编码</th>
				<th>委托人</th>
				<th>送检人(机构)</th>
				<th>受理人</th>
				<th>专业</th>
				<th>类型</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="entrust:entrustRegister:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entrustRegister">
			<tr>
				<td><a href="${ctx}/entrust/entrustRegister/form?id=${entrustRegister.id}">
					${entrustRegister.code}
				</a>
				</td>
				<td>
					${entrustRegister.caseCode}
				</td>
				<td>
					${entrustRegister.clientName}
				</td>
				<td>
					${entrustRegister.agentName}
				</td>
				<td>
					${entrustRegister.serverName}
				</td>
				<td>
					${fns:getDictLabel(entrustRegister.specialty, 'specialtyCode', '')}
				</td>
				<td>
					${fns:getDictLabel(entrustRegister.type, 'typeCode', '')}
				</td>
				<td>
					${fns:getDictLabel(entrustRegister.status, 'entrust_statusCode', '')}
				</td>
				<td>
					<fmt:formatDate value="${entrustRegister.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${entrustRegister.remarks}
				</td>
				<shiro:hasPermission name="entrust:entrustRegister:edit">
				<td>
					<%-- <a  target="_blank" href="${ctx}/entrust/entrustRegister/report?id=${entrustRegister.id}">查看报告</a> --%>
					<a href="${ctx}/entrust/entrustRegister/archive?id=${entrustRegister.id}">归档</a>
				</td>
				</shiro:hasPermission>
				<td>
					<a  target="_blank" href="${ctx}/entrust/entrustRegister/details?id=${entrustRegister.id}">详情</a>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>