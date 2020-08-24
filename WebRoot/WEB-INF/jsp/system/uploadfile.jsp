<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>上传日志</title>
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
		<style>
			.w_200{width:200px}
			#uploadfileform .input_box{text-align:center;margin-bottom:15px}
		</style>
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${path}/js/yiwei.js"></script>
		<script type="text/javascript">
			$(function() {
			    datagrid2("uploadfile", "getAllUploadfile");
			});
			
			//格式化解析状态
			function isHandle(value, row, index) {
			    if (value == 1) {
			        return '<img src="images/ok.png" title="解析成功" />';
			    } else if (value == -1){
			        return '<img src="images/cancel.png" title="解析失败" />';
			    } else {
			    	return '<img src="images/warning.png" title="等待解析" />';
			    }
			}

			//格式化是否加密
			function isEncrypt(value, row, index) {
			    if (value == 1) {
			        return '<img src="images/accept.png" />';
			    } else {
			        return '<img src="images/delete.png" />';
			    }
			}

			//格式化文件大小
			function filesize(val, row, index) {
				var num = val/1024;
				return num.toFixed(2)+" KB";
			}

			//格式化文件名称
			function filename(val, row, index){
				return "<span t itle='"+row.upFilepath+"'>"+val+"</span>";
			}

			//查询
			function findUploadfile(){
				var params = $('#uploadfile').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
				var fields =$('#form').serializeArray(); //自动序列化表单元素为JSON对象  
				$.each( fields, function(i, field){
					params[field.name] = field.value; //设置查询参数  
				});   
				$('#uploadfile').datagrid('reload'); //设置好查询参数 reload 
			} 

			//清空
			function clearForm() {
			    $("#cusName").textbox("setValue","");
			    $("#filename").textbox("setValue","");
			    $("#upFileflag").combobox("select","");
			    $("#isHandle").combobox("select","");
			}

			//查看文件内容
			function fileContent(){
				var array = $('#uploadfile').datagrid('getSelections'); // 获取选中项数组
				var len = array.length;
			    if (len > 1) {
			    	$.messager.alert('系统提示','只能选择一个文件查看','warning');
			    } else if (array.length < 1) {
			        $.messager.alert('系统提示','请选择需要查看的文件','warning');
			    } else{
					var path = array[0].upFilepath;
					var fileName = array[0].filename;
					var encrypt = array[0].isEncrypt;
					$.post("fileContent",{path:path,fileName:fileName,encrypt:encrypt}, function(data) {
						$('#uplcontent').dialog('open');
						$("#upltable").empty();
						$.each(data, function (i, item) {
							$("#upltable").append("<tr><td>"+item+"</td></tr>");
						});
					});
		        }
			}

			//解析选中文件
			function parseSelFile(){
				var array = $('#uploadfile').datagrid('getSelections'); // 获取选中项数组
				var ids = ""; // id字符串
		        for (var i = 0; i < array.length; i++) { 
	                if (i != array.length - 1) {
	                	ids += array[i].upId + ",";
	                } else {
	                	ids += array[i].upId;
	                }
		        }
		        if (array != "") {
                    $.messager.confirm('系统提示', '是否解析选中文件？', function(r) {
                       if (r) {
                           $.post("parseSelFile", {ids: ids}, function(data) {
                           		$('#uploadfile').datagrid('reload');
                            	$.messager.show({
			                        title: '系统提示',
			                        msg: data.status
			                    });
                           });
                       	}
   	                });
		        } else {
	                $.messager.alert("系统提示", "请选择要解析的文件！",'warning');
		        }
			}

			//解析全部文件
			function parseAllFile() {
			    $.messager.confirm('系统提示', '是否解析全部文件？',function(r) {
			        if (r) {
			        	$.post("parseAllFile",function(data) {
			        		$.messager.show({
		                        title: '系统提示',
		                        msg: data.status
		                    });
			            });
			        }
			    });
			}

			//删除文件
			function fileDelete(){
				var array = $('#uploadfile').datagrid('getSelections'); // 获取选中项数组
				var ids = ""; // id字符串
		        for (var i = 0; i < array.length; i++) { 
	                if (i != array.length - 1) {
	                	ids += array[i].upId + ",";
	                } else {
	                	ids += array[i].upId;
	                }
		        }
		        if (array != "") {
                    $.messager.confirm('系统提示', '是否要删除选中文件?', function(r) {
                        if (r) {
                            $.post("fileDelete", {ids: ids}, function(data) {
                            	$('#uploadfile').datagrid('reload');
                            	$.messager.show({
    		                        title: '系统提示',
    		                        msg: data.status
    		                    });
	                        });
                        }
	                });
		        } else {
	                $.messager.alert("系统提示", "请选择要删除的文件！",'warning');
		        }
			}

			//重名文件去重，只保留一条
			function repeatFile(){
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除同一客户的重名文件..."
				});	
				$.ajax({
					type: "POST",
			        url: "repeatFile",
			        success: function(data) {
						$('#uploadfile').datagrid('reload');
						$.messager.show({
	                        title: '系统提示',
	                        msg: data.status
	                    });
					}
				});
			}

			//删除已注销定点的文件
			function delCancleFile(){
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除已注销定点的文件..."
				});	
				$.ajax({
					type: "POST",
			        url: "delCancleFile",
			        success: function(data) {
						$('#uploadfile').datagrid('reload');
			        	$.messager.show({
							title: "系统提示",
			                msg: data.status
						});	
					}
				});
			}

			//定点上传统计生成
			function couUploadfile() {
			    $.messager.confirm('系统提示', '是否生成定点上传统计?', function(r) {
			        if (r) {
			            $.post("couUploadfile", function(data) {
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
			<div class="easyui-panel" title="文件解析" data-options="border:true,fit:true" iconCls="icon-zc">
				<table id="uploadfile" data-options="checkOnSelect:true,singleSelect:false,fit:true,rownumbers:true,pagination:true" width="95%" cellpadding="2" cellspacing="1" toolbar="#toolbar">
                   	<thead>
						<tr>
							<th data-options="field:'sb',checkbox:true"></th>
							<th field="upId" align="center">文件编号</th>
							<th field="cusName">定点名称</th>
							<th field="upFilepath" editor="{type:'text'}" hidden="true">存放路径</th>
							<th field="filename" editor="{type:'text'}" formatter="filename">文件名称</th>
							<th field="upDate" editor="{type:'datetimebox'}">上传日期</th>
							<th field="upFileflag" editor="{type:'text'}" formatter="fmtUpFileflag" align="center">文件类型</th>
							<th field="isEncrypt" align="center" editor="{type:'text'}" formatter="isEncrypt">是否加密</th>
							<th field="filesize" editor="{type:'text'}" formatter="filesize" align="right">文件大小</th>
							<th field="upCollectiondate" editor="{type:'datetimebox'}">采集日期</th>
							<th field="isHandle" align="center" editor="{type:'text'}" formatter="isHandle">解析状态</th>
							<th field="upName" editor="{type:'text'}">解析结果</th>
							<th field="upCount" editor="{type:'numberbox'}" align="right">成功条数</th>
						</tr>
					</thead>
				</table> 
			</div>
     		<div id="toolbar" style="width: 100%; height: 25px; padding: 2px 0;">
				<div class="barbox1-1">
					<form id="form" onkeydown="if (event.keyCode == 13) {findUploadfile()}">
						<div style="float:left;margin:0 2px;">
							<select id="upFileflag" name="upFileflag" class="easyui-combobox" data-options="width:150,panelHeight:'auto'" >
								<option value="" selected="true">全部类型</option>
								<option value="102,202,303">X 项目编码</option>
								<option value="103,203,304">G 供应商/生产商信息</option>
								<option value="104,204,305">Y 医护人员信息</option>
								<option value="106,206,307">R 入库信息</option>
								<option value="112,212,316">C 出库信息</option>
								<option value="116,216,318">K 库存信息</option>
								<option value="108,208,317">X 销售信息</option>
								<option value="114,214,314">K 库房信息</option>
								<option value="110,210">C 处方信息</option>
								<option value="101,113">F 分店信息</option>
								<option value="308">Z 住院记录</option>
								<option value="309">C 出院记录</option>
								<option value="310">Y 医嘱信息</option>
								<option value="319">Y 医嘱执行记录</option>
								<option value="115,215,315">K 科室信息</option>
								<option value="313">Z 诊断记录</option>
								<option value="325">B 病案首页(概要)</option>
								<option value="326">B 病案首页(出院诊断)</option>
								<option value="327">B 病案首页(手术操作)</option>
								<option value="321">J 检查、检验结果</option>
								<option value="322">J 检查、检验结果详细</option>
							</select>
						</div>
						<div style="float:left;margin:0 2px;">
							<select id="isHandle" name="isHandle" class="easyui-combobox" data-options="width:100,panelHeight:'auto'" >
								<option value="" selected="true">全部状态</option>
								<option value="0">= 等待解析</option>
								<option value="1">√ 解析成功</option>
								<option value="-1">× 解析失败</option>
							</select>
						</div>
						<div style="float:left;margin:0 2px;">
							<a href="#" class="easyui-linkbutton" onclick="findUploadfile();" iconCls="icon-search" >查询</a>
						</div>
						<div style="float:left;margin:0 2px;">
							<input id="cusName" name="cusName" class="easyui-textbox" data-options="prompt:'定点名称/定点编码'" />
						</div>
						<div style="float:left;margin:0 2px;">
							<input id="filename" name="filename" class="easyui-textbox" data-options="prompt:'文件名称'" />
						</div>
						<div style="float:left;margin:0 2px;">
							<a href="#" class="clear" onClick="clearForm()"><img style="margin-top: 2px;" class="cl-xx-child" src="images/xx.png" /></a>
						</div>
					</form>
					<a href="#" class="easyui-linkbutton" onclick="findUploadfile();" iconCls="icon-search" >查询</a>
					<a href="#" class="easyui-linkbutton" onclick="fileContent();" iconCls="icon-ok" style="padding: 0 10px; margin: 0 2px;">查看文件内容</a>
					<a href="#" class="easyui-linkbutton" onclick="parseSelFile();" iconCls="icon-ok" style="padding: 0 10px; margin: 0 2px;">解析选中文件</a>
					<a href="#" class="easyui-linkbutton" onclick="parseAllFile();" iconCls="icon-ok" style="padding: 0 10px; margin: 0 2px;">解析全部文件</a>
					<a href="#" class="easyui-linkbutton" onclick="fileDelete();" iconCls="icon-remove" style="padding: 0 10px; margin: 0 2px;">删除文件</a>
					<a href="#" class="easyui-linkbutton" onclick="repeatFile();" iconCls="icon-cancel" style="padding: 0 10px; margin: 0 2px;">删除重名文件</a>
					<a href="#" class="easyui-linkbutton" onclick="delCancleFile();" iconCls="icon-remove" style="padding: 0 10px; margin: 0 2px;">删除已注销定点的文件</a>
					<a href="#" class="easyui-linkbutton" onclick="couUploadfile();" iconCls="icon-ok" style="padding: 0 10px; margin: 0 2px;">定点上传统计生成</a>
				</div>
			</div>
		</div>
		
		<!-- 查看文件内容 -->
		<div id="uplcontent" class="easyui-dialog" title="查看文件内容" style="width: 1280px; height:720px;" data-options="buttons:'#uplcontent_btn',closed:true,modal:true">
			<table class="ew_table" style="height: auto;">
				<tbody id="upltable" align="left"></tbody>
			</table>
		</div>
		<div id="uplcontent_btn">
			<a href="javascript:void(0)" onclick="javascript: $('#uplcontent').dialog('close');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
		</div>
		
	</body>
</html>