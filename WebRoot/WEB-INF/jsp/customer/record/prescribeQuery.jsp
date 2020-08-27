<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>处方记录</title> <c:set value="${pageContext.request.contextPath}"
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
                        <table id="prescribe" title="处方记录"
                               style="width: 100%; height: 535px">
                            <thead>
                            <tr>
                                <%--<th field="" width="80">医院编码</th>--%>
                                <th field="rpNo" halign="center" width="130">处方单号</th>
                                <%--<th field="" width="100">处方科别</th>--%>
                                <th field="rpDeptname" halign="center" width="140">处方科别</th>
                                <th field="rpItemcode" halign="center" width="100">处方编码</th>
                                <th field="rpItemname" halign="center" width="150">处方名称</th>
                                <th field="rpPtsname" halign="center" width="80" align="center">患者姓名</th>
                                <th formatter="sexFormatter" field="rpPtssex" halign="center" width="60" align="center">性别</th>
                                <th field="rpPtsage" halign="center" width="70" align="center">年龄</th>
                                <th field="rpPtshealthcard" halign="center" width="80">医保卡号</th>
                                <th field="rpPtsidcard" halign="center" width="180">身份证号</th>
                                <th formatter="birthdayFormatter" field="rpPtsbirthday" halign="center" width="110">出生日期</th>
                                <th field="rpItemprice" halign="center" width="100">单价</th>
                                <th field="rpItemnum" halign="center" width="100">数量</th>
                                <th field="rpItemspecification" halign="center" width="140">规格</th>
                                <th field="rpItemdosageform" halign="center" width="120">剂型</th>
                                <th field="rpItemmfrs" halign="center" width="150">生产商</th>
                                <th field="rpItemmakein" halign="center" width="150">生产地</th>
                                <th field="rpItembatchno" halign="center" width="130">批号</th>
                                <%--<th field="" width="80">仓库编码</th>--%>
                                <th field="rpWhname" halign="center" width="150">仓库名称</th>
                                <%--<th field="" width="80">货位/货架号</th>--%>
                                <th field="rpDrugfreq" halign="center" width="80">用药频次</th>
                                <th field="rpDrugroute" halign="center" width="120">用药途径</th>
                                <th field="rpDrugtime" halign="center" width="100">用药时间</th>
                                <th field="rpDrugamount" halign="center" width="80">用药量</th>
                                <%--<th field="" width="80">处方医师</th>--%>
                                <th field="rpDrname" halign="center" width="80" align="center">处方医师</th>
                                <th field="rpDrtime" halign="center" width="170">开具日期</th>
                                <%--<th field="" width="80">审核医师</th>--%>
                                <th field="rpAuditname" halign="center" width="80" align="center">审核医师</th>
                                <th field="rpAudittime" halign="center" width="170">审核日期</th>
                                <%--<th field="" width="80">核对医师</th>--%>
                                <th field="rpCheckname" halign="center" width="80" align="center">核对医师</th>
                                <th field="rpChecktime" halign="center" width="170">核对日期</th>
                                <%--<th field="" width="80">附件</th>
                                <th field="" width="80">采集日期</th>
                                <th field="" width="80">添加日期</th>--%>
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
        $("#prescribe").datagrid('load', {
            'date': date
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

    function birthdayFormatter(value, row, index) {
        if (value != null) {
            return value.split(" ")[0];
        }
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
        //日期框
        $('#input_date_param').datebox({
            //日期框选中时请求后台数据
            onSelect: function (date) {
                var dateParam = timeStamp2String(date.getTime())
                doSearch(dateParam);
            }
        }).datebox('setValue', initDate);

        // easyui表格初始化
        $('#prescribe').datagrid({
            url: "/record/prescribeData",
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