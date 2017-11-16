<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电泳室管理</title>
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
		<li><a href="${ctx}/dna/dnaElectrophoresisParting/">电泳室列表</a></li>
		<li class="active"><a href="${ctx}/dna/dnaElectrophoresisParting/form?id=${dnaElectrophoresisParting.id}">电泳室<shiro:hasPermission name="dna:dnaElectrophoresisParting:edit">${not empty dnaElectrophoresisParting.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaElectrophoresisParting:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaElectrophoresisParting" action="${ctx}/dna/dnaElectrophoresisParting/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
	
	<table width="800" align="center" style="margin-top:30px"
		class="inputTable">
		<tr align="center">
			<th colspan="4" style="text-align: center;"><h2>电泳分型</h2></th>
		</tr>
		<tr>
			<th>甲酰胺批号</th>
			<td><form:input path="formamideBatchNumber" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>是否已验证有效</th>
			<td><form:radiobuttons path="validated" items="${fns:getDictList('flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/></td>
		</tr>
		<tr>
			<th>内标</th>
			<td><form:input path="internalStandard" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>是否已验证有效</th>
			<td><form:radiobuttons path="validated1" items="${fns:getDictList('flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/></td>
		</tr>
		<tr>
			<th>实验室温度</th>
			<td><form:input path="temperature" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>冰箱温度</th>
			<td><form:input path="refrigeratorTemperature" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>			
		</tr>
		<tr>
			<th>产物取量和处理</th>
			<td><form:input path="product" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>	
			<th>相对湿度</th>
			<td colspan="3"><form:input path="humidity" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>		
		</tr>
	
		<tr>
			<th>阳性对照</th>
			<td ><form:input path="positiveControl" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
			<th>阴性对照</th>
			<td ><form:input path="negativeControl" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
		</tr>
		
		<tr>
			<th>实验室</th>
			<td>
				<a href="${ctx}/synth/synthLab/form?id=${lab.id}">	${lab.name}</a>
				<form:hidden path="lab.id" value="${lab.id}"/>
			</td>
			
		</tr>
	</table>
	<table width="300" align="center" style="margin-top:30px" class="inputTable" >
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
		
		
		<div class="control-group" style="margin-left: 430px;">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		
		
		
			
		
			<div class="control-group">
				<label class="control-label">电泳分型明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>样品编号</th>
								<th>备注</th>
								<shiro:hasPermission name="dna:dnaElectrophoresisParting:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dnaElectrophoresisPartingIteamList">
						</tbody>
						<shiro:hasPermission name="dna:dnaElectrophoresisParting:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#dnaElectrophoresisPartingIteamList', dnaElectrophoresisPartingIteamRowIdx, dnaElectrophoresisPartingIteamTpl);dnaElectrophoresisPartingIteamRowIdx = dnaElectrophoresisPartingIteamRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dnaElectrophoresisPartingIteamTpl">//<!--
						<tr id="dnaElectrophoresisPartingIteamList{{idx}}">
							<td class="hide">
								<input id="dnaElectrophoresisPartingIteamList{{idx}}_id" name="dnaElectrophoresisPartingIteamList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaElectrophoresisPartingIteamList{{idx}}_delFlag" name="dnaElectrophoresisPartingIteamList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dnaElectrophoresisPartingIteamList{{idx}}_sampleNumber" name="dnaElectrophoresisPartingIteamList[{{idx}}].sampleNumber" type="text" value="{{row.sampleNumber}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="dnaElectrophoresisPartingIteamList{{idx}}_remarks" name="dnaElectrophoresisPartingIteamList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="dna:dnaElectrophoresisParting:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dnaElectrophoresisPartingIteamList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dnaElectrophoresisPartingIteamRowIdx = 0, dnaElectrophoresisPartingIteamTpl = $("#dnaElectrophoresisPartingIteamTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dnaElectrophoresisParting.dnaElectrophoresisPartingIteamList)};
							for (var i=0; i<data.length; i++){
								addRow('#dnaElectrophoresisPartingIteamList', dnaElectrophoresisPartingIteamRowIdx, dnaElectrophoresisPartingIteamTpl, data[i]);
								dnaElectrophoresisPartingIteamRowIdx = dnaElectrophoresisPartingIteamRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaElectrophoresisParting:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>