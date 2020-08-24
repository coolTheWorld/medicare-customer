<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>出院记录</title>

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
				var cusId = $("#discharged").attr("name");
				var url = 'getAllDischarged?cusId='+cusId+"&startDate="+startDate+"&endDate="+endDate;
				datagrid('discharged', '出院记录', 'dcId', url);
			});

			//表格查询  
			function queryForm(){  
			  	var cusId=$("#discharged").attr("name");
				var startDate=$("#startDate").datebox("getValue"); 
				var endDate=$("#endDate").datebox("getValue");
				var hospNo = $("#hospNo").val(); 
				var siPtsname = $("#siPtsname").val(); 
			  	$('#discharged').datagrid({
					url:'getAllDischarged',
					queryParams:{
			  			cusId:cusId,
			  			startDate:startDate,
			  			endDate:endDate,
			  			hospNo:hospNo,
			  			siPtsname:siPtsname
				    },
				});
			} 
	
			//点击清空按钮出发事件
			function clearForm() {
				 $('#searchForm').form('clear');  
			}

			//删除重复数据
			function deleteRepeat(type){
				var cusId=$("#discharged").attr("name");
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除出院记录重复数据..."
				});	
				$.ajax({
					type: "POST",
			        url: "deleteRepeat",
			        data:{type:type,cusId:cusId,name:'出院记录'},
			        success: function(data) {
			        	$("#discharged").datagrid('reload');
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
							<th>出院开始日期</th>
							<td><input class="easyui-datebox" id="startDate" data-options="editable:false" name="startDate" style="width:100px" /></td>
							<th>出院结束日期</th>
							<td><input class="easyui-datebox" id="endDate" data-options="editable:false" name="endDate" style="width:100px" /></td>
							<th>住院号</th>
							<td><input id="hospNo" name="hospNo" style="width:100px" /></td>
							<th>患者姓名/出院诊断名称</th>
							<td><input id="siPtsname" name="siPtsname" style="width:100px" /></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-search" onclick="queryForm();">查询</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-remove" onclick="clearForm();">清空</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-cancel" onclick="deleteRepeat('discharged');">删除重复数据</a></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- datagrid表格 -->
			<div data-options="region:'center',split:false">
				<table id="discharged" name="${cusId }">
					<thead>
						<tr>
							<th field="dcId" align="center" hidden=true></th>
							<th field="cusId" hidden=true align="center">客户ID</th>
							<th field="cusDareway" hidden=true align="center">医院编码</th>
							<th field="caseNo" align="center" formatter="fmtNull">病案号</th>
							<th field="hospNo" align="center" formatter="fmtNull">住院号</th>
							<th field="dcIndate" align="center">入院日期</th>
							<th field="dcHospdays" align="center">住院天数</th>
							<th field="dcOutdate" align="center">出院日期</th>
							<th field="dcDiagcode" align="center" formatter="fmtNull">出院诊断编码</th>
							<th field="dcDiagname" align="center" formatter="fmtNull">出院诊断名称</th>
							<th field="siPtsname" align="center">患者姓名</th>
							<th data-options="field:'siPtssex',align:'center',formatter:formatOperSex">性别</th>
							<th field="siPtsage" align="center" formatter="fmtNull">年龄</th>
							<th field="siPtsidcard"  align="center" formatter="fmtNull">身份证号</th>
							<th field="siPtshealthcard" align="center">社保卡号</th>
							<th data-options="field:'dcOutype',align:'center',formatter:formatOperdcOutype">出院方式</th>
							<th field="dcHosptimes" align="center">住院次数</th>
							<th field="dcDoctorcode"  hidden=true align="center">医师编码</th>
							<th field="dcDoctorname" align="center" formatter="fmtNull">诊断医师</th>
							<th field="dcPickime" hidden=true align="center">采集日期</th>
							<th field="dcAddtime" align="center">创建日期</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
</html>
