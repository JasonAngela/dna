<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>dna试验管理</title>
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
		
		
		
		function ps(id,name) {
			$("#ss").val(name);
			$("#ss2").val(id);
		}
		function method() {
			var dx=$("#ss2").val();
			location.href="${ctx}/dna/dnaExperiment/findExtraction?fid="+dx;
		}
		function psa(id,name) {
			$("#dd").val(name);
			$("#dd2").val(id);
		}
		function methoda() {
			var dx=$("#dd2").val();
			location.href="${ctx}/dna/dnaExperiment/findExpansion?fid="+dx;
		}
		
		function psb(id,name) {
			$("#ee").val(name);
			$("#ee2").val(id);
		}
		
		function methodb() {
			var dx=$("#ee2").val();
			location.href="${ctx}/dna/dnaExperiment/findElctrophoresi?fid="+dx;
		}
		
		
		function next1() { 
			 var msg = "是否已添加提取室，扩增室及电泳室\n\n请确认！"; 
			 if (confirm(msg)==true){
				
				 $("#d1").hide();
				 $("#d2").show();
			  return true; 
			 }else{ 
			  return false; 
			 } 
		} 
		
		
	</script>
</head>
<body>

		
		
		<form:form id="inputForm" modelAttribute="dnaExperiment" action="${ctx}/dna/dnaExperiment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
	<div id="d1">	
			<div class="control-group" id="id1">
				<label class="control-label">提取室：</label>
				<div class="controls"  >
					<form:select path="lab.id" onchange="ps(this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].label)">
						<form:options items="${extractionChambers}" itemLabel="name" itemValue="id" />
					</form:select>
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="javascript:void(0);" onclick="method()">
					  	<input type="button" id="ss" value="${extractionChambers[0].name}">
					  	</a> 
					  	<input type="hidden" id="ss2" value="${extractionChambers[0].id}">
				</div>
			</div>
		
		<div class="control-group">
			<label class="control-label">扩增室：</label>
			<div class="controls">
				<form:select path="expansionChambers" onchange="psa(this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].label)">
					<form:options items="${expansionChambers}" itemLabel="name" itemValue="id" />
				</form:select>
				  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<a href="javascript:void(0);" onclick="methoda()">
				  	<input type="button" id="dd" value="${expansionChambers[0].name}">
				  	</a> 
				  	<input type="hidden" id="dd2" value="${expansionChambers[0].id}">
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">电泳室：</label>
			<div class="controls">
				<form:select path="elctrophoresisChambers"  onchange="psb(this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].label)">
					<form:options items="${elctrophoresisChambers}" itemLabel="name" itemValue="id"/>
				</form:select>
					&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" onclick="methodb()">
						<input type="button" id="ee" value="${elctrophoresisChambers[0].name}">
					</a>
						<input type="hidden" id="ee2" value="${elctrophoresisChambers[0].id}">
			</div>
			
		</div>
		 	<input type="button" value="下一步骤" onclick="next1()">
		</div>
		
	<div id="d2" style="display: none;">
			<div class="control-group">
				<label class="control-label">基因盒：</label>
				<div class="controls">
					<form:select path="cassette.id">
						<form:options itemLabel="name" itemValue="id" items="${cassetteList}"/> 
					</form:select>
				</div>
			</div>
		<div class="control-group">
			<label class="control-label">选择板子：</label>
			<div class="controls">
				<form:select path="boardId">
					<form:options items="${boardList}" itemLabel="code" itemValue="id"/>
				</form:select>
				&nbsp;
				<a href="${ctx}/dna/dnaExperiment/addNewBoard" onclick="confirm('确认添加新板子')">添加新板</a>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dnaExperiment.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dnaExperiment.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		 
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			 
			<div class="control-group">
				<label class="control-label">试验样品：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>检样编码</th>
								<th>备注</th>
								<shiro:hasPermission name="dna:dnaExperiment:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dnaExperimentSpecimenList">
						</tbody>
						<shiro:hasPermission name="dna:dnaExperiment:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#dnaExperimentSpecimenList', dnaExperimentSpecimenRowIdx, dnaExperimentSpecimenTpl);dnaExperimentSpecimenRowIdx = dnaExperimentSpecimenRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dnaExperimentSpecimenTpl">//<!--
						<tr id="dnaExperimentSpecimenList{{idx}}">
							<td class="hide">
								<input id="dnaExperimentSpecimenList{{idx}}_id" name="dnaExperimentSpecimenList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaExperimentSpecimenList{{idx}}_delFlag" name="dnaExperimentSpecimenList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<select id="dnaExperimentSpecimenList{{idx}}_specimenCode" name="dnaExperimentSpecimenList[{{idx}}].specimenCode" data-value="{{row.specimenCode}}" class="input-small">
									<option value="" label=""/>
									<c:forEach items="${notExperimentList}" var="item">
										<option value="${item.code}">${item.code}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="dnaExperimentSpecimenList{{idx}}_remarks" name="dnaExperimentSpecimenList[{{idx}}].remarks" type="text" value="{{row.remarks}}" maxlength="255" class="input-small "/>
							</td>
							<shiro:hasPermission name="dna:dnaExperiment:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dnaExperimentSpecimenList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dnaExperimentSpecimenRowIdx = 0, dnaExperimentSpecimenTpl = $("#dnaExperimentSpecimenTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dnaExperiment.dnaExperimentSpecimenList)};
							for (var i=0; i<data.length; i++){
								addRow('#dnaExperimentSpecimenList', dnaExperimentSpecimenRowIdx, dnaExperimentSpecimenTpl, data[i]);
								dnaExperimentSpecimenRowIdx = dnaExperimentSpecimenRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaExperiment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		</div>
	</form:form>
	
</body>
</html>