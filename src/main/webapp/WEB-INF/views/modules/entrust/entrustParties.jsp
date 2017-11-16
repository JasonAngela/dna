<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>鉴定资格</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
	function big(picname){
     	var div=document.getElementById("big");
     	var img=document.getElementById("bigimg");
     	img.src=picname;
     	div.style.display="block";
     }
     function close2(){
   		var div=document.getElementById("big");
   		var img=document.getElementById("bigimg");
   		img.src="";
   		div.style.display="none";
   	}
     function printEntrust(){
			$("#lover").hide();
			$("#lover1").hide();
			$("#a").show();
			window.print();
			$("#lover").show();
			$("#lover1").show();
			$("#a").hide();
			
		}
	  
     </script>
	
</head>
<body>


<div align="center" id="a">
 	<c:forEach  items="${pic}" var="pics">
		<img alt="" src="${pics}"  onclick="big(this.src)" >
		<br>
			<br>
				<br>
					<br>
						<br>
							<br>
	</c:forEach>
	<div id="big" style="display:none; width: 800; height: 600; position:fixed; top:10%; left:27%; z-index:5;">
   	<img src="" width="800" height="600" id="bigimg" onclick="close2()"></div>
   	
</div>

<div id="lover" class="form-actions" align="center">
	<input class="btn" type="button" value="去打印" onclick="printEntrust()"/>
</div>
</body>
</html>