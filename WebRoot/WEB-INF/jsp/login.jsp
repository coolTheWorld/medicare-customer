<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
		<title>淄博市医保进销存数据监管平台登录</title>
		<meta name="viewport" content="width=device-width,initial-scale=1.0,chrome=1"/>
		<link rel="shortcut icon" href="${path}/images/21.ico"/>
		<style>
			*{padding:0;margin:0;FONT-FAMILY: "微软雅黑", Arial, Helvetica, sans-serif;}
			body{
				width: 100%;
				height: 100%;
				background: url(images/login_bg.jpg) #4771a0 no-repeat;
				overflow:hidden;
			}
			.login-wrap{
				position: absolute;
				margin-top: -200px;
				margin-left: -350px;
				left: 50%;
				top: 50%;
			}
			.login-top{

				width:715px;height:70px;
				background:green;
				margin:0 auto;
				background:url(images/logo1.png);
				margin-bottom:0;
			}
			.login-content{
				width:100%;height:auto;
				margin:20px auto;
				border-radius:6px;
				margin-bottom:0;
				white-space:nowrap;
			}
			/*@media only screen and (max-width:1400px) {
				.login-top{
					margin-top:90px;
				}
			}*/
			.c-left{
				width:64%;height:300px;
				background:#fff;
				float:left;border-radius:6px 0 0 6px;
			}
			.c-right{
				width:35%;height:300px;
				background:#E6EFF8;
				float:left;border-radius:0 6px 6px 0;
				border-left:1px #ccc solid;
				text-indent:25px;
			}
			.c-right .cP1{
				white-space: nowrap;
				text-overflow:ellipsis;
				overflow:hidden;
				margin-top:40px;
				font-weight:bold;
				color:#555;
			}
			.c-right .pC{
				white-space: nowrap;
				text-overflow:ellipsis;
				overflow:hidden;
				font-size:12px;
				color:#555;
				margin-top:20px;
			}
			.c-right span{
				font-size:10px;
				text-indent:20px;;
			}
			.c-Lcontent{
				width:63%;height:240px;
				
				margin:32px auto;
			}
			.c-Lcontent span{
				color:#4771a0;
				font-size:17px;
				font-weight:bold;
			}
			.c-Lcontent P{
				margin-top:20px;
				font-size:13px;
				color:#555;
			}
			.iPt{
				margin-top:10px;
				padding:7px 4px;
				width:84%;
				border-radius:4px;
				border:none;
				border:1px #ccc solid;
			}
			.sub-btn{
				padding:6px 40px;
				background:#4771a0;
				margin-top:20px;
				border:none;
				color:#fff;
			}
			.logoBOTTOM{
				width:50%;height:40px;
				margin:0 auto;
				line-height: 40px;
				text-align:center;
			}
			.logoBOTTOM span{
				font-size:13px;
				color:#fff;
				font-weight:bold;
			}
		</style>
	</head>
	<body>
		<div class="login-wrap">
			<div class="login-top"></div>
			<div class="login-content">
				<div class="c-left">
					<div class="c-Lcontent">
						<span>用户登录</span>
						<form action="/index" method="post">
							<p>账号：</p>
							<input class="iPt"  type="text" name="userCode" value="${userCode }" />
							<p>密码：</p>
							<input class="iPt" type="password" name="passWord" /><br />
							<input id="loginBtn" class="sub-btn" type="submit" value="登录" />
							<span style="color:red;font-size:12px;margin-left:12%;font-weight:normal">${info}</span>
						</form>
					</div>
				</div>
				<div class="c-right">
					<br /><br />
					<p class="cP1">登录须知</p>
					<p class="pC">1. 建议用谷歌浏览器使用本系统。</p>
					<p class="pC">2. 如忘记密码请联系超级管理员。</p>					
				</div>
			</div><br />
			<div class="logoBOTTOM">
				<span>© 淄博亿维计算机科技有限公司</span>
			</div>
		</div>
	</body>
</html>
