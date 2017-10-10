<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<title>云采购后台管理系统</title>
	<%--<%@ include file="hpurchase/layout/head.jsp" %>--%>
</head>
<%
	String path = request.getContextPath();
%>
<script language="JavaScript">
	var basePath = '<%=path%>';
</script>

<div class="home">
	<div class="logo">
		<a href="#"><img src="" /></a>
		<span>运营账号登录</span>
	</div>
	<div style=" clear:both;"></div>
</div>
<div class="banner">
	<div class="login">
		<div class="login-tab">
			<div class="tab">
				<form id="loginForm">
					<%--<#if timeout?exists && timeout=='yes'>
						<span class="info">您尚未登录或已超时，请重新登录。</span>
					</#if>--%>
					<%--<input type="hidden" id="referer" value="${referer!''}" />--%>
					<div class="tab-one">
						<span>登录名：<input type="text" class="text" id="userName" name="userName" class="input_text easyui-validatebox" data-options="required:true"/></span>
					</div>
					<div class="tab-one">
						<span>登录密码：<input type="password" name="password" class="text" id="password" class="easyui-validatebox input_text" data-options="validType:['string','length[5,20]']"/></span>
					</div>
<%--					<div>
						<span>验证码：</span>
						<div class="m_account" id="verification_num">
							<input type="text" tabindex="3" name="validcode"  size="8"  class="m_small" id="validcode" />
							<img src="${path}/validcode?vtype=userLogin" alt="会员登录验证码" class="code_img">
							<div class="m_forget">
								<a href="javascript:;">看不清楚换个图片</a>
							</div>
						</div>
					</div>--%>
					<div class="tab-one">
						<span>验证码：</span>
						<span><input type="input" id="validcode" name="validcode" class="text1" size="8" style="width: 162px;"/></span>
						<div class="tab-img">
							<img src="<%=path%>/hpurchase/validcode?vtype=userLogin" alt="会员登录验证码" class="code_img">
						</div>
					</div>

					<div class="tab-button">
						<input type="button" name="login_btn1" id="login_btn1" value="登录" class="loginbtnfocus btn" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="text_bg"><img src="" /></div>
	<div class="img_bg"><img src="" /> </div>
</div>
<div class="last">
	<p> </p>
</div>