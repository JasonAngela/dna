<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>鉴定意见管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/entrust/entrustExpertOpinion/form?id=${entrustExpertOpinion.id}">鉴定意见 <shiro:lacksPermission name="entrust:entrustExpertOpinion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>样本编码</th>
				<th>样本编码</th>
				<th>CPI</th>
				<th>RCP</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="#{dnaResultList}" var="result" varStatus="status"> 
				<tr>
					<th>${status.index+1}</th>
					<th>${result.parentCode}</th>
					<th>${result.childCode}</th>
					<th>${result.cpi}</th>
					<th>${result.rcp}</th>
					<th><a href="javascript:" onclick='$("#contentTable_${result.id}").toggle()'>详情</a></th>
				</tr>
				<tr>
					<table id="contentTable_${result.id}" class="table table-striped table-bordered table-condensed" style="display: none;">
						<thead>
							<tr>
								<th>序号</th>
								<th>基因座</th>
								<th>pi值</th>
				<c:forEach items="${entrustAbstracts}" var="item">
					<th>
						${item.clientName}
						<br/>
						(${item.specimenCode})
					</th>
				</c:forEach>  
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${result.dnaPiResultItemList }" var="item" varStatus="itemStatus">
							<tr>
								<th>${itemStatus.index+1}</th>
								<th>${item.geneLoci}</th>
								<th>${item.pi}</th>
								<c:forEach items="${str}" var="strMap">
									<c:forEach items="${entrustAbstracts}" var="item">
										<td style="text-align: center;width: 215px;">${strMap.value[item.specimenCode]}</td>
									</c:forEach>
								</c:forEach>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form:form id="inputForm" modelAttribute="entrustExpertOpinion" action="${ctx}/entrust/entrustExpertOpinion/saveAnalyze" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden path="act.businessId"/>
		<form:hidden path="register.id" value="${registerId}"/>
		<form:hidden path="mapping.id"/>
		<sys:message content="${message}"/>		
		 <div class="control-group">
			<label class="control-label">pic：</label>
			<div class="controls">
				<form:hidden id="pic" path="mapping.pic" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="pic" type="files" uploadPath="/entrust/mapping" selectMultiple="true" maxWidth="1001" maxHeight="1011"/>
			</div>
		</div>
	  
		<div class="form-actions">
			<shiro:hasPermission name="entrust:entrustExpertOpinion:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>