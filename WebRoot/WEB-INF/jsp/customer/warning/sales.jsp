<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html class="x-admin-sm" lang="zh">
<head>
        <meta charset="UTF-8">
        <title>销售数据预警</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="${path}/evolution/css/font.css">
        <link rel="stylesheet" href="${path}/evolution/css/xadmin.css">
        <script src="${path}/evolution/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="${path}/evolution/js/xadmin.js"></script>
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <form class="layui-form layui-col-space5">
                                <div class="layui-inline layui-show-xs-block">
                                    <input class="layui-input"  autocomplete="off" placeholder="开始日" name="start" id="start">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                                </div>
								<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
                            </form>
                        </div>
                        <div class="layui-card-body layui-table-body layui-table-main">
                            <table class="layui-table layui-form">
                                <thead>
                                  <tr>
                                    <th>
                                      <input type="checkbox" lay-filter="checkall" name="" lay-skin="primary">
                                    </th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>性别</th>
                                    <th>手机</th>
                                    <th>地址</th>
                                    <th>状态</th>
                                    <th>操作</th></tr>
                                </thead>
                                <tbody>
                                  <tr>
                                    <td>
                                      <input type="checkbox" name="id" value="1"   lay-skin="primary"> 
                                    </td>
                                    <td>1</td>
                                    <td>小明</td>
                                    <td>男</td>
                                    <td>13000000000</td>
                                    <td>北京市 海淀区</td>
                                    <td class="td-status">
                                      <span class="layui-btn layui-btn-normal layui-btn-mini">已确认</span></td>
                                    <td class="td-manage">
                                      <a onclick="member_stop(this,'10001')" href="javascript:;"  title="查看">
                                        <i class="layui-icon">&#xe601;</i>
                                      </a>
                                      <a title="处理"  onclick="member_show(this,'10001')"href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                      </a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>
                                      <input type="checkbox" name="id"  value="2" lay-skin="primary">
                                    </td>
                                    <td>1</td>
                                    <td>小明</td>
                                    <td>男</td>
                                    <td>13000000000</td>
                                    <td>北京市 海淀区</td>
                                    <td class="td-status">
                                      <span class="layui-btn layui-btn-normal layui-btn-mini">已确认</span></td>
                                    <td class="td-manage">
                                      <a onclick="member_stop(this,'10001')" href="javascript:;"  title="查看">
                                        <i class="layui-icon">&#xe601;</i>
                                      </a>
                                      <a title="处理"  onclick="member_show(this,'10001')"href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                      </a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>
                                      <input type="checkbox" name="id" value="3"  lay-skin="primary">
                                    </td>
                                    <td>1</td>
                                    <td>小明</td>
                                    <td>男</td>
                                    <td>13000000000</td>
                                    <td>北京市 海淀区</td>
                                    <td class="td-status">
                                      <span class="layui-btn layui-btn-normal layui-btn-mini">已确认</span></td>
                                    <td class="td-manage">
                                      <a onclick="member_stop(this,'10001')" href="javascript:;"  title="查看">
                                        <i class="layui-icon">&#xe601;</i>
                                      </a>
                                      <a title="处理"  onclick="member_show(this,'10001')"href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                      </a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>
                                      <input type="checkbox" name="id" value="4"  lay-skin="primary">
                                    </td>
                                    <td>1</td>
                                    <td>小明</td>
                                    <td>男</td>
                                    <td>13000000000</td>
                                    <td>北京市 海淀区</td>
                                    <td class="td-status">
                                      <span class="layui-btn layui-btn-normal layui-btn-mini">已确认</span></td>
                                    <td class="td-manage">
                                      <a onclick="member_stop(this,'10001')" href="javascript:;"  title="查看">
                                        <i class="layui-icon">&#xe601;</i>
                                      </a>
                                      <a title="处理"  onclick="member_show(this,'10001')"href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                      </a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>
                                      <input type="checkbox" name="id" value="5"  lay-skin="primary">
                                    </td>
                                    <td>1</td>
                                    <td>小明</td>
                                    <td>男</td>
                                    <td>13000000000</td>
                                    <td>北京市 海淀区</td>
                                    <td class="td-status">
                                      <span class="layui-btn layui-btn-normal layui-btn-mini">已确认</span></td>
                                    <td class="td-manage">
                                      <a onclick="member_stop(this,'10001')" href="javascript:;"  title="查看">
                                        <i class="layui-icon">&#xe601;</i>
                                      </a>
                                      <a title="处理"  onclick="member_show(this,'10001')"href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                      </a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>
                                     <input type="checkbox" name="id" value="6" lay-skin="primary">
                                    </td>
                                    <td>1</td>
                                    <td>小明</td>
                                    <td>男</td>
                                    <td>13000000000</td>
                                    <td>北京市 海淀区</td>
                                    <td class="td-status">
                                      <span class="layui-btn layui-btn-normal layui-btn-mini">已确认</span></td>
                                    <td class="td-manage">
                                      <a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
                                        <i class="layui-icon">&#xe601;</i>
                                      </a>
                                      <a title="处理"  onclick="member_show(this,'10001')"href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                      </a>
                                    </td>
                                  </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="layui-card-body ">
                            <div class="page">
                                <div>
                                  <a class="prev" href="">&lt;&lt;</a>
                                  <a class="num" href="">1</a>
                                  <span class="current">2</span>
                                  <a class="num" href="">3</a>
                                  <a class="num" href="">489</a>
                                  <a class="next" href="">&gt;&gt;</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
    <script>
      layui.use(['laydate','form'], function(){
        var laydate = layui.laydate;
        var  form = layui.form;


        // 监听全选
        form.on('checkbox(checkall)', function(data){

          if(data.elem.checked){
            $('tbody input').prop('checked',true);
          }else{
            $('tbody input').prop('checked',false);
          }
          form.render('checkbox');
        }); 
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });


      });

       /*用户-启用*/
      function member_stop(obj,id){
          layer.confirm('确认是否启用？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }

      // 查看
      function member_show(obj,id){
          layer.confirm('确认是否处理吗？',function(index){
              //发异步删除数据
              
             
          });
      }



      function delAll (argument) {
        var ids = [];

        // 获取选中的id 
        $('tbody input').each(function(index, el) {
            if($(this).prop('checked')){
               ids.push($(this).val())
            }
        });
  
        layer.confirm('确认要删除吗？'+ids.toString(),function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
</html>