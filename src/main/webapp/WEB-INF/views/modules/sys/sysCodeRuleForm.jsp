<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>编码规则管理</title>
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
		<li><a href="${ctx}/sys/sysCodeRule/">编码规则列表</a></li>
		<li class="active"><a href="${ctx}/sys/sysCodeRule/form?id=${sysCodeRule.id}">编码规则<shiro:hasPermission name="sys:sysCodeRule:edit">${not empty sysCodeRule.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:sysCodeRule:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysCodeRule" action="${ctx}/sys/sysCodeRule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">编码生成规则明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>序号</th>
								<th>类型</th>
								<th>模式</th>
								<th>长度</th>
								<th>关联序列</th>
								<shiro:hasPermission name="sys:sysCodeRule:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sysCodeRuleItemList">
						</tbody>
						<shiro:hasPermission name="sys:sysCodeRule:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#sysCodeRuleItemList', sysCodeRuleItemRowIdx, sysCodeRuleItemTpl);sysCodeRuleItemRowIdx = sysCodeRuleItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="sysCodeRuleItemTpl">//<!--
						<tr id="sysCodeRuleItemList{{idx}}">
							<td class="hide">
								<input id="sysCodeRuleItemList{{idx}}_id" name="sysCodeRuleItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sysCodeRuleItemList{{idx}}_delFlag" name="sysCodeRuleItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="sysCodeRuleItemList{{idx}}_seq" name="sysCodeRuleItemList[{{idx}}].seq" type="text" value="{{row.seq}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<select id="sysCodeRuleItemList{{idx}}_codeType" name="sysCodeRuleItemList[{{idx}}].codeType" data-value="{{row.codeType}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('sys_ruleCodeType')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="sysCodeRuleItemList{{idx}}_pattern" name="sysCodeRuleItemList[{{idx}}].pattern" type="text" value="{{row.pattern}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="sysCodeRuleItemList{{idx}}_digitalLength" name="sysCodeRuleItemList[{{idx}}].digitalLength" type="text" value="{{row.digitalLength}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<select id="sysCodeRuleItemList{{idx}}_sequence" name="sysCodeRuleItemList[{{idx}}].sequence.id" data-value="{{row.sequence.id}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${sequenceList}" var="seq">
										<option value="${seq.id}">${seq.name}</option>
									</c:forEach>
								</select>
							</td>
							<shiro:hasPermission name="sys:sysCodeRule:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sysCodeRuleItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sysCodeRuleItemRowIdx = 0, sysCodeRuleItemTpl = $("#sysCodeRuleItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sysCodeRule.sysCodeRuleItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#sysCodeRuleItemList', sysCodeRuleItemRowIdx, sysCodeRuleItemTpl, data[i]);
								sysCodeRuleItemRowIdx = sysCodeRuleItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:sysCodeRule:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>