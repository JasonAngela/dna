<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>dna日常记录管理</title>
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
		<li><a href="${ctx}/dna/dnaDaily/">dna日常记录列表</a></li>
		<li class="active"><a href="${ctx}/dna/dnaDaily/form?id=${dnaDaily.id}">dna日常记录<shiro:hasPermission name="dna:dnaDaily:edit">${not empty dnaDaily.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaDaily:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dnaDaily" action="${ctx}/dna/dnaDaily/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">dna日常明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>实验室</th>
								<th>温度</th>
								<th>相对湿度</th>
								<th>冰箱温度</th>
								<th>其它</th>
								<th>备注</th>
								<shiro:hasPermission name="dna:dnaDaily:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dnaDailyItemList">
						</tbody>
						<shiro:hasPermission name="dna:dnaDaily:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#dnaDailyItemList', dnaDailyItemRowIdx, dnaDailyItemTpl);dnaDailyItemRowIdx = dnaDailyItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dnaDailyItemTpl">//<!--
						<tr id="dnaDailyItemList{{idx}}">
							<td class="hide">
								<input id="dnaDailyItemList{{idx}}_id" name="dnaDailyItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dnaDailyItemList{{idx}}_delFlag" name="dnaDailyItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dnaDailyItemList{{idx}}_lab" name="dnaDailyItemList[{{idx}}].lab.id" type="hidden" value="{{row.lab.id}}" maxlength="255" class="input-small "/>{{row.lab.name}}
							</td>
							<td>
								<input id="dnaDailyItemList{{idx}}_temperature" name="dnaDailyItemList[{{idx}}].temperature" type="text" value="{{row.temperature}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaDailyItemList{{idx}}_relativeHumidity" name="dnaDailyItemList[{{idx}}].relativeHumidity" type="text" value="{{row.relativeHumidity}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaDailyItemList{{idx}}_refrigeratorTemperature" name="dnaDailyItemList[{{idx}}].refrigeratorTemperature" type="text" value="{{row.refrigeratorTemperature}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="dnaDailyItemList{{idx}}_other" name="dnaDailyItemList[{{idx}}].other" type="text" value="{{row.other}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="dnaDailyItemList{{idx}}_remarks" name="dnaDailyItemList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dnaDailyItemRowIdx = 0, dnaDailyItemTpl = $("#dnaDailyItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dnaDaily.dnaDailyItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#dnaDailyItemList', dnaDailyItemRowIdx, dnaDailyItemTpl, data[i]);
								dnaDailyItemRowIdx = dnaDailyItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="dna:dnaDaily:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>