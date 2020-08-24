<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>人脸识别</title>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<link rel="stylesheet" href="${path}/easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="${path}/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/mystyle.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/base.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/faceApp.css" />
<link href="${path}/js/common.css" rel="stylesheet">
<script src="${path}/easyui/jquery-1.7.2.min.js"></script>
<script src="${path}/easyui/jquery.easyui.min.js"></script>
<script src="${path}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${path}/js/yiwei.js"></script>
<script src="${path}/js/hls.min.js"></script>
</head>
<body>
<!--顶部-->
<header class="header left">
  <div class="left nav">
      <ul>
          <li><i class="nav_1"></i><a href="/face/toVisualization">返回首页</a> </li>
       </ul>
  </div>
    <div class="header_center left" style="position:relative">  
        <h2 id="tblTitle"><strong>出、入院记录</strong></h2>
    </div>
    <div class="right nav text_right">
        <ul>
        </ul>
    </div>

</header>
<!--内容部分-->
<div class="con left" style="width:100%;">
    <!--统计分析图-->
    <div class="left div_table_box" style="width: 100%;">
        <div class="div_any_child" style="height: 500px;">
            <div class="div_any_title"><img src="../images/title_4.png">人脸识别记录</div>
            <div class="table_p" style="margin-top: 40px;">
                <table id="treatmentdetail">
                    <thead><tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>身份证号</th>
                        <th>入院时间</th>
                        <th>出院时间</th>
                        <th>治疗/滞留时长(分钟)</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>

    </div>

</div>
<div class="left nav" style="width: 50%; margin-top: 20px;">
    <ul>
        <li><i class="nav_2"></i><a id="pagepre" href="#">上一页</a> </li>
        <li><i class="nav_3"></i><a id="pagenext" href="#">下一页</a> </li>
    </ul>
</div>
<div id="playback" class="easyui-dialog" title="视频回放"
	style="width: 810px; height: 510px;"
	data-options="iconCls:'icon-xqwh', buttons:'#qhsz_btns', closed:true, modal:true">
	<video id="videoCell" display:inline-block; src=""
		style="width:780px; height:460px;" controlsList="nodownload"
		autoplay=""> </video>
</div>

<script type="text/javascript">
var type = "${datatype}";
var hls;
$(function(){
	hls = new Hls();
	getDataByPage(1);
})


function getDataByPage(page){
	$.get("/face/getTreatment?page="+page+"&type="+type, 
		function(data){
		$("#tblTitle").html("<strong>" + data.title + "</strong>");
		$("#treatmentdetail tbody").html(data.html);		
		$("#pagepre").attr("href", "javascript: getDataByPage("+parseInt(page-1)+");"); 
		$("#pagenext").attr("href", "javascript: getDataByPage("+parseInt(page+1)+");");
	});	
}

//录像回放
function playback(tmid){
	$.ajax({
		type: "POST",
		data: "tmid=" + tmid,
		url: "/face/getPlaybackURLVO",
		success: function(data) {
		console.log(data);
		$("#el-dialog").show();
		$('#playback').dialog('open').dialog('center');
		var video = document.getElementById("videoCell");
			video.src = data;
			hls.loadSource(data);
			hls.attachMedia(video);
			hls.on(Hls.Events.MANIFEST_PARSED, function(){
			   video.play();
			});
		}
	});
}
</script>
</body>
</html>

