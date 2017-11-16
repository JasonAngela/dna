<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>dna试验</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function allowDrop(ev)
		{
		ev.preventDefault();
		}

		function drag(ev)
		{
			var gray = $("#"+ev.target.id).css("background-color");
			ev.dataTransfer.setData("sourceId",ev.target.id);
		}

		function drop(ev)
		{
			ev.preventDefault();
			var sourceId=ev.dataTransfer.getData("sourceId");
			ev.target.value=$("#"+sourceId).html();
			$("#"+sourceId).css("display","none")
			$("#"+sourceId).attr("selected","true");
		}
		function clearData(inputVar){
			var sourceId = inputVar.value;
			$("#"+sourceId).css("display","inline");
			$("#"+sourceId).attr("selected","false");
			inputVar.value = "";
			
		 	
		}
		
		function selectedPosition(hang,lie){
			$("#selected_hang").val(hang);
			$("#selected_lie").val(lie);
		}
		
		function selectAll(){
			var selected_hang =$("#selected_hang").val();
			var selected_lie =$("#selected_lie").val();
			if(selected_hang==0||selected_lie==0){
				alert("请选择放入的起始！");
				return;
			}
			
			$(".specimenCode_value[selected='false']").each(function(i){
				if(selected_lie>12){
					alert("改板子不能容下如此多数据！");
					return;
				}
				var targetInput = $("#dnaBoardJggList_"+selected_lie+"_"+selected_hang);
				if(targetInput.val()!=""){
					alert("位置：！"+selected_hang+":"+selected_lie+" 不为空！");
					return;
				}
				targetInput.val($(this).html().trim());
				$(this).css("display","none")
				$(this).attr("selected","true");
				selected_hang++;
				if(selected_hang>8){
					selected_hang=1;
					selected_lie++;
				}
				
			});
			
			
		}
	</script>
 
</head>
<body>
	<ul class="nav nav-tabs">
	</ul>
	<c:if test="${not empty stockList }">
	<form:form id="searchForm" modelAttribute="dnaBoard" action="${ctx}/dna/dnaBoard/" method="post" class="breadcrumb form-search">
		 
		 <ul class="ul-form">
		 	<li><label>请先选择：</label>
		 		<form:select path="code">
		 			<form:options items="${stockList}" itemLabel="batchNumber" itemValue="batchNumber"/>
				</form:select>
		 	</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确定"/></li>
			<li class="clearfix"></li>
			</ul>
	</form:form>
	</c:if>
	<sys:message content="${message}"/>
	<c:if test="${not empty  dnaExperiment.board}">
	<form:form id="inputForm" modelAttribute="dnaExperiment" action="${ctx}/dna/dnaExperiment/board/save" method="post" class="breadcrumb form-search">
		<form:hidden path="id"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden path="act.businessId"/>
		<div>
			<div id="specimenList" style="float: left;">
				<c:forEach items="${dnaExperiment.dnaExperimentSpecimenList}" var="speciment">
					<span draggable="true" ondragstart="drag(event)" selected="false" id="${speciment.specimenCode}" class="specimenCode_value">${speciment.specimenCode}</span>
				</c:forEach>
			</div>
			
			<div id="specimenList" style="float: right;">
				<select id="selected_hang">
					<option value="0">行</option>
					<option value="1">A</option>
					<option value="2">B</option>
					<option value="3">C</option>
					<option value="4">D</option>
					<option value="5">E</option>
					<option value="6">F</option>
					<option value="7">G</option>
					<option value="8">H</option>
				</select>
				<select id="selected_lie">
					<option value="0">列</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
				<input  class="btn btn-primary" type="button" value="全部添加" onclick="selectAll()"/>
			</div>
		</div>
		 
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
			<td>&nbsp;</td>
			<c:forEach var="item" begin="1" end="12">
				<td align="center" style="text-align: center;">${item}</td>
			</c:forEach>
			</tr>
			</thead>
			<c:forEach items="#{dnaExperiment.board.dnaBoardJggList}" var="row" varStatus="kl">	
					<c:if test="${(row.lie-1)%12 eq 0}">
						<tr>
							<td>${row.hangLabel}</td>
					</c:if>
					<c:if test="${empty row.specimenCode}">
					<td>
						<input type="hidden" name="board.dnaBoardJggList[${kl.index}].id" value="${row.id}"/>
						<input type="hidden" name="board.dnaBoardJggList[${kl.index}].hang" value="${row.hang}"/>
						<input type="hidden" name="board.dnaBoardJggList[${kl.index}].lie" value="${row.lie}"/>
						<input id="dnaBoardJggList_${row.lie}_${row.hang}" ondrop="drop(event)" 
						ondragover="allowDrop(event)"  name="board.dnaBoardJggList[${kl.index}].specimenCode" 
						type="text" style="width: 130px;" value="${row.specimenCode}"
						readonly="readonly"
						onclick="selectedPosition(${row.hang},${row.lie })"
						ondblclick="clearData(this)"/>
					</td>
					</c:if>
					<c:if test="${not empty row.specimenCode}">
					<td style="background-color: gray;  width: 130px; " >
							${row.specimenCode}
				 	</td>
				 	</c:if>
					<c:if test="${(row.lie)%12 eq 0}">
						</tr>
					</c:if>
			</c:forEach>
		</table>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	</c:if>
	
</body>

</html>