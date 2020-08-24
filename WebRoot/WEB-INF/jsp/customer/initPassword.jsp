<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>初始化密码</title>
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
    <div class="message">修改初始密码</div>
    <div id="darkbannerwrap"></div>
    <form method="post" class="layui-form" action="${path}/customerUser/doInit">
        <input id = "password" name="password" lay-verify="required" placeholder="输入新密码" type="password"  class="layui-input">
        <hr class="hr15">
        <input id = "confirmPassword" name="confirmPassword" lay-verify="required" placeholder="确认密码" type="password" class="layui-input">
        <hr class="hr15">
        <%-- <div class="layui-row  layui-col-space10" style="height: 40px;">
            <div class="layui-col-md6">
                <!-- <div id="v_container" style="height: 50px;"></div> -->
                <img class = "about-idCode" style="height: 50px;"src="${path}/customerUser/getCode" onclick="refresh(this)" />
            </div>
            <div class="layui-col-md6">
                <input id= "input_code" name="code" style="height: 50px;" lay-verify="required" placeholder="验证码"
                       type="verification" class="layui-input">
            </div>
        </div> --%>
        <hr class="hr15">
        <!-- <input type="checkbox" name="remember" lay-skin="primary" title="记住密码"> -->
        <hr class="hr15">
        <input value="确认" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20">
    </form>
</div>
<script>
    $(function () {
    	//提示信息
    	layui.use('layer', function(){
    		  var layer = layui.layer;
    		  var mes = "${mes}";
    		  if(mes){
    			  layer.msg(mes,{offset:'t',time:10000,icon: 7,area: '400px'});
    		  }
    	});
        /* // 初始化验证码插件
        var verifyCode = new GVerify("v_container"); */
        layui.use('form', function () {
            var form = layui.form;
            form.on('submit(login)', function (data) {
                /* //   是否记住密码
                var remember = Boolean(data.field.remember);
                // 账户
                var username = data.field.username;
                // 密码
                var password = data.field.password; */
                /* // 验证码
                var verification = data.field.verification;
                // 判断是否记住密码,记住密码的话.就将账号密码存储到cookie
                if (remember) {
                    document.cookie = "userNmae=" + username + ";password=" + password + ";"
                }
                // 与图片验证码判断数值是否相同
                var verifyState = verifyCode.validate(verification)
                if (!verifyState) {
                    layer.msg('验证码有误')
                    return false;
                } */
                /* layer.msg("验证成功,即将跳转", function () {
                    location.href = 'index.html'
                }); */
                //return false;//阻止表单跳转
            });
        });
    })
</script>
</body>
</html>