<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/themes/default/easyui.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/themes/icon.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/monitor.js"></script>
<title>远程服务器性能监控系统</title>
</head>
<body>   
   <div class="easyui-layout" data-options="fit:true">
   		<div id = "title" data-options="region:'north'" style="height:100px;text-align: center">
   			<h1>远程服务器性能监控系统</h1>
			欢迎 ${User.username}<a  onclick="out()">退出</a>
   		</div>
   		<div data-options="region:'west',title:'远程监控'" style="width:200px">
   			<div class="easyui-accordion" data-options="fit:true">
   				<div title="基本性能监控" data-options="iconCls:'icon-large-chart'">
   					<a onclick="addTag(this,'${pageContext.request.contextPath}/pages/cpu.jsp')" class="easyui-linkbutton" data-options='plain:true' style="width:100%">CPU运行监控</a>
   					<a onclick="addTag(this,'${pageContext.request.contextPath}/pages/disk.jsp')" class="easyui-linkbutton" data-options='plain:true' style="width:100%">磁盘使用监控</a>
   				</div>
   				<div title="文件系统信息监控" data-options="iconCls:'icon-search'" >
   					<ul id="dirTreeId" class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/ShowDiskDir',lines:true"></ul>
   				</div>
   				<div title="远程操作监控" data-options="iconCls:'icon-large-clipart'">
   					<a onclick="addTag(this,'${pageContext.request.contextPath}/RemoteDestop')" class="easyui-linkbutton" data-options='plain:true' style="width:100%">远程桌面监控</a>
   				</div>
   			</div>
   		</div>
   		<div data-options="region:'center'" >
   			<div class="easyui-tabs" id="centerTabId" data-options="fit:true" >
   				<%-- 
	   			<div title="测试">
	   		
	   				<iframe src="http://www.baidu.com" style="width: 100%;height:100%" frameborder="0" scrolling="auto"></iframe>
	   			</div>
   				--%>
   			</div>
   		</div>
   		<div data-options="region:'south'" style="height:100px;text-align: center;font-size: 20px;padding-top:10px;">
   			东莞理工学院<br/>
   			www.dgut.edu.cn<br/>
   		</div>
   		<%-- 
   		<div data-options="region:'east',title:'东标题'" style="width:200px"></div>
   		--%>
   </div>
</body> 
</html>