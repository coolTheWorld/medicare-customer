<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>定点结算</title>
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
		<style>
			.w_200{width:300px}
			#form .input_box{text-align:center;margin-bottom:15px}
		</style>
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${path}/js/yiwei.js"></script>
		<script type="text/javascript">
			$(function() {
			    datagrid2("tzddlog", "getAllTzddlog");
			})
			
			//显示定点名称
			function tzCusname(val, row, index) {
				if(row.tzStatus==0 && row.tzOperate==0){
					return "<font color='red'>" + val + "</font>";
				} else {
					return val;
				}
			}

			//显示操作日志
			function tzNote(val, row, index) {
				if(row.tzStatus==0 && row.tzOperate==0){
					return "<font color='red'>" + val + "</font>";
				} else {
					return val;
				}
			}

			//显示定点编码
			function tzYybm(val, row, index) {
				if(row.tzStatus==0 && row.tzOperate==0){
					return "<font color='red'>" + val + "</font>";
				} else {
					return val;
				}
			}

			//显示操作时间
			function tzDate(val, row, index) {
				if(row.tzStatus==0 && row.tzOperate==0){
					return "<font color='red'>" + val + "</font>";
				} else {
					return val;
				}
			}
	
			//显示定点资格操作类型
			function tzOperate(val, row, index) {
				if(val==0){
					return "<img src='easyui/themes/icons/node.png' title='暂停医保结算' />";
				} else if(val==1){
					return "<img src='easyui/themes/icons/accept.png' title='开启医保结算' />";
				} else if(val == 9) {
			        return "<font color='blue'>初始化</font>";
			    } else {
			    	return val;
			    }
			}

			//显示地维响应结果
			function tzStatus(val, row, index) {
				if (val == 0) { 
			        return '<img src="images/ok.png"/>';
			    } else if(val == 1) {
			        return '<img src="images/cancel.png" />';
			    } else if(val == 9) {
			        return "<font color='blue'>初始化</font>";
			    } else {
			    	return val;
			    }
			}

			//显示扫描
			function tzScan(val, row, index) {
				if (val == 0) { 
			        return '<img src="easyui/themes/icons/star.png"/>';
			    } else {
			        return '<img src="easyui/themes/icons/stop.png" />';
			    }
			}

			//显示医保标记
			function tzYbcx(val, row, index) {
				if (val == 0) { 
			        return '<img src="easyui/themes/icons/sign.png"/>';
			    } else {
			        return '<img src="easyui/themes/icons/nosign.png" />';
			    }
			}
			//查询
			function findTzddlog() {
				var tzCusname = $("input[name='tzCusname']").val();
				$('#tzddlog').datagrid({
					url : 'getAllTzddlog',
					queryParams:{tzCusname:tzCusname}
				});
			}
	
			//清空输入框
			function clearForm() {
				$('#searchForm').form('clear');
			}

			//开关医保结算
			function updateTzOperate(operate){
				var array = $('#tzddlog').datagrid('getSelections');
			    if (array.length > 1) {
			    	$.messager.alert('系统提示','只能选择一个定点进行操作','warning');
			    } else if (array.length < 1) {
			        $.messager.alert('系统提示','请选择需要操作的定点','warning');
			    } else {
			    	var info;
				    if(operate==1){
				    	info="是否开启该定点的医保结算？";
				    } else {
				    	info="是否暂停该定点的医保结算？";
				    }
			    	$.messager.confirm('系统提示', info, function(r) {
						if (r) {
							var tzId = $('#tzddlog').datagrid('getSelected').tzId;
							$.post("updateTzOperate", {
								tzId : tzId, operate:operate
							}, 
							function(data) {
								$('#tzddlog').datagrid('reload');
								$.messager.show({
				                    title: '系统提示',
				                    msg: data.status
				                });	
							});
						}
					});
			    }
			}
			
			//查找已暂停医保结算定点
			function getTzOperateStop() {
				$('#tzddlog').datagrid( {
					url : 'getTzOperateStop'
				});
			}

			//开关扫描
			function updateTzScan(scan) {
				var array = $('#tzddlog').datagrid('getSelections');
				var ids = "";
				for (var i = 0; i < array.length; i++) {
					if (i != array.length - 1) {
						ids += array[i].tzId + ",";
					} else {
						ids += array[i].tzId;
					}
				}
				if (array != "") {
					var info;
				    if(scan==0){
				    	info="是否开启选中定点的扫描？";
				    } else {
				    	info="是否停止选中定点的扫描？";
				    }
					$.messager.confirm('系统提示', info, function(r) {
						if (r) {
							$.post("updateTzScan", {
								ids : ids, scan : scan
							}, 
							function(data) { 
								$('#tzddlog').datagrid('reload');
								$.messager.show({
				                    title: '系统提示',
				                    msg: data.status
				                });	
							});
						}
					});
				} else {
					$.messager.alert("系统提示", "请选择要操作的定点", "warning");
				}
			}

			//查询已停止扫描定点
			function getTzScanStop() {
				$('#tzddlog').datagrid( {
					url : 'getTzScanStop'
				});
			}

			//开关医保标记
			function updateTzYbcx(ybcx) {
				var array = $('#tzddlog').datagrid('getSelections');
				var ids = "";
				for ( var i = 0; i < array.length; i++) {
					if (i != array.length - 1) {
						ids += array[i].tzId + ",";
					} else {
						ids += array[i].tzId;
					}
				}
				if (array != "") {
					var info;
				    if(ybcx==0){
				    	info="是否标记选中定点的医保？";
				    } else {
				    	info="是否取消选中定点的医保标记？";
				    }
					$.messager.confirm('系统提示', info, function(r) {
						if (r) {
							$.post("updateTzYbcx", {
								ids : ids, ybcx : ybcx
							}, 
							function(data) { 
								$('#tzddlog').datagrid('reload');
								$.messager.show({
				                    title: '系统提示',
				                    msg: data.status
				                });	
							});
						}
					});
				} else {
					$.messager.alert("系统提示", "请先选择要操作的定点！", "warning");
				}
			}

			//查询使用医保程序定点
			function getTzYbcxStart() {
				$('#tzddlog').datagrid( {
					url : 'getTzYbcxStart'
				});
			}

			//添加定点
			function addTzddlog() {
				$.post("addTzddlog", null, function(data) {// 前台没有返回json,里面就没有data
					$('#tzddlog').datagrid('reload');
					$.messager.show({
	                    title: '系统提示',
	                    msg: data.status
	                });
				});
			}

			//修改定点
			function updateTzddlog(){
				var array = $('#tzddlog').datagrid('getSelections'); // 获取选中的行
			    if (array.length > 1) {
			    	$.messager.alert('警告','只能选择一个定点进行修改','warning');
			    } else if (array.length < 1) {
			        $.messager.alert('警告','请选择需要修改的定点','warning');
			    } else {
			        var row = $('#tzddlog').datagrid('getSelected'); 
			        $('#updateTzdd').dialog('open').dialog('center').dialog('setTitle', '修改定点信息');
			        $('#form').form('load', row);
				}
			}

			//保存定点信息
			function saveTzdd() {
			    $.ajax({
			        cache: true,
			        type: "POST",
			        url: 'updateTzddlog',
			        data: $('#form').serialize(),
			        success: function(data) {
			            $.messager.show({
			                title: '系统提示',
			                msg: data.status
			            });
			            $('#tzddlog').datagrid('reload');
			            $('#updateTzdd').dialog('close');
			        },
			        error: function(request) {
			            $.messager.alert('警告','操作错误','error');
			        }
			    });
			}

			//删除定点
			function deleteTzddlog() {
				var array = $('#tzddlog').datagrid('getSelections');// 获取删除选中项数组
				var ids = "";// 要删除的id字符串
				for ( var i = 0; i < array.length; i++) {// 组成一个字符串，ID主键之间用逗号隔开
					if (i != array.length - 1) {
						ids += array[i].tzId + ",";
					} else {
						ids += array[i].tzId;
					}
				}
				if (array != "") {
					$.messager.confirm('系统提示', '是否要删除选中的定点?', function(r) {
						if (r) {
							$.post("deleteTzddlog", {
								ids : ids
							}, 
							function(data) { // 前台没有返回json,里面就没有data
								$('#tzddlog').datagrid('reload');
								$.messager.show({
				                    title: '系统提示',
				                    msg: data.status
				                });	
							});
						}
					});
				} else {
					$.messager.alert("系统提示", "请先选择要删除的定点！", "warning");
				}
			}

			//停止定点
			function stopTzddlog(){
				$.messager.confirm('系统提示', '是否停止不符合数据上传要求的定点的医保结算?', function(r) {
			        if (r) {
			            $.post("stopTzddlog", function(data) {
			            	$.messager.show({
		                        title: '系统提示',
		                        msg: data.status
		                    });
			            });
			        }
			    });
			}
			
		</script>
	</head>
	<body>
		<div class="easyui-layout" data-options="fit:'true',">
			<div class="easyui-panel" title="定点结算" data-options="border:true,fit:true" iconCls="icon-zc">
				<table id="tzddlog"  data-options="checkOnSelect:true,singleSelect:true,fit:true,rownumbers:true,pagination:true" width="95%" cellpadding="2" cellspacing="1" toolbar="#toolbar">
					<thead>
						<tr>
                            <th data-options="field:'tzId',hidden:'true',align:'center'">定点资格ID</th>
                            <th data-options="field:'tzCusId',hidden:'true',align:'center'">定点ID</th>    
                            <th data-options="field:'tzCusname',align:'left',formatter:tzCusname">定点名称</th> 
                            <th data-options="field:'tzYybm',align:'center',formatter:tzYybm">定点编码</th>
                            <th data-options="field:'tzDate',align:'center',formatter:tzDate">操作时间</th> 
                            <th data-options="field:'tzOperate',align:'center',formatter:tzOperate">医保结算操作</th>  
                            <th data-options="field:'tzStatus',align:'center',formatter:tzStatus">地纬响应结果</th>   
                            <th data-options="field:'tzScan',align:'center',formatter:tzScan">是否扫描</th>   
                            <th data-options="field:'tzYbcx',align:'center',formatter:tzYbcx">医保标记</th>   
                            <th data-options="field:'tzNote',align:'left',formatter:tzNote">操作日志</th>                            
						</tr>   
                      </thead>
                  </table> 
			</div>
			<div id="toolbar" style="width:100%;height:25px; padding:2px 0; position:relative;" >
				<div class="barbox1-1">
					<form id="searchForm" onkeydown="if(event.keyCode==13){ queryForm();}">
						<div style="float:left;margin:0 2px;">
							<input id="searchname" name="tzCusname" class="easyui-searchbox" AUTOCOMPLETE="off" data-options="searcher:findTzddlog,prompt:'请输入查询条件'" />
						</div>
						<div style="float:left;margin:0 2px;">
							<a href="#" class="clear" onClick="clearForm()"><img style="margin-top: 2px;" class="cl-xx-child" src="images/xx.png" /></a>
						</div>
					</form>
				</div>
				<!-- 开关医保结算 -->
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-accept" onclick="updateTzOperate('1');" style="float: left;">开启医保结算</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-node" onclick="updateTzOperate('0');" style="float: left;">暂停医保结算</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getTzOperateStop();" style="float: left;color:#f00">查找已暂停医保结算定点</a>                   
                <a href="javascript:void(0);" class="datagrid-btn-separator" style="float: left;"></a>
                <!-- 开关扫描 -->
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-star" onclick="updateTzScan(0);" style="float: left;">开启扫描</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-stop" onclick="updateTzScan(1);" style="float: left;">停止扫描</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getTzScanStop();" style="float: left;">查询已停止扫描定点</a>
	            <a href="javascript:void(0);" class="datagrid-btn-separator" style="float: left;"></a>
	            <!-- 开关医保标记 -->   
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-sign" onclick="updateTzYbcx(0);" style="float: left;">标记医保</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-nosign" onclick="updateTzYbcx(1);" style="float: left;">取消医保标记</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getTzYbcxStart();" style="float: left;">查询使用医保程序定点</a>
				<a href="javascript:void(0);" class="datagrid-btn-separator" style="float: left;"></a>
				<!-- 添加修改删除定点 -->
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addTzddlog();" style="float: left;">添加定点</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onClick="updateTzddlog()" style="float: left;">修改定点</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteTzddlog();" style="float: left;">删除定点</a>
                <a href="javascript:void(0);" class="datagrid-btn-separator" style="float: left;"></a>
                <!-- 停止定点 -->
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-node" onclick="stopTzddlog();" style="float: left;">定时停止定点</a>
			</div>
       </div>	
       
       <!-- 修改定点信息 -->
		<div id="updateTzdd" class="easyui-dialog" title="修改定点信息" style="width: 430px; height:200px;" data-options="iconCls:'icon-edit',buttons:'#updateTzdd_btn',closed:true,modal:true,">
			<form id="form">
				<div class="input_box">   
			        <h1 style="margin:25px 0 20px 0">修改定点信息</h1>
			    </div>  
				<div class="input_box">   
			        <label>定点名称：</label>   
			        <input id="tzCusname" name="tzCusname" class="easyui-textbox w_200"> 
			    </div>  
			    <!-- 不修改的字段保持原值 -->
				<input id="tzId" name="tzId" type="hidden" value="" />
				<input id="tzCusid" name="tzCusid" type="hidden" value="" />
				<input id="tzYybm" name="tzYybm" type="hidden" value="" />
				<input id="tzDate" name="tzDate" type="hidden" value="" />
				<input id="tzOperate" name="tzOperate" type="hidden" value="" />
				<input id="tzStatus" name="tzStatus" type="hidden" value="" />
				<input id="tzScan" name="tzScan" type="hidden" value="" />
				<input id="tzYbcx" name="tzYbcx" type="hidden" value="" />
				<input id="tzNote" name="tzNote" type="hidden" value="" />
			</form>
		</div>
		<div id="updateTzdd_btn">
			<a href="javascript:void(0)" onclick="saveTzdd()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
			<a href="javascript:void(0)" onclick="javascript: $('#updateTzdd').dialog('close');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
		</div>
		
	</body>
</html>