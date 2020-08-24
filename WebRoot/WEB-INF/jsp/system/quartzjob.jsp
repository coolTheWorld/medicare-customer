<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>任务管理</title>
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
		<style>
			.w_200{width:200px}
			#quartzform .input_box{text-align:center;margin-bottom:15px}
		</style>
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${path}/js/yiwei.js"></script>
		<script type="text/javascript">
			$(function() {
			    $(".tabb").tabs({
			        fit: true,
			        border: false,
			        selected: 0,
			    });

			    datagrid2("quartzjob", "getAllQuartzJob");
	
			})
			// 根据状态显示图片
			function triggerStatus(value, row, index) {
			    if (row.trigger_state == 'PAUSED') { // 这里是判断哪些行
			    	 return '<a href="#"></a><img src="images/delete.png" title="已停用" alt="" />';
			    } else {
			        return '<a href="#"></a><img src="images/accept.png"  title="已启用" alt="" />';
			    }
			}
	
			function classNameStatus(value, row, index) {
			    if (row.className == '1') { // 这里是判断哪些行
			    	 return '解析任务';
			    } else if (row.className == '2'){
			        return '预警任务';
			    } else if (row.className == '3'){
			        return '定点资格任务';
			    } else {
			        return '解析任务';
			    } 
			}
	
			// 增加
			function addQuartzJob() {
			    $('#muUserid').val('');
			    $('#quartzbtn').dialog('open').dialog('center').dialog('setTitle', '新增任务');
			    $('#quartzform').form('clear'); // 清除表单数据
			    url = 'addJobgroup';
			}
	
			function openCron() {
			    $('#quartzbtn2').dialog('open').dialog('center').dialog('setTitle', '表达式构建');
			}
	
			// form表单提交保存
			function saveQuartz() {
			    $.ajax({
			        cache: true,
			        type: "POST",
			        url: 'addJobgroup',
			        data: $('#quartzform').serialize(),
			        async: false,
			        success: function(data) {
			            $.messager.show({
			                title: '系统提示',
			                msg: data.status
			            });
			            $('#quartzjob').datagrid('reload'); // 刷新
			            $('#quartzbtn').dialog('close');
			        },
			        error: function(request) {
			            $.messager.alert('警告','操作错误');
			        }
			    });
			}
			// 删除
			function delQuartzJob() {
			    var array = $('#quartzjob').datagrid('getSelected'); // 获取删除选中项数组
			    if (array != null && array != "") {
			        $.messager.confirm('系统提示', '是否要删除选中任务?',
				    function(r) {
				            if (r) {
				                var trigger = array.trigger_name;
				                $.post("delJobgroup", {
				                	triggername:trigger
				                },
				                function() {// 前台没有返回json,里面就没有data
				                    $('#quartzjob').datagrid({
				                        url: "getAllQuartzJob"
				                    });
				                    $.messager.show({
				                        title: '系统提示',
				                        msg: "删除成功"
				                    });
				                });
				            }
				        });
			    } else {
			        $.messager.alert("系统提示", "请先选择要删除的任务！");
			    }
			}
	
			//暂停
			function pauseQuartzJob() {
			    var array = $('#quartzjob').datagrid('getSelected'); // 获取删除选中项数组
			    if (array != null && array != "") {
			        $.messager.confirm('系统提示', '是否要暂停选中任务?',
				    function(r) {
				            if (r) {
				                var trigger = array.trigger_name;
				                $.post("pausejobgroup", {
				                	triggername:trigger
				                },
				                function() {// 前台没有返回json,里面就没有data
				                    $('#quartzjob').datagrid({
				                        url: "getAllQuartzJob"
				                    });
				                    $.messager.show({
				                        title: '系统提示',
				                        msg: "暂停成功"
				                    });
				                });
				            }
				        });
			    } else {
			        $.messager.alert("系统提示", "请先选择要暂停的任务！");
			    }
			}
	
			//开启
			function resumeQuartzJob() {
			    var array = $('#quartzjob').datagrid('getSelected'); // 获取删除选中项数组
			    if (array != null && array != "") {
			        $.messager.confirm('系统提示', '是否要开启选中任务?',
				    function(r) {
				            if (r) {
				                var trigger = array.trigger_name;
				                $.post("resumeTriggers", {
				                	triggername:trigger
				                },
				                function() {// 前台没有返回json,里面就没有data
				                    $('#quartzjob').datagrid({
				                        url: "getAllQuartzJob"
				                    });
				                    $.messager.show({
				                        title: '系统提示',
				                        msg: "开启成功"
				                    });
				                });
				            }
				        });
			    } else {
			        $.messager.alert("系统提示", "请先选择要开启的任务！");
			    }
			}
		</script>
	</head>
	
	<body>
		<div class="easyui-layout" data-options="fit:'true',">
			<div class="xqgl">
				<div class="easyui-panel" title="任务管理" data-options="border:true,fit:true" iconCls="icon-zc">
					<table id="quartzjob" data-options="checkOnSelect:true,fit:true,singleSelect:true,rownumbers:true,pagination:true" width="95%" height="500" cellpadding="2" cellspacing="1" toolbar="#mytoolbar">
						<thead>
							<th data-options="field:'sb',checkbox:true"></th>
							<th data-options="field:'job_name',halign:'center',align:'left',width:140">任务名称</th>
							<th data-options="field:'job_group',halign:'center',align:'left',width:100">任务分组</th>
							<th data-options="field:'cron_expression',align:'center',width:80">表达式</th>
							<th data-options="field:'trigger_name',align:'center',width:80">触发器</th>
							<th data-options="field:'className',align:'center',width:80,formatter:classNameStatus">执行类</th>
							<th data-options="field:'trigger_state',align:'center',width:60,formatter:triggerStatus">启用状态</th>
							</tr>
						</thead>
					</table>
				</div>
				<div id="mytoolbar"
					style="width: 100%; height: 25px; padding: 2px 0;" class="">
					<a href="javascript:void(0)" onclick="addQuartzJob(this);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" style="float: left;">添加任务</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" plain="true" onclick="delQuartzJob()">删除任务</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-accept" plain="true" onclick="resumeQuartzJob()">启动</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-node" plain="true" onclick="pauseQuartzJob()">停止</a>
				</div>
			</div>
		</div>
		</div>
		</div>
		</div>
		<div id="quartzbtn" class="easyui-dialog" title="新增任务" style="width: 430px; height: 350px;" data-options="iconCls:'icon-add',buttons:'#nfd1_btns',closed:true,modal:true,">
			<form id="quartzform" action="" method="">
				<div class="input_box">   
			        <h1 style="margin:25px 0 20px 0">任务信息</h1>
			    </div>  
				<div class="input_box">   
			        <label>任务名称：</label>   
			        <input id="job_name" name="job_name" class="easyui-textbox w_200"> 
			    </div>  
			    <div class="input_box">   
			        <label>任务分组：</label>   
			        <input id="job_group" name="job_group" class="easyui-textbox w_200"> 
			    </div>  
			    <div class="input_box">   
			        <label>  表达式：</label>   
			        <input id="cron_expression" name="cron_expression" class="easyui-textbox w_200">
					<a href="javascript:void(0)" onclick="openCron(this);">构建</a>
			    </div> 
			    <div class="input_box">   
			        <label>触发器：</label>   
			        <input id="trigger_name" name="trigger_name" class="easyui-textbox w_200"> 
			    </div> 
			    <div class="input_box">   
			        <label>执行类：</label>   
			         <select id="classid" name="classId" class="easyui-combobox" style="width:205px">   
					    <option value="1">解析任务</option>   
					    <option value="2">预警任务</option> 
					    <option value="3">定点资格任务</option>
					</select>  
			    </div>  
			</form>
		</div>
		
		<div id="quartzbtn2" class="easyui-dialog" title="表达式构建" style="width: 900px; height: 700px;" data-options="closed:true,modal:true">
			<iframe id="cronwindow" src="cron/index.htm" style="width:850px;height:600px"></iframe>
		</div>
		
		<div id="nfd1_btns">
			<a href="#" onclick="javascript: saveQuartz();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
			<a href="#" onclick="javascript: $('#quartzbtn').dialog('close');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</body>
</html>