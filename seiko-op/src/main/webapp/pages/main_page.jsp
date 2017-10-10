<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" > </meta>
	<!--head-->
	<%@ include file="layout/head.jsp" %>
</head>
<body class="easyui-layout">
<!-- begin of header -->
<div class="header" data-options="region:'north',border:false,split:true">
	<div class="header-left">
		<h1>EasyUI Web Admin</h1>
	</div>
	<div class="header-right">
		<p><strong class="easyui-tooltip" title="2条未读消息">admin</strong>，欢迎您！</p>
		<p><a href="#">网站首页</a>|<a href="#">支持论坛</a>|<a href="#">帮助中心</a>|<a href="#">安全退出</a></p>
	</div>
</div>
<!-- end of header -->
<!-- begin of sidebar -->
<div class="sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'">
	<div class="easyui-accordion" data-options="border:false,fit:true">
		<%@ include file="menu.jsp" %>
	</div>
</div>
<!-- end of sidebar -->
<!-- begin of main -->
<div class="main" data-options="region:'center'">
	<div id="tabs" class="easyui-tabs" data-options="border:false,fit:true">
		<%--<div title="首页" data-options="href:'temp/layout-1.html',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>--%>
	</div>
</div>
<!-- end of main -->
<!-- begin of footer -->
<div class="footer" data-options="region:'south',border:true,split:true">
	&copy; 2013 All Rights Reserved
</div>
<!-- end of footer -->
<script type="text/javascript" src="<c:url value='/statics/js/main.js'/>"></script>
</body>
</html>

