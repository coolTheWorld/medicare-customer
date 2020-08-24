<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>注册信息</title>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/base.css" />
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
<script src="${path}/js/hls.min.js"></script>
<script type="text/javascript">
$(function() {
	datagrid2("reglist", "getAllRegdata");
});
function Show() {
	$('#addArea').dialog('open').dialog('center').dialog('setTitle', '注册');
	$('#areaform').form('clear'); // 清除表单数据
	//
	$("#username").textbox('setValue', 'system');
	$("#userpass").textbox('setValue', 'Admin123');
	$("#serverip").textbox('setValue', '10.115.170.53');
	$("#serverport").textbox('setValue', '82');
}
function regCallback() {
	var pubK = getPubKey();
	var tok = getToken();
	if (pubK.length != 0) {
		if (tok.length != 0) {
			$.ajax({
				url : "regCallback?serverip=" + $('#serverip').val()
						+ "&serverport=" + $('#serverport').val()
						+ "&username=" + $('#username').val()
						+ "&userpass=" + $('#userpass').val() + "&userid="
						+ $('#userid').val() + "&usertoken="
						+ $('#usertoken').val() + "&callbackurl="
						+ $('#callbackurl').val() + "&platname="
						+ $('#platname').val() + "&platdesc="
						+ $('#platdesc').val() + "&sysexpir="
						+ $("input[name='sysexpir']").val()
						+ "&expirationTime="
						+ $('#expirationTime').datetimebox('getValue'),
				success : function(map) {
					if ("true" == map.success) {
						$.messager.show({
							title : '系统提示',
							msg : "注册成功！"
						});
					} else {
						$.messager.show({
							title : '系统提示',
							msg : "注册失败！" + map.errMsg
						});
					}
				}

			});
		} else {
			$.messager.show({
				title : '系统提示',
				msg : "未获取到token!"
			});
		}
	} else {
		$.messager.show({
			title : '系统提示',
			msg : "未获取到公钥!"
		});
	}
}
function getPubKey() {
	//大华平台的地址（10.115.170.53） 端口：82 system
	var username = $('#username').val();
	var userpass = $('#userpass').val();
	var result;
	$.ajax({
		type : "POST",
		url : "getPubkey?username=" + username + '&userpass=' + userpass,
		async : false,
		success : function(data) {
			$("#Pubkey").textbox('setValue', data);
			result = data;
		}
	});
	return result;
}
function getToken() {
	//大华平台的地址（10.115.170.53） 端口：82 system

	var username = $('#username').val();
	var userpass = $("#Pubkey").val();
	var result;
	$.ajax({
		type : "POST",
		data : "username=" + username + "&userpass="
				+ encodeURIComponent(userpass),
		url : "getToken",
		async : false,
		success : function(data) {
			$("#usertoken").textbox('setValue', data.token);
			result = data.token;
		}
	});
	return result;
}

$(function() {
	$('#sysexpir').combobox({
		onChange : function(n, o) {
			if ("0" == n) {
				$('#expirationTime').datebox('setValue', '');
			}
		}
	});
});

/*  function saveRegInfo() {
	var code = $('#code').val();
	//200:成功;400 验证问题;999:其他
	if("200" == code){
		 var reginfo = new Object; 
		 reginfo.platip = $('#serverip').val();
		 reginfo.platport = $('#serverport').val();
	     reginfo.sysname = $('#platname').val();
		 reginfo.syscburl = $('#callbackurl').val();
		 reginfo.sysdesc =  $('#platdesc').val();
		 reginfo.usrcode = code;
		 reginfo.sysexpir = $("input[name='sysexpir']").val();
		 reginfo.expirtime = $('#expirationTime').datetimebox('getValue');
										 
	$.ajax({
		cache : true,
		type : "POST",
		url : 'face/addRegInfo',
		data : reginfo,
		async : false,
		success : function(data) {
			$.messager.show({
				title : '系统提示',
				msg : data
			});
			$('#reglist').datagrid('reload'); // 刷新
			$('#addReg').dialog('close');
		},
		error : function(request) {
			$.messager.alert('系统提示', '注册出錯！', 'error');
		}
	});
	}else{
		$.messager.alert('系统提示', '注册失败 ！', 'error');
	}
}  */
function sysexpir(value, row, index) {
	if (value == false) {
		return "否";
	} else {
		return "是";
	}
}
function clos() {
	$('#addArea').dialog('close');
	$('#reglist').datagrid('reload');
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div class="easyui-panel" title="注册信息"
		data-options="border:true,fit:true" iconCls="icon-zc">
		<table id="reglist"
			data-options="fit:true,rownumbers:true,pagination:true" width="95%"
			cellpadding="2" cellspacing="1" toolbar="#toolbar">
			<thead>
				<tr>
					<th field="regid" align="center">注册ID</th>
					<th field="platip" align="center">平台IP</th>
					<th field="platport" align="center">平台端口</th>
					<th field="sysname" align="center">系统名称</th>
					<th field="syscburl" align="center">回调地址</th>
					<th field="sysdesc" align="center">系统描述</th>
					<th field="usrcode" align="center">用户code</th>
					<th
						data-options="field:'sysexpir',align:'center',formatter:sysexpir">是否有有效期</th>
					<th field="expirtime" align="center">有效期</th>
					<th field="eventlist" align="center">订阅事件列表</th>
				</tr>

			</thead>
		</table>
	</div>

</div>
<div id="toolbar"
	style="width: 100%; height: 25px; padding: 2px 0; position: relative;"
	class="">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-add" onclick="Show();" style="float: left;">注册</a>
</div>
<div id="table"></div>
<c:if test="${!empty page.list}">
	<div align="right" style="width: 80%; margin: 0 auto">
		<div class="row-fluid">
			<div class="span12">
				<%@include file="/WEB-INF/jsp/system/page.jsp"%>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${empty page.list}">
	<div class="text-center">
		<H3>未查找到数据!</H3>
	</div>
</c:if>
<!-- 添加注册信息 -->
<div id="addArea" class="easyui-dialog" title="注册信息"
	style="width: 500px; height: 300px; line-height: 30px;"
	data-options="iconCls:'icon-addd',closed:true,modal:true,">
	<form id="areaform">
		<div class="input_box">
			<label>用户名：&nbsp;&nbsp;&nbsp;</label> <input id="username" name=""
				class="easyui-textbox" /> <label>密码：&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<input id="userpass" name="" class="easyui-textbox">
		</div>
		<div class="input_box">
			<label>公钥：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input id="Pubkey"
				name="platip" class="easyui-textbox w_50" editable="false"><label>token：&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input id="usertoken" name="platport" class="easyui-textbox w_50"
				editable="false" />
		</div>
		<!-- 	<div id="toolbar"
			style="width: 100%; padding: 2px 0; position: relative;"
			class="" >
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="getPubKey();" style="float: left;margin-left:12px">获取公钥</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="getToken();" style="float: left;margin-left:12px">获取token</a>
		</div> -->
		<div class="input_box">
			<label>大华ip地址：</label> <input id="serverip" name="platip"
				class="easyui-textbox w_50"> <label>大华端口号：</label> <input
				id="serverport" name="platport" class="easyui-textbox w_50" />
		</div>
		<div class="input_box">
			<label>回调地址：</label> <input id="callbackurl" name=""
				class="easyui-textbox w_50"> <label>平台名称：</label> <input
				id="platname" name="" class="easyui-textbox w_50">
		</div>
		<div class="input_box">
			<label>系统描述：</label> <input id="platdesc" name=""
				class="easyui-textbox w_50"> <label>userid：</label> <input
				id="userid" name="" class="easyui-textbox w_50">
		</div>
		<div class="input_box">
			<label>是否有有效期：</label> <select id=sysexpir name="sysexpir"
				class="easyui-combobox" style="text-align: center"
				data-options="width:100,panelHeight:'auto'">
				<option value="0">否</option>
				<option value="1">是</option>
			</select> <label>有效期:</label><input id="expirationTime" name="expirationTime"
				class="easyui-datetimebox" style="width: 200px;"></input>
		</div>
		<!-- 	<div class="input_box">
		<label>code：</label> <input id="code" name=""
				class="easyui-textbox w_50">
		</div> -->
		<div style="text-align: center">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" onclick="regCallback();">注册</a> <a
				href="javascript:void(0)" onclick="javascript: clos();"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				style="margin-left: 20px;">关闭</a>
		</div>
	</form>
</body>
</html>