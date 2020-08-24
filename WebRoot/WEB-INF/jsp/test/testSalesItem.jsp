<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>客户管理</title>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/faceApp.css" />
<style>
.w_200 {
	width: 200px
}
#form .input_box {
	text-align: center;
	margin-bottom: 15px
}
</style>
<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
<script src="${path}/easyui/jquery.easyui.min.js"></script>
<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${path}/js/yiwei.js"></script>
<script type="text/javascript">
		$(function() {
			var startDate = '${startDate}';
			var endDate = '${endDate}';
			$("#startDate").datebox("setValue", startDate);  
			$("#endDate").datebox("setValue", endDate);
			var url = 'getSalesitemVO?startDate='+startDate+'&endDate='+endDate;
			datagrid2("saleslist", url);
		});
		
		//表格查询  
		function queryForm(){  
			var startDate=$("#startDate").datebox("getValue"); 
			var endDate=$("#endDate").datebox("getValue");
			var syear = startDate.substring(0,4);
			var eyear = endDate.substring(0,4);
			console.log(syear+","+eyear);
			if(startDate=='' || endDate==''){
				$.messager.alert("信息提示框", "请填入查询日期!");
				return false;
			}
			if(startDate > endDate){
				$.messager.alert("信息提示框", "开始日期不能大于结束日期!");
				return false;
			}
			if((eyear-syear)>=2){
				$.messager.alert("信息提示框", "请不要跨两年以上进行查询!");
				return false;
			}
			  $('#saleslist').datagrid({
				url:'getSalesItem',
				queryParams:{
			  		startDate:startDate,
			  		endDate:endDate
				   },
			});
		}
			
		//点击清空按钮出发事件
		function clearForm() {
			$('#searchForm').form('clear');  
		}
		//添加新选项卡显示进销存数据
		function show(url,value){
			window.parent.addTab(unescape(value), url);//调用父类方法添加选项卡
		}
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:'true',">
		<div class="easyui-panel" title="销售记录" data-options="border:true,fit:true" iconCls="icon-zc">
			<table id="saleslist" data-options="checkOnSelect:true,singleSelect:true,fit:true,rownumbers:true,pagination:true,fitColumns:true"
				cellpadding="2" cellspacing="1" toolbar="#toolbar">
				<thead>
					<tr>
						<th data-options="field:'sb',checkbox:true"></th>
						<th data-options="field:'soNo',halign:'center',align:'left',width:140">单号</th>
						<th data-options="field:'soSalespsnname',halign:'center',align:'left',width:120">姓名</th>
						<th data-options="field:'drugName',halign:'center',align:'left',width:80">药品名称</th>
						<th data-options="field:'drugNum',halign:'center',align:'left',width:140">数量</th>
						<th data-options="field:'drugSalesprice',halign:'center',align:'left',width:100">单价</th>
						<th data-options="field:'soDatetime',halign:'center',align:'left',width:80">日期</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="toolbar" style="width: 100%; height: 35px; padding: 2px 0;">
			<div class="barbox1-1">
				<form id="searchForm" onkeydown="if(event.keyCode==13){queryForm();}">
					<table>
						<tr>
							<th>开始日期</th> 
							<td><input id="startDate" class="easyui-datebox" data-options="editable:false" name="startDate" style="width:120px" /></td><!-- editable:false禁止用户手动输入,easyui-datetimebox带时分秒 -->
							<th> - </th>
							<td><input id="endDate" class="easyui-datebox" data-options="editable:false" name="endDate" style="width:120px" /></td>
							
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-search" onclick="queryForm();">查询</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-remove" onclick="clearForm();">清空</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
