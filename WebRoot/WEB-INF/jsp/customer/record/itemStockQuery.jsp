<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>库存记录</title> <c:set value="${pageContext.request.contextPath}"
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
                <!-- <div class="col-lg-12"> -->
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
                        <table id="itemStock" title="库存记录"
                               style="width: 100%; height: 535px">
                            <thead>
                            <tr>
                                <%--<th field="" width="80">医院编码</th>--%>
                                <th field="itemCode" halign="center" width="120">库存编号</th>
                                <th field="itemName" halign="center" width="250">库存名称</th>
                                <th field="itemNum" halign="center" width="100" align="center">库存数量</th>
                                <th field="itemUnit" halign="center" width="80" align="center">单位</th>
                                <th field="itemSpecification" halign="center" width="140">规格</th>
                                <th field="itemBatchno" halign="center" width="130">生产批号</th>
                                <th field="itemPermission" halign="center" width="130">批准文号</th>
                                <th formatter = "itemDateFormatter" field="itemMfgdate" halign="center" width="110">生产日期</th>
                                <th formatter = "itemDateFormatter" field="itemExpdate" halign="center" width="110">有效期</th>
                                <th field="itemMfrs" halign="center" width="150">生产商</th>
                                <th field="itemMakein" halign="center" width="150">产地</th>
                                <th field="itemHcscode" halign="center" width="110">本位码</th>
                                <%--<th field="" width="80">库房编码</th>--%>
                                <th field="itemWhname" halign="center" width="150">库房名称</th>
                                <th field="itemLocation" halign="center" width="120">货位/货架号</th>
                                <%--<th field="" width="80">采集时间</th>
                                <th field="" width="80">创建时间</th>
                                <th field="" width="80">对比库存</th>--%>
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
        $("#itemStock").datagrid('load', {
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

    function itemDateFormatter(value, row, index){
        if (value != null) {
            return value.split(" ")[0];
        }
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
        $('#itemStock').datagrid({
            url: "/record/itemStockData",
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