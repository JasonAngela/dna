<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电泳分型板管理</title>
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
		<li><a href="${ctx}/dna/dnaBoard/">电泳分型板列表</a></li>
		<li class="active"><a href="${ctx}/dna/dnaBoard/form?id=${dnaBoard.id}">电泳分型板<shiro:hasPermission name="dna:dnaBoard:edit">${not empty dnaBoard.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dna:dnaBoard:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
		
	<c:if test="${not empty  board}">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
			<td>&nbsp;</td>
			<c:forEach var="item" begin="1" end="12">
				<td align="center" style="text-align: center;">${item}</td>
			</c:forEach>
			</tr>
			</thead>
			<c:forEach items="#{board.dnaBoardJggList}" var="row" varStatus="kl">	
					<c:if test="${(row.lie-1)%12 eq 0}">
						<tr>
							<td>${row.hangLabel}</td>
					</c:if>
					<td>
						<input type="hidden" name="dnaBoardJggList[${kl.index}].id" value="${row.id}"/>
				 		<input id="dnaBoardJggList{{idx}}_specimenCode" name="dnaBoardJggList[${kl.index}].specimenCode" type="text" style="width: 80px;" value="${row.specimenCode}"/>
					</td>
					<c:if test="${(row.lie)%12 eq 0}">
						</tr>
					</c:if>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>