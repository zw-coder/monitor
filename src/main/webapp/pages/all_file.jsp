<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
		var currentId;
		$(function(){
			//右键
			var show = false;
			$(document).bind("contextmenu",function(event){
				event.preventDefault();
				if(show){
					$("#menuId").menu("show",{left:event.pageX,top:event.pageY});
				}
			});
			//控制只能
			$(".fileStyle:not(:last)").hover(function(){
				show = true;
				currentId = $(this).attr("data");
			},function(){
				show = false;
			});
			
		});
		
		function download(){

			var id = encodeURI(currentId);
			id= id.replace("+","%2B");
			var url = "${pageContext.request.contextPath}/FileDownload?id=" + id;
			var newWin = window.open(url);
			//if(! document.all){
			//	newWin.document.execCommand("SaveAs");
			//}
			setTimeout("newWin.window.close();newWin.close();", 200,newWin);
			
		}
		
		function showAll(obj){
			//展开tree
			var dirPath = $(obj).attr("data");
			var $treeObj = parent.$("#dirTreeId");
			var node = $treeObj.tree('find',dirPath);
			$treeObj.tree("expand",node.target);
			//启用新tag
			parent.openTag(node);
		}
		
		function openDialog(){
			$("#uploadDialogId").dialog("open");
		}
		function closeDialog(){
			$("#uploadDialogId").dialog("close");
		}
	</script>
	
</head>
<body>
	<div style="width: 100%;height:100%;margin: auto">
		<c:forEach items="${allFileBean}" var="fileBean">
			<c:if test="${fileBean.file}">
				<div class="fileStyle" data="${fileBean.id}">
					<img src="${pageContext.request.contextPath}/image/FileType/Middle/${fileBean.icoName}" style="width: 120px;height: 100px" />
					<span>${fileBean.text}</span>
				</div>
			</c:if>
			<c:if test="${not fileBean.file}">
				<div class="fileStyle" onclick="showAll(this)" data="${fileBean.id}">
					<img src="${pageContext.request.contextPath}/image/FileType/Middle/${fileBean.icoName}" style="width: 120px;height: 100px" />
					<span>${fileBean.text}</span>
				</div>
			</c:if>
		</c:forEach>
		<%--最有一个为上传内容 --%>
		<div class="fileStyle"  onclick="openDialog()">
			<img src="${pageContext.request.contextPath}/image/FileType/Middle/upload.png" style="width: 120px;height: 100px" />
			<span>${treeBean.text}</span>
		</div>
		
		<div id="menuId" class="easyui-menu">
			<div onclick="download()">打包下载</div>
			<div>删除</div>
		</div>
		
		
		<div id="uploadDialogId" class="easyui-dialog" style="width: 400px;height: 300px;" data-options="modal:true,closed:true,buttons:[{text:'上传',iconCls:'icon-ok',handler:function(){document.forms[0].submit()}},{text:'关闭',handler:closeDialog}]">
			<form action="${pageContext.request.contextPath}/FileUpload" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${id}" />
				<table>
					<tr>
						<td>选择文件</td>
						<td><input type="file" name="resource" /></td>
					</tr>
					<%-- 
					<tr>
						<td></td>
						<td><input type="submit" value="上传" /></td>
					</tr>
					--%>
				</table>
			</form>
		</div>
	</div>
</body>
</html>