<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>人脸识别</title>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<link href="${path}/js/common.css" rel="stylesheet">
<script src="${path}/js/Plugin/jquery-3.3.1.min.js"></script>
<script src="${path}/js/Plugin/echarts.js"></script>
<script src="${path}/js/Plugin/echarts.min.js"></script>
<script src="${path}/js/common.js"></script>
<script src="${path}/js/index.js"></script>
<script src="${path}/js/Plugin/laydate/laydate.js"></script>
</head>
<body>
	<header class="header left">
    <!-- <div class="left nav">
        <ul>
            <li class="nav_active"><i class="nav_1"></i><a href="index.html">采集概况</a> </li>
            <li><i class="nav_2"></i><a href="quota.html">库存平衡</a> </li>
            <li><i class="nav_3"></i><a href="trend.html">刷卡统计</a> </li>
            <li><i class="nav_4"></i><a href="chronic.html">销量统计</a> </li>
            </ul>
    </div> -->
    <div class="header_center left" style="position:relative">        
        <h2><strong>人脸数据比对分析</strong></h2>
    </div>
    <div class="right nav text_right">
        <ul>

        </ul>
    </div>

</header>
<!--内容部分-->
<div class="con left">
    <!--数据总概-->
    <div class="con_div">
        <div class="con_div_text left">
            <div class="con_div_text01 left">
                <img src="${path}/images/info_6.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p><a style="color: #fff" href="/face/toTreatment?type=t1">血透总人次</a></p>
                    <p id="total1">0</p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="${path}/images/info_3.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p><a style="color: #fff" href="/face/toTreatment?type=c1">本月血透人次</a></p>
                    <p id="month1">0</p>
                </div>
            </div>
        </div>
        <div class="con_div_text left">
            <div class="con_div_text01 left">
                <img src="${path}/images/info_10.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p><a style="color: #fff" href="/face/toTreatment?type=t2">康复总人次</a></p>
                    <p id="total2" class="sky">0</p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="${path}/images/info_3.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p><a style="color: #fff" href="/face/toTreatment?type=c2">本月康复人次</a></p>
                    <p id="month2" class="sky">0</p>
                </div>
            </div>
        </div>
        <div class="con_div_text left" id="a">

            <div class="con_div_text01 left">
                <img src="${path}/images/info_7.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p><a style="color: #fff" href="/face/toTreatment?type=w0">本月结算人次</a></p>
                    <p id="warn0" class="org">0</p>
                </div>
            </div>
            <div class="con_div_text01 right" >
                <img src="${path}/images/info_5.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p>
                    	<!-- <a style="color: #fff" href="#">人脸比对预警</a></br> -->
                    	<a style="color: #fff" href="/face/toTreatment?type=w1">血透预警<a>/
                    	<a style="color: #fff" href="/face/toTreatment?type=w2">康复预警</a>
                    </p>
                    <div id="warning" style="margin-top: -5px;">
                    <span id="warn1" class="org" style="font-size: 30px;font-weight: bold;">0</span>
                    <span style="color: white;">/</span>
                    <span id="warn2" class="org" style="font-size: 30px;font-weight: bold;">0</span>
                	</div>	
                </div>
            </div>
        </div>
    </div>
    <!--统计分析图-->
    <div class="div_any">
        <div class="left div_any01">
            <div class="div_any_child">
                <div class="div_any_title"><img src="${path}/images/title_1.png">本周住院记录排名</div>
                <p id="pieChart1" class="p_chart"></p>
            </div>
            <div class="div_any_child">
                <div class="div_any_title"><img src="${path}/images/title_2.png">本周血透次数排名</div>
                <p id="histogramChart" class="p_chart"></p>
            </div>
        </div>
        <div class="div_any02 left ">
            <div class="div_any_child div_height">
                <div class="div_any_title any_title_width"><img src="${path}/images/title_0.png">抓拍实时人脸图像 </div>
                 <div id="imagShow" style="width:97.5%;height:95%;display: inline-block;padding-left: 1.25%;padding-top:2.2%">
					
				</div>
            </div>
        </div>
        <div class="right div_any01">
            <div class="div_any_child">
                <div class="div_any_title"><img src="${path}/images/title_3.png">数据采集条数(当日)</div>
                <p id="lineChart" class="p_chart"></p>
            </div>
            <div class="div_any_child">
                <div class="div_any_title"><img src="${path}/images/title_4.png">结算异常数据</div>
                <p id="lineChart2" class="p_chart"></p>
            </div>
        </div>
    </div>

    <%-- <div id="el-dialog" class="">
        <div class="xc_layer"></div>
        <div class="popBox" id="printView">
            <div class="ttBox"><span class="tt" id="reportTitle">第一医院</span><img src="${path}/images/close.png" style="width: 30px;float: right;cursor: pointer;" title="关闭弹窗" class="close"/></div>
            <div class="txtBox" id="el-dialog_body">
             <div style="height:100%;width: 98%;margin-left: 1%;">
               <div class="left div_any01" style="width: 64%;">
                   <div class="div_any_child">
                       <div class="div_any_title"><div type="text" class="demo-input" id="date1" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="${path}/images/title_4.png">门诊住院人次</div>
                       <p id="lineChart3" class="p_chart"></p>
                   </div>
                   <div class="div_any_child">
                       <div class="div_any_title"><div type="text" class="demo-input" id="date2" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="${path}/images/title_7.png">医疗费用</div>
                       <p id="lineChart4" class="p_chart"></p>
                   </div>
               </div>
               <div class="left div_any01"  style="width: 32%;">
                   <div class="div_any_child">
                       <div class="div_any_title"><div type="text" class="demo-input" id="date3" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="${path}/images/title_18.png">病人年龄段分布</div>
                       <p id="pieChart2" class="p_chart"></p>
                   </div>
                   <div class="div_any_child">
                       <div class="div_any_title"><div type="text" class="demo-input" id="date4" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="${path}/images/title_20.png">医疗费用组成</div>
                       <p id="pieChart3" class="p_chart"></p>
                   </div>
               </div>

             </div>
            </div>
        </div>
    </div> --%>

</div>
<script type="text/javascript">
var lasttime = null, allcount = 0, percount=15;
var timer = setInterval("showLiveImgs()", 3000);
$(function(){
	getStatistics();
	showLiveImgs();
})


function getStatistics(){
	$.get("/face/getStatistics", function(data){
		$.each(data.total, function(i, n){
			if(n.platname == "八院血透库"){
				$("#total1").html(n.total);
			}else if(n.platname == "六院PT库"){
				$("#total2").html(n.total);
			}  			
		});
		$.each(data.month, function(i, n){
			if(n.platname == "八院血透库"){
				$("#month1").html(n.total);
			}else if(n.platname == "六院PT库"){
				$("#month2").html(n.total);
			}  			
		});
		$.each(data.warn, function(i, n){
			if(n.platname == "八院血透库"){
				$("#warn1").html(n.total);
			}else if(n.platname == "六院PT库"){
				$("#warn2").html(n.total);
			}  
		});
		
	});	
}

function showLiveImgs(){
	$.ajax({
		type: "GET",
	    url: "/face/showImg?lasttime="+lasttime+"&getnum="+percount,
	    dataType: 'json',
	    success: function(datas) {
	    	for(var i=0; i<datas.length; i++){
	    		var cbevents = datas[i];
	    		var detecttime = cbevents.detecttime;
	    		if (i == 0)
	    		   lasttime = detecttime;
	    		console.log(lasttime);
	    		//var tmstart = treatment.cardid;
	    		//var tmstartStr = tmstart.format("yyyy/MM/dd hh:mm:ss");
	    		var sex = "";
	    		if(cbevents.sex=='1'){
	    			sex="男";
	    		}else{
	    			sex="女";
	    		}
	    		var imagstart = "<div class='aa' style='height:180px; width:128px;margin-left:15px;margin-top:10px;padding-top:20px; background: url(\"${path}/images/face-old.png\") no-repeat;'>"
				    		    +"<img class='face-img' src='"+cbevents.imageurl+"' style='width:70px;height:70px;display: inline-block;padding-left: 25%;padding-top:2.2%'/>"
					    		+"<div class='capture-text'>"
					    		+"<div class='face-time'>"+detecttime.substr(5,14)+"</div>"
					    		+"<div class='face-name' style='display:block;'>姓名: "+cbevents.usrname+"</div>"
					    		+"<div class='face-sex' style='display:block;'>性别: "+sex+"</div>"
					    		+"<div class='face-sex' style='display:block;'>"+cbevents.groupname+"</div>"
					    		+"</div>"
					    		+"</div>";
		    	allcount++;
		    	//$("#imagShow").append(imagstart);
		    	if(allcount<=15){
		    	   percount=1;	
		    	   $("#imagShow").append(imagstart);
		    	}else
		    	   $("#imagShow").prepend(imagstart);
		    	if(allcount>15){
		    		$("#imagShow").children("div:last-child").remove();
		    	}
	    	}
	    }
	});
}
</script>
</body>
</html>
