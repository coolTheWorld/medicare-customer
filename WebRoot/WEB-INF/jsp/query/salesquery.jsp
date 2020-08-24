<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>销售统计</title>
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path }/css/ew-main.css" />
		<link rel="stylesheet" href="${path }/css/ew-home.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${path}/js/yiwei.js"></script>
		<script type="text/javascript">
			function getSaleslist(){  
			    var yybm = $("#yybm").searchbox("getValue");
				var begin_time = $("#begindt").datebox("getValue");  
				var end_time = $("#enddt").datebox("getValue");
	
			    if(yybm == ''){
			    	$.messager.alert("提示", "请输入要查询单位的医院编码！","warning");
			    	$('#yybm').next('span').find('input').focus();
			    	return;
			    }else{
			        var ahref = 'getAllSales?flag=1&dwcode=' + yybm; 
			    }
			    ahref = ahref + '&bgdt=' + begin_time + '&eddt=' + end_time; 
			    
			    $('#salesquery').datagrid({url:ahref,
			    	 onLoadSuccess:function(data){
			    		if(data.cus){
			    			$('#cusid').val(data.cus.cusId); 
			    			$('#cusname').text(data.cus.cusName);
			    			$('#cusflag').text(data.cus.cusFlag);
			    			$('#cuscontact').text(data.cus.cusContact);
			    			$('#cusphone').text(data.cus.cusPhone);
			    			$('#cusaddr').text(data.cus.cusAddr);
			    			$('#cusdareway').text(data.cus.cusDareway);
			    		}
				        if(data.total == 0){
				            var body = $(this).data().datagrid.dc.body2;
				            body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="16">没有查询到满足条件的数据</td></tr>');
				        }else{
				        	getDruganalyze();
				        }
				    }
			    });     
			}
	
			function exportStatistics(){
				var yybm = $("#yybm").searchbox("getValue");
				var begin_time = $("#begindt").datebox("getValue");  
				var end_time = $("#enddt").datebox("getValue");
	
			    if(yybm == ''){
			    	$.messager.alert("提示", "请输入要查询单位的医院编码！","warning");
			    	$('#yybm').next('span').find('input').focus();
			    	return;
			    }else{
			        var ahref = 'expStatistics?flag=1&dwcode=' + yybm; 
			    }
			    ahref = ahref + '&bgdt=' + begin_time + '&eddt=' + end_time; 
				
			    window.location.href = ahref;
			}
	
			function setDtPickerVal(){
				//设置时间  
				$("#begindt").datebox("setValue", begin_time);  
				$("#enddt").datebox("setValue", end_time);
			}
	
			function fmtDrugName(val, row, index) {
				var drugCode=row.drugcode;
				var drugName=row.drugname;
			    return '<a href="javascript:void(0);" aurl="toSaleslist?dc='+drugCode+'" onclick="showlist(this);" name="'+drugName+'">'+row.drugname+'</a>';
			}
	
			//添加新选项卡显示销售记录
			function showlist(obj){
				var ahref = $(obj).attr('aurl');
				var cusId=$('#cusid').val();
				var begin_time = $("#begindt").datebox("getValue");  
				var end_time = $("#enddt").datebox("getValue");
				ahref = ahref + '&cus='+cusId + '&bgdt=' + begin_time + '&eddt=' + end_time;
				var title = obj.name + ' - 销售记录';
				window.parent.addTab(title, ahref);//调用父类方法添加选项卡
			}
	
			function fmtDrugopt(val, row, index) {
				var drugCode=row.drugcode;
				var drugName=row.drugname;
			    return '<a href="javascript:void(0);" aurl="toStockanalyze?dc='+drugCode+'" onclick="showall(this);" name="'+drugName+'">库存分析</a>';
			}
	
			//添加新选项卡显示出入库综合信息
			function showall(obj){
				var ahref = $(obj).attr('aurl');
				var cusId=$('#cusid').val();
				//var cusName=$('#cusname').val();
				var begin_time = $("#begindt").datebox("getValue");  
				var end_time = $("#enddt").datebox("getValue");
				ahref = ahref + '&cus='+cusId + '&bgdt=' + begin_time + '&eddt=' + end_time;
				var title = obj.name + ' - 库存分析';
				window.parent.addTab(title, ahref);//调用父类方法添加选项卡
			}
	
			function fmtRMKLoading(val, row, index) {
				return "<img id='loading_"+row.drugcode+"' src='/images/loading.gif' alt='0' />";
			}
	
			function getDruganalyze() {
				var rows = $('#salesquery').getData();
				for(var i=0; i<rows.length; i++){
				    var row = rows[i]
				    
				}
			}

			//清空
			function clearForm() {
			    $("#yybm").textbox('setValue','')
			}
	
			$(function() {
				setDtPickerVal();
				$('#yybm').next('span').find('input').focus();
				$('#begindt2').datebox({
					onSelect: function(date){
						//	选中时间/起始时间
						var full = date.getFullYear();
						var month = date.getMonth()+1;
						var day = date.getDate();
						var minDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
						if(day <= 31){
							$('#enddt').datebox('setValue',time(0));  
						}
						function time(number){
						    var date=new Date(minDate);
						    var strDate=date.getFullYear()+"-";  
						    if(number==0){ 
							    strDate+=date.getMonth()+2+"-";  
							    strDate+=number;  
						    }
						    return strDate;  
						}  
					}
				});
			});
		</script>
	</head>

	<body>
	    <div class="easyui-layout" title="销售查询列表" data-options="collapsible:false,noheader:true,border:false,fit:true" >
	    	<div name="querybar" title="查询条件" data-options="region:'north',border:false" style="padding:5px; height:65px;">
				<div class="barbox1">
					<div style="float: left; width: 200px; line-height: 22px;">
						<input id="yybm" class="easyui-searchbox" style="width: 160px;" data-options="prompt:'请输入医院编码',"></input>
							<a href="javascript:" class="clear" onClick=clearForm();>
								<img style="margin:3px 5px;position: absolute" class="cl-xx-child" src="images/xx.png" />
							</a>
					</div>
				</div>
				<div class="top-time" style="background: none; float: left; margin-left: 10px;">
					<table>
						<tr>
							<td>开始日期:</td>
							<td><input id="begindt" class="easyui-datebox" data-options="sharedCalendar:'#cc',editable:false" /></td>
							<td>结束日期:</td>
							<td><input id="enddt" class="easyui-datebox"data-options="sharedCalendar:'#cc',editable:false" /></td>
						</tr>
					</table>

					<div id="cc" class="easyui-calendar"></div>
					<script type="text/javascript">
					var begin_time = '${begindt}';
					var end_time = '${enddt}';
					</script>
				</div>
				<div style="float: left; margin-left: 5px;">
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px" onclick="javascript:getSaleslist();">查询</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-xl'" style="width: 80px" onclick="javascript:exportStatistics();">导出</a>
				</div>
	    	</div>
	    	<div id="queryresult" title="查询结果" data-options="region:'center',noheader:false,border:false" style="height:300px;">
				<div class="easyui-layout" title="结果" data-options="noheader:true,border:false,fit:true" >
					<div id="cusinfo" data-options="collapsible:true,region:'north',border:false" style="padding:5px; height:128px;">
	                   <input type="hidden" id="cusid" value="0" />
	                   <table class="ew_table" style="width: 100%; ">
		                   <tbody>
		                       <tr><th colspan="4">定点信息</th></tr>
		                       <tr><th width="20%">单位名称</th><td width="30%" id="cusname"></td><th width="20%">单位性质</th><td width="30%" id="cusflag"></td></tr>
		                       <tr><th>联系人</th><td id="cuscontact"></td><th>联系电话</th><td id="cusphone"></td></tr>
		                       <tr><th>联系地址</th><td id="cusaddr"></td><th>医院编码</th><td id="cusdareway"></td></tr>
		                   </tbody>
	                   </table>
	                </div>  
					<div id="querydata" data-options="region:'center',noheader:true,border:false" >
						<!-- datagrid表格 -->
						<table id="salesquery" name="${cusId}" class="easyui-datagrid" data-options="loadMsg:'数据装载中......',pagination:true,singleSelect:true,fit:true,border:false,rownumbers:true">
						<thead>
							<tr>
								<th field="siId" align="center" hidden=true></th>
								<th field="drugcode" halign="center" align="left" width="100" sortable="true">项目编码</th>
								<th field="drugname" halign="center" align="left" width="180" sortable="true" formatter="fmtDrugName">项目名称</th>
								<th field="totalquantity" halign="center" align="right" width="80" sortable="true">销售总量</th>
								<th field="totalamount" halign="center" align="right" width="80" sortable="true">销售总额</th>
								<th field="drugspecification" halign="center" align="left" width="100">项目规格</th>
								<th field="drugmfrs" halign="center" align="left" width="180" formatter="fmtNull">生产商</th>
								<th field="drugmadein" halign="center" align="left" width="180" formatter="fmtNull">产地</th>
								<th field="operate" halign="center" align="center" width="80" formatter="fmtDrugopt">操作</th>
								<th field="remark" halign="center" align="center" width="80" formatter="fmtRMKLoading">可疑</th>
							</tr>
						</thead>				
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
