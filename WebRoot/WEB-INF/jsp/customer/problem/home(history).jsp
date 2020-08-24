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
      font-size: 12px;
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
      
      <div class="layui-col-md8">
        <div class="layui-card">
          <div class="layui-card-header">反馈信息</div>
          <div class="layui-card-body">
            <form class="layui-form layui-form-pane">
              <div class="item-magrin" style="width: 40%;">

                <select name="city" lay-verify="">
                  <option value="">选择问题类型</option>
                  <option value="010">功能建议</option>
                  <option value="021">界面建议</option>
                  <option value="0571">数据问题</option>
                  <option value="0571">新的需求</option>
                  <option value="单据问题">单据问题</option>
                </select>

              </div>
              <div class="item-magrin" style="width: 50%;">
                <input type="text" name="title" required lay-verify="required" placeholder="请输入描述标题" autocomplete="off"
                  class="layui-input">
              </div>
              <div class="item-magrin"  style="width: 60%;">
                <textarea name="" required lay-verify="required" placeholder="请输入具体内容"
                  class="layui-textarea"></textarea>
              </div>
              <div class="item-magrin">
                <button type="button" class="layui-btn" id="test1">
                  <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
              </div>
              
              <button class="layui-btn"> 提交</button>
            </form>
          </div>
        </div>
      </div>
      <!-- 左侧表单组件 --end -->
      <!-- 右侧公告信息 -->
      <div class="layui-col-md4">
        <div class="layui-card">
          <div class="layui-card-header">
            <h3>常见问题</h3></div>
          <div class="layui-card-body help-p" >
            <h3>如何联系运维人员</h3>
            <p >运维人员在线时间为:8:20 --- 12:00 1:30 --5:30 <br>
              微信:15069308173<br>
              qq:2645327082<br>
            </p>
            <h3>数据发生意外时</h3>
            <p>及时截图保存数据,停止对系统的操作,与运维人员联系,勿自己随意操作</p>
          </div>
        </div>
      </div>
     
    </div>

  </div>
  </div>
  <script>
    layui.use('upload', function(){
      var upload = layui.upload;
       
      //执行实例
      var uploadInst = upload.render({
        elem: '#test1' //绑定元素
        ,url: '/upload/' //上传接口
        ,done: function(res){
          //上传完毕回调
        }
        ,error: function(){
          //请求异常回调
        }
      });
    });
    </script>
</body>
</html>