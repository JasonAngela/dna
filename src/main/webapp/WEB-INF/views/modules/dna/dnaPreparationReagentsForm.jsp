<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试剂配制记录表管理</title>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dna/dnaPreparationReagents/form?id=${dnaPreparationReagents.id}">试剂配制记录表<shiro:hasPermission name="dna:dnaPreparationReagents:edit">${not empty dnaPreparationReagents.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaPreparationReagents:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaPreparationReagents" action="${ctx}/dna/dnaPreparationReagents/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		
		
		<%-- <div class="control-group">
			<label class="control-label">编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<table width="800" align="center" style="margin-top:30px"
		class="inputTable">
		<tr>
			<th colspan="4" style="text-align: center;"><h2>${jdname}DNA提取记录表</h2></th>
		</tr>
		<tr>
			<th>实验前</th>
				<td colspan="3">
						<form:checkboxes  path="beforeTrial"  items="${fns:getDictList('beforeTrialCode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="" />
				</td>
		</tr>
		<tr>
			<th>实验室温度</th>
			<td><form:input path="temperature" htmlEscape="false" maxlength="64" class="input-xlarge "/></td>
			<th>冰箱温度</th>
			<td><form:input path="refrigeratorTemperature" htmlEscape="false" maxlength="64" class="input-xlarge "/></td>	
		</tr>
		<tr>
			<th>相对湿度</th>
			<td>
			<form:input path="humidity" htmlEscape="false" maxlength="64" class="input-xlarge "/></td>
			<th>实验室</th>
			<td>
			<a href="${ctx}/synth/synthLab/form?id=${lab.id}">	${lab.name}</a>
			<form:hidden path="lab.id" value="${lab.id}"/>
			</td>
		</tr>
		<tr>
		<th>PCR试剂来源</th>
			<td><form:input path="pcrReagentSource" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>	
		</tr>
	</table>
	<table width="300" align="center" style="margin-top:30px" class="inputTable">
		<tr>
			<th colspan="5" style="text-align: center;"><h2>${jdname}使用设备</h2></th>
		</tr>
		<tr>
			<th style="width: 15px;">
					选择
			</th>
			<th>
			 	设备名称
			</th>
			
			<th>
			 设备编号
			</th>
		</tr>
		       <c:forEach items="${extraction }"  var="extraction"  varStatus="kl">
		 <tr>
		    	<td>
		       		<input type="checkbox" name="synthEquipments[${kl.index}].id"  value="${extraction.id} ">
		   	 	</td>
		   	 	<td>
		   	 		${extraction.name} 
		   	 	</td>
		   	 	<td>
		   	 		 ${extraction.code}
		   	 	</td>
		  </tr>
		       </c:forEach>
		</table>
		
	<br/>
	<div>	
		<span style="color:red;margin-left: 700px;">按照需要检验项目的标本、阴阳性对照的数量，预计要准备的每种试剂数量：</span>
	</div>
	
	<table width="800" align="center" style="margin-top:30px"
		class="inputTable">
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
			<form:input value="0.375ul" path="otherone" htmlEscape="false" maxlength="255" class="input-xlarge "/><br/>
			<form:input path="first" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul
			</td>
			<td rowspan="4"><form:input path="nAddOne" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<td><form:input path="totalOne" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul</td>		
		</tr>	
		<tr>
			<td>PCRReaction Mix</td>
			<td> 
			<form:input value="2.625ul" path="othertow" htmlEscape="false" maxlength="255" class="input-xlarge "/><br/>
			<form:input path="second" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul
			</td>			
			<td><form:input path="totalTwo" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul</td>		
		</tr>	
		<tr>
			<td>primer Set</td>
			<td> 
			<form:input path="othertr" value="1.375ul" htmlEscape="false" maxlength="255" class="input-xlarge "/><br/>
			<form:input path="third" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul</td>		
			<td><form:input path="totalThree" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul</td>		
		</tr>
		<tr>
			<td>Gold、DNA Polymerase (5U/ul)</td>
			<td> 
			<form:input path="otherfour" value="0.125ul" htmlEscape="false" maxlength="255" class="input-xlarge "/><br/>
			<form:input path="fourth" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul</td>			
			<td><form:input path="totalFour" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul</td>		
		</tr>
		<tr>
			<td>DNA(含检测样本和阴阳性对照）</td>
			<td> 
			<form:input path="otherfive" value="2.5ul" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			<br/>
			<form:input path="fifth" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul
			</td>			
			<td >n=<form:input path="other" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<td><form:input path="totalFive" htmlEscape="false" maxlength="255" class="input-xlarge "/>ul</td>		
		</tr>		
	</table>
	
	<div>	
		<span style="color:red;margin-left: 700px;">注：n包括检测样本数，阳性对照一管，阴性对照一管</span>		
	</div>				
	
	<table border="0" width="500" align="center" style="margin-top:20px" class="inputTable">
		<tr>
			<th>仪器设备使用：振荡器</th>
			<td><form:input path="instrumentUse" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>日期</th>
			<td><form:input path="userDate" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
		</tr>
		<tr>
			<th> 检验人</th>
			<td><form:input path="surveyor" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>复核人</th>
			<td><form:input path="reviewer" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
		</tr>
	</table>
	
	
		<div class="control-group" style="margin-left: 360px;">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">试剂配制记录明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>样品编号</th>
								<th>备注</th>
								<shiro:hasPermission name="dna:dnaPreparationReagents:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dnaPreparationReagentsIteamList">
						</tbody>
						<shiro:hasPermission name="dna:dnaPreparationReagents:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#dnaPreparationReagentsIteamList', dnaPreparationReagentsIteamRowIdx, dnaPreparationReagentsIteamTpl);dnaPreparationReagentsIteamRowIdx = dnaPreparationReagentsIteamRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dnaPreparationReagentsIteamTpl">//<!--
						<tr id="dnaPreparationReagentsIteamList{{idx}}">
							<td class="hide">
								<input id="dnaPreparationReagentsIteamList{{idx}}_id" name="dnaPreparationReagentsIteamList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaPreparationReagentsIteamList{{idx}}_delFlag" name="dnaPreparationReagentsIteamList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dnaPreparationReagentsIteamList{{idx}}_sampleNumber" name="dnaPreparationReagentsIteamList[{{idx}}].sampleNumber" type="text" value="{{row.sampleNumber}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="dnaPreparationReagentsIteamList{{idx}}_remarks" name="dnaPreparationReagentsIteamList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="dna:dnaPreparationReagents:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dnaPreparationReagentsIteamList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dnaPreparationReagentsIteamRowIdx = 0, dnaPreparationReagentsIteamTpl = $("#dnaPreparationReagentsIteamTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dnaPreparationReagents.dnaPreparationReagentsIteamList)};
							for (var i=0; i<data.length; i++){
								addRow('#dnaPreparationReagentsIteamList', dnaPreparationReagentsIteamRowIdx, dnaPreparationReagentsIteamTpl, data[i]);
								dnaPreparationReagentsIteamRowIdx = dnaPreparationReagentsIteamRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaPreparationReagents:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>