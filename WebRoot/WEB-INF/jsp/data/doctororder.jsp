<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>医嘱信息</title>

		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
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
				var cusId = $("#doctororder").attr("name");
				var url = 'getAllDoctororder?cusId='+cusId+"&startDate="+startDate+"&endDate="+endDate;
				datagrid('doctororder', '医嘱信息', 'doId', url);
			})
			
			//表格查询  
			function queryForm(){  
			  	var cusId=$("#doctororder").attr("name");
				var startDate=$("#startDate").datebox("getValue"); 
				var endDate=$("#endDate").datebox("getValue");
				var hospNo = $("#hospNo").val();
				var doNo = $("#doNo").val();
				var itemCode = $("#itemCode").val();
			  	$('#doctororder').datagrid({
					url:'getAllDoctororder',
					queryParams:{
			  			cusId:cusId,
			  			startDate:startDate,
			  			endDate:endDate,
			  			hospNo:hospNo,
			  			doNo,doNo,
			  			itemCode:itemCode
				    },
				});
			} 
	
			//点击清空按钮出发事件
			function clearForm() {
				 $('#searchForm').form('clear');  
			}

			//删除重复数据
			function deleteRepeat(type){
				var cusId=$("#doctororder").attr("name");
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除医嘱信息重复数据..."
				});	
				$.ajax({
					type: "POST",
			        url: "deleteRepeat",
			        data:{type:type,cusId:cusId,name:'医嘱信息'},
			        success: function(data) {
			        	$("#doctororder").datagrid('reload');
			        	$.messager.show({
							title: "系统提示",
			                msg: data.info
						});
					}
				});
			}
		</script>
	</head>
	<body>
		<div class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'高级查询'" style="height: 65px; background: #F4F4F4;">
				<form id="searchForm" onkeydown="if(event.keyCode==13){ queryForm();}">
					<table>
						<tr>
							<th>开立开始日期</th>
							<td><input class="easyui-datebox" id="startDate" data-options="editable:false" name="startDate" style="width:100px" /></td>
							<th>开立结束日期</th>
							<td><input class="easyui-datebox" id="endDate" data-options="editable:false" name="endDate" style="width:100px" /></td>
							<th>住院号</th>
							<td><input id="hospNo" name="hospNo" style="width:100px"/></td>
							<th>医嘱号</th>
							<td><input id="doNo" name="doNo" style="width:100px"/></td>
							<th>项目编码/项目名称</th>
							<td><input id="itemCode" name="itemCode" style="width:100px"/></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-search" onclick="queryForm();">查询</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-remove" onclick="clearForm();">清空</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-cancel" onclick="deleteRepeat('doctororder');">删除重复数据</a></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- datagrid表格 -->
			<div data-options="region:'center',split:false">
				<table id="doctororder" name="${cusId }">
					<thead>
						<tr>
							<th field="doId" align="center" hidden=true></th>
							<th field="cusId" hidden=true align="center">客户ID</th>
							<th field="cusPid" hidden=true align="center">客户父ID</th>
							<th field="cusDareway" hidden=true align="center">医院编码</th>
							<th field="caseNo" align="center" formatter="fmtNull">病案号</th>
							<th field="hospNo" align="center">住院号</th>
							<th field="doNo" align="center" formatter="fmtNull">医嘱号</th>
							<th data-options="field:'doType',align:'center',formatter:formatOperdoType">医嘱类型</th>
							<th field="doBegintime" align="center">开立日期</th>
							<th field="doEndtime" align="center">停止日期</th>
							<th field="itemCode" align="center" formatter="fmtNull">项目编码</th>
							<th field="itemName" align="left" formatter="fmtNull">项目名称</th>
							<th field="itemPrice" align="center">单价</th>
							<th field="itemFreq"  hidden=true align="center">频次</th>
							<th field="itemAmount" align="center" formatter="fmtNull">用量</th>
							<th field="doDept" hidden=true   align="center">开立科室</th>
							<th field="doDeptname" align="center">开立科室</th>
							<th field="doDoctor"  hidden=true align="center">开立医师</th>
							<th field="doDoctorname" align="center" formatter="fmtNull">开立医师</th>
							<th field="doCheck"  hidden=true align="center" formatter="fmtNull">审核护士</th>
							<th field="doCheckname" align="center" formatter="fmtNull">审核护士</th>
							<th field="doEnddr"  hidden=true align="center">停止医师</th>
							<th field="doEnddrrname" hidden=true  align="center">停止医师</th>
							<th field="doEndnurse" hidden=true  hidden=true align="center">停止护士</th>
							<th field="doEndnursename"  hidden=true  align="center">停止护士</th>
							<th field="doPicktime" hidden=true align="center">采集日期</th>
							<th field="doAddtime" align="center">创建日期</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
</html>
