<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>入院信息表</title>

		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${path}/js/yiwei.js"></script>
		<script type="text/javascript">
			$(function() {
				var cusId = $("#customerInfo").attr("name");
				var url = 'customerInfo?cusId='+cusId;
				datagrid('customerInfo', '入院信息', 'hosNo', url);
			});

			//查看医嘱
			function doctororder(){
				var array = $('#customerInfo').datagrid('getSelections');// 获取选中项数组
				if(array.length==0){
					$.messager.alert("系统提示","请选择一个入院病人!");
				} else {
					var cusId=array[0].cusId;
					var hospNo=array[0].hospNo;
					$.post("getDoctororder",{cusId:cusId,hospNo:hospNo}, function(data) {
						$('#doc').dialog('open');
						$("#doctable").empty();
						$.each(data, function (i, item) {
							var doBegintime = new Date(item.doBegintime).toString();
							$("#doctable").append("<tr><td>"+item.itemName+"</td><td>"+item.itemPrice+"</td><td>"+item.itemAmount+"</td><td>"+item.doDeptname+"</td><td>"+item.doDoctorname+"</td><td>"+item.doCheckname+"</td><td>"+doBegintime+"</td></tr>");
						});
					});
				}
			}

			//查看已出院
			function discharged(id){
				window.location="toCustomerInfo2?id="+id;
			}
			
		</script>
	</head>
	<body>
	<div class="easyui-layout" fit="true" border="false">
		<div data-options="region:'north',title:'高级查询'" style="height: 65px; background: #F4F4F4;">
			<form id="searchForm">
				<table>
					<tr>
						<td>
							<a class="easyui-linkbutton" href="javascript:void(0);" onclick="doctororder()">查看医嘱信息</a>
							<a class="easyui-linkbutton" href="javascript:void(0);" onclick="discharged('${cusId}')">查看已出院病人</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- datagrid表格 -->
		<div data-options="region:'center',split:false">
			<table id="customerInfo" name="${cusId }">
				<thead>
					<tr>
						<th field="cusId" hidden=true align="center">客户ID</th>
						<th field="hospNo" align="center">住院号</th>
						<th field="siPtsname" align="center">患者姓名</th>
						<th field="hospIntime" align="center">入院日期</th>
						<th field="hospDeptname" align="center">住院科室</th>
						<th field="icdName" align="center">入院诊断</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<!-- 医嘱信息 -->
		<div id="doc" class="easyui-dialog" title="医嘱信息" style="width: 660px; height:800px;" data-options="closed:true,modal:true">
			<div style="padding: 2px; width: 100%; height: 100%;">
				<table id="doctable" class="ew_table" style="width: 100%; height: 100%;">
					<tbody>
						<tr>
							<th>项目名称</th>
							<th>单价</th>
							<th>用量</th>
							<th>开立科室</th>
							<th>开立医师</th>
							<th>审核护士</th>
							<th>开立日期</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
	</body>
</html>