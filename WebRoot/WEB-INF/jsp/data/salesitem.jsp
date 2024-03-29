<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>销售信息</title>

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
				var cusId = $("#salesitem").attr("name");
				var url = 'getAllSalesitem?cusId=' + cusId+"&startDate="+startDate+"&endDate="+endDate;
				datagrid('salesitem', '销售信息', 'siId', url);
			});
	
			//表格查询  
			function queryForm(){ 
			  	var cusId=$("#salesitem").attr("name");
				var startDate=$("#startDate").datebox("getValue"); 
				var endDate=$("#endDate").datebox("getValue"); 
				var soNo = $("#soNo").val();
				var drugCode = $("#drugCode").val();
				var soSalespsnname = $("#soSalespsnname").val();
			  	$('#salesitem').datagrid({
					url:'getAllSalesitem',
					queryParams:{
			  			cusId:cusId,
			  			startDate:startDate,
			  			endDate:endDate,
			  			soNo:soNo,
			  			drugCode:drugCode,
			  			soSalespsnname:soSalespsnname
				    },
				});
			} 
	
			//点击清空按钮出发事件
			function clearForm() {
				$('#searchForm').form('clear');  
			}

			//删除重复数据
			function deleteRepeat(type){
				var cusId=$("#salesitem").attr("name");
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除销售信息重复数据..."
				});	
				$.ajax({
					type: "POST",
			        url: "deleteRepeat",
			        data:{type:type,cusId:cusId,name:'销售信息'},
			        success: function(data) {
			        	$("#salesitem").datagrid('reload');
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
							<th>销售开始日期</th>
							<td><input class="easyui-datebox" id="startDate" data-options="editable:false" name="startDate" style="width:100px" /></td>
							<th>销售结束日期</th>
							<td><input class="easyui-datebox" id="endDate" data-options="editable:false" name="endDate" style="width:100px"  /></td>
							<th>销售编号</th>
							<td><input id="soNo" name="soNo" style="width:100px"  /></td>
							<th>项目编码/项目名称</th>
							<td><input id="drugCode" name="drugCode" style="width:100px"  /></td>
							<th>患者姓名/结算方式</th>
							<td><input id="soSalespsnname" name="soSalespsnname" style="width:100px"  /></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-search" onclick="queryForm();">查询</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-remove" onclick="clearForm();">清空</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-cancel" onclick="deleteRepeat('salesitem');">删除重复数据</a></td>
						</tr>
					</table>
				</form>
			</div>
		
			<!-- datagrid表格 -->
			<div data-options="region:'center',split:false">
				<table id="salesitem" name="${cusId }">
					<thead>
						<tr>
							<th field="siId" align="center" hidden=true></th>
							<th field="cusId" hidden=true align="center">客户ID</th>
							<th field="cusParentid" hidden=true align="center">客户上级ID</th>
							<th field="drugEid" align="center" formatter="fmtNull">本位码</th>
							<th field="soNo" align="center">销售编号</th>
							<th field="soDatetime" align="center">销售日期</th>
							<th field="drugCode" align="center">项目编码</th>
							<th field="drugName" align="left">项目名称</th>
							<th data-options="field:'soPaytype',hidden:true,align:'center',formatter:formatOperPaytype">结算方式</th>
							<th field="siSettlementname" align="center" formatter="fmtColor">结算方式</th>
							<th field="drugSalesprice" align="center">销售单价</th>
							<th field="drugNum" align="center">销售数量</th>
							<th field="siUnit" align="center" formatter="fmtNull">单位</th>
							<th field="drugSpecification" align="center" formatter="fmtNull">规格</th>
							<th field="drugBatchno" align="center">生产批号</th>
							<th field="drugMfrs" align="center">生产商</th>
							<th field="drugMadein" align="center">产地</th>
							<th field="drugMfgdate" hidden=true align="center">生产日期</th>
							<th field="drugExpdate" hidden=true align="center">有效期</th>
							<th field="siOpcode" hidden=true align="center">销售员编码</th>
							<th field="soSalespsnname" align="center" formatter="fmtNull">患者姓名</th>
							<th data-options="field:'siPtssex',align:'center',formatter:formatOperSex">性别</th>
							<th field="siPtsage" align="center" formatter="fmtNull">年龄</th>
							<th field="siPtsidcard" hidden=true align="center">身份证号</th>
							<th field="siPtshealthcard" align="center" >社保卡号</th>
							<th field="cusDareway" hidden=true align="center">医院编码</th>
							<th data-options="field:'siStatus',align:'center',formatter:formatOpersiStatus">结算状态</th>
							<th field="siOpcode" hidden=true align="center">操作员编码</th>
							<th field="siOpname" align="center" formatter="fmtNull">操作员</th>
							<th field="drugPicktime" hidden=true align="center">采集日期</th>
							<th field="soCreatedatetime" align="center">创建日期</th>
							<th field="siTracecode" align="center">追溯码</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
</html>
