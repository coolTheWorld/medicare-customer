<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>供应商/生产商信息</title>

		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${path}/js/yiwei.js"></script>
		<script type="text/javascript">
			$(function() {
				var cusId=$("#supplier").attr("name");
				var cusFlag=$("#supplier").attr("cusFlag");
				var url =  'getAllSupplier?cusId='+cusId+'&cusFlag='+cusFlag;
				datagrid('supplier', '供应商/生产商信息', 'spId', url);
			});

			//删除重复数据
			function deleteRepeat(type){
				var cusId=$("#supplier").attr("name");
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除供应商/生产商信息重复数据..."
				});	
				$.ajax({
					type: "POST",
			        url: "deleteRepeat",
			        data:{type:type,cusId:cusId,name:'供应商/生产商信息'},
			        success: function(data) {
			        	$("#supplier").datagrid('reload');
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
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-cancel" onclick="deleteRepeat('supplier');">删除重复数据</a></td>
						</tr>
					</table>
				</form>
			</div>
			
			<div data-options="region:'center',split:false">
				<table id="supplier" name="${cusId }" cusFlag="${cusFlag }">
					<thead>
						<tr>
							<th field="spId" align="center" hidden=true>供应商id</th>
							<th field="cusId" hidden=true align="center" editor="{type:'text'}">客户ID</th>
							<th field="cusParentid" hidden=true align="center">客户上级ID</th>
							<th field="spCode" align="center" editor="{type:'text'}">企业编码</th>
							<th field="spName" align="left" formatter="fmtNull">企业名称</th>
							<th field="spContact" hidden=true align="center">联系人</th>
							<th field="spPhone" hidden=true align="center">联系电话</th>
							<th field="spCertificateno" hidden=true align="center">证书编号</th>
							<th field="spQuality" hidden=true align="center">质量认证情况</th>
							<th data-options="field:'spCat',align:'center',formatter:formatOperSpCat">企业类别</th>
							<th field="spPostcode"  hidden=true align="center">邮政编码</th>
							<th field="spEmail" hidden=true  align="center">电子邮件</th>
							<th field="spAddress" hidden=true align="center">详细地址</th>
							<th field="spFax"  hidden=true align="center">企业传真</th>
							<th data-options="field:'spStatus',align:'center',formatter:formatOperStatus">状态</th>
							<th field="cusDareway" hidden=true align="center">医院编码</th>
							<th field="acCode" hidden=true align="center">统筹区划编码</th>
							<th field="acName" hidden=true align="center">统筹区划名称</th>
							<th field="spPicktime" hidden=true align="center">采集时间</th>
							<th field="spAddtime" align="center">创建时间</th>
							<th field="spAnnex" hidden=true align="center">附件</th>
							<th field="spRemark" hidden=true align="center">备注</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
</html>