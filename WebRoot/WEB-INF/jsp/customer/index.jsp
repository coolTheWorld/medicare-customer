<%@ page language="java" import="java.util.*,yibao.yiwei.entity.system.CustomerUser" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html class="x-admin-sm" lang="zh">
<head>
    <meta charset="UTF-8">
    <title>定点数据上传预警平台</title>
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
    <!-- <link rel="stylesheet" href="./css/theme5.css"> -->

    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!-- [if lt IE 9]> -->
    <!-- <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script> -->
    <script type="text/javascript" src="${path}/evolution/js/jquery.min.js"></script>
    <script src="${path}/evolution/lib/layui/layui.js" charset="utf-8"></script>
    <script src="${path}/evolution/js/html5.min.js"></script>
    <script src="${path}/evolution/js/respond.min.js"></script>
    <!-- <![endif] -->

</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="${path}/customer/index">定点进销存数据查询系统</a></div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>

    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${requestScope.customer.cusName}</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <!-- <dd>
                    <a onclick="xadmin.open('个人信息','javascript:;')">个人信息</a>
                </dd> -->
                <dd>
                    <a onclick="modifyPassword()">修改密码</a>
                </dd>
                <dd>
                    <a href="${path}/customerUser/signOut">退出登录</a>
                </dd>
            </dl>
        </li>

    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="上传记录">&#xe74a;</i>
                    <cite>上传记录</cite>
                    <i class="iconfont nav_right">&#xe6a7;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('入库记录','${path}/record/toWareHouseItems')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>入库记录</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('出库记录','${path}/record/toDeliveryItems')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>出库记录</cite></a>
                    </li>
                    <%-- <li>
                        <a onclick="xadmin.add_tab('退货记录','${path}/record/toReturnItems')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>退货记录</cite></a>
                    </li> --%>
                    <li>
                        <a onclick="xadmin.add_tab('销售记录','${path}/record/toSalesItems')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>销售记录</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('库存记录','${path}/record/toItemStock')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>库存记录</cite></a>
                    </li>
                    <c:if test="${customer.cusFlag eq '201' or customer.cusFlag eq '301'}">
                    <li>
                        <a onclick="xadmin.add_tab('处方记录','${path}/record/toPrescribe')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>处方记录</cite></a>
                    </li>
                    </c:if>
                    <c:if test="${customer.cusFlag eq '201' or customer.cusFlag eq '301'}">
                    <li>
                        <a onclick="xadmin.add_tab('门诊记录','${path}/record/toClinicrecords')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>门诊记录</cite></a>
                    </li>
                    </c:if>
                    <c:if test="${customer.cusFlag eq '301'}">
                    <li>
                        <a onclick="xadmin.add_tab('住院记录','${path}/record/toHospitalized')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>住院记录</cite></a>
                    </li>
                    </c:if>
                    <c:if test="${customer.cusFlag eq '301'}">
                    <li>
                        <a onclick="xadmin.add_tab('出院记录','${path}/record/toDischarged')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>出院记录</cite></a>
                    </li>
                    </c:if>
                </ul>
            </li>
            <!-- 关闭数据预警 
            <li>
                <a onclick="xadmin.add_tab('数据预警首页','${path}/warning/home')">
                    <i class="iconfont left-nav-li" lay-tips="数据预警">&#xe6fb;</i>
                    <cite>数据预警</cite>
                    <i class="iconfont nav_right">&#xe6a7;</i>
                </a>
                <ul class="sub-menu">
                    <%-- <li>
                        <a onclick="xadmin.add_tab('数据预警首页','dataWarning.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>数据预警首页</cite></a>
                    </li> --%>
                    <li>
                        <a onclick="xadmin.add_tab('上传异常','${path}/warning/upload')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>上传异常</cite>
                        </a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('库存异常','${path}/warning/stock')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>库存异常</cite>
                        </a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('销售异常','${path}/warning/sales')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>销售异常</cite>
                        </a>
                    </li>
                </ul>
            </li>
            -->
            <li>
                <a onclick="xadmin.add_tab('数据确认','${path}/dataConfirm/home',true)">
                    <i class="iconfont left-nav-li">&#xe6a3;</i>
                    <cite>数据确认</cite>
                </a>
            </li>
            <li>
                <a onclick="xadmin.add_tab('问题反馈','${path}/problem/home',true)">
                    <i class="iconfont left-nav-li">&#xe6a3;</i>
                    <cite>问题反馈</cite>
                </a>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>首页
            </li>
        </ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd>
            </dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${path}/customer/home' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>
    </div>
</div>
<!-- <div class="page-content-bg"></div> -->
<div id = "div-index-footer" class="layui-footer layui-col-md12">
		<blockquote id = "blockquote-mid" class="layui-elem-quote layui-quote-nm">本系统由 淄博亿维数字科技有限公司 提供技术支持</blockquote>
</div>
<style id="theme_style">
#div-password{
    margin: 0px;
    min-height: 300px;
}
#blockquote-mid{
	text-align:center
}
#div-index-footer{
position: absolute;
right: 0;
bottom: 0px;
overflow: hidden;
z-index: 1;
}
</style>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<script src="${path}/evolution/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${path}/evolution/js/xadmin.js"></script>
<script>

if("${sessionScope.user.cusId}" == "-1"){
	layerShow();
}

//遮罩层
function layerShow() {
    layui.use('layer', function () {
        var layer = layui.layer;
        var shadow = layer.open({
            type: 1
            ,
            title: '<b>系统提示</b>'
            ,
            closeBtn: false
            ,
            area: '300px;'
            ,
            shade: 0.8
            ,
            id: 'CaptchaProblem' //设定一个id，防止重复弹出
            //, btn: ['我知道了']
            ,
            btnAlign: 'c'
            ,
            moveType: 1 //拖拽模式，0或者1
            ,
            
            content: '<div style="padding: 30px; line-height: 22px; background-color: #eee; font-weight: 300;"> <h3>未查询到当前定点（' + '${sessionScope.user.userAccount}' + '）的进销存数据。<br/><br/>详情请咨询：18253329695<br/><br/><div style = "text-align:center;"><a class="layui-btn layui-btn-sm" href="${path}/customerUser/signOut">注销</a></div></div>'

            ,
            success: function (layero) {

            }
        });
    });
}
	
	var index;
	<!--修改密码弹出层-->
	function modifyPassword(){
		layui.use('layer', function(){
			  var layer = layui.layer;
			  index = layer.open({
				  title: '修改密码'
				  ,type:1
				  ,content: $("#div-password")
				});
			});
		  $('#password').val(''); 
		  $('#oldPassword').val(''); 
		  $('#confirmPassword').val(''); 
	}
    // 是否开启刷新记忆tab功能
    var is_remember = false;
    $(function(){    	
    	//修改密码
        layui.use('form', function () {
            var form = layui.form;
            form.on('submit(modify)', function (data) {
            	//原始密码
            	var oldPassword = data.field.oldPassword;
                // 密码
                var password = data.field.password;
                // 确认密码
                var confirmPassword = data.field.confirmPassword;
                //请求修改密码接口
                $.ajax({
                	type:"POST",
                	url:"/customerUser/doModify",
                	data:{
                		oldPassword:oldPassword,
                		password:password,
                		confirmPassword:confirmPassword
                	},
                	dataType:"json",
                	success:function(data){
                		
                		if(data.flag == '1'){
                			//修改成功
                			layui.use('layer', function(){
                      		   var layer = layui.layer;
                      		   layer.msg(data.mes,{offset:'t',icon: 1,time:6000,area: '190px'});
                      		   layer.close(index);
                      		});
                		}else if(data.flag == '0'){
                			//失败
                			layui.use('layer', function(){
                      		   var layer = layui.layer;
                      		   layer.msg(data.mes,{offset:'t',icon: 7,time:6000,area: '190px'});
                      		  
                      		});
                		}else if(data.flag == '2'){
                			//失败
                			layui.use('layer', function(){
                      		   var layer = layui.layer;
                      		   layer.msg(data.mes,{offset:'t',icon: 7,time:6000,area: '290px'});
                      		});
                		}else if(data.flag == '3'){
                			//失败
                			layui.use('layer', function(){
                      		   var layer = layui.layer;
                      		   layer.msg(data.mes,{offset:'t',icon: 7,time:6000,area: '290px'});
                      		});
                			
                		}else if(data.flag == '4'){
                			//失败
                			layui.use('layer', function(){
                      		   var layer = layui.layer;
                      		   layer.msg(data.mes,{offset:'t',icon: 7,time:6000,area: '290px'});
                      		});
                		}else{
                			//失败
                			layui.use('layer', function(){
                      		   var layer = layui.layer;
                      		   layer.msg(data.mes,{offset:'t',icon: 7,time:6000,area: '190px'});
                      		  
                      		});
                		}
                	},
                	error:function(data){
                		//提示信息
                    	layui.use('layer', function(){
                    		  var layer = layui.layer;
                    		  layer.msg(data.mes,{offset:'t',icon: 2,time:6000,area: '190px'});
                    		  layer.close(index);
                    	});
                	}
                })
                return false;//阻止表单跳转
            });
        }); 
    });
    
</script>

</body>
<!-- 修改密码表单 -->
<div  id='div-password' class = "login">
	<form id = "form-password" method="post" class="layui-form" >
		<input id = "oldPassword" name="oldPassword" lay-verify="required" placeholder="输入原始密码" type="password"  class="layui-input">
        <hr class="hr15">
        <input id = "password" name="password" lay-verify="required" placeholder="输入新密码" type="password"  class="layui-input">
        <hr class="hr15">
        <input id = "confirmPassword" name="confirmPassword" lay-verify="required" placeholder="确认新密码" type="password" class="layui-input">
        <hr class="hr15">
        <hr class="hr15">
        <input value="确认" lay-submit lay-filter="modify" style="width:100%;" type="submit">
        <hr class="hr20">
	</form>
</div>
</html>