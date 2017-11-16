<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>二联体管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dna/dnaPiResult/">二联体列表</a></li>
		<li class="active"><a href="${ctx}/dna/dnaPiResult/form?id=${dnaPiResult.id}">二联体<shiro:hasPermission name="dna:dnaPiResult:edit">${not empty dnaPiResult.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaPiResult:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaPiResult" action="${ctx}/dna/dnaPiResult/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">父编码：</label>
			<div class="controls">
				<form:input path="parentCode" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子编码：</label>
			<div class="controls">
				<form:input path="childCode" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">委托id：</label>
			<div class="controls">
				<form:input path="register.id" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">dna_pi_result_item：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>基因座</th>
								<th>P</th>
								<th>Q</th>
								<th>p</th>
								<th>q</th>
								<th>pi</th>
								<shiro:hasPermission name="dna:dnaPiResult:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dnaPiResultItemList">
						</tbody>
						<shiro:hasPermission name="dna:dnaPiResult:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#dnaPiResultItemList', dnaPiResultItemRowIdx, dnaPiResultItemTpl);dnaPiResultItemRowIdx = dnaPiResultItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dnaPiResultItemTpl">//<!--
						<tr id="dnaPiResultItemList{{idx}}">
							<td class="hide">
								<input id="dnaPiResultItemList{{idx}}_id" name="dnaPiResultItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaPiResultItemList{{idx}}_delFlag" name="dnaPiResultItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dnaPiResultItemList{{idx}}_geneLoci" name="dnaPiResultItemList[{{idx}}].geneLoci" type="text" value="{{row.geneLoci}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaPiResultItemList{{idx}}_pValue" name="dnaPiResultItemList[{{idx}}].pValue" type="text" value="{{row.pValue}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<input id="dnaPiResultItemList{{idx}}_qValue" name="dnaPiResultItemList[{{idx}}].qValue" type="text" value="{{row.qValue}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<input id="dnaPiResultItemList{{idx}}_pProb" name="dnaPiResultItemList[{{idx}}].pProb" type="text" value="{{row.pProb}}" class="input-small "/>
							</td>
							<td>
								<input id="dnaPiResultItemList{{idx}}_qProb" name="dnaPiResultItemList[{{idx}}].qProb" type="text" value="{{row.qProb}}" class="input-small  number"/>
							</td>
							<td>
								<input id="dnaPiResultItemList{{idx}}_pi" name="dnaPiResultItemList[{{idx}}].pi" type="text" value="{{row.pi}}" class="input-small  number"/>
							</td>
							<shiro:hasPermission name="dna:dnaPiResult:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dnaPiResultItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dnaPiResultItemRowIdx = 0, dnaPiResultItemTpl = $("#dnaPiResultItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dnaPiResult.dnaPiResultItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#dnaPiResultItemList', dnaPiResultItemRowIdx, dnaPiResultItemTpl, data[i]);
								dnaPiResultItemRowIdx = dnaPiResultItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaPiResult:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>