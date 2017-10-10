<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="white" />
<meta content="white" name="apple-mobile-web-app-status-bar-style" />


<title>后台管理系统</title>


<%
	String path = request.getContextPath();
	request.setAttribute("path",path );

	ResourceBundle res = ResourceBundle.getBundle("application");
//	String deployDate = res.getString("deployDate");
//	request.setAttribute("deployDate", deployDate);

%>

<script language="JavaScript">
	var basePath = '<%=path%>';
</script>
<!--   css   -->
<link rel="stylesheet" type="text/css" href="<c:url value='/statics/css/style.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/statics/css/icon.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/statics/js/easyui/themes/default/easyui.css'/>" />
<!--   /css   -->

<!--   js   -->
<script type="text/javascript" src="<c:url value='/statics/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/statics/js/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/statics/js/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/statics/js/jquery.blockUI.js'/>"></script>
<script type="text/javascript" src="<c:url value='/statics/js/jquery.loading.js'/>"></script>
<script type="text/javascript" src="<c:url value='/statics/js/jquery.form.js'/>"></script>

<script type="text/javascript" src="<c:url value='/statics/js/base.js'/>"></script>
<!--   /js   -->


