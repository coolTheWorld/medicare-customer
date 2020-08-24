<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html class="x-admin-sm" lang="zh">
<head>
    <meta charset="UTF-8">
    <title>定点登录</title>
    <c:set value="${pageContext.request.contextPath}"
		var="path" scope="page" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="${path}/evolution/css/font.css">
    <link rel="stylesheet" href="${path}/evolution/css/login.css">
    <link rel="stylesheet" href="${path}/evolution/css/xadmin.css">
    <script type="text/javascript" src="${path}/evolution/js/jquery.min.js"></script>
    <script src="${path}/evolution/lib/layui/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${path}/evolution/js/html5.min.js"></script>
    <script src="${path}/evolution/js/respond.min.js"></script>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">定点进销存数据平台</div>
    <div id="darkbannerwrap"></div>
    <form method="post" class="layui-form" action="${path}/customerUser/doLogin">
        <input id = "userAccount" name="userAccount" placeholder="定点编码" type="text" lay-verify="required" class="layui-input" value = "${userAccount}">
        <hr class="hr15">
        <input name="userPassword" lay-verify="required" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <div class="layui-row  layui-col-space10" style="height: 40px;">
            <div class="layui-col-md6">
                <!-- <div id="v_container" style="height: 50px;"></div> -->
                <img class = "about-idCode" style="height: 50px;"src="${path}/customerUser/getCode" onclick="refresh(this)" />
            </div>
            <div class="layui-col-md6">
                <input id= "input_code" name="code" style="height: 50px;" lay-verify="required" placeholder="验证码"
                       type="verification" class="layui-input">
            </div>
        </div>
        <hr class="hr15">
        <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20">
    </form>
</div>
<script src="${path}/evolution/js/gVerify.js"></script>
<script>
	//防止登录页面嵌入iframe框架
	if (top.location != self.location){
		top.location=self.location;
	}
	//点击刷新验证码
	function refresh(obj) {
		var i = Math.random();
		obj.src = "${path}/customerUser/getCode?id = " + i//img标签每次获得不同的ID属性来使每次url的get参数不一致,来达到刷新页面的目的
	}
	
    $(function () {
    	//提示信息
    	layui.use('layer', function(){
    		  var layer = layui.layer;
    		  var mes = "${mes}";
    		  if(mes){
    			  layer.msg(mes,{offset:'t',icon: 2,time:7000,area: '150px'});
    		  }
    		  var relogin = "${relogin}";
    		  if(relogin){
    			  layer.msg(relogin,{offset:'t',icon: 1,time:7000,area: '220px'});
    		  }
    	});
    	//初始化form
    	layui.use('form', function(){
    		  var form = layui.form;
    	});
    })
</script>
<!-- 底部结束 -->
</body>
</html>