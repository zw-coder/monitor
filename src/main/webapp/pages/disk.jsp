<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/monitor.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/themes/default/easyui.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/themes/icon.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	
	<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/flot/excanvas.min.js"></script><![endif]-->
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/flot/jquery.flot.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/flot/jquery.flot.pie.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/monitor.js"></script>
	
	
	<script type="text/javascript" >
		/*
		*/
	
		$(function(){
			
			var options = {
				series:{
					pie:{
						show:true
						/*
						,
						label:{
							show:true
						}
						*/
					}
				},
				legend:{
					/*
					*/
					labelFormatter:function(label,series){
						//return "<div style='font-size:8pt; text-align:center; padding:2px; color:white;'>" + label + "<br/>" + Math.round(series.percent) + "%</div>";
						return label + " "+ Math.round(series.percent) + "%";
					}
				}
				
			};

			
			/*
			var data = [{"label":"未使用","data":20},{"label":"已使用","data":80}];
			$.plot("#cpuShowId",data,options);
			$.plot("#cpuShowId2",data,options);
			*/
			
			var url = "${pageContext.request.contextPath}/ShowDiskData";
			$.post(url,function(data){
				for(var i = 0 ; i < data.length; i++){
					
					var diskData = data[i];
					//创建div
					var divId = "diskShowId" + i;
					
					$(".demo-container").append("<div id='"+divId+"'  class='demo-placeholder'>"+diskData.name+"</div>");
					//显示图标
					$.plot("#" + divId, diskData.data,options);
				}
			});
		});
	</script>
	
</head>
<body>
	<div class="demo-container">
		<%-- 
		<div id="cpuShowId" class="demo-placeholder">C盘</div>
		<div id="cpuShowId2" class="demo-placeholder">D盘</div>
		<div id="cpuShowId3" class="demo-placeholder"></div>
		<div id="cpuShowId4" class="demo-placeholder"></div>
		--%>
	</div>
</body>
</html>