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
		var hls;
		$(function() {
			hls = new Hls();
			   datagrid2("eventlist", "getAllEvent");
		});
		
		//表格查询  
		function queryForm(){  
			var startDate=$("#startDate").datetimebox("getValue"); 
			var endDate=$("#endDate").datetimebox("getValue"); 
			var usrname = $("#usrname").textbox("getValue");
			var cardid = $("#cardid").textbox("getValue");
			var groupname = $("#groupname").combobox("getValue"); 
			  $('#eventlist').datagrid({
				url:'getAllEvent',
				queryParams:{
			  		startDate:startDate,
			  		endDate:endDate,
			  		usrname:usrname,
			  		cardid:cardid,
			  		groupname:groupname
				   },
			});
		}
			
		function fmtOperation(value,row,index){
			var value2 = escape(value);//escape编码（防止空格）,unescape解码
			var libUrl = row.liburl;    //底库
			var minUrl = row.faceminurl;//特写
			var imgUrl = row.imageurl;  //场景
			var detectTime = row.detecttime;
			var dt = Number(new Date(detectTime)) / 1000;
			var rows = row;
			var fmtHtml = "<a href=\"javascript:;\" onclick=\"imageview1('" +rows.eventid + "')\">查看</a>&nbsp;&nbsp;"
				+"<a href=\"javascript:;\" onclick=\"playback('" +rows.eventid + "')\">录像</a>";
			return fmtHtml;
		}
			
		function formatterSim(value,row,index){
			var similarity = row.similarity
			return similarity+"%";
		}
		
		function formatterFeatures(value,row,index){
			var a = row.features;
			var b = a.indexOf(']');
			var c = a.substring(1,b);
			var d = c.split(',');
			var fmtHtml = '';
			if(d[0]=='0'){
				fmtHtml+= ' 未知';
			}else if(d[0]=='1'){
				fmtHtml+= ' 戴眼镜';
			}else if(d[0]=='2'){
				fmtHtml+= ' 微笑';
			}else if(d[0]=='3'){
				fmtHtml+= ' 愤怒';
			}else if(d[0]=='4'){
				fmtHtml+= ' 悲伤';
			}else if(d[0]=='5'){
				fmtHtml+= ' 厌恶';
			}else if(d[0]=='6'){
				fmtHtml+= ' 害怕';
			}else if(d[0]=='7'){
				fmtHtml+= ' 惊讶';
			}else if(d[0]=='8'){
				fmtHtml+= ' 正常';
			}else if(d[0]=='9'){
				fmtHtml+= ' 大笑';
			}else if(d[0]=='10'){
				fmtHtml+= ' 没戴眼镜';
			}else if(d[0]=='11'){
				fmtHtml+= ' 高兴';
			}else if(d[0]=='12'){
				fmtHtml+= ' 困惑';
			}else if(d[0]=='13'){
				fmtHtml+= ' 尖叫';
			}else if(d[0]=='14'){
				fmtHtml+= ' 戴太阳眼镜';
			}
			if(d[1]=='0'){
				fmtHtml+= ' 未知';
			}else if(d[1]=='1'){
				fmtHtml+= ' 戴眼镜';
			}else if(d[1]=='2'){
				fmtHtml+= ' 微笑';
			}else if(d[1]=='3'){
				fmtHtml+= ' 愤怒';
			}else if(d[1]=='4'){
				fmtHtml+= ' 悲伤';
			}else if(d[1]=='5'){
				fmtHtml+= ' 厌恶';
			}else if(d[1]=='6'){
				fmtHtml+= ' 害怕';
			}else if(d[1]=='7'){
				fmtHtml+= ' 惊讶';
			}else if(d[1]=='8'){
				fmtHtml+= ' 正常';
			}else if(d[1]=='9'){
				fmtHtml+= ' 大笑';
			}else if(d[1]=='10'){
				fmtHtml+= ' 没戴眼镜';
			}else if(d[1]=='11'){
				fmtHtml+= ' 高兴';
			}else if(d[1]=='12'){
				fmtHtml+= ' 困惑';
			}else if(d[1]=='13'){
				fmtHtml+= ' 尖叫';
			}else if(d[1]=='14'){
				fmtHtml+= ' 戴太阳眼镜';
			}
			return fmtHtml;
		}
		
		function imageview1(eventid){
			$.ajax({
					type: "POST",
					url:"toImageview",
					data:{eventid:eventid},
					async: false,//默认true异步，false同步
					success: function(row){
						$('#origPath').attr("src", "");
						$('#facePath').attr("src", "");
						$('#registerPath').attr("src", "");
						$('#devChnname').html("");
						$('#recDateStr').html("");
						$('#sexStr').html("");
						$('#birthday').html("");
						$('#provinceStr').html("");
						$('#cityStr').html("");
						$('#groupName').html("");
						$('#cardTypeStr').html("");
						$('#cardId').html("");
						var cardidStr = row.cardid;
						var birthdayStr = cardidStr.substring(6, 10) + "-" + cardidStr.substring(10, 12) + "-" + cardidStr.substring(12, 14);
						var sexStr = (parseInt(cardidStr.substr(16, 1)) % 2 == 1) ? '男' : '女';
						$('#origPath').attr("src", row.faceminurl);
						$('#facePath').attr("src", row.imageurl);
						$('#registerPath').attr("src", row.liburl);
						$('#devChnname').html(row.channelname);
						$('#similarity').html(row.similarity);
						$('#recDateStr').html(row.detecttime);
						$('#name').html(row.usrname);
						$('#sexStr').html(sexStr);
						$('#birthday').html(birthdayStr);
						$('#provinceStr').html(row.province);
						$('#cityStr').html(row.city);
						$('#groupName').html(row.groupname);
						$('#cardTypeStr').html("身份证");
						$('#cardId').html(cardidStr);		
						$('#imgview').dialog('open').dialog('center');
					}
			});
		}
			
		//录像回放
		function playback(eventid){
			$.ajax({
				type: "POST",
				data: "eventid=" + eventid,
			      url: "/face/getPlaybackURL",
			      success: function(data) {
			      	console.log(data);
			       	$('#playback').dialog('open').dialog('center');
			       	var video = document.getElementById("videoCell");
			           video.src = data;
			           hls.loadSource(data);
			           hls.attachMedia(video);
			           hls.on(Hls.Events.MANIFEST_PARSED, function(){
			             video.play();
			           });
			        	//$.messager.show({
						//	title: "录像地址",
			            //    msg: data
						//});	
				}
			});
		}
		
		
		function closeMessageDialog() {
			$('#imgview').dialog('close');

		}	
		//点击清空按钮出发事件
		function clearForm() {
			$('#searchForm').form('clear');  
		}
		//添加新选项卡显示进销存数据
		function show(url,value){
			window.parent.addTab(unescape(value), url);//调用父类方法添加选项卡
		}
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:'true',">
		<div class="easyui-panel" title="人脸记录" data-options="border:true,fit:true" iconCls="icon-zc">
			<table id="eventlist" data-options="checkOnSelect:true,singleSelect:true,fit:true,rownumbers:true,pagination:true,fitColumns:true"
				cellpadding="2" cellspacing="1" toolbar="#toolbar">
				<thead>
					<tr>
						<th data-options="field:'sb',checkbox:true"></th>
						<th field="eventid" hidden=true align="center">事件ID</th>
						<th field="calltime" hidden=true align="center">回调IP</th>
						<th field="rawdata" hidden=true align="center">原始内容</th>

						<th
							data-options="field:'sessionid',halign:'center',align:'left',width:100, hidden:true">sessionid</th>
						<th
							data-options="field:'channelcode',halign:'center',align:'left',width:100, hidden:true">通道编码</th>
						<th
							data-options="field:'devicecode',halign:'center',align:'left',width:100, hidden:true">设备编码</th>
						<th
							data-options="field:'devicename',halign:'center',align:'left',width:100, hidden:true">设备名称</th>
						<th
							data-options="field:'intfacecode',halign:'center',align:'left',width:100, hidden:true">事件接口</th>
						<th
							data-options="field:'sex',halign:'center',align:'left',width:100, hidden:true">性别</th>
						<th
							data-options="field:'cardtype',halign:'center',align:'left',width:100, hidden:true">证件类型</th>
						<th
							data-options="field:'liburl',halign:'center',align:'left',width:100, hidden:true">底库图</th>
						<th
							data-options="field:'faceminurl',halign:'center',align:'left',width:100, hidden:true">特写图</th>
						<th
							data-options="field:'imageurl',halign:'center',align:'left',width:100, hidden:true">场景图</th>
						<th
							data-options="field:'province',halign:'center',align:'left',width:100, hidden:true">省份</th>
						<th
							data-options="field:'city',halign:'center',align:'left',width:100, hidden:true">城市</th>


						<th
							data-options="field:'detecttime',halign:'center',align:'left',width:140">识别时间</th>
						<th
							data-options="field:'channelname',halign:'center',align:'left',width:120">抓拍地点</th>
						<th
							data-options="field:'usrname',halign:'center',align:'left',width:80">姓名</th>
						<th
							data-options="field:'cardid',halign:'center',align:'left',width:140">证件号码</th>
						<th
							data-options="field:'features',halign:'center',align:'left',width:100,formatter:formatterFeatures">人脸特征</th>
						<th
							data-options="field:'similarity',halign:'center',align:'left',width:80,formatter:formatterSim">相似度</th>
						<th
							data-options="field:'groupname',halign:'center',align:'left',width:100">注册库</th>

						<th
							data-options="field:'opera',halign:'center',align:'center',width:100,formatter:fmtOperation">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div id="toolbar" style="width: 100%; height: 35px; padding: 2px 0;">
			<div class="barbox1-1">
				<form id="searchForm" onkeydown="if(event.keyCode==13){queryForm();}">
					<table>
						<tr>
							<th>抓拍日期</th> 
							<td><input id="startDate" class="easyui-datetimebox" data-options="editable:false" name="startDate" style="width:100px" /></td><!-- editable:false禁止用户手动输入,easyui-datetimebox带时分秒 -->
							<th> - </th>
							<td><input id="endDate" class="easyui-datetimebox" data-options="editable:false" name="endDate" style="width:100px" /></td>
							<th>患者姓名</th>
							<td><input id="usrname" class="easyui-textbox" name="usrname" style="width:100px" /></td>
							<th>身份证号码</th>
							<td><input id="cardid" class="easyui-textbox" name="cardid" style="width:100px" /></td>
							<th>拍摄地点</th>
							<td>
								<select id="groupname" name="groupname" class="easyui-combobox" style="text-align: center" data-options="width:100,panelHeight:'auto'" >
									<option value="" selected="true">&nbsp;</option>
									<option value="八院血透库">八院血透库</option>
									<option value="六院PT库">六院PT库</option>
								</select>
							</td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-search" onclick="queryForm();">查询</a></td>
							<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-remove" onclick="clearForm();">清空</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- 图像预览 -->
	<div id="imgview" class="easyui-dialog" title="人脸识别详情" style="width: 820px; height: 690px;"
		data-options="iconCls:'icon-xqwh', buttons:'#qhsz_btns', closed:true, modal:true">
		<div class="modal-scrollable" style="z-index: 1060;">
			<div class="modal hide fade in modal-overflow" id="faceRecognitionModal" data-backdrop="static"
				data-draggable-handle="handle" data-width="810px" avalonctrl="faceRecognitionCtrl" novalidate="novalidate"
				tabindex="-1" aria-hidden="false" style="display: block; width: 800px;">

				<div class="modal-body no-padding">
					<div class="form-horizontal">
						<div class="top-box">
							<div class="big-imgBox">
								<img class="full" height="400px" width="600px" id="origPath"
									src="http://10.115.170.53:50000/56879ceb-24d7-11ea-a61b-08eded28a34e/20200509/1/dsf_a21ff713-91b3-11ea-a2f9-08eded28a34e_30667586_30742941.jpg">
							</div>
							<div class="same-info">
								<ul>
									<li>
										<p>匹配人员</p>
									</li>
									<li><img height="146px" width="118px" id="facePath"
										src="http://10.115.170.53:50000/56879ceb-24d7-11ea-a61b-08eded28a34e/20200509/1/dsf_a21ff713-91b3-11ea-a2f9-08eded28a34e_30660978_30663974.jpg">
									</li>
									<li>相似度<span id="similarity">74%</span>
									</li>
									<li><img height="146px" width="118px" id="registerPath"
										src="http://10.115.170.53:80/facePic/20200331/37030319790630423X20200331171349.jpg">
									</li>
								</ul>
							</div>
							<div class="detail-info">
								<div class="place-time">
									<ul>
										<li><b>报警地点</b> <span id="devChnname">六院PT人脸枪机</span></li>
										<li><b>报警时间</b> <span id="recDateStr">2020-05-10
												14:08:55</span></li>
									</ul>
								</div>
								<div class="people-info">
									<p>
										<b>匹配人员信息</b>
									</p>
									<ul>
										<li><b>姓名</b> <span id="name">李莹</span></li>
										<li><b>性别 </b> <span id="sexStr">男</span></li>
										<li><b>出生日期 </b> <span id="birthday">2020-03-31</span></li>
									</ul>
									<ul>
										<li><b>省份 </b> <span id="provinceStr">山东</span></li>
										<li><b>城市 </b> <span id="cityStr">淄博</span></li>
										<li><b>注册库 </b> <span id="groupName">六院PT库</span></li>
									</ul>
									<ul>
										<li><b>证件类型 </b> <span id="cardTypeStr">身份证</span></li>
										<li><b>证件号码 </b> <span id="cardId">37030319790630423X</span>
										</li>
									</ul>
								</div>
								<span> <a href="#" class="easyui-linkbutton"
									onClick="closeMessageDialog()" iconCls="icon-no"
									style="padding: 0 10px; margin: 10px 300px; width: 80px;">关闭</a>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	视频回放
	<div id="playback" class="easyui-dialog" title="视频回放"
		style="width: 810px; height: 510px;"
		data-options="iconCls:'icon-xqwh', buttons:'#qhsz_btns', closed:true, modal:true">
		<video id="videoCell" display:inline-block; src=""
			style="width:780px; height:460px;" controlsList="nodownload"
			autoplay=""> </video>
	</div>


</body>
</html>
