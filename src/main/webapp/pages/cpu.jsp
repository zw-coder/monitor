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
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/monitor.js"></script>
	
	
	<script type="text/javascript" >
		/*
			plot(选择器,数据,参数)
				1.选择器：jQuery选择器
				2.数据
					* 格式1：数组 [..,..,..] 。数据项格式：[x坐标数据,y坐标数据]
					* 格式2：数组[..,..,..] 。数据项格式：{"label":"标签名称","data":"格式1的数据"}
				3.参数 options，{} 一组对象数据
					xaxis:{}	x轴信息
					yaxis:{}	y轴信息
					series:{points:{}, lines:{} , bars:{}}	显示方式(点、线、饼)
					grid:{}		网格设置
		*/
	
		$(function(){
			/*
			var data = [{ label:'第一块CPU',
						data:[
			            [0,3.5],
			            [1,13.5],
			            [2,33.5],
			            [3,23.5],
			            [4,43.5],
			            [5,33.5],
			            [6,13.5],
			            [7,23.5],
			            [8,33.5]]
						}
			            ];
			*/
			var options = {
				colors:[ "#cb4b4b", "#4da74d", "#9440ed","#edc240","#afd8f8"],
				xaxis:{
					show:false
				},
				yaxis:{
					max:100,
					min:0,
					tickDecimals:1,
					ticks:5
						
				},
				 grid: {
	                    show: true
				 }
			};
			var url = "/ShowCPUData";
			
			showData(true);
			function showData(flag){
				$.post(url,function(data){
					for(var i = 0 ; i < data.length; i++){
						//创建div
						var divId = "cpuShowId" + i;
						if(flag){
							$(".demo-container").append("<div id='"+divId+"'  class='demo-placeholder'></div>");
						}
						//显示图标
						var cpuData = data[i];
						$.plot("#" + divId,  [cpuData]  ,options);
					}
				});
			}
			setInterval(showData, 1000,false);
			
			/*
			data = {"data":[[1,"4.6"],[2,"4.6"],[3,"3.2"],[4,"6.2"],[5,"9.4"],[6,"1.5"],[7,"7.9"],[8,"7.8"],[9,"6.1"],[10,"21.8"]],"label":"第1块CPU"};
			
			$.plot("#cpuShowId",  [data]  ,{
				xaxis:{
					show:false
				},
				yaxis:{
					max:100,
					min:0,
					tickDecimals:1,
					ticks:10
						
				}
			});
			$.plot("#cpuShowId2",  data  ,{
				xaxis:{
					show:false
				},
				yaxis:{
					max:100,
					min:0,
					tickDecimals:1,
					ticks:10
						
				}
			});
			$.plot("#cpuShowId3",  data  ,{
				xaxis:{
					show:false
				},
				yaxis:{
					max:100,
					min:0,
					tickDecimals:1,
					ticks:10
						
				}
			});
			$.plot("#cpuShowId4",  data ,{
				xaxis:{
					show:false
				},
				yaxis:{
					max:100,
					min:0,
					tickDecimals:1,
					ticks:10
						
				}
			});
			*/

		});
	</script>
	
</head>
<body>
	<div class="demo-container">
		<%-- 
		<div id="cpuShowId" class="demo-placeholder"></div>
		<div id="cpuShowId2" class="demo-placeholder"></div>
		<div id="cpuShowId3" class="demo-placeholder"></div>
		<div id="cpuShowId4" class="demo-placeholder"></div>
		--%>
	</div>
</body>
</html>