<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
		<title>淄博市医保进销存数据监管平台</title> 
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" type="text/css" href="${path}/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="${path}/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${path}/css/YBstyle.css" />
		<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
		<link rel="shortcut icon" href="${path}/images/21.ico"/>
		<script type="text/javascript" src="${path}/easyui/js/jquery.min.js"></script>
		<script type="text/javascript" src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="${path}/easyui/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${path}/easyui/js/highcharts.js"></script>
		<script type="text/javascript" src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${path}/easyui/js/main.js"></script>
		<script type="text/javascript">
			function fullScreen(me) {
		        if (typeof(document.mozCancelFullScreen) != 'undefined') {
		            if (document.mozFullScreen) {
		                document.mozCancelFullScreen();
		               /* $(me).attr("title", "全屏").removeClass("main-btn-cfull")
		                    .addClass("main-btn-full");*/
		            } else {
		                document.body.mozRequestFullScreen();
		               /* $(me).attr("title", "取消全屏").removeClass("main-btn-full")
		                    .addClass("main-btn-cfull");*/
		            }
		        } else if (typeof(document.webkitCancelFullScreen) != 'undefined') {
		            if (document.webkitIsFullScreen) {
		                document.webkitCancelFullScreen();
		               /* $(me).attr("title", "全屏").removeClass("main-btn-cfull")
		                    .addClass("main-btn-full");*/
		            } else {
		                document.body.webkitRequestFullScreen();
		               /* $(me).attr("title", "取消全屏").removeClass("main-btn-full")
		                    .addClass("main-btn-cfull");*/
		            }
		        } else {
		            //alert('您的浏览器不支持全屏接口，请按F11键扩展全屏。');
		        }
		    }
	
			// 打开修改密码窗口
			function openupdatePW() {
				$('#xgmm').dialog('open').dialog('center').dialog('setTitle', '修改密码');
				$('#form').form('clear'); // 清除表单数据
			}
			// 修改密码
			function updatePW() {
				var fir = $("#fir").val();
				var sec = $("#sec").val();
				if (fir != sec) {
					$.messager.alert("系统提示","两次输入的密码不一致!","warning");
				} else {
					$.post("updatePW", {pw : fir}, function(data) {
						$.messager.show({
							title : '系统提示',
							msg : data.status
						});
						$('#xgmm').dialog('close');
					});
				}
			}

		</script>
	</head>
	
	<body class="easyui-layout">
		<div class="nav_top_logo" data-options="region:'north',split:false,border:false" style="height: 61px;">
			<div class="logo_top1">
				<div class="logo_l">
					<img src="${path}/images/CategorizeMenu2.png" />
				</div>
				<div class="logo_r">
					<b>淄博市医保进销存数据监管平台</b>
				</div>
			</div>
			<div class="nav_top_right_admin" style="right:140px">
				欢迎您，<span class="l-btn-icon icon-user_add" style="margin-left:-5px;">&nbsp;</span>
				<a id="user" name=${manager.muUsername}  href="javascript:void(0)">${manager.muUsername}</a>&nbsp;
			</div>
			<div class="nav_top_right">&nbsp;&nbsp;
				<span class="l-btn-icon icon-key">&nbsp;</span>
				<a href="##" style="margin-left:18px;" onclick="openupdatePW()" id="pow">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a class="a1"  style="margin-left:0px;" href="#" onclick="signout()">退出</a>
				
				<div id="xgmm" class="easyui-dialog" title="修改密码" style="width:300px; height: 200px;padding-top:30px" data-options="iconCls:'icon-key',buttons:'#xgmm-btn',closed:true,modal:true,">
					<form id="form">
						<table id="xqin" style="margin:auto;">
							<tr><td>登录密码：<input id="fir" type="password" /></td></tr>
							<tr><td>再输一次：<input id="sec" type="password" /></td></tr>
						</table>
					</form>
				</div>
				<div id="xgmm-btn">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updatePW()"  data-options="iconCls:'icon-ok'">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript: $('#xgmm').dialog('close');" data-options="iconCls:'icon-cancel'">取消</a>
				</div>
			</div>
		</div>
		
		<!-- 菜单区 -->
		<div data-options="region:'west',title:'功能菜单',split:true,collapsible:true" iconCls="icon-world" style="width: 180px;">
			<div class="easyui-accordion" data-options="fit:true,animate:false,selected:0" id="fl">
				<div title="数据查询" data-options="iconCls:'icon-search'" style="overflow: auto; padding: 10px;">
					<ul class="easyui-tree" class="fl1">
					    <li iconCls="icon-search">
					    	<a href="javascript:void(0);" class="cs-navi-tab" src="${path}/toAreaCustomer?areacode=${areacodes}" >综合查询</a>
					    </li>
					    
						<c:forEach items="${areacodeList}" var="areacode">
							<li data-options="selected:true, checked:true,state:'closed'" iconCls="icon-folder1">
								<span><a href="javascript:void(0);" src="${path }/toAreaCustomer?areacode=${areacode.acAreacode}" class="cs-navi-tab" style="color: #000;">${areacode.acAreaname }</a></span>
								<ul>
									<li>
										<span><a href="javascript:void(0);" fid=${areacode.acAreaname } src="${path }/toAreaCustomer?areacode=${areacode.acAreacode}&cusFlag=101" class="customertree">单体药店</a></span>
									</li>
									<li>
										<span><a href="javascript:void(0);" fid=${areacode.acAreaname } src="${path }/toAreaCustomer?areacode=${areacode.acAreacode}&cusFlag=102" class="customertree">连锁药店</a></span>
									</li>
									<li>
										<span><a href="javascript:void(0);" fid=${areacode.acAreaname } src="${path }/toAreaCustomer?areacode=${areacode.acAreacode}&cusFlag=201" class="customertree">门诊</a></span>
									</li>
									<li>
										<span><a href="javascript:void(0);" fid=${areacode.acAreaname } src="${path }/toAreaCustomer?areacode=${areacode.acAreacode}&cusFlag=301" class="customertree">医院</a></span>
									</li>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</div>
				
				<c:forEach items="${priList}" var="p">
					<c:if test="${p.priParent=='0'}">
						<div title="${p.priName }" data-options="iconCls:'${p.priIcon}'" style="overflow: auto; padding: 10px;">
							<ul class="fl1">
								<c:forEach items="${priList}" var="p2">
									<c:if test="${p2.priParent==p.priId}">
										<li>
											<a href="javascript:void(0);" src="${p2.priUrl }" class="cs-navi-tab">
												<img src="${p2.priIcon }" class="img1" />${p2.priName }
											</a>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
						</c:if>
				</c:forEach>
			</div>
		</div>
		
		<!--center区 -->
		<div data-options="region:'center'" style="overflow: hidden;">
			<!-- tabs -->
			<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
				<div title="首页" iconCls="icon-house">
					<div class="panel1 fl">
						<div class="panel1_header">
							<div class="panel1_header_left">
								<b class="b1">定点动态</b>
							</div>
						</div>
						<table>
							<tbody>
								<c:forEach items="${tzddList}" var="tzdd">
									<tr>
										<td style="text-align:left; padding-left: 10px;">${tzdd.tzCusname } ${tzdd.tzYybm }</td>
										<td style="text-align:left; padding-right: 10px;" title="${tzdd.tzNote }"><font color='red'>${fn:substring(tzdd.tzNote,0,43)}</font></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<div class="panel2 fr">
						<div class="panel2_header">
							<div class="panel2_header_left">
								<b class="b1">快速导航</b>
							</div>
						</div>
						<div class="panel2_content">
							<div class="panel2_content_child">
								<div class="panel2_content_child_box">
									<div class="panel2_content_child_box5">
										<a href="javascript:void(0);" src="getCustomerList?cusFlag=101" class="cs-navi-tab">
											<div class="panel2_content_child_box5_left">
												<img src="${path}/images/dtyd.png" />
											</div>
											<div class="panel2_content_child_box5_right">
												<p>单体药店数据</p>
											</div> 
										</a>
									</div>
									<div class="panel2_content_child_box6">
										<a href="javascript:void(0);" src="getCustomerList?cusFlag=102" class="cs-navi-tab">
											<div class="panel2_content_child_box6_left">
												<img src="${path}/images/ls.png" />
											</div>
											<div class="panel2_content_child_box6_right">
												<p>连锁药店数据</p>
											</div> 
										</a>
									</div>
									<div class="panel2_content_child_box7">
										<a href="javascript:void(0);" src="getCustomerList?cusFlag=201" class="cs-navi-tab">
											<div class="panel2_content_child_box7_left">
												<img src="${path}/images/mz.png" />
											</div>
											<div class="panel2_content_child_box7_right">
												<p>门诊数据</p>
											</div> 
										</a>
									</div>
									<div class="panel2_content_child_box8">
										<a href="javascript:void(0);" src="getCustomerList?cusFlag=301" class="cs-navi-tab">
											<div class="panel2_content_child_box8_left">
												<img src="${path}/images/yy.png" />
											</div>
											<div class="panel2_content_child_box8_right">
												<p>医院数据</p>
											</div> 
										</a>
									</div>
									<div class="panel2_content_child_box5">
										<a href="javascript:void(0);" src="toCouUploadfileByDate?cusFlag=101" class="cs-navi-tab">
											<div class="panel2_content_child_box5_left">
												<img src="${path}/images/dtyd.png" />
											</div>
											<div class="panel2_content_child_box5_right">
												<p>单体药店统计</p>
											</div> 
										</a>
									</div>
									<div class="panel2_content_child_box6">
										<a href="javascript:void(0);" src="toCouUploadfileByDate?cusFlag=102" class="cs-navi-tab">
											<div class="panel2_content_child_box6_left">
												<img src="${path}/images/ls.png" />
											</div>
											<div class="panel2_content_child_box6_right">
												<p>连锁药店统计</p>
											</div> 
										</a>
									</div>
									<div class="panel2_content_child_box7">
										<a href="javascript:void(0);" src="toCouUploadfileByDate?cusFlag=201" class="cs-navi-tab">
											<div class="panel2_content_child_box7_left">
												<img src="${path}/images/mz.png" />
											</div>
											<div class="panel2_content_child_box7_right">
												<p>门诊统计</p>
											</div> 
										</a>
									</div>
									<div class="panel2_content_child_box8">
										<a href="javascript:void(0);" src="toCouUploadfileByDate?cusFlag=301" class="cs-navi-tab">
											<div class="panel2_content_child_box8_left">
												<img src="${path}/images/yy.png" />
											</div>
											<div class="panel2_content_child_box8_right">
												<p>医院统计</p>
											</div> 
										</a>
									</div>

								</div>
							</div>
						</div>
					</div>
					
					<div class="panel3 fl">
						<div class="panel3_header">
							<div class="panel3_header_left">
								<b class="b1">统计分析</b>
							</div>
						</div>
						<div class="panel3-content">
							<div style="width: 94%; height: 120px; margin: 0px auto 0 auto; background: #fff;">
								<div id="chart_combo" class="chart_combo"></div>
							</div>
						</div>
					</div>
					
					<div class="panel4 fr">
						<div class="panel4_header">
							<div class="panel4_header_left">
								<b class="b1">上传预警</b>
							</div>
						</div>
						<table id="travel">
							<thead>
								<tr>
									<th scope="col" rowspan="2">上传项目/未上传数量</th>
								</tr>

								<tr>
									<th scope="col">单体药店</th>
									<th scope="col">连锁药店</th>
									<th scope="col">门诊</th>
									<th scope="col">医院</th>
									<th scope="col">其它</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">第一数据上传</th>
									<td>48</td>
									<td>32</td>
									<td>25</td>
									<td class="red">29</td>
									<td>20</td>
								</tr>

								<tr>
									<th scope="row">第二数据上传</th>
									<td>36</td>
									<td>29</td>
									<td>27</td>
									<td>31</td>
									<td>19</td>
								</tr>

								<tr>
									<th scope="row">第三数据上传</th>
									<td class="red">33</td>
									<td>24</td>
									<td>20</td>
									<td class="red">25</td>
									<td>15</td>
								</tr>

								<tr>
									<th scope="row">第四数据上传</th>
									<td>47</td>
									<td>39</td>
									<td>36</td>
									<td>40</td>
									<td>33</td>
								</tr>

								<tr>
									<th scope="row">第五数据上传</th>
									<td>69</td>
									<td class="red">66</td>
									<td>43</td>
									<td>66</td>
									<td>47</td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<div class="nav_bottom" data-options="region:'south',split:false,title:''" style="height: 34px;">
			&copy;2018&nbsp;-&nbsp;淄博亿维计算机科技有限公司版权所有<!-- <a href="javascript:;" onclick="fullScreen(this)">全屏</a> -->
		</div>
		
		<noscript>
			<b><h1 style="text-align: center; color: red;">
					您的浏览器阻止了JS脚本运行，请打开脚本设置或点击下方允许阻止内容！！！
				</h1> </b>
			<br />
			<h2 style="text-align: center;">
				IE浏览器设置方法：工具-->Internet选项-->安全-->自定义级别-->脚本选框选择启用-->重启浏览器
			</h2>
		</noscript>
		
		<div id="mm" class="easyui-menu cs-tab-menu">
			<div id="mm-tabupdate">刷新</div>
			<div class="menu-sep"></div>
			<div id="mm-tabclose">关闭</div>
			<div id="mm-tabcloseright">关闭右侧</div>
			<div id="mm-tabcloseleft">关闭左侧</div>
			<div id="mm-tabcloseother">关闭其他</div>
			<div id="mm-tabcloseall">关闭全部</div>
		</div>
	</body>
</html>
