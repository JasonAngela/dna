




<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>保存成功管理</title>
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
			$("a.jquery-word-export").click(function(event) {
		        $("#pagecontent").wordExport();
		    });
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
		  function changeMoneyToChinese(){
		  		var money=$("#allmoney").val();
				var cnNums = new Array("零","壹","贰","叁","肆","伍","陆","柒","捌","玖"); //汉字的数字
				var cnIntRadice = new Array("","拾","佰","仟"); //基本单位
				var cnIntUnits = new Array("","万","亿","兆"); //对应整数部分扩展单位
				var cnDecUnits = new Array("角","分","毫","厘"); //对应小数部分单位
				var cnInteger = "整"; //整数金额时后面跟的字符
				var cnIntLast = "元"; //整型完以后的单位
				var maxNum = 999999999999999.9999; //最大处理的数字
				
				var IntegerNum; //金额整数部分
				var DecimalNum; //金额小数部分
				var ChineseStr=""; //输出的中文金额字符串
				var parts; //分离金额后用的数组，预定义
				
				if( money == "" ){
				return "";
				}
				money = parseFloat(money);
				//alert(money);
				if( money >= maxNum ){
				$.alert('超出最大处理数字');
				return "";
				}
				if( money == 0 ){
				ChineseStr = cnNums[0]+cnIntLast+cnInteger;
				//document.getElementById("show").value=ChineseStr;
				return ChineseStr;
				}
				money = money.toString(); //转换为字符串
				if( money.indexOf(".") == -1 ){
				IntegerNum = money;
				DecimalNum = '';
				}else{
				parts = money.split(".");
				IntegerNum = parts[0];
				DecimalNum = parts[1].substr(0,4);
				}
				if( parseInt(IntegerNum,10) > 0 ){//获取整型部分转换
				zeroCount = 0;
				IntLen = IntegerNum.length;
				for( i=0;i<IntLen;i++ ){
				n = IntegerNum.substr(i,1);
				p = IntLen - i - 1;
				q = p / 4;
				m = p % 4;
				if( n == "0" ){
				zeroCount++;
				}else{
				if( zeroCount > 0 ){
				ChineseStr += cnNums[0];
				}
				zeroCount = 0; //归零
				ChineseStr += cnNums[parseInt(n)]+cnIntRadice[m];
				}
				if( m==0 && zeroCount<4 ){
				ChineseStr += cnIntUnits[q];
				}
				}
				ChineseStr += cnIntLast;
				//整型部分处理完毕
				}
				if( DecimalNum!= '' ){//小数部分
				decLen = DecimalNum.length;
				for( i=0; i<decLen; i++ ){
				n = DecimalNum.substr(i,1);
				if( n != '0' ){
				ChineseStr += cnNums[Number(n)]+cnDecUnits[i];
				}
				}
				}
				if( ChineseStr == '' ){
				ChineseStr += cnNums[0]+cnIntLast+cnInteger;
				}
				else if( DecimalNum == '' ){
				ChineseStr += cnInteger;
				}
				$("#dx").val(ChineseStr);
				}
		  function printEntrust(){
				$("#lover").hide();
				$("#lover1").show();
				$("#a").show();
				$("#b").show();
				window.print();
				$("#lover").show();
				$("#lover1").hide();
				$("#a").hide();
				$("#b").hide();
			}
		  
		 
		
	</script>
	<style type="text/css">
	 body{  
            margin:0;  
            padding:0;  
            background-color:#C1CDCD;
            height: 200px;  
            width: 200px;  
            /*body的背景色是不受body本身的宽高的影响的。  
              body的背景色就是铺满整个页面的。  
            */  
              
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
body
{
font-family : 微软雅黑,宋体;
font-size : x-large;
}
	#pagecontent{
	
	background-color: #F0FFFF;
	    }
	</style>
	
	
	
	
</head>
<body>
<div id="pagecontent" style="width: 930px;margin-left: 300px;">
	
	<form:form id="inputForm" modelAttribute="entrustRegister" action="${ctx}/entrust/entrustRegister/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
	<div id="pagecontent" style="width: 649;">
	<div id="a" class="o">
		<h1 align="center">江西开元司法鉴定中心<br/>
		司法鉴定委托书</h1>
		<h6 style="margin-left: 630px;" id="lover1" >赣(开)[${simple}]物鉴字第${casecode}号</h6>	
	</div>
		<table  style="width: 830px;"  width="830" align="center" height="730" class="inputTable">
			<tr> 
				<th colspan="2" style="width: 276px;">委 托 人</th>
				<td colspan="2" style="width: 276px;">${entrustRegister.clientName}</td>
				<th colspan="2" style="width: 276px;">委托人电话</th>
				<td colspan="2" style="width: 276px;">${entrustRegister.clientTel}
				</td>
			</tr>
			<tr>
				<th colspan="2">联系地址</th>
				<td colspan="2">${entrustRegister.clientAddress}</td>
				<th width="120" colspan="2">联系电话</th>
				<td colspan="2">${entrustRegister.clientZipcode}
				</td>
			</tr>
			<tr>
				<th colspan="2">委托日期</th>
				<td colspan="2">
				${entrustRegister.entrustDate }
				</td>
				<th colspan="2">送检人(机构)</th>
				<td colspan="2">
				${entrustRegister.agentName}
				</td>
			</tr>
			<tr style="height: 80px;">
			   <th colspan="2">司法鉴定
			   <br/>
			   机&nbsp;&nbsp;构
			   </th>
			   <td colspan="7">
			   机构名称: &nbsp;&nbsp;江西开元司法鉴定中心    &nbsp;&nbsp;&nbsp;&nbsp;许可证号:360010056   <br/>
			 地址: &nbsp;&nbsp;南昌市高新五路666号创立大厦五楼 <br/>
			  邮编:&nbsp;&nbsp;330096<br/>
			  联系人:${entrustRegister.serverName}
			 联系电话: ${entrustRegister.agentTel}
			   </td>
			</tr>
			<tr>	
				<th colspan="2">委托鉴定事项</th>
				<td colspan="6">
				<form:select path="type" class="">
					<form:options items="${fns:getDictList('typeCode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</td>
			</tr>
			<tr height="80px">
				<th colspan="2">是否属于
				<br/>
				重新鉴定</th>
				<td colspan="2">
					<form:radiobuttons path="authorizeNotification" items="${fns:getDictList('flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
				</td>
				<th>鉴定用途  
				 <td colspan="4">
					<input type="checkbox" <c:if test="${fn:trim(entrustRegister.whether) eq '1'}">checked="checked"</c:if> name="whetherq" value="1" /> 个人鉴定   &nbsp;&nbsp;
					<input type="checkbox" <c:if test="${fn:trim(entrustRegister.whether) eq '2'}">checked="checked"</c:if> name="whetherq" value="2" /> 司法鉴定<br/>
					<input type="checkbox" <c:if test="${fn:trim(entrustRegister.whether) eq '3'}">checked="checked"</c:if> name="whetherq" value="3" /> 个体识别  &nbsp;&nbsp;				  								
					<input type="checkbox" <c:if test="${fn:trim(entrustRegister.whether) eq '4'}">checked="checked"</c:if> name="whetherq" value="4" /> 亲缘鉴定<br/>
					<input type="checkbox" <c:if test="${fn:trim(entrustRegister.whether) eq '5'}">checked="checked"</c:if> name="whetherq" value="5" /> 其他:	&nbsp;&nbsp;			
				${entrustRegister.sendMode}
				</td>      	      	      
			</tr>
			<tr>
				<th colspan="2">鉴定材料:</th>
				<td colspan="7">
				<form:select path="aboutMaterials" class=" ">
						<form:options items="${fns:getDictList('aboutMaterialsCode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
			</tr>
			<tr height="70px">
				<th rowspan="2" colspan="2">预计费用及收取方式</th>
				<td colspan="7" >
				   预计收费总金额:￥:${entrustRegister.standardFee} 
				 大写:${entrustRegister.capital}
				</td>
			</tr>
				<tr>
					<td colspan="7">
						<form:checkboxes  path="chargeway"  items="${fns:getDictList('chargewayCode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="" />
				</td>
				</tr>
				<tr>
					<th colspan="2">司法鉴定意见
					<br>
					书发送方式 </th>
				<td colspan="7">
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.sendWay) eq '1'}">checked="checked"</c:if> name="sendWayq" value="1" /> 自取		<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.sendWay) eq '2'}">checked="checked"</c:if> name="sendWayq" value="2" /> 邮递    &nbsp;&nbsp;地址:${entrustRegister.clientFax}<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.sendWay) eq '3'}">checked="checked"</c:if> name="sendWayq" value="3" /> 其他方式（说明） 	&nbsp;&nbsp;${entrustRegister.purposeOther}<br/>
				</tr>
				</table>
			 <table  style="width: 830px;"  width="830" align="center" height="730" class="inputTable">
				<tr height="400px;">
					<td colspan="8">
					约定事项:<br/>
					1.(1)关于鉴定材料:<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.materialDispose) eq '1'}">checked="checked"</c:if> name="materialDisposeq" value="1" /> 所有鉴定材料无须退换。	<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.materialDispose) eq '2'}">checked="checked"</c:if> name="materialDisposeq" value="2" /> 鉴定材料须完整，无损坏地退还委托人。	<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.materialDispose) eq '3'}">checked="checked"</c:if> name="materialDisposeq" value="3" /> 因鉴定需要，鉴定材料可能损坏、耗尽，导致无法完整退还。	<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.materialDispose) eq '4'}">checked="checked"</c:if> name="materialDisposeq" value="4" /> 对保管和使用鉴定材料的特殊要求:	${entrustRegister.specialRequirements} <br/>	
				      (2)关于剩余鉴定材料:<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.remaining) eq '1'}">checked="checked"</c:if> name="remainingq" value="1" />      
				      委托人于${entrustRegister.weeks}周内自行取回。委托人未按时取回的，鉴定机构有权自行处理。<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.remaining) eq '2'}">checked="checked"</c:if> name="remainingq" value="2" />				              
				     	鉴定机构自行处理，如需发生处理费的，按有关收费标准或协商收取${entrustRegister.specialFee}元处理费。<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.remaining) eq '3'}">checked="checked"</c:if> name="remainingq" value="3" />其他方式:${entrustRegister.otherWay}<br/>	
					2.鉴定时限：<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.timeLimitReport) eq '1'}">checked="checked"</c:if> name="timeLimitReportq" value="1" />	
   						${entrustRegister.clientEmail} 之前完成鉴定，提交司法鉴定意见书。<br/>
<input type="checkbox" <c:if test="${fn:trim(entrustRegister.timeLimitReport) eq '2'}">checked="checked"</c:if> name="timeLimitReportq" value="2" />							
						从该委托书生效之日起     ${entrustRegister.timeLimitResult} 个工作日内完成鉴定，提交司法鉴定意见书。<br/>
						注：鉴定过程中补充或者重新提取鉴定材料所需时间，不计入鉴定时限。<br/>
					3.需要回避的鉴定人：   ${entrustRegister.avoidAppraisers}，回避事由：${entrustRegister.evadingReason}。<br/>
					4.经双方协商一致，鉴定过程中可变更委托书内容。<br/>
					5.其他约定事项： 	${entrustRegister.clientReceiver}<br/>
					</td>	
				</tr>
				<tr>
				<th colspan="2">
				  鉴定风险提示：
				</th>
				<td colspan="7">
				1.鉴定意见属于专家的专业意见，是否被采信取决于办案机关的审查和判断，鉴定人和鉴定机构无权干涉；<br/>
				2.由于受鉴定材料或者其他因素限制，并非所有的鉴定都能得出明确的鉴定意见；<br/>
				3.鉴定活动遵循依法独立、客观、公正的原则，只对鉴定材料和案发事实负责，不会考虑是否有利于任何一方当事人。<br/>
                                    委托人（签名）：         <br/>               
				</td>
				</tr>
				<tr>
					<th colspan="2">其他需要说
					<br/>
					明的事项</th>
					<td colspan="7">
					${entrustRegister.remarks}
					</td>
				</tr>   
				  
				  
				  
				<tr>
				  <th rowspan="6" >与鉴定有关<br/>的基本案情</th>
				</tr>
				
				<tr>		  
				  <td>姓名</td>
				  <td>性别</td>
				  <td>称谓</td>
				  <td>出生日期</td>
				  <td align="center">证件名称</td>
				  <td colspan="2" style="width: 276px;">证件号码</td>
				</tr>
				
				 <c:forEach items="${allentrustAbstracts}" var="allAb">
				<tr>
				    <td>${allAb.clientName}</td>
				    <td>
						 <select>
							<option <c:if test="${fn:trim(allAb.gender) eq '1'}">selected="selected"</c:if> value="男">男</option>
							<option <c:if test="${fn:trim(allAb.gender) eq '2'}">selected="selected"</c:if> value="女">女</option>
							<option <c:if test="${fn:trim(allAb.gender) eq '3'}">selected="selected"</c:if> value="其他">其他</option>
						</select>
				    </td>
					<td>
					<select>
							<option <c:if test="${fn:trim(allAb.appellation) eq '1'}">selected="selected"</c:if> value="父亲">父亲</option>
							<option <c:if test="${fn:trim(allAb.appellation) eq '2'}">selected="selected"</c:if> value="母亲">母亲</option>
							<option <c:if test="${fn:trim(allAb.appellation) eq '3'}">selected="selected"</c:if> value="小孩">小孩</option>
							<option <c:if test="${fn:trim(allAb.appellation) eq '4'}">selected="selected"</c:if> value="其他">其他</option>			
						</select>
					</td>
					<td>${allAb.birthday}</td>
					<td>
					<select>
							<option <c:if test="${fn:trim(allAb.idType) eq '1'}">selected="selected"</c:if> value="身份证">身份证</option>
							<option <c:if test="${fn:trim(allAb.idType) eq '2'}">selected="selected"</c:if> value="户口">户口</option>
							<option <c:if test="${fn:trim(allAb.idType) eq '3'}">selected="selected"</c:if> value="驾照">驾照</option>
							<option <c:if test="${fn:trim(allAb.idType) eq '4'}">selected="selected"</c:if> value="护照">护照</option>
							<option <c:if test="${fn:trim(allAb.idType) eq '5'}">selected="selected"</c:if> value="其他">其他</option>
							<option <c:if test="${fn:trim(allAb.idType) eq '6'}">selected="selected"</c:if> value="无">无</option>
						</select>
						</td>
					<td colspan="2">${allAb.idNo}</td>
				</tr>
				 </c:forEach>
		</table>
		</form:form>
		</div>
		<div id="lover" class="form-actions" align="center">
			<input class="btn" type="button" value="去打印" onclick="printEntrust()"/>
			<a class="jquery-word-export"><input class="btn" type="button" value="导出word"></a>
		</div>
	
</body>
