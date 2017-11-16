<%@ page contentType="text/html;charset=UTF-8" %>
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
	<%-- 	<li class="active"><a href="${ctx}/dna/addExtraction/form?id=${dnaExtractRecord.id}">保存成功<shiro:hasPermission name="dna:dnaExtractRecord:edit">${not empty dnaExtractRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaExtractRecord:edit">查看</shiro:lacksPermission></a></li> --%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaExtractRecord" action="${ctx}/dna/dnaExperiment/addExtraction" method="post" class="form-horizontal">
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
			
			<td><form:input path="temperature" htmlEscape="false" maxlength="64" class=" required"/>
				<span class="help-inline"><font color="red">*</font> </span></td>
			<th>冰箱温度</th>
			<td><form:input path="refrigeratorTemperature" htmlEscape="false" maxlength="64" class=" required"/>
				<span class="help-inline"><font color="red">*</font> </span></td>	
		</tr>
		<tr>
			<th>相对湿度</th>
			<td>
			<form:input path="humidity" htmlEscape="false" maxlength="64" class=" required"/>
				<span class="help-inline"><font color="red">*</font> </span></td>
			<th>提取室</th>
			<td>
			<a href="${ctx}/synth/synthLab/form?id=${lab.id}">	${lab.name}</a>
			<form:hidden path="lab.id" value="${lab.id}"/>
			</td>
		
		</tr>			
	</table>
	
	
	
	<table width="800" align="center" style="margin-top:30px"
		class="inputTable">
		<tr>
		<%-- 	<th>移液器 &nbsp;&nbsp;&nbsp;<input type="button" class="btn ok" value="选择设备" onclick="xzsb()" /></th>
			<td><input name="dnaStrfxjjjl.jyq" type="text" id="jyq" value="${dnaStrfxjjjl.jyq}"/></td> --%>
			<th>最后模版溶液体积</th>
			<td><form:input path="finalTemplate" htmlEscape="false" maxlength="255" class="input-xlarge " value="200"/></td>
			<th>温育温度和时间</th>
			<td><form:input path="other" htmlEscape="false" maxlength="255" class="input-xlarge " value="56℃/11时"/></td>
		</tr>
		<tr>
			<th>加热过程</th>
			<td><form:input path="heatingProcess" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>存放温度</th>
			<td>
			<form:input path="storageTemperature" htmlEscape="false" maxlength="255" class="input-xlarge " value="10℃"/></td>
		</tr>
		<tr>
			<th>提取的模板存放处</th>
			<td><form:input path="extractedTemplate" htmlEscape="false" maxlength="255" class="input-xlarge " value="冰箱"/></td>
			<th>模板交接日期</th>
			<td><form:input path="templateHandoverDate" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
		</tr>	
			<tr>
			<th>提取者</th>
			<td><form:input path="extractors" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>复核人</th>
			<td><form:input path="reviewer" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			
		</tr>
		<tr>
			<th>接收人</th>
			<td><form:input path="recipient" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>提取日期</th>
			<td><form:input path="otherone" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
		</tr>
	</table>
		
	
		
		<table width="300" align="center" style="margin-top:30px" class="inputTable">
		<tr>
			<th colspan="5" style="text-align: center;"><h2>${jdname}使用设备</h2></th>
		</tr>
		<tr>
			<th>
				选择
			</th>
		
			<th>
			 	设备名称
			</th>
			
			<th>
			 设备编号
			</th>
		</tr>
		       <c:forEach items="${extraction}"  var="extraction"  varStatus="kl">
		 <tr>
		    	<td style="width: 15px;">
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
		
		
		<div class="control-group" style="margin-left: 430px;">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		
		<%-- 
		
		<div class="control-group">
			<label class="control-label">仪器设备使用：</label>
			<div class="controls">
				<form:input path="instrumentUse" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生物安全柜/超净台：</label>
			<div class="controls">
				<form:input path="biosafetyCabinet" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">最后模版溶液体积：</label>
			<div class="controls">
				<form:input path="finalTemplate" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提取的模板存放处：</label>
			<div class="controls">
				<form:input path="extractedTemplate" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">加热过程：</label>
			<div class="controls">
				<form:input path="heatingProcess" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">存放温度：</label>
			<div class="controls">
				<form:input path="storageTemperature" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提取者：</label>
			<div class="controls">
				<form:input path="extractors" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">复核人：</label>
			<div class="controls">
				<form:input path="reviewer" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板交接：</label>
			<div class="controls">
				<form:input path="transferTemplate" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接收人：</label>
			<div class="controls">
				<form:input path="recipient" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">模板交接日期：</label>
			<div class="controls">
				<form:input path="templateHandoverDate" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> 		<div class="control-group">
			<label class="control-label">其他：</label>
			<div class="controls">
				<form:input path="other" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>--%>
		<%-- <div class="control-group">
			<label class="control-label">其他1：</label>
			<div class="controls">
				<form:input path="otherone" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他2：</label>
			<div class="controls">
				<form:input path="othertow" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其它3：</label>
			<div class="controls">
				<form:input path="othertr" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其它4：</label>
			<div class="controls">
				<form:input path="otherfour" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其它5：</label>
			<div class="controls">
				<form:input path="otherfive" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<div style="color:red;margin-left: 320px;">
		注：DNA提取法按司法部司法鉴定所《chelex-100法提取DNA作业指导书》。CH:chelex-100方法：IQ:DNAIQ法:二步：二步消化法
	  </div>
			<div class="control-group">
				<label class="control-label">提取室详情：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>提取轮次</th>
								<th>样品编号</th>
								<th>检材类型</th>
								<th>取材量</th>
								<th>提取方式</th>
								<th>试剂批号</th>
								<th>核酸抽提方法</th>
								<th>备注</th>
								<shiro:hasPermission name="dna:dnaExtractRecord:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dnaExtractRecordItemList">
						</tbody>
						<shiro:hasPermission name="dna:dnaExtractRecord:edit"><tfoot>
							<tr><td colspan="12"><a href="javascript:" onclick="addRow('#dnaExtractRecordItemList', dnaExtractRecordItemRowIdx, dnaExtractRecordItemTpl);dnaExtractRecordItemRowIdx = dnaExtractRecordItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dnaExtractRecordItemTpl">//<!--
						<tr id="dnaExtractRecordItemList{{idx}}">
							<td class="hide">
								<input id="dnaExtractRecordItemList{{idx}}_id" name="dnaExtractRecordItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaExtractRecordItemList{{idx}}_delFlag" name="dnaExtractRecordItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dnaExtractRecordItemList{{idx}}_extractRounds" name="dnaExtractRecordItemList[{{idx}}].extractRounds" type="text" value="{{row.extractRounds}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaExtractRecordItemList{{idx}}_sampleNumber" name="dnaExtractRecordItemList[{{idx}}].sampleNumber" type="text" value="{{row.sampleNumber}}" maxlength="255" class="input-small "/>
							</td>
							<td width="10px;">
							<select id="dnaExtractRecordItemList{{idx}}_checkTypes" name="dnaExtractRecordItemList[{{idx}}].checkTypes" data-value="{{row.checkTypes}}" class=" ">
									<c:forEach items="${fns:getDictList('aboutMaterialsCode')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>

							<select id="dnaExtractRecordItemList{{idx}}_basedAmount" name="dnaExtractRecordItemList[{{idx}}].basedAmount" data-value="{{row.basedAmount}}" class=" ">
									<c:forEach items="${fns:getDictList('basedamountCode')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select><br/>
							<input id="dnaExtractRecordItemList{{idx}}_basedAmountOther" name="dnaExtractRecordItemList[{{idx}}].basedAmountOther" type="text" value="{{row.basedAmountOther}}" maxlength="255" class="input-small "/>
							</td>
							<td>
							<select id="dnaExtractRecordItemList{{idx}}_extractWay" name="dnaExtractRecordItemList[{{idx}}].extractWay" data-value="{{row.extractWay}}" class=" ">
									<c:forEach items="${fns:getDictList('extractwayCode')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select><br/>
								<input id="dnaExtractRecordItemList{{idx}}_extractWayother" name="dnaExtractRecordItemList[{{idx}}].extractWayother" type="text" value="{{row.extractWayother}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaExtractRecordItemList{{idx}}_reagentBatches" name="dnaExtractRecordItemList[{{idx}}].reagentBatches" type="text" value="{{row.reagentBatches}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaExtractRecordItemList{{idx}}_extractionMethod" name="dnaExtractRecordItemList[{{idx}}].extractionMethod" type="text" value="{{row.extractionMethod}} " maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="dnaExtractRecordItemList{{idx}}_remarks" name="dnaExtractRecordItemList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="dna:dnaExtractRecord:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dnaExtractRecordItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dnaExtractRecordItemRowIdx = 0, dnaExtractRecordItemTpl = $("#dnaExtractRecordItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dnaExtractRecord.dnaExtractRecordItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#dnaExtractRecordItemList', dnaExtractRecordItemRowIdx, dnaExtractRecordItemTpl, data[i]);
								dnaExtractRecordItemRowIdx = dnaExtractRecordItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>