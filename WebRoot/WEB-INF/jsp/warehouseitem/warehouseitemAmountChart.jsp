<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>药品入库数量统计图</title>
		<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
		<link rel="stylesheet" href="${path}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${path}/css/bootstrap.css" />
		<link rel="stylesheet" href="${path}/css/bootstrap-theme.css" />
		<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
		<script src="${path}/js/highcharts.js"></script>
		<script src="${path}/js/public.js"></script>
		<script type="text/javascript">
			var mychart;
			var map = [];
			var cusNames = [];
			// 清空饼图内容
			function chartClear() {
				var series = mychart.series;
				while (series.length > 0) {
					for ( var i = 0; i < mychart.series.length; i++) {
						mychart.series[i].remove();
					}
					series.length = 0;
				}
				mychart.redraw();
			}
			// 加载饼图内容
			function loadChart(customerName, days) {
				mychart.showLoading(); // 显示加载中...
				map.length = 0;
				var geturl = 'warehouseitemAmountChartJson?days=' + days
						+ '&cusName=' + customerName;
				jQuery.getJSON(geturl, null, function(data) {
					chartClear();
					if (data != null) {
						cusNames = data.cusNames;
						// 为图表设置值
						jQuery.each(data.map, function(k, v) {
							var arr = [ k, v ];
							map.push(arr);
						});
						var seriedata = {
							'type' : 'pie',
							'name' : '占总药品数量',
							'data' : map
						};
						var serie = mychart.addSeries(seriedata);
					} else {
						alert('未查询到数据!');
						// setInterval (showTime(), 2000);
					}
				});
				mychart.hideLoading();// 加载完成后隐藏
			}
			// 初始化jsp
			$(function() {
				mychart = new Highcharts.Chart( {
					chart : {
						// 将报表对象渲染到层上
					renderTo : 'mychartdiv'
				},
	
				title : {
					text : '入库数量统计图',
					x : -20
				// center
					},
					tooltip : { // 数据提示框(一般为单位)
						pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'// 显示百分比
					},
					plotOptions : {
						pie : {
							allowPointSelect : true,
							cursor : 'pointer',
							dataLabels : {
								enabled : true,
								color : '#000000',
								connectorColor : '#000000',
								format : '<b>{point.name}</b>: {point.y} 条'// 显示实际数值
							}
						}
					},
					loading : {
						style : {// 覆盖在绘图区的加载页面的样式
							position : 'absolute',
							backgroundColor : 'white',
							opacity : 1,
							textAlign : 'center'
						},
						labelStyle : {// 加载标签的span的CSS样式
							fontWeight : 'bold',
							color : 'black',// 查询字体颜色
							position : 'relative',
							top : '1em'
						}
					},
					series : [],
					credits : {
						enabled : false
					// 禁用版权信息
					}
				});
	
				$('a.cus:first').css("color", "red");// 第一条字体颜色变红
				$('li.cusitem:first').addClass('active');// 添加激活状态
				var c = encodeURI(encodeURI($('li.cusitem.active').first().attr('cusname')));
				// 默认查询用户当天数据
				loadChart(c, 1);
	
				// 日期点击绑定事件
				$("a.days").bind("click", function() {
					var d = $(this).attr('day');
					var cus = $('li.cusitem.active').first().attr('cusname');
					var c = encodeURI(encodeURI(cus));
	
					loadChart(c, d);
				});
				// 药店名称点击绑定事件
				$("a.cus").bind("click", function() {
					$('li.cusitem.active>a').css("color", "#000");// 激活前的a 字体变黑
						$('li.cusitem.active').removeClass('active'); // 移除激活状态
						$(this).css("color", "red"); // 新选中字体变红
						$(this).parent('li').addClass('active'); // 上一级激活
						var d = $(this).attr('day'); // 获取天数: a 标签 属性名为'day' 的值
						var cus = $('li.cusitem.active').first().attr('cusname'); // 获取药店名称
						// li 标签激活的第一条 属性名是 'cusname'
						var c = encodeURI(encodeURI(cus));
						loadChart(c, d);
					});
	
			});
		</script>
	</head>
	<body>

		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-4 column">
							<div style="margin-top: 20px"></div>
								<ol>
									<c:if test="${!empty cusNames}">
										<c:forEach items="${cusNames}" var="name">
											<li class="cusitem" cusname="${name}">
												<a href="javascript: void(0);" class="cus" day="1">${name }</a>
											</li>
										</c:forEach>
									</c:if>
								</ol>
							
						</div>
						<div class="col-md-8 column">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<div style="margin-top: 20px"></div>
									<div id="mychartdiv" style="min-width: 800px; height: 400px;"></div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<div class="text-center">
										<c:choose>
											<c:when test="${!empty status}">
												<ul class="pagination">
													<li>
														<a href="javascript: void(0);" class="days" day="1">一天</a>
													</li>
													<li>
														<a href="javascript: void(0);" class="days" day="7">一周</a>
													</li>
													<li>
														<a href="javascript: void(0);" class="days" day="30">一月</a>
													</li>
												</ul>
											</c:when>
											<c:otherwise>
												<H3>
													...未显示
												</H3>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
