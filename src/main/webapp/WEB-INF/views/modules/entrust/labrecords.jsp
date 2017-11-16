<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<title>实验室记录表</title>
	<meta name="decorator" content="default"/>
<head>
<style type="text/css">
		.table {
	font-size:12px;
	border-collapse:collapse;
	border-top: 1px solid #7F9DB9;
	border-left: 1px solid #7F9DB9;
}

.table td, .table th {
	vertical-align: middle;
	border-bottom: 1px solid #7F9DB9;
	border-right: 1px solid #7F9DB9;
	padding:2px;
	text-align: center;
	line-height:18px;
}

.table th {
	font-weight:normal;
	background-color:#C3DAF9;
}

.inputTable {
	font-size:12px;
	border-collapse:collapse; 
	border-top: 1px solid #7F9DB9;
	border-left: 1px solid #7F9DB9;
}

.inputTable td, .inputTable th {
	vertical-align: middle;
	text-align:left;
	border-bottom: 1px solid #7F9DB9;
	border-right: 1px solid #7F9DB9;
	padding:2px 0px 0px 18px;
	line-height:20px;
}

.inputTable th {
	font-weight:normal;
	background-color:#C3DAF9;
}
	</style>
</head>
<body>
<c:if test="${not empty  dnaExtractRecord}">
	<h3>一:提取室</h3>
			<table width="800" align="center" style="margin-top:30px"
		class="inputTable">
		<tr>
		<th>操作人</th>
			<td>${dnaExtractRecord.operator.name}</td>
		<th>操作时间</th>
			<td><fmt:formatDate value="${dnaExtractRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<th>实验前</th>
				<td colspan="3">
					
				<input type="checkbox" <c:if test="${fn:trim(dnaExtractRecord.beforeTrial) eq '1'}">checked="checked"</c:if> /> 打开通风设备; &nbsp;&nbsp;
								<input type="checkbox" <c:if test="${fn:trim(dnaExtractRecord.beforeTrial) eq '2'}">checked="checked"</c:if> /> 实验台面清洁（75%酒精擦拭）; &nbsp;&nbsp;
				</td>
		</tr>
		<tr>
			<th>实验室温度</th>
			<td>
				<input type="text" value="${dnaExtractRecord. temperature}" readonly="readonly"> 
			</td>
			<th>冰箱温度</th>
			<td>
			<input type="text" value="${dnaExtractRecord. refrigeratorTemperature}" readonly="readonly"> 
			</td>	
		</tr>
		<tr>
			<th>相对湿度</th>
			<td>
			<input type="text" value="${dnaExtractRecord. humidity}" readonly="readonly"> 
			</td>
			<th>提取室</th>
			<td>
				${dnaExtractRecord.lab.name}
			
			</td>
		
		</tr>			
		<tr>
			<th>最后模版溶液体积</th>
			<td>
			<input type="text" value="${dnaExtractRecord. finalTemplate}" readonly="readonly"> 
			</td>
			<th>温育温度和时间</th>
			
			<td><input type="text" value="${dnaExtractRecord. other}" readonly="readonly"> </td>
		</tr>
		<tr>
			<th>加热过程</th>
			<td>
			<input type="text" value="${dnaExtractRecord. heatingProcess}" readonly="readonly"> 
			</td>
			<th>存放温度</th>
			<td>
			<input type="text" value="${dnaExtractRecord. storageTemperature}" readonly="readonly"> 
			</td>
		</tr>
		<tr>
			<th>提取的模板存放处</th>
			
			<td>
			<input type="text" value="${dnaExtractRecord. extractedTemplate}" readonly="readonly"> </td>
			<th>模板交接日期</th>
			<td>
			<input type="text" value="${dnaExtractRecord. templateHandoverDate}" readonly="readonly">
			</td>
		</tr>	
			<tr>
			<th>提取者</th>
			<td>
			<input type="text" value="${dnaExtractRecord. extractors}" readonly="readonly">
			</td>
			<th>复核人</th>
			
			<td>
				<input type="text" value="${dnaExtractRecord. reviewer}" readonly="readonly">
		</td>
			
		</tr>
		<tr>
			<th>接收人</th>
			<td>
				<input type="text" value="${dnaExtractRecord. recipient}" readonly="readonly">
			</td>
			<th>提取日期</th>
			<td>
			<input type="text" value="${dnaExtractRecord. otherone}" readonly="readonly">
			
		</td>
		</tr>
<%-- 		<c:forEach items="${ dnaExtractRecord.dnaExtractRecordItemList}" var="dnaExtractRecordItemLists">
		
			${dnaExtractRecordItemLists.sampleNumber}
		</c:forEach> --%>
	</table>
		
</c:if>
	
		<c:if test="${not empty  dnaPreparationReagents}">
			<h3> 二:扩增</h3>
				<table width="800" align="center" style="margin-top:30px"
		class="inputTable">
		<tr>
			<th>操作人</th>
			<td>${dnaPreparationReagents.operator.name}</td>
			<th>操作时间</th>
			<td><fmt:formatDate value="${dnaPreparationReagents.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
		</tr>	
		
		<tr>
			<th colspan="8">Aamplesheet:</th>
		</tr>
		
		
		<tr>
			<th>PCR反应液组成</th>
			<th>12ul体系 ul体系</th>
			<th>n+1</th>
			<th>总数</th>	
		</tr>	
		<tr>
			<td>纯水</td>
			<td>
			<input type="text" value="${dnaPreparationReagents.otherone}" readonly="readonly">
			<input type="text" value="${dnaPreparationReagents.first}" readonly="readonly">
			ul
			</td>
			<td rowspan="4">
			nAddOne
			<input type="text" value="${dnaPreparationReagents.totalOne}" readonly="readonly">
			</td>
			<td>XQW
			<input type="text" value="${dnaPreparationReagents.totalOne}" readonly="readonly">
			ul</td>		
		</tr>	
		<tr>
			<td>PCRReaction Mix</td>
			<td> 
				<input type="text" value="${dnaPreparationReagents.othertow}" readonly="readonly">
			<br/>
				<input type="text" value="${dnaPreparationReagents.second}" readonly="readonly">
		ul
			</td>			
			<td>
			<input type="text" value="${dnaPreparationReagents.totalTwo}" readonly="readonly">
		ul</td>		
		</tr>	
		<tr>
			<td>primer Set</td>
			<td> 
			<input type="text" value="${dnaPreparationReagents.othertr}" readonly="readonly">
			<br/>
			<input type="text" value="${dnaPreparationReagents.third}" readonly="readonly">
			ul</td>		
			<td>
			<input type="text" value="${dnaPreparationReagents.totalThree}" readonly="readonly">
			ul</td>		
		</tr>
		<tr>
			<td>Gold、DNA Polymerase (5U/ul)</td>
			<td> 
			<input type="text" value="${dnaPreparationReagents.otherfour}" readonly="readonly">
			<br/>
			<input type="text" value="${dnaPreparationReagents.fourth}" readonly="readonly">
			ul</td>			
			<td>
			<input type="text" value="${dnaPreparationReagents.totalFour}" readonly="readonly">ul</td>		
		</tr>
		<tr>
			<td>DNA(含检测样本和阴阳性对照）</td>
			<td> 
			<input type="text" value="${dnaPreparationReagents.otherfive}" readonly="readonly">
		
			<br/>
			<input type="text" value="${dnaPreparationReagents.fifth}" readonly="readonly">
			ul
			</td>			
			<td >n=
			<input type="text" value="${dnaPreparationReagents.other}" readonly="readonly">
		</td>
			<td>
			<input type="text" value="${dnaPreparationReagents.totalFive}" readonly="readonly">
			ul</td>		
		</tr>		
	</table>
			</c:if>
			
		<c:if test="${not empty  dnaElectrophoresisParting}">
			<h3>三 :电泳室</h3>
				<table width="800" align="center" style="margin-top:30px"
		class="inputTable">
		<tr>
		<th>操作人</th>
		<td> ${dnaElectrophoresisParting.operator.name}</td>
		<th>操作时间</th>
		<td><fmt:formatDate value="${dnaElectrophoresisParting.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		
		
		<tr>
			<th>甲酰胺批号</th>
			<td>
				<input type="text" value="${dnaElectrophoresisParting.formamideBatchNumber}" readonly="readonly">
			</td>
			<th>是否已验证有效</th>
			<td>
				
				<input type="checkbox" <c:if test="${fn:trim(dnaElectrophoresisParting.validated) eq '0'}">checked="checked"</c:if> /> 否&nbsp;&nbsp;
								<input type="checkbox" <c:if test="${fn:trim(dnaElectrophoresisParting.validated) eq '1'}">checked="checked"</c:if> /> 是 &nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<th>内标</th>
			<td>
			<input type="text" value="${dnaElectrophoresisParting.internalStandard}" readonly="readonly">
		</td>
			<th>是否已验证有效</th>
			<td>
			<input type="checkbox" <c:if test="${fn:trim(dnaElectrophoresisParting.validated1) eq '0'}">checked="checked"</c:if> /> 否&nbsp;&nbsp;
								<input type="checkbox" <c:if test="${fn:trim(dnaElectrophoresisParting.validated1) eq '1'}">checked="checked"</c:if> /> 是 &nbsp;&nbsp;</td>
		</tr>
		<tr>
			<th>实验室温度</th>
			<td>
			<input type="text" value="${dnaElectrophoresisParting.temperature}" readonly="readonly">
			</td>
			<th>冰箱温度</th>
			<td>
			<input type="text" value="${dnaElectrophoresisParting.refrigeratorTemperature}" readonly="readonly">
			</td>			
		</tr>
		<tr>
			<th>产物取量和处理</th>
			<td>
			<input type="text" value="${dnaElectrophoresisParting.product}" readonly="readonly">
			</td>	
			<th>相对湿度</th>
			<td colspan="3">
			<input type="text" value="${dnaElectrophoresisParting.humidity}" readonly="readonly">
			</td>		
		</tr>
	
		<tr>
			<th>阳性对照</th>
			<td >
			<input type="text" value="${dnaElectrophoresisParting.positiveControl}" readonly="readonly">
			</td>
			<th>阴性对照</th>
			<td >
			<input type="text" value="${dnaElectrophoresisParting.negativeControl}" readonly="readonly">
			</td>
		</tr>
		
		<tr>
			<th>实验室</th>
			<td>
			
				<input type="text" value="${dnaElectrophoresisParting.lab.name}" readonly="readonly">
				
			</td>
			
		</tr>
	</table>
			
			
			
			</c:if>
			
		
	<c:if test="${not empty  board}">
			<h3> 四:电泳板</h3>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
			<td>&nbsp;</td>
			<c:forEach var="item" begin="1" end="12">
				<td align="center" style="text-align: center;">${item}</td>
			</c:forEach>
			</tr>
			</thead>
			<c:forEach items="#{board.dnaBoardJggList}" var="row" varStatus="kl">	
					<c:if test="${(row.lie-1)%12 eq 0}">
						<tr>
							<td>${row.hangLabel}</td>
					</c:if>
					<td>
						<input type="hidden" name="dnaBoardJggList[${kl.index}].id" value="${row.id}"/>
				 		<input id="dnaBoardJggList{{idx}}_specimenCode" name="dnaBoardJggList[${kl.index}].specimenCode" type="text" style="width: 80px;" value="${row.specimenCode}"/>
					</td>
					<c:if test="${(row.lie)%12 eq 0}">
						</tr>
					</c:if>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>