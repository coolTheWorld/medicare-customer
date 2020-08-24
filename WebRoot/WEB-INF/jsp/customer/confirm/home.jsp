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
              <h1>数据确认</h1>
            </div>
            <div class="layui-card-body help-p" >
              <h2>1、每天的记录只能确认一次，请谨慎提交确认记录</h2>
              <!-- <p >文字</p> -->
              <br/>
              <h2>2、确认上传的数据无误</h2>
              <!-- <p >文字</p> -->
              <br/>
              <h2>3、在虚线框中选择对应的记录(例如入库记录、出库记录、库存记录、销售记录)</h2>
              <!-- <p>系统处理上传数据并非实时显示，需要一定的处理时间，请稍后再试。</p> -->
              <br/>
              <h2>4、点击数据确认按钮</h2>
              <br/>
              <p>若有任何问题，请查看问题反馈或致电亿维客服：18253329695</p>
              <br/>
              <form class="layui-form layui-form-pane" action = "/dataConfirm/confirm">
              	<div id = "div-border" class="layui-col-md12 div-center">
              		<input type="checkbox" value = "1" name="recordWarehouse" title="入库记录" lay-skin="primary" />
					<input type="checkbox" value = "1" name="recordDelivery" title="出库记录" lay-skin="primary" /> 
					<input type="checkbox" value = "1" name="recordSales" title="销售记录" lay-skin="primary" />
					<input type="checkbox" value = "1" name="recordItemstock" title="库存记录" lay-skin="primary" />
					<c:if test="${customer.cusFlag eq '201' or customer.cusFlag eq '301'}">
						<input type="checkbox" value = "1" name="recordPrescribe" title="处方记录" lay-skin="primary" />
					</c:if>
					<c:if test="${customer.cusFlag eq '201' or customer.cusFlag eq '301'}">
						<input type="checkbox" value = "1" name="recordClinicrecords" title="门诊记录" lay-skin="primary" />
					</c:if>
					<c:if test="${customer.cusFlag eq '301'}">
						<input type="checkbox" value = "1" name="recordHospitalized" title="住院记录" lay-skin="primary" />
					</c:if>
					<c:if test="${customer.cusFlag eq '301'}">
						<input type="checkbox" value = "1" name="recordDischarged" title="出院记录" lay-skin="primary" />
              		</c:if>
              	</div>
              	<div class="layui-col-md12 item-magrin div-center">
                  <input type = "submit" id = "btn-submit" class="layui-btn" lay-submit lay-filter="confirm" value = "数据确认"/>
                </div>	 
              </form>
              <div class = "layui-col-md12 item-magrin">
              </div>
              <div class = "layui-col-md12">
              		<h3>确认结果</h3>
              </div>
              <!-- 表格 -->
              <div class = "layui-col-md12 div-center">
				<table class="layui-table"
                                   lay-data="{url:'/dataConfirm/findByCustomer', id:'confirmData'}"
                                   lay-filter="confirmData">
                     <thead>
                         <tr>
                            <th lay-data="{field:'recordWarehouse', minWidth:80}">入库记录</th>
                            <th lay-data="{field:'recordDelivery', minWidth:80}">出库记录</th>
                            <th lay-data="{field:'recordSales', minWidth:80}">销售记录</th>
                            <th lay-data="{field:'recordItemstock', minWidth:80}">库存记录</th>
                            <c:if test="${customer.cusFlag eq '201' or customer.cusFlag eq '301'}">
                            	<th lay-data="{field:'recordPrescribe', minWidth:80}">处方记录</th>
                            </c:if>
                            <c:if test="${customer.cusFlag eq '201' or customer.cusFlag eq '301'}">
                            <th lay-data="{field:'recordClinicrecords', minWidth:80}">门诊记录</th>
                            </c:if>
                            <c:if test="${customer.cusFlag eq '301'}">
                            <th lay-data="{field:'recordHospitalized', minWidth:80}">住院记录</th>
                            </c:if>
                            <c:if test="${customer.cusFlag eq '301'}">
                            <th lay-data="{field:'recordDischarged', minWidth:80}">出院记录</th>
                         	</c:if>
                         </tr>
                     </thead>
               	</table>
              </div>
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
	<script type="text/javascript">
	
		$(function(){
	    	$('.contont').css('height',window.innerHeight/10*8)
	    	layui.use(['form','layer','table'], function(){ 
				var form = layui.form;
				var layer = layui.layer;
				var table = layui.table;
	    		form.on('submit(confirm)', function(data){
	    		var data = {
	    			"recordWarehouse":data.field.recordWarehouse,
	    			"recordDelivery":data.field.recordDelivery,
	    			"recordSales":data.field.recordSales,
	    			"recordClinicrecords":data.field.recordClinicrecords,
	    			"recordPrescribe":data.field.recordPrescribe,
	    			"recordHospitalized":data.field.recordHospitalized,
	    			"recordDischarged":data.field.recordDischarged,
	    			"recordItemstock":data.field.recordItemstock
	    		};
	    		$.ajax({
	    			type:"POST",
	    			url:"/dataConfirm/confirm",
	    			contentType:'application/json;charset=UTF-8',
	    			data:JSON.stringify(data),
	    			dataType:"json",
	    			success:function(data){
	    				if(data.flag == 1){
	    					layer.msg(data.mes,{offset:'t',icon: 1,time:7000,area: '200px'});
	    					table.reload('confirmData', {
	    						  url: '/dataConfirm/findByCustomer'
	    					});
	    				}
	    				else if(data.flag == 0){
	    					layer.msg(data.mes,{offset:'t',icon: 7,time:7000,area: '250px'});
	    				}
	    			},
	    			error:function(data){
	    				layer.msg("数据确认失败，请联系亿维客服",{offset:'t',icon: 2,time:6000,area: '250px'});
	    			}
	    		}) 
		    		return false; //阻止表单跳转
	   		 	});
			});
	  	})	
	  	
	</script>
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
        	#div-border{
        		border:1px dashed #000;
        	}
        	.div-center{
        		text-align:center;
        	}
  </style>
</body>

</html>