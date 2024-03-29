<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>处方信息</title>
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
				var cusId=$("#prescribe").attr("name");
				var url = 'getAllPrescribe?cusId='+cusId+"&startDate="+startDate+"&endDate="+endDate;
				datagrid('prescribe', '处方信息', 'rpId', url);
			});

			//表格查询  
			function queryForm(){  
			  	var cusId=$("#prescribe").attr("name");
				var startDate=$("#startDate").datebox("getValue"); 
				var endDate=$("#endDate").datebox("getValue"); 
				var rpNo = $("#rpNo").val();
				var rpItemcode = $("#rpItemcode").val();
			  	$('#prescribe').datagrid({
					url:'getAllPrescribe',
					queryParams:{
			  			cusId:cusId,
			  			startDate:startDate,
			  			endDate:endDate,
			  			rpNo:rpNo,
			  			rpItemcode:rpItemcode
				    },
				});
			} 
	
			//点击清空按钮出发事件
			function clearForm() {
				 $('#searchForm').form('clear');  
			}

			//删除重复数据
			function deleteRepeat(type){
				var cusId=$("#prescribe").attr("name");
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除处方信息重复数据..."
				});	
				$.ajax({
					type: "POST",
			        url: "deleteRepeat",
			        data:{type:type,cusId:cusId,name:'处方信息'},
			        success: function(data) {
			        	$("#prescribe").datagrid('reload');
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
							<th>开具开始日期</th>
							<td><input class="easyui-datebox" id="startDate" data-options="editable:false" name="startDate" style="width:100px" /></td>
							<th>开具结束日期</th>
							<td><input class="easyui-datebox" id="endDate" data-options="editable:false" name="endDate" style="width:100px" /></td>
							<th>处方单号</th>
							<td><input id="rpNo" name="rpNo" style="width:100px"/></td>
							<th>项目编码/项目名称</th>
							<td><input id="rpItemcode" name="rpItemcode" style="width:100px"/></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-search" onclick="queryForm();">查询</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-remove" onclick="clearForm();">清空</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-cancel" onclick="deleteRepeat('prescribe');">删除重复数据</a></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- datagrid表格 -->
			<div data-options="region:'center',split:false">
				<table id="prescribe" name="${cusId }" onkeydown="if(event.keyCode==13){ queryForm();}">
					<thead>
						<tr>
							<th field="presId" align="center" hidden=true></th>
							<th field="cusId" hidden=true align="center" editor="{type:'text'}">客户ID</th>
							<th field="cusParentid" hidden=true align="center" editor="{type:'text'}">客户上级ID</th>
							<th field="rpNo" align="center" >处方单号</th>
							<th field="rpDrtime" align="center" >开具日期</th>
							<th field="rpDeptno"  align="center" >处方科别</th>
							<th field="rpDeptname" align="center" >处方科别名称</th>
							<th field="rpPtsname" align="center" >患者姓名</th>
							<th data-options="field:'rpPtssex',align:'center',formatter:formatOperSex">性别</th>					
							<th field="rpPtsage" align="center" >年龄</th>
							<th field="rpPtshealthcard" align="center" >社保卡号</th>
							<th field="rpPtsidcard" align="center" formatter="fmtNull">身份证号</th>
							<th field="rpPtsbirthday" hidden=true  align="center" >出生日期</th>
							<th field="rpItemcode" align="center" >项目编码</th>
							<th field="rpItemname" align="center" >项目名称</th>
							<th field="rpItemprice" align="center" >单价</th>
							<th field="rpItemnum" align="center" >数量</th>
							<th field="rpItemspecification" align="center" formatter="fmtNull">规格</th>
							<th field="rpItemdosageform" align="center" >剂型</th>
							<th field="rpItemmfrs" hidden=true align="center" >生产商</th>
							<th field="rpItemmakein" hidden=true align="center" >生产地</th>
							<th field="rpItembatchno" align="center" >批号</th>
							<th field="rpWhcode" hidden=true align="center" >仓库编码</th>
							<th field="rpWhname" align="center" >仓库名称</th>
							<th field="rpLocation" hidden=true align="center" >货位/货架号</th>
							<th field="rpDrugfreq" hidden=true align="center" >用药频次</th>
							<th field="rpDrugroute" hidden=true align="center" >用药途径</th>
							<th field="rpDrugtime" hidden=true align="center" >用药时间</th>
							<th field="rpDrugamount" hidden=true align="center" >用药量</th>
							<th field="rpDrcode" hidden=true align="center" >处方医师</th>
							<th field="rpDrname" align="center" >处方医师名称</th>
							<th field="rpAuditcode" hidden=true align="center" >审核医师</th>
							<th field="rpAuditname" align="center" >审核医师名称</th>
							<th field="rpAudittime" hidden=true align="center" >审核日期</th>
							<th field="rpCheckcode" hidden=true align="center" >核对医师</th>
							<th field="rpCheckname" hidden=true align="center" >核对医师名称</th>
							<th field="rpChecktime" hidden=true align="center" >核对日期</th>
							<th field="rpAnnex" hidden=true align="center" >附件</th>
							<th field="rpPicktime" hidden=true align="center" >采集日期</th>
							<th field="rpAddtime" align="center" >创建日期</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
</html>
