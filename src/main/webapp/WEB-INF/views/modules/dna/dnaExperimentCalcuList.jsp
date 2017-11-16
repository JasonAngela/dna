<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>二联体管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("a.jquery-word-export").click(function(event) {
		        $("#pagecontent").wordExport();
		    });
		});
		function CheckedNo(){ 
			$(':checkbox').attr('checked',''); 
			}  
		
		</script>
	
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
	
	<div id="pagecontent">
	
	<table class="inputTable">
	
	
	<c:forEach items="${str}" var="strMap" >
				<tr>
						<td style="text-align: center;width: 166px;">${strMap.key}</td>
					 <c:forEach items="${strMap.value}" var="item">
						<td style="text-align: center;width: 215px;">${item}</td>
					</c:forEach>
						<c:forEach items="${dnaPiResultItem}" var="dnaPiResultItem">
						   <c:if test="${ dnaPiResultItem.geneLoci eq strMap.key }">
								 	<td>${ dnaPiResultItem.pi}</td>
								 	<td>${ dnaPiResultItem.formula}</td>
								 	<td>${ dnaPiResultItem.pProb}</td>
								 	<td>${ dnaPiResultItem.qProb}</td>
								 	<td>${ dnaPiResultItem.min}</td>
								 </c:if>
						</c:forEach>
					
				</tr>
			</c:forEach>
	</table>
	
	<table class="inputTable">
	<c:forEach items="${dnaPiResult}" var="dnaPiResult">
	  <tr>
	   <th style="text-align: center;width: 166px;">cpi</th>
	   <th style="text-align: center;width: 166px;">rcp</th>
	  </tr>
	  <tr>
	  	<td>${dnaPiResult.cpi}</td>
	  	<td>${dnaPiResult.rcp}</td>
	  </tr>
	  </c:forEach>
	  
	  <tr>
	  	<th>pi</th>
	  	<td>${pi}</td>
	  		<th>pi计算值</th>
	  	<td>${jj }</td>
	  </tr>
	</table>
</div>
		<div id="lover" class="form-actions" align="center">
			<a class="jquery-word-export"><input class="btn" type="button" value="导出word"></a>
		</div>
		
</body>
</html>