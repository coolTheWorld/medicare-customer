<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>门诊记录</title>
    <c:set value="${pageContext.request.contextPath}"
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
                        <table id="clinicrecords" title="门诊记录"
                               style="width: 100%; height: 535px">
                            <thead>
                            <tr>
                                <th field="diagNo" halign="center" width="110">门诊编号</th>
                                <th field="diagDeptname" halign="center" width="130">科室名称</th>
                                <th field="diagDoctorname" halign="center" width="80" align="center">医师姓名</th>
                                <th field="siPtsname" halign="center" width="80" align="center">患者姓名</th>
                                <th formatter="sexFormatter" field="siPtssex" halign="center" width="50" align="center">
                                    性别
                                </th>
                                <th field="siPtsage" halign="center" width="50" align="center">年龄</th>
                                <th field="diagDatetime" halign="center" width="170">门诊日期</th>
                                <th field="diagDoctortime" halign="center" width="170">诊断日期</th>
                                <th field="diagIcdcode" halign="center" width="130">诊断代码</th>
                                <th field="diagIcdname" halign="center" width="150">诊断名称</th>
                                <th field="siPtsidcard" halign="center" width="120">身份证号</th>
                                <th field="siPtshealthcard" halign="center" width="140">医保卡号</th>
                                <th field="regType" halign="center" width="80">挂号类型</th>
                                <th field="diagType" halign="center" width="80">就诊方式</th>
                                <th field="secType" halign="center" width="80">险种类别</th>
                                <th field="diagPaytype" halign="center" width="80">医疗类别</th>
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
        $("#clinicrecords").datagrid('load', {
            'date': date
        });
    }

    //格式化当前日期
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

    $(function () {
        // easyui表格初始化
        $('#clinicrecords').datagrid({
            url: "/record/clinicrecordsData",
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
        });
        //日期框
        $('#input_date_param').datebox({
            //日期框选中时请求后台数据
            onSelect: function (date) {
                var dateParam = timeStamp2String(date.getTime())
                doSearch(dateParam);
            }
        }).datebox('setValue', initDate);
    });
</script>
</body>

</html>