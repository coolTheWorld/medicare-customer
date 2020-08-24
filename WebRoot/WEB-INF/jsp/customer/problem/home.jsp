<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html class="x-admin-sm" lang="zh">
<head>
  <meta charset="UTF-8">
  <title>定点进销存数据查询系统</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport"
    content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <link rel="stylesheet" href="${path}/evolution/css/font.css">
  <link rel="stylesheet" href="${path}/evolution/css/xadmin.css">
  <script src="${path}/evolution/lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="${path}/evolution/js/xadmin.js"></script>
  <script type="text/javascript" src="${path}/evolution/lib/layui/lay/modules/form.js"></script>
  <style>
            .item-magrin {
              margin-top: 10px;
              margin-bottom: 10px;
              
            }
            .help-p{
              
            }
            .help-p > p{
              color: #757575;
              font-size: 14px;
            }
            .contont{
                padding: 40px;
            }
        
          </style>
  <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="${path}/evolution/js/html5.min.js"> </script>
        <script src="${path}/evolution/js/respond.min.js"></script>
</head>

<body>
  <div class="layui-fluid">


    <div class="layui-row layui-col-space15">
      <!-- 左侧表单组件 --start -->
      <div class="layui-col-md3"></div>
      <div class="layui-col-md6">
        <div class="layui-card contont"  >
            <div class="layui-card-header">
              <h1>常见问题</h1></div>
            <div class="layui-card-body help-p" >
              <h2>1、提示进销存数据未上传？</h2>
              <p >请检查进销存数据采集系统是否正常开启，并检查医保网络是否通畅。
              </p>
              <br/>
              <h2>2、查询不到今天的进销存数据？</h2>
              <p>系统处理上传数据并非实时显示，需要一定的处理时间，请稍后再试。</p>
              <br/>
              <h2>3、不知如何上传进销存数据？</h2>
              <p>请致电亿维客服：18253329695</p>
              <br/>
              <h2>其他问题？</h2>
              <form class="layui-form layui-form-pane">
                <div class="item-magrin" style="width: 50%;">
                  <!-- <input type="text" name="title" required lay-verify="required" placeholder="请输入描述标题" autocomplete="off"
                    class="layui-input"> -->
                    <select name="proType" lay-verify="required">
                  		<option value="0" disabled selected>选择问题类型</option>
                  		<option value="001">功能建议</option>
                  		<option value="002">界面建议</option>
                  		<option value="003">数据问题</option>
                  		<option value="004">新的需求</option>
                  		<option value="005">单据问题</option>
                	</select>
                </div>
                <div class="item-magrin"  style="width: 60%;">
                  <textarea name="proContent" required lay-verify="required" placeholder="请输入具体内容"
                    class="layui-textarea"></textarea>
                </div>
                <div class="item-magrin">
                  <button type="button" class="layui-btn" id="btn-upload">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                  </button>
                </div>
                <div class="item-magrin">
                	<div class="layui-row  layui-col-space10" style="height: 40px;">
            			<div class="layui-col-md3">
                			<input id= "input_code" name="code" style="height: 50px;" lay-verify="required" placeholder="验证码"
                       		type="verification" class="layui-input">
            			</div>
            			<div class="layui-col-md3">
                			<img class = "about-idCode" style="height: 50px;"src="${path}/customerUser/getCode" onclick="refresh(this)" />
            			</div>
            			<div class="layui-col-md6">
                			<div style="height: 50px;"></div>
            			</div>
        			</div>
                </div>
                <div class="item-magrin">
                  <button id = "btn-submit" class="layui-btn"> 提交</button>
                </div>
              </form>
            </div>
          </div>
      </div>
      <div class="layui-col-md3"></div>
      <!-- 左侧表单组件 --end -->
      <!-- 右侧公告信息 -->
      <div class="layui-col-md4">
      </div>
    </div>
  </div>
  <script src="${path}/evolution/js/jquery.min.js"></script>
  <script>
	//点击刷新验证码
	function refresh(obj) {
		var i = Math.random();
		obj.src = "${path}/customerUser/getCode?id = " + i//img标签每次获得不同的ID属性来使每次url的get参数不一致,来达到刷新页面的目的
	}
    layui.use('upload', function(){
      var upload = layui.upload;
       
      //执行实例
      var uploadInst = upload.render({
        elem: '#btn-upload' //绑定元素
        ,url: '/upload/' //上传接口
        ,auto: false //选择文件后不自动上传
        ,bindAction: '#btn-submit' //指向一个按钮触发上传
        ,accept: 'images' //指定允许上传时校验的文件类型
        ,acceptMime: 'image/*' //规定打开文件选择框时，筛选出的文件类型
        ,size: '512' //设置文件最大可允许上传的大小，单位 KB
        ,data: {
        	code:function(){
        		return $("#input_code").val();
        	}
        }
        ,done: function(res){
          //上传完毕回调  </div>
        }
        ,error: function(){
          //请求异常回调
        }
      });
    });
    
  $(function(){
    $('.contont').css('height',window.innerHeight/10*8)
  })
    </script>
</body>
</html>