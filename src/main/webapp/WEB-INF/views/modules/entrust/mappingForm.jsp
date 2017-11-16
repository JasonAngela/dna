<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图谱管理</title>
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
	</script>
</head>
<body>
	 <c:if test="${not empty  pic}">
	       <c:forEach  items="${pic}" var="pics" >
				  <object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" width="100%" height="100%" border="0"> 
			      <param name="_Version" value="65539"> 
			      <param name="_ExtentX" value="20108"> 
			      <param name="_ExtentY" value="10866"> 
			      <param name="_StockProps" value="0"> 
			      <param name="SRC" value="testing_pdf.pdf"> 
				   <embed id="pdffile" width="100%" height="680" src="${pics}" type="application/pdf" >  
				  </object> 
		           <br/><br/><br/>
		 </c:forEach>
	 </c:if>
</body>
</html>