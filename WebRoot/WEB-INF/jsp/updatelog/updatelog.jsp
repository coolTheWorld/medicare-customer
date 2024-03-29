<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>客户端升级日志表</title>
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<!-- 中文分页 -->
		<script src="${path}/easyui/ajaxfileupload.js"></script>
		<script type="text/javascript">
			$(function() {
				var datagrid;
				var editRow = undefined; // 定义全局变量,当前编辑的行
				datagrid = $('#updatelog').datagrid( {
					title : '客户端升级日志表',
					iconCls : 'icon-ok',
					idField : 'ulId',// 主键
					pageSize : 20,// 默认选择的分页是每页5行数据
					pageList : [ 5, 10, 20, 50 ],// 可以选择的分页集合
					nowrap : false,// true,列内容多时自动折至第二行
					striped : true,// 设置为true将交替显示行背景。
					collapsible : false,// 显示可折叠按钮
					url : 'getAllUpdatelog',
					loadMsg : '数据装载中......',
					singleSelect : false,// 为true时只能选择单行,无法批量删除
					fit : true, // datagrid自适应宽度
					fitColumns : true,// 允许表格自动缩放，以适应父容器，防止水平滚动
					sortName : 'ulId',// 当数据表格初始化时以哪一列来排序
					sortOrder : 'desc',// 定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
					remoteSort : false,// 定义从服务器对数据进行排序。
					checkbox : true,
					pagination : true,// 分页
					rownumbers : false, // 行数
					toolbar : [ {
						text : '添加',
						iconCls : 'icon-add',
						handler : function() {// 添加列表的操作按钮添加，修改，删除等
							// 添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
						if (editRow != undefined) {
							datagrid.datagrid("endEdit", editRow);
						}
						// 添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
						if (editRow == undefined) {
							datagrid.datagrid("insertRow", {
								index : 0, // index start with 0
								row : {
	
								}
							});
							// 将新插入的那一行开启编辑状态
							datagrid.datagrid("beginEdit", 0);
							// 给当前编辑的行赋值
							editRow = 0;
						}
	
					}
					}, '-', {
						text : '修改',
						iconCls : 'icon-edit',
						handler : function() {
							// 修改时要获取选择到的行
						var rows = datagrid.datagrid("getSelections");
						// 如果只选择了一行则可以进行修改，否则不操作
						if (rows.length == 1) {
							// 修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
						if (editRow != undefined) {
							datagrid.datagrid("endEdit", editRow);
						}
						// 当无编辑行时
						if (editRow == undefined) {
							// 获取到当前选择行的下标
							var index = datagrid.datagrid("getRowIndex", rows[0]);
							// 开启编辑
							datagrid.datagrid("beginEdit", index);
							// 把当前开启编辑的行赋值给全局变量editRow
							editRow = index;
							// 当开启了当前选择行的编辑状态之后，
							// 应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
							datagrid.datagrid("unselectAll");
						}
					}
				}
					}, '-', {
						text : '保存',
						iconCls : 'icon-save',
						handler : function() {
							// 保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
						// alert(editRow);//显示当前第几行数据
						datagrid.datagrid("endEdit", editRow);
	
						// 使用JSON序列化datarow对象，发送到后台。
						var rows = datagrid.datagrid('getChanges');
						var json = JSON.stringify(rows);
						// alert("json:" + json);
						$.post("addOrUpdateUpdatelog", {
							json : json
						}, function(data) {
							if (data.status == "add") {
								$.messager.alert('提示', "保存成功!", 'info');
								datagrid.datagrid('reload');
							} else if (data.status == "update") {
								$.messager.alert('提示', "修改成功!", 'info');
								datagrid.datagrid('reload');
							}
						}, "json");
					}
					}, '-', {
						text : '取消(编辑/选择)',
						iconCls : 'icon-redo',
						handler : function() {
							// 取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
						editRow = undefined;
						datagrid.datagrid("rejectChanges");
						datagrid.datagrid("unselectAll");
					}
					}, '-', {
						text : '批量删除',
						iconCls : 'icon-remove',
						handler : function() {
							var array = $('#updatelog').datagrid('getSelections');// 获取删除选中项数组
						var ids = "";// 要删除的id字符串
						for ( var i = 0; i < array.length; i++) {// 组成一个字符串，ID主键之间用逗号隔开
							if (i != array.length - 1) {
								ids += array[i].ulId + ",";
							} else {
								ids += array[i].ulId;
							}
						}
						if (array != "") {
							$.messager.confirm('提示', '是否要删除选中信息?', function(r) {
								if (r) {
									$.post("delUpdatelog", {
										ids : ids
									// 传向后台的参数,用,分隔
											}, function() { // 前台没有返回json,里面就没有data
												$('#updatelog').datagrid( {
													url : "getAllUpdatelog"
												});
												$.messager.alert('操作提示', "删除成功!", 'info');
											});
								}
							});
						} else {
							$.messager.alert("提示", "请先选择要删除的信息！", "error");
						}
	
					}
					}, '-' ],
					onAfterEdit : function(rowIndex, rowData, changes) {
						// endEdit该方法触发此事件
					// console.info(rowData); //ie 不兼容
					editRow = undefined;
				},
				onDblClickRow : function(rowIndex, rowData) {
					// 双击开启编辑行
					if (editRow != undefined) {
						datagrid.datagrid("endEdit", editRow);
					}
					if (editRow == undefined) {
						datagrid.datagrid("beginEdit", rowIndex);
						editRow = rowIndex;
					}
				}
	
				});
	
			});
	

			// ajax 实现文件上传

			function ajaxFileUpload(obj) {
				var posturl = $(obj).parents('form').attr('action');
				$.ajaxFileUpload( {
					url : posturl,
					secureuri : false,
					fileElementId : "file",
					dataType : "json",
					success : function(data) {
							$(obj).parents('form')[0].reset();// 表单重置
							alert(data.errmsg);
							return;
				},

				});
			}
			
			// 清空查询表单
			function clearForm() {
				$('#queryForm').form('clear');
			}
			// 条件查询
			function dosearch() {
				var ulVersions = $('#ulVersions').val();
				ulVersions = $.trim(ulVersions);
				var ulUpdatetime = $('#ulUpdatetime').val();
				ulUpdatetime = $.trim(ulUpdatetime);
				$('#updatelog').datagrid('load', {
					ulVersions : ulVersions,
					ulUpdatetime : ulUpdatetime
				});
			}
			// 验证表单查询为空 未应用
			function doValidate() {
				var fileName = $('#fileName').val();
				fileName = $.trim(fileName);
				if (fileName == null || fileName == '') {
					alert("请输入文件名称!");
					return true;
					// 调用doValidate if(true) return null
				}
			}
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>华丽分割线以下部分为应用bootstramp方法
			// 根据选择器checkbox的checked值进行全选/反选操作
	
			function selectAll(selector)
			{  
			    var checkBoxList=document.getElementsByName("isSelected");  
			    if(selector.checked)  
			    {  
			        var checkedFlag=true;  
			    }  
			    else  
			    {  
			        var checkedFlag=false;  
			    }  
			    for(i=0;i<checkBoxList.length;i++)  
			    {  
			        checkBoxList[i].checked=checkedFlag;  
			    }  
			}  
	
			// 获取要删除的记录的id序列后，进行批量多条删除操作
			function deleteItems()
			{  
			    var delList=selectedList();  
			    if(delList=="")  
			    {  
			        alert("尚未选择要删除的记录！");  
			        return null;  
			    }  
			    var a = window.confirm("确认删除吗?");
			    if(a){
			    	// 获取所有要删除的id数组
				    var idList=delList.split(",");
					    for(i=0;i<idList.length;i++){
					    	publicDel(idList[i]);  
					     }
				    }
			}  
	
			// 获取要删除的记录的id序列，通过","分割
			function selectedList()
			{  
			    var delList="";  
			    var checkBoxList=document.getElementsByName("isSelected");  
			    for(i=0;i<checkBoxList.length;i++)  
			    {  
			        if(checkBoxList[i].checked)  
			        {  
			            delList+=checkBoxList[i].value+",";  
			        }  
			    }  
			    if(delList=="")  
			    {  
			        return delList;  
			    }  
			    delList=delList.slice(0,delList.length-1);  
			    return delList;  
			}  
			// -------------------****************-----------------------
			// 单独删除
			function deleteItem(idList)// 发出异步请求
			{ 
				var a =window.confirm("确认删除吗?");
				if(a){
					publicDel(idList);
				}
			}  
	
			// 抽取出单独删除和批量删除的公共方法
			function publicDel(idList){
				 var xhr=createXHR();  
				    xhr.onreadystatechange=function()  
				    {  
				    	// 获取XMLHTTP readyState状态==4,响应已完成；您可以获取并使用服务器的响应了
				        if(xhr.readyState==4)  
				        {  
				            if(xhr.status>=200&&xhr.status<300||xhr.status==304)  
				            {               
				               <!-- alert(xhr.responseText);  -->     
				               // 从页面列表中删除数据及样式
				                doDeleteSuccess(idList);  	              
				            }  
				        }  
				    }  
				    xhr.open("get","delUpdatelogOld?ulId="+idList,true);  
				    xhr.send(null);  
			}
			function createXHR()  
			{  
			    if(window.XMLHttpRequest)  
			    {  
			        return new XMLHttpRequest();  
			    }  
			    else if(window.ActiveXObject)  
			    {  
			        return new ActiveXObject("Microsoft.XMLHTTP");  
			    }  
			}  
	
			// 将已经删除的行从列表里清除，依据删除的行的id
			function doDeleteSuccess(idList)  
			{  
			    var idArray=idList.split(",");// 将id序列分割为数组
			    for(i=0;i<idArray.length;i++)  
			    {  
			        var btn=document.getElementById(idArray[i]);  
			        // 获取要删除的节点，此处为button所在的<tr></tr>标签
			        var whoToDel=btn.parentNode.parentNode;  
			        whoToDel.parentNode.removeChild(whoToDel);  
			    }  
			} 
			// 删除确认功能未应用于页面
			// 注意如果方法直接写在jsp页面必须将"." 路径换为 ${pageContext.request.contextPath }
			function dodelete(id) {
				var a = window.confirm("确认删除吗?");
				if (a) {
					window.location.href = "./delUpdatelog?id=" + id;
				}
			}
				
		</script>
	</head>

	<body>
					<!-- 文件上传开始 --><%--
					<div class="row clearfix">
						<div class="col-md-12 column">
							<form class="form-inline" action="${path }/service/upload"
								method="post" id="upUpdatelog" enctype="multipart/form-data">
								<div style="margin-top: 20px;">
									<table>
										<tbody>
											<tr>
												<td>
													<input type="file" id="file"
														style="border: 1px solid #BBB; height: 30px; width: auto"
														name="file" />
												</td>
												<td>
													<input type="button" class="btn btn-default btn-sm"
														onclick=ajaxFileUpload(this); value="上传" />
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</form>
						</div>
					</div>
					--%><!-- 文件上传结束 -->
					<!-- 表格查询开始 -->
					<%--
					<div class="row" clearfix>
						<div class="col-md-12 column">
							<div style="margin-top: 20px;"></div>
							<form id="queryForm" style="text-align: left">
								<table width="560px">
									<tr align="left">
										<td>
											客户ID:
											<input id="cusId" />

											<a href="#" onclick="clearForm();" class="easyui-linkbutton"
												iconCls="icon-search">清空</a>
										</td>
									</tr>
									<tr align="left">
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;日期:
											<input id="ulUpdatetime" />

											<a href="#" onclick="dosearch();" class="easyui-linkbutton"
												iconCls="icon-search">查询</a>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					--%>
					<!-- 表格查询结束 -->
					<!-- datagrid表格开始 -->
							<table id="updatelog">
								<thead>
									<tr>
										
										<th data-options="field:'sb',checkbox:true"></th>
										<th field="ulId"  align="center" hidden=true>
											<!-- hidden=true 隐藏列 -->
										</th>
										<th field="cusId" hidden=true  align="center" editor="{type:'text'}">
											客户ID
										</th>
										<th field="ulVersions" align="center" editor="{type:'text'}">
											程序版本号
										</th>
										<th field="ulLasttime" align="center"
											editor="{type:'datetimebox'}">
											上次更新时间
										</th>
										<th field="ulUpdatetime" align="center"
											editor="{type:'datetimebox'}">
											本次更新时间
										</th>
									</tr>
								</thead>
							</table>
							<!-- 显示添加按钮的Div 不在js中显示的另一种方式 -->
							<%--	<div id="easyui_toolbar"
								style="padding: 2px 0px 2px 15px; height: auto">
								<a href="javascript:void(0)" id="easyui_add"
									class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
								<a href="javascript:void(0)" id="easyui_edit"
									class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
								<a href="javascript:void(0)" id="deltable"
								class="easyui-linkbutton" iconCls="icon-remove" plain="true">批量删除</a>
							</div>--%>

					<!-- datagrid表格结束 -->
	</body>
</html>
