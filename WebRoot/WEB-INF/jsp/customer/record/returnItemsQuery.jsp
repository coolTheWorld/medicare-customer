<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh">

<head>
<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
	<title>退货记录</title> <c:set value="${pageContext.request.contextPath}"
		var="path" scope="page" />
	<link rel="icon" href="favicon.ico" type="image/ico">
		<meta name="keywords" content="医保管理系统">
			<meta name="description" content="医保管理系统">
				<meta name="author" content="yinqi">


					<link href="${path}/evolution/css/bootstrap.min.css"
						rel="stylesheet">
						<link href="${path}/evolution/css/materialdesignicons.min.css"
							rel="stylesheet">
							<link href="${path}/evolution/css/style.min.css" rel="stylesheet">
								<!-- <link href="css/easyui/easyui.css" rel="stylesheet"> -->
								<!-- <link href="css/easyui/icon.css" rel="stylesheet"> -->
								<!-- easyui美化包 -->
								<link href="${path}/evolution/css/easyui-beautify/easyui.css"
									rel="stylesheet">
									<link
										href="${path}/evolution/css/easyui-beautify/easyui_animation.css"
										rel="stylesheet">
										<link
											href="${path}/evolution/css/easyui-beautify/easyui_plus.css"
											rel="stylesheet">
											<link href="${path}/evolution/css/easyui-beautify/icon.css"
												rel="stylesheet">
</head>

<body>
	<div class="lyear-layout-web">
		<div class="lyear-layout-container">
			<%-- <!--左侧导航-->
			<aside class="lyear-layout-sidebar"> <!-- logo -->
			<div id="logo" class="sidebar-header">
				<a href=""> <img src="../../images/CategorizeMenu2.png" /> 医保系统
				</a>
			</div>
			<div class="lyear-layout-sidebar-scroll">

				<nav class="sidebar-main">
				<ul class="nav nav-drawer">
					<!--               <li class="nav-item"> <a href="../../Index.html"><i class="mdi mdi-home"></i> 后台首页</a> </li> -->
					<li class="nav-item"><a
						href="${path}/customer/toWareHouseItems"><i class="mdi "></i>
							入库记录</a></li>
					<li class="nav-item "><a
						href="${path}/customer/toDeliveryItems"><i class="mdi "></i>
							出库记录</a></li>
					<li class="nav-item "><a
						href="${path}/customer/toSalesItems"><i class="mdi "></i>
							销售记录</a></li>
					<li class="nav-item active"><a
						href="${path}/customer/toReturnItems"><i class="mdi "></i>
							退货记录</a></li>
					<li class="nav-item "><a
						href="${path}/customer/toPrescribe"><i class="mdi "></i>
							处方记录</a></li>
					<li class="nav-item "><a
						href="${path}/customer/toClinicrecords"><i class="mdi "></i>
							门诊记录</a></li>
					<li class="nav-item "><a
						href="${path}/customer/toHospitalized"><i class="mdi "></i>
							入院记录</a></li>
					<li class="nav-item "><a
						href="${path}/customer/toDischarged"><i class="mdi "></i>
							出院记录</a></li>
				</ul>
				</li>
				</ul>
				</nav>

				<div class="sidebar-footer">
					<p class="copyright">
						Copyright &copy; 2020. <a target="_blank" href="#">医保</a>
						定点数据上传查询.
					</p>
				</div>
			</div>

			</aside>
			<!--End 左侧导航-->

			<!--头部信息-->
			<header class="lyear-layout-header"> <nav
				class="navbar navbar-default">
			<div class="topbar">

				<div class="topbar-left">
					<div class="lyear-aside-toggler">
						<span class="lyear-toggler-bar"></span> <span
							class="lyear-toggler-bar"></span> <span class="lyear-toggler-bar"></span>
					</div>
					<span class="navbar-page-title">  </span>
				</div>

				<ul class="topbar-right">
					<li class="dropdown dropdown-profile"><a
						href="javascript:void(0)" data-toggle="dropdown"> <img
							class="img-avatar img-avatar-48 m-r-10"
							src="${path}/evolution/images/users/OIP.jpg" alt="管理员" /> <span>管理员
								<span class="caret"></span>
						</span>
					</a>
						<ul class="dropdown-menu dropdown-menu-right">
							<li><a href="lyear_pages_profile.html"><i
									class="mdi mdi-account"></i> 个人信息</a></li>
							<li><a href="lyear_pages_edit_pwd.html"><i
									class="mdi mdi-lock-outline"></i> 修改密码</a></li>
							<li><a href="javascript:void(0)"><i
									class="mdi mdi-delete"></i> 清空缓存</a></li>
							<li class="divider"></li>
							<li><a href="lyear_pages_login.html"><i
									class="mdi mdi-logout-variant"></i> 退出登录</a></li>
						</ul></li>

				</ul>

			</div>
			</nav> </header>
			<!--End 头部信息-->

			<!--页面主要内容-->
			<main class="lyear-layout-content"> --%>

			<div class="container-fluid">

				<div class="row">
					<!-- <div class="col-lg-12"> -->
						<div class="card">
							<div class="card-toolbar clearfix">
								<!-- <div class="pull-right ">
									<a href="#" class="easyui-linkbutton m-r-5"
										data-options="iconCls:'icon-search'">查询</a> <a href="#"
										class="easyui-linkbutton m-r-5"
										data-options="iconCls:'icon-help'">帮助</a>
								</div> -->
								<div class="toolbar-btn-action">
									<span style="font-weight: 700; padding-right: 5px;">选择日期:</span>
									<input id="input_date_param" class="easyui-datebox m-r-5">
									<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchYesterday()">昨天</a>
									<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchBeforeYesterday()">前天</a>
								
								</div>
							</div>
							<div class="card-body">
								<table id="returnItems" title="退货记录"
									style="width: 100%; height: 535px">
									<thead>
										<tr>
											<th field="CUS_DAREWAY" width="80">医院编码</th>
											<th field="wiId" width="100">入库id</th>
											<th field="WI_TYPE" width="100">入库类别</th>
											<th field="DRUG_CODE" width="80">药品编码</th>
											<th field="drugName" width="80">药品名称</th>
											<th field="DRUG_NUM" width="80">入库数量</th>
											<th field="DRUG_PURCHASEPRICE" width="100">采购价</th>
											<th field="DRUG_BATCHNO" width="110">生产批号</th>
											<th field="DRUG_MFRS" width="80">生产商</th>
											<th field="DRUG_MADEIN" width="80">产地</th>
											<th field="DRUG_EXPDATE" width="80">有效期</th>
											<th field="DRUG_MFGDATE" width="80">生产日期</th>
											<th field="DRUG_EID" width="80">电子监管码</th>
											<th field="WI_CODE" width="80">入库编号</th>
											<th field="wiDatetime" width="80">入库日期</th>
											<th field="WI_PRICE" width="80">零售价</th>
										</tr>
									</thead>
								</table>

							</div>

						</div>
					<!-- </div> -->

				</div>

			</div>
			<!-- <div class="easyui-panel" style="position: relative;bottom: 0;">
          <div class="easyui-pagination"  id="paginton"></div>
        </div> --> 
        <!-- </main> -->
			<!--End 页面主要内容-->
		</div>
	</div>

	<script type="text/javascript" src="${path}/evolution/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${path}/evolution/js/easyUi/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${path}/evolution/js/jquery.cookie.js"></script>
	<script type="text/javascript"
		src="${path}/evolution/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${path}/evolution/js/perfect-scrollbar.min.js"></script>
	<script type="text/javascript" src="${path}/evolution/js/main.min.js"></script>
	<script type="text/javascript"
		src="${path}/evolution/js/easyui-beautify/jquery.insdep-extend.min.js"></script>

	<script type="text/javascript">
		var initDate = timeStamp2String(new Date().getTime());
		$(document).ready(function() {

			//日期框
			$('#input_date_param').datebox({
				//日期框选中时请求后台数据
				onSelect : function(date) {
					console.log("1");
					var dateParam = timeStamp2String(date.getTime())
					doSearch(dateParam);
				}
			}).datebox('setValue', initDate);
		})

		//查询昨天数据
		function searchYesterday(){
			var time=new Date().getTime()-24*60*60*1000;//获取昨天日期
			doSearch(timeStamp2String(time));
		}
		
		//查询昨天数据
		function searchBeforeYesterday(){
			var time=new Date().getTime()-24*60*60*1000*2;//获取前天日期
			doSearch(timeStamp2String(time));
		}
		
		function doSearch(date) {
			$("#returnItems").datagrid('load', {
				'date' : date
			});
		}

		//格式化日期
		function timeStamp2String(time) {
			var datetime = new Date();
			datetime.setTime(time);
			var year = datetime.getFullYear();
			var month = datetime.getMonth() + 1 < 10 ? "0"
					+ (datetime.getMonth() + 1) : datetime.getMonth() + 1;
			var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
					: datetime.getDate();
			return year + "-" + month + "-" + date;
		}

		/* function getData() {
			var rows = [];
			for (var i = 1; i <= 800; i++) {
				var amount = Math.floor(Math.random() * 1000);
				var price = Math.floor(Math.random() * 1000);
				rows.push({
					inv : 'Inv No ' + i,
					date : $.fn.datebox.defaults.formatter(new Date()),
					name : 'Name ' + i,
					amount : amount,
					price : price,
					cost : amount * price,
					note : 'Note ' + i
				});
			}
			return rows;
		} */

		/* function pagerFilter(data) {
			if (typeof data.length == 'number'
					&& typeof data.splice == 'function') { // is array
				data = {
					total : data.length,
					rows : data
				}
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
				onSelectPage : function(pageNum, pageSize) {
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh', {
						pageNumber : pageNum,
						pageSize : pageSize
					});
					dg.datagrid('loadData', data);
				}
			});
			if (!data.originalRows) {
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		} */

		$(function() {
			// 存储数据
			/* var MyData = getData() */

			// easyui表格初始化
			$('#returnItems').datagrid({
				/* loadFilter : pagerFilter, */
				url : "/record/returnItemsData",
				queryParams : {
					'date' : initDate
				},
				method : "get",
				rownumbers : true,
				singleSelect : true,
				autoRowHeight : false,
				width: window.innerWidth/10*9,
				height: window.innerHeight/10*8,
				pagination : true,
				pageSize : 20,
				pageList : [ 10, 20, 50, 100 ],
				emptyMsg : '未查询到相应数据',
			})
			/* .datagrid('loadData', MyData); */
			// $('#paginton').pagination({})
		});
	</script>
</body>

</html>