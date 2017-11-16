<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
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
<body>
	<h1 align="center">鉴定委托材料接收（采集）单</h1>
	<table border="1px" width="800" align="center" style="margin-top:30px"class="inputTable">
	
		<tr>
			<th>鉴定事项</th>
			<td colspan="10">
				<input type="checkbox" checked="checked"/>疑父—母—子   <input type="checkbox"/>疑父—疑母—子   <input type="checkbox"/>（单亲）疑父（母）—子(女)   <input type="checkbox"/>兄（姐）—妹（弟）   <input type="checkbox"/>兄—弟<br/>
				<input type="checkbox"/>姐—妹         <input type="checkbox"/>爷（婆）—孙     <input type="checkbox"/>姨—甥                   <input type="checkbox"/>叔(伯)—侄            <input type="checkbox"/>其它			
			</td>
		</tr>
        <tr>
			<th>鉴定事由</th>
			<td colspan="10"></td>
		</tr>
         <tr>
			<th rowspan="2">序号</th>
			<th rowspan="2">自诉称谓</th>
            <th rowspan="2">性别</th>
            <th rowspan="2">籍贯</th>
            <th rowspan="2">民族</th>
            <th rowspan="2">证件号码</th>
            <th rowspan="2">检材类型</th>
            <th rowspan="2">检材编号</th>
            <th colspan="3" align="center">指    纹</th>
            </tr>
            <tr>
            <td>左手食指</td>	<td>右手食指</td><td>	其他手指</td>
		</tr>
		<c:forEach items="${entrustAbstracts}" var="entrustAbstracts" varStatus="a">
          <tr>
			<td>${a.index+1}</td>
			<td>${entrustAbstracts.clientName}</td>
            <td>
            	<c:if test="${entrustAbstracts.gender ==1}">男</c:if> 
				<c:if test="${entrustAbstracts.gender ==2}">女</c:if>
				<c:if test="${entrustAbstracts.gender ==3}">未知</c:if>
			</td>
            <td></td>
            <td></td>
            <td>${jazy.idNo}</td>
            <td>${jazy.idNo}</td>
            <td>${jazy.specimenCode}</td>
            <td><input type="checkbox"/></td>
            <td><input type="checkbox" checked='checked'/></td>	
            <td><input type="checkbox"/></td>
           </tr>
		</c:forEach>
           <tr>
           	<td colspan="11">
            婚姻状况：<input type="checkbox"/>非婚          <input type="checkbox" checked="checked"/>已婚           <input type="checkbox"/>离婚          <input type="checkbox"/>其他
            </td>
           </tr>
           <tr>
           	<td colspan="2">
            委托人或被鉴定人签名：
            ${entrustRegister.clientName}
            </td>
            <td colspan="3">
            受理人：${entrustRegister.serverName}
            </td>
            <td colspan="2">
            样本采集人：${entrustRegister.serverName}
            <br/><br/>
			校对人 ：${goodsentry.lqr}
            </td>
            <td colspan="2">
            照相人：${entrustWtdj.serverName}
            </td>
            <td colspan="2">
            样本接收人：${goodsentry.lqr}
            </td>
           </tr>
	</table>
	<table border="0" width="500" align="center" style="margin-top:20px">
			<tr id="btn">
			<td align="center">
			<input type="button" class="btn" value="打印" onclick="printEntrust();"/>
			 <input type="button" class="btn cancel" value="取消" onclick="javascript:history.back();" /></td>
		</tr>
	</table>

</body>
</html>