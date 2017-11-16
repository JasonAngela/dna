<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>DNA归档</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			
		});
		
	</script>
	
	<style type="text/css">
	a:HOVER {
	color: red;	
}
	a{
	color: blue;
	}
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
      <div align="center">
		      <table style="margin-top:30px;width: 500px;height: 700px;"  class="inputTable">
				<tr>
					<th>序号${jdname}</th>
					<th>内容</th>
				</tr>
				<tr>
					<th>1</th>
					<td>卷宗封面</td>
				</tr>
				<tr>
					<th>2</th>
					<td>卷内目录</td>
				</tr>
				<tr>
					<th>3</th>
					<td><a  href="${ctx}/entrust/entrustRegister/details?id=${entrustRegister.id}">司法鉴定委托书</a></td>
				</tr>
		
				<tr>
					<th>4</th>
					<td><a  href="${ctx}/entrust/entrustRegister/entrustRegisterReceipt?id=${entrustRegister.id}">鉴定材料接收和返还清单</a></td>
				</tr>
				<tr>
					<th>5</th>
						<td><a href="${ctx}/entrust/entrustRegister/sheeet?id=${entrustRegister.id}" >鉴定材料流转单</a></td>
				</tr>
				<tr>
				
					<th>6</th>
					<td><a href="${ctx}/entrust/entrustRegister/findBoard?id=${entrustRegister.id}" >法医物证鉴定室DNA实验室记录表</a></td>
				</tr>
				<tr>
					<th>7</th>
					<td><a href="${ctx}/entrust/entrustRegister/map?id=${entrustRegister.id}">原始检验记录表、图谱</a></td>
				</tr>
				<tr>
					<th>8</th>
					<td><a  href="${ctx}/entrust/entrustRegister/report?id=${entrustRegister.id}">司法鉴定意见书（正稿）</a></td>
				</tr>
				<tr>
					<th>9</th>
					<td>司法鉴定意见书底稿</td>
				</tr>
				<tr>
					<th>10</th>
					<td><a  href="${ctx}/entrust/entrustRegister/parties?id=${entrustRegister.id}">	当事人身份证复印件或当事人户口本复印件</a></td>
				</tr>
				<tr>
					<th>11</th>
					<td><a  href="${ctx}/entrust/entrustRegister/chargeCredentials?id=${entrustRegister.id}">收费凭据</a></td>
				</tr>
				<tr>
					<th>12</th>
					<td><a href="${ctx}/entrust/entrustRegister/licensed?id=${entrustRegister.id}">鉴定人执业证复印件</a></td>
				</tr>
				<tr>
					<th>13</th>
					<td><a href="${ctx}/appraisal_xkzfyj.do?id=${entrustWtdj.id}" >司法鉴定许可证复印件</a></td>
				</tr>
				<tr>
					<th>14</th>
					<td>卷内备考表</td>
				</tr>
				<tr>
					<th>15</th>
					<td>案卷封底</td>
				</tr>
			</table>
      </div>
</body>
</html>