<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>住院记录</title> <c:set value="${pageContext.request.contextPath}"
                               var="path" scope="page"/>
    <link rel="icon" href="favicon.ico" type="image/ico">
    <meta name="keywords" content="医保管理系统">
    <meta name="description" content="医保管理系统">
    <meta name="author" content="eway">


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
        <div class="container-fluid">
            <div class="row">
                <div class="card">
                    <div class="card-toolbar clearfix">
                        <div class="toolbar-btn-action">
                            <span style="font-weight: 700; padding-right: 5px;">选择日期:</span>
                            <input id="input_date_param" class="easyui-datebox m-r-5">
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchYesterday()">昨天</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               onclick="searchBeforeYesterday()">前天</a>
                        </div>
                    </div>
                    <div class="card-body">
                        <table id="hospitalized" title="入院记录"
                               style="width: 100%; height: 535px">
                            <thead>
                            <tr>
                                <%--<th field="" width="80">医院编码</th>--%>
                                <th field="caseNo" halign="center" width="100">病案号</th>
                                <th field="siPtsname" halign="center" width="80" align="center">患者姓名</th>
                                <th formatter="sexFormatter" field="siPtssex" halign="center" width="60" align="center">性别</th>
                                <th field="siPtsage" halign="center" width="70" align="center">年龄</th>
                                <th field="hospDeptname" halign="center" width="130">入院科室</th>
                                <%--<th field="" width="80">主冶医师编码</th>--%>
                                <th field="hospDoctorname" halign="center" width="80" align="center">主冶医师</th>
                                <th formatter="doctorTypeFormatter" field="doctorType" halign="center" width="150">就医类别</th>
                                <th field="hospIntime" halign="center" width="170">住院日期</th>
                                <th field="hospAreas" halign="center" width="100" align="center">病区名称</th>
                                <th field="hospWardno" halign="center" width="80" align="center">房间号</th>
                                <th field="hospBedno" halign="center" width="80" align="center">床位号</th>
                                <th field="siPtsidcard" halign="center" width="180">身份证号</th>
                                <th field="siPtshealthcard" halign="center" width="80">医保卡号</th>
                                <th field="hospNo" halign="center" width="150">住院号</th>
                                <th formatter="diagTypeFormatter" field="diagType" halign="center" width="150" align="center">接诊方式</th>
                                <%--<th field="" width="80">门诊科室编码</th>--%>
                                <th field="diagDeptname" halign="center" width="150" align="center">门诊科室</th>
                                <%--<th field="" width="110">确诊医师编码</th>--%>
                                <th field="diagDoctorname" halign="center" width="80" align="center">确认医师</th>
                                <th field="diagDatetime" halign="center" width="170">确诊日期</th>
                                <th field="icdCode" halign="center" width="100">疾病编码</th>
                                <th field="icdName" halign="center" width="150">疾病名称</th>
                                <th formatter="hospTypeFormatter" field="hospType" halign="center" width="120" align="center">住院类别</th>
                                <th formatter="hospWayFormatter" field="hospWay" halign="center" width="80" align="center">入院方式</th>
                                <%--<th field="" width="80">入院科室编码</th>--%>
                                <%--<th field="" width="80">采集日期</th>
                                <th field="" width="80">创建日期</th>--%>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
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

    //查询昨天数据
    function searchYesterday() {
        var time = new Date().getTime() - 24 * 60 * 60 * 1000;//获取昨天日期
        doSearch(timeStamp2String(time));
    }

    //查询昨天数据
    function searchBeforeYesterday() {
        var time = new Date().getTime() - 24 * 60 * 60 * 1000 * 2;//获取前天日期
        doSearch(timeStamp2String(time));
    }

    function doSearch(date) {
        $("#hospitalized").datagrid('load', {
            'date': date
        });
    }

    function doctorTypeFormatter(value, row, index) {
        if (value == null) {
            value = "未说明"
        } else if (value == "001") {
            value = "本地定点医院就医";
        } else if (value == "002") {
            value = "异地安置";
        } else if (value == "003") {
            value = "长期驻外";
        } else if (value == "004") {
            value = "准假外出";
        } else if (value == "005") {
            value = "异地转诊转院";
        } else if (value == "006") {
            value = "非定点急诊";
        } else if (value == "007") {
            value = "异地安置的异地转院";
        } else if (value == "008") {
            value = "长驻外地的异地转院";
        } else if (value == "009") {
            value = "累加";
        } else if (value == "010") {
            value = "其他";
        }
        return value;
    }

    function diagTypeFormatter(value, row, index) {
        if (value == null || value == "0") {
            value = "未说明"
        } else if (value == "001") {
            value = "新发生";
        } else if (value == "002") {
            value = "自急诊转入";
        } else if (value == "003") {
            value = "自门诊大病转入";
        } else if (value == "004") {
            value = "自门诊特殊转入";
        } else if (value == "005") {
            value = "自家庭病床转入";
        } else if (value == "006") {
            value = "自住院转入";
        } else if (value == "007") {
            value = "康复住院";
        }
        return value;
    }

    function hospTypeFormatter(value, row, index) {
        console.log(value);
        if (value == null) {
            value = "未说明"
        } else if (value == "001") {
            value = "住院";
        } else if (value == "002") {
            value = "家庭病床";
        } else if (value == "003") {
            value = "急诊转院";
        } else if (value == "004") {
            value = "门诊慢性病";
        }
        return value;
    }

    function hospWayFormatter(value, row, index) {
        if (value == null) {
            value = "未说明"
        } else if (value == "001") {
            value = "门诊";
        } else if (value == "002") {
            value = "急诊";
        } else if (value == "003") {
            value = "转入";
        }
        return value;
    }

    function sexFormatter(value, row, index) {
        if (value == null) {
            value = "未说明"
        } else if (value == "1") {
            value = "男"
        } else if (value == "2") {
            value = "女"
        } else {
            value == "未说明"
        }
        return value
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

    $(function () {
        //日期框
        $('#input_date_param').datebox({
            //日期框选中时请求后台数据
            onSelect: function (date) {
                var dateParam = timeStamp2String(date.getTime())
                doSearch(dateParam);
            }
        }).datebox('setValue', initDate);

        // easyui表格初始化
        $('#hospitalized').datagrid({
            url: "/record/hospitalizedData",
            queryParams: {
                'date': initDate
            },
            method: "get",
            rownumbers: true,
            singleSelect: true,
            autoRowHeight: false,
            width: window.innerWidth / 10 * 9,
            height: window.innerHeight / 10 * 8,
            pagination: true,
            pageSize: 20,
            pageList: [10, 20, 50, 100],
            emptyMsg: '未查询到相应数据',
        })
    });
</script>
</body>
</html>