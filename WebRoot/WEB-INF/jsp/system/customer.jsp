<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>客户管理</title>
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
		<style>
			.w_200{width:200px}
			#form .input_box{text-align:center;margin-bottom:15px}
		</style>
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src="${path}/js/yiwei.js"></script>
		<script type="text/javascript">
			$(function() {
			    datagrid2("customer", "getAllCustomer");
			});
	
			// 根据值显示图片
			function fmtStatus(value, row, index) {
			    if (value == 1 || value == 2) { // 这里是判断哪些行
			        return '<img src="images/accept.png" />';
			    } else {
			        return '<img src="images/warning.png" />';
			    }
			}
	
			// 显示定点类型
			function fmtFlag(val , row, index){
				if(val==101){
					return '单体药店';
				}else if(val==102){
					return '连锁药店';
				}else if(val==201){
					return '门诊';
				}else {
					return '医院';
				}
			}

			//显示区县
			function fmtArea(value, row, index) {
				var cusId = row.cusId;
				var text;
				$.ajax({
					type: "POST",
					url:"cusArea",
					data:{cusId:cusId},
					async: false,//默认true异步，false同步
					success: function(data){
						text = data.areaName;
					}
				})
				if(text==null){
					return "<font color='red'>请设置区划</font>";
				} else {
					return text;
				}
			}

			//查询
			function findUser() {
			    var params = $('#customer').datagrid('options').queryParams;// 先取得 datagrid 的查询参数
			    var fields = $('#searchForm').serializeArray(); // 自动序列化表单元素为JSON对象
			    $.each(fields, function(i, field) {
			        params[field.name] = field.value; // 设置查询参数
			    });
			    $('#customer').datagrid('reload'); // 设置好查询参数 reload
			}
	
			//清空
			function clearForm() {
			    $("#searchname").textbox('setValue','');
			    $("#searchAreacode").combobox("select","");
			    $("#searchCusFlag").combobox("select","");
			}

			//检查上传情况
			function checkUpload(){
				var array = $('#customer').datagrid('getSelections'); // 获取选中的行
			    if (array.length > 1) {
			    	$.messager.alert('系统提示','只能检查一个定点的上传情况','warning');
			    } else if (array.length < 1) {
			        $.messager.alert('系统提示','请选择需要检查的定点','warning');
			    } else {
			    	$.messager.show({
	                 	title: '系统提示',
	                    msg: "正在检查定点上传情况，请稍候"
		             });
			        var cusId = $('#customer').datagrid('getSelected').cusId; 
			        $.post("checkUpload",{cusId:cusId}, function(data) { 
						 $.messager.show({
		                 	title: '系统提示',
		                    msg: data.status
			             });	
					});
			    }
			}
			
			//打开区划设置
			function openAreacus() {
				var array = $('#customer').datagrid('getSelections');
				if (array.length < 1) {
					$.messager.alert('系统提示', '请选择需要设置区划的定点','warning');
				} else {
					$('#qhsz').dialog('open').dialog('center');
				}
			}

			//保存区划设置
			function saveQh(){
				var areacode = $("input[name='areacode']:checked").val();//获取选择的区划编号
				var array = $('#customer').datagrid('getSelections');//获取选择的数组
				var ids = "";//cusid字符串
				for ( var i = 0; i < array.length; i++) {// 组成一个字符串，ID主键之间用逗号隔开
					if (i != array.length - 1) {
						ids += array[i].cusId + ",";
					} else {
						ids += array[i].cusId;
					}
				}
				if(areacode==null){
					$.messager.alert('系统提示', '请选择区划！','warning');
				} else {
					$('#qhsz').dialog('close');
					$.post("addAreacus", {
						ids : ids,
						areacode : areacode
					}, function(data) { 
							$("#customer").datagrid('reload');
							$.messager.show( {
								title : '系统提示',
								msg : data.status
					   		});
					});
				}
			}

			//修改定点信息
			function updateCustomer() {
			    var array = $('#customer').datagrid('getSelections'); // 获取选中的行
			    if (array.length > 1) {
			    	$.messager.alert('系统提示','只能选择一个定点进行修改','warning');
			    } else if (array.length < 1) {
			        $.messager.alert('系统提示','请选择需要修改的定点','warning');
			    } else {
			        var row = $('#customer').datagrid('getSelected'); 
			        $('#updateCus').dialog('open').dialog('center').dialog('setTitle', '修改定点信息');
			        $('#form').form('load', row);
				}
			}

		  	//保存定点信息
			function saveCus() {
			    $.ajax({
			        cache: true,
			        type: "POST",
			        url: 'updateCustomer',
			        data: $('#form').serialize(),
			        success: function(data) {
			            $.messager.show({
			                title: '系统提示',
			                msg: data.status
			            });
			            $('#customer').datagrid('reload');
			            $('#updateCus').dialog('close');
			        },
			        error: function(request) {
			            $.messager.alert('系统提示','操作错误','error');
			        }
			    });
			}

		  	//修改定点状态(通过审核或注销)
		  	function updateStatus(type){
		  		var array = $('#customer').datagrid('getSelections');
			    var ids = "";
			    for (var i = 0; i < array.length; i++) {
			        if (i != array.length - 1) {
			            ids += array[i].cusId + ",";
			        } else {
			            ids += array[i].cusId;
			        }
			    }
			    var info;
			    if(type==1){
			    	info="是否通过审核所选定点？";
			    } else {
			    	info="是否注销所选定点？";
			    }
			    if (array != "") {
			    	 $.messager.confirm('系统提示', info, function(r) {
			            if (r) {
			                $.post("updateCustomerStatus", {
			                    ids: ids,type:type
			                },
			                function(data) {
			                    $("#customer").datagrid('reload');
			                    $.messager.show({
			                        title: '系统提示',
			                        msg: data.status
			                    });
			                });
			            }
		        	 });
			    } else {
			    	$.messager.alert('系统提示','请至少选择一个定点','warning');
			    }
		  	}

		  	//删除已注销定点
			function delCusCancle(){
				$.messager.show({
					title: "系统提示",
	                msg: "正在删除已注销定点..."
				});	
				$.ajax({
					type: "POST",
			        url: "delCusCancle",
			        success: function(data) {
						$("#customer").datagrid('reload');
			        	$.messager.show({
							title: "系统提示",
			                msg: data.status
						});	
					}
				});
			}
			
		  	//显示当前定点进销存数据
			function fmtCusname(value,row,index){
				var value2 = escape(value);//escape编码（防止空格）,unescape解码
				var cusId = row.cusId;
				var cusFlag = row.cusFlag;
				var url = "getCustomerList?cusId="+cusId+"&cusFlag="+cusFlag;
				return "<a href='javascript:void(0)' onclick=show('"+url+"','"+value2+"'); >"+unescape(value2)+"</a>";
			}
			
			//添加新选项卡显示进销存数据
			function show(url,value){
				window.parent.addTab(unescape(value), url);//调用父类方法添加选项卡
			}
			
		</script>
	</head>
	<body>
		<div class="easyui-layout" data-options="fit:'true',">
			<div class="easyui-panel" title="定点信息" data-options="border:true,fit:true" iconCls="icon-zc">
				<table id="customer" data-options="checkOnSelect:true,singleSelect:false,fit:true,rownumbers:true,pagination:true" cellpadding="2" cellspacing="1" toolbar="#toolbar">
					<thead>
						<tr>
							<th data-options="field:'sb',checkbox:true"></th>
							<th field="cusParentid" hidden=true align="center" editor="{type:'text'}">客户上级ID</th>
							<th field="cusRegip" hidden=true align="center" editor="{type:'text'}">注册ip</th>
							<th field="cusUniqure" hidden=true align="center" editor="{type:'text'}">唯一标识</th>
							<th data-options="field:'cusName',align:'left',width:280,formatter:fmtCusname">定点名称</th>
							<th data-options="field:'cusDareway',align:'center',width:80">定点编码</th>
							<th field="cusId" align="center">客户ID</th>
							<th field="cusBranchcode" align="center" editor="{type:'text'}">分店编码</th>
							<th data-options="field:'cusPcode',align:'left',width:150, hidden:true">简码</th>
							<th data-options="field:'b',align:'center',formatter:fmtArea">所在区县</th>
							<th data-options="field:'cusAddr',align:'left',width:280">联系地址</th>
							<th data-options="field:'cusContact',align:'center',width:80">联系人</th>
							<th data-options="field:'cusPhone',align:'center',width:100">联系电话</th>									
							<th data-options="field:'cusRegdate',align:'center'" editor="{type:'datetimebox'}">注册日期</th>
							<th	data-options="field:'cusFlag',align:'center',width:80,formatter:fmtFlag">定点类型</th>
							<th data-options="field:'cusStatus',align:'center',width:60,formatter:fmtStatus">状态</th>
							<th data-options="field:'cusRemark',align:'center',width:60">备注</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="toolbar" style="width: 100%; height: 25px; padding: 2px 0;">
				<div class="barbox1-1">
					<form id="searchForm" onkeydown="if(event.keyCode==13){queryForm();}">
						<div style="float:left;margin:0 2px;">
							<select id="searchAreacode" name="acAreacode" class="easyui-combobox" style="text-align: center" data-options="width:100,panelHeight:'auto'" >
								<option value="" selected="true">全部区县</option>
								<c:forEach items="${areacodeList}" var="areacode">
									<option value="${areacode.acAreacode }">${areacode.acAreaname }</option>
								</c:forEach>
								<option value="-1">未划分区县</option>
							</select>
						</div>
						<div style="float:left;margin:0 2px;">
							<select id="searchCusFlag" name="cusFlag" class="easyui-combobox" style="text-align: center" data-options="width:100,panelHeight:'auto'" >
								<option value="" selected="true">全部类型</option>
								<option value="101">单体药店</option>
								<option value="102">连锁药店</option>
								<option value="201">门诊</option>
								<option value="301">医院</option>
							</select>
						</div>
						<div style="float:left;margin:0 2px;">
							<input id="searchname" name="cusName" class="easyui-searchbox" data-options="searcher:findUser,prompt:'请输入查询条件',"></input>
						</div>
						<div style="float:left;margin:0 2px;">
							<a href="#" class="clear" onClick="clearForm()"><img style="margin-top: 2px;" class="cl-xx-child" src="images/xx.png" /></a>
						</div>
					</form>
					<a href="#" class="easyui-linkbutton" onClick="checkUpload()" iconCls="icon-zoom" style="padding: 0 10px; margin: 0 2px;">检查上传情况</a>
					<a href="#" class="easyui-linkbutton" onClick="openAreacus()" iconCls="icon-xqwh" style="padding: 0 10px; margin: 0 2px;">区划设置</a>
					<a href="#" class="easyui-linkbutton" onClick="updateCustomer()" iconCls="icon-edit" style="padding: 0 10px; margin: 0 2px;">修改信息</a>
					<a href="#" class="easyui-linkbutton" onClick="updateStatus('1')" iconCls="icon-ok" style="padding: 0 10px; margin: 0 2px 0 0;">通过审核</a>
					<a href="#" class="easyui-linkbutton" onClick="updateStatus('0')" iconCls="icon-cancel" style="padding: 0 10px; margin: 0 2px;">注销定点</a>
					<a href="#" class="easyui-linkbutton" onClick="delCusCancle()" iconCls="icon-remove" style="padding: 0 10px; margin: 0 2px;">删除已注销定点</a>
				</div>
			</div>
		</div>
		
		<!-- 区划设置 -->
		<div id="qhsz" class="easyui-dialog" title="区划设置" style="width: 300px; height:400px;" data-options="iconCls:'icon-xqwh',buttons:'#qhsz_btns',closed:true,modal:true,">
	    	<c:forEach items="${areacodeList}" var="areacode">
	    		<div style="width:200px;display:inline-block;text-align:left;margin:5px 10px;">
					<input name="areacode" type="radio" value="${areacode.acAreacode }"/>
					<span>${areacode.acAreaname }</span> 
		        </div> 
			</c:forEach>   
		</div>
		<div id="qhsz_btns">
			<a href="javascript:void(0)" onclick="saveQh()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
			<a href="javascript:void(0)" onclick="javascript: $('#qhsz').dialog('close');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
		</div>
		
		<!-- 修改定点信息 -->
		<div id="updateCus" class="easyui-dialog" title="修改定点信息" style="width: 430px; height:420px;" data-options="iconCls:'icon-edit',buttons:'#updateCus_btns',closed:true,modal:true,">
			<form id="form">
				<div class="input_box">   
			        <h1 style="margin:25px 0 20px 0">修改定点信息</h1>
			    </div>  
				<div class="input_box">   
			        <label>定点名称：</label>   
			        <input id="cusName" name="cusName" class="easyui-textbox w_200"> 
			    </div>  
			    <div class="input_box">   
			        <label>定点编码：</label>   
			        <input id="cusDareway" name="cusDareway"  class="easyui-textbox w_200"> 
			    </div> 
			    <div class="input_box">   
			        <label>联系地址：</label>
			        <input id="cusAddr" name="cusAddr" class="easyui-textbox w_200"> 
			    </div>  
			    <div class="input_box">   
			        <label>联系人：</label>   
			        <input id="cusContact" name="cusContact" class="easyui-textbox w_200"> 
			    </div>  
			    <div class="input_box">   
			        <label>联系电话：</label>   
			        <input id="cusPhone" name="cusPhone" class="easyui-textbox w_200"> 
			    </div>
			    <div class="input_box">   
			        <label>备注：</label>   
			        <input id="cusRemark" name="cusRemark" class="easyui-textbox w_200"> 
			    </div>
			    <div class="input_box">   
			        <label>拼音简码：</label>   
			        <input id="cusPcode" name="cusPcode" class="easyui-textbox w_200"> 
			    </div>
			    <!-- 不修改的字段保持原值 -->
				<input id="cusId" name="cusId" type="hidden" value="" />
				<input id="cusParentid" name="cusParentid" type="hidden" value="" />
				<input id="cusRegip" name="cusRegip" type="hidden" value="" />
				<input id="cusFlag" name="cusFlag" type="hidden" value="" />
				<input id="cusUniqure" name="cusUniqure" type="hidden" value="" />
				<input id="cusRegdate" name="cusRegdate" type="hidden" value="" />
				<input id="cusBranchcode" name="cusBranchcode" type="hidden" value="" />
				<input id="cusStatus" name="cusStatus" type="hidden" value="" />
			</form>
		</div>
		<div id="updateCus_btns">
			<a href="javascript:void(0)" onclick="saveCus()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
			<a href="javascript:void(0)" onclick="javascript: $('#updateCus').dialog('close');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
		</div>
		
	</body>
</html>
