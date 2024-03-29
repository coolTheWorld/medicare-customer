<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>某定点各药品销售量统计图 客户列表</title>

		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
		<link rel="stylesheet" href="${path}/css/icon.css" />
		<link rel="stylesheet" href="${path }/css/ew-main.css" />
		<link rel="stylesheet" href="${path }/css/ew-home.css" />
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/easyui/jquery.easyui.min.js"></script>
		<script src="${path}/js/listload.js"></script>
	</head>

	<body>
		<div id="content_wrap" class="easyui-layout content_wrap" data-options="fit:true" style="">
			<!-- 左侧药店列表开始  easyui-accordion  menu_warp-->
			<div data-options="region:'west',title:'定点列表',split:true,width:200,minWidth:200,border:false" class="menu_warp" style="">
				<div class="easyui-list" >
					<ul class="easyui-tree" data-options="lines:false">
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="list">
								<li>
									<span><a ghref="${path }/toEachDrugTotalNumChart?cusId=${list[1]}"> ${list[0] }</a> </span>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
			<!-- 左侧药店列表结束 -->
			<!-- 中间药店内容开始 -->
			<div id="mainPanle" region="center" style="overflow: auto">
				<div id="tabs" class="easyui-tabs" fit="true" border="false">
					<div id="mm" class="easyui-menu" style="width: 150px; overflow: scroll">
						<div id="mm-tabreload">
							刷新
						</div>
						<div id="mm-tabcloseall">
							关闭全部
						</div>
						<div id="mm-tabcloseother">
							关闭其他
						</div>
						<div class="menu-sep"></div>
						<div id="mm-tabcloseright">
							关闭右侧标签
						</div>
						<div id="mm-tabcloseleft">
							关闭左侧标签
						</div>
					</div>
				</div>
			</div>
			<!-- 中间药店内容结束 -->
		</div>
	</body>
</html>
