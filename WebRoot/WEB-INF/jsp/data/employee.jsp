<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>医护人员信息</title>

		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${path}/js/yiwei.js"></script>
		<script type="text/javascript">
		
			$(function() {
				var cusId=$("#employee").attr("name");
				var url = 'getAllEmployee?cusId='+cusId;
				datagrid('employee', '医护人员信息', 'emId', url);
			});

			//删除重复数据
			function deleteRepeat(type){
				var cusId=$("#employee").attr("name");
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除医护人员信息重复数据..."
				});	
				$.ajax({
					type: "POST",
			        url: "deleteRepeat",
			        data:{type:type,cusId:cusId,name:'医护人员信息'},
			        success: function(data) {
			        	$("#employee").datagrid('reload');
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
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-cancel" onclick="deleteRepeat('employee');">删除重复数据</a></td>
						</tr>
					</table>
				</form>
			</div>
			
			<div data-options="region:'center',split:false">
				<table id="employee" name="${cusId }">
					<thead>
						<tr>
							<th field="emId" align="center" hidden=true></th>
							<th field="cusId" align="center" hidden=true>客户ID</th>
							<th field="cusParentid" align="center" hidden=true>客户上级ID</th>
							<th field="emCode" align="center">人员编码</th>
							<th field="emName" align="center">人员姓名</th>
							<th data-options="field:'emSex',align:'center',formatter:formatOperSex">性别</th>
							<th field="emIdnum" hidden=true align="center">身份证号</th>
							<th field="emTitlename" hidden=true  align="center">职称</th>
							<th field="emQualification" hidden=true align="center">从业资格</th>
							<th field="emPhone" hidden=true align="center">联系电话</th>
							<th field="emAnnex" hidden=true align="center">附件信息</th>
							<th field="acCode" hidden=true align="center">统筹区编码</th>
							<th field="acName" hidden=true align="center">统筹区名称</th>
							<th data-options="field:'emTitlecode',hidden:true,align:'center',formatter:formatOperTitlecode">职称编码</th>
							<th data-options="field:'emJobcode',align:'center',formatter:formatOperJobcode">职务编码</th>
							<th field="emJobname" hidden=true  align="center">职务名称</th>
							<th field="emCertified" hidden=true  align="center">医师资格证编号</th>
							<th field="emLicence" hidden=true  align="center">医师执业证编号</th>
							<th data-options="field:'emClassifycode',hidden:true,align:'center',formatter:formatOperClassifycode">执业类别编码</th>
							<th field="emClassifyname"  hidden=true align="center">执业类别名称</th>
							<th	data-options="field:'emScopecode',hidden:true,align:'center',formatter:formatOperScopecode">执业范围编码</th>
							<th field="emScopename"  hidden=true align="center">执业范围名称</th>
							<th field="deptCode" align="center">科室编码</th>
							<th field="deptName" align="center">科室名称</th>
							<th	data-options="field:'emIsexpert',hidden:true,align:'center',formatter:formatOperIsnot">是否专家</th>
							<th	data-options="field:'emStatus',align:'center',formatter:formatOperStatus">状态</th>
							<th field="emRemark"  hidden=true align="center">备注</th>
							<th field="emPicktime" hidden=true align="center">采集时间</th>
							<th field="emAddtime" align="center">创建时间</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
</html>
