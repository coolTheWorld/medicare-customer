<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>删除数据</title>
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
			$(function() {
				$("#delete").datagrid({
					
			    });
			})
			
			//删除已注销
			function deleteCancle(type,name){
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除"+name+"已注销定点数据..."
				});	
				$.ajax({
					type: "POST",
			        url: "deleteCancle",
			        data:{type:type,name:name},
			        success: function(data) {
			        	$.messager.show({
							title: "系统提示",
			                msg: data.info
						});	
					}
				});
			}
	
			//删除重复数据
			function deleteRepeat(type,name){
				var cusId = $("#cusId").searchbox("getValue");
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除"+name+"重复数据..."
				});	
				$.ajax({
					type: "POST",
			        url: "deleteRepeat",
			        data:{type:type,cusId:cusId,name:name},
			        success: function(data) {
						$.messager.show({
							title: "系统提示",
			                msg: data.info
						});	
					}
				});
			}
	
			//实时查询定点
			function getCusName(param,success,error){
				var q = param.q || "";
				if(q.length <= 0) {
					return false;
				}
				$.ajax({
					url:"getCusName",
					type:"post",
					data:{str:q },
					success:function(data){
						var items = $.map(data.rows, function(item){
							return {
								id:item.id,
								name:item.text
							};
						});
						success(items);
					},
				});
			}
	
			//清空输入框
			function clearForm() {
			    $("#cusId").textbox('setValue','')
			}
			
		</script>
	</head>
	<body>
		<div class="easyui-layout" data-options="fit:'true',">
			<div class="easyui-panel" title="冗余数据去除" data-options="border:true,fit:true" iconCls="icon-zc">
				<table id="delete" data-options="checkOnSelect:true,singleSelect:true,fit:true,rownumbers:true,pagination:true" width="95%" cellpadding="2" cellspacing="1" toolbar="#toolbar">
					<thead>
						<tr>
							<th data-options="field:'a',width:200">删除已注销定点的数据</th>
							<th data-options="field:'b',width:200">删除重复数据</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('drugcatalog','项目编码')">项目编码</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('drugcatalog','项目编码')">项目编码</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('supplier','供应商/生产商信息')">供应商/生产商信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('supplier','供应商/生产商信息')">供应商/生产商信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('employee','医护人员信息')">医护人员信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('employee','医护人员信息')">医护人员信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('warehouseitem','入库信息')">入库信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('warehouseitem','入库信息')">入库信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('deliveryitem','出库信息')">出库信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('deliveryitem','出库信息')">出库信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('itemstock','库存信息')">库存信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('itemstock','库存信息')">库存信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('salesitem','销售信息')">销售信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('salesitem','销售信息')">销售信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('warehouse','库房信息')">库房信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('warehouse','库房信息')">库房信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('prescribe','处方信息')">处方信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('prescribe','处方信息')">处方信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('hospitalized','住院记录')">住院记录</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('hospitalized','住院记录')">住院记录</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('discharged','出院记录')">出院记录</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('discharged','出院记录')">出院记录</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('doctororder','医嘱信息')">医嘱信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('doctororder','医嘱信息')">医嘱信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('orderperform','医嘱执行记录')">医嘱执行记录</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('orderperform','医嘱执行记录')">医嘱执行记录</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('department','科室信息')">科室信息</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('department','科室信息')">科室信息</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('clinicrecords','诊断记录')">诊断记录</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('clinicrecords','诊断记录')">诊断记录</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('medrecords','病案首页(概要)')">病案首页(概要)</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('medrecords','病案首页(概要)')">病案首页(概要)</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('dischargediag','病案首页(出院诊断)')">病案首页(出院诊断)</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('dischargediag','病案首页(出院诊断)')">病案首页(出院诊断)</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('operation','病案首页(手术操作)')">病案首页(手术操作)</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('operation','病案首页(手术操作)')">病案首页(手术操作)</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('checkresult','检查、检验结果')">检查、检验结果</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('checkresult','检查、检验结果')">检查、检验结果</a></td>
						</tr>
						<tr>
							<td><a href="javascript:;" onclick="deleteCancle('checkdetail','检查、检验结果详细')">检查、检验结果详细</a></td>
							<td><a href="javascript:;" onclick="deleteRepeat('checkdetail','检查、检验结果详细')">检查、检验结果详细</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="toolbar" style="width: 100%; height: 25px; padding: 2px 0;">
				<div style="float:left;margin:0 2px;">
					<input id="cusId" class="easyui-combobox" style="width:400px;" data-options="prompt:'请输入定点名称', loader: getCusName,mode: 'remote',valueField: 'id',textField: 'name'"/>
				</div>
				<div style="float:left;margin:0 2px;">
					<a href="#" class="clear" onClick="clearForm()"><img style="margin-top: 2px;" class="cl-xx-child" src="images/xx.png" /></a>
				</div>
			</div>
		</div>
	</body>
</html>
