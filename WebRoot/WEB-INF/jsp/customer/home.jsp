<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html class="x-admin-sm" lang="zh" >
<head>
    <meta charset="UTF-8">
    <title>定点数据上传预警平台首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
        content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${path}/evolution/css/font.css">
    <link rel="stylesheet" href="${path}/evolution/css/xadmin.css">
    <script src="${path}/evolution/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${path}/evolution/js/xadmin.js"></script>
</head>

<body>
    <!-- <div class="x-nav">
            <span class="layui-breadcrumb">
                <a href="">首页</a>
                <a href="">演示</a>
                <a>
                    <cite>导航元素</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
                <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
            </a>
        </div> -->
    <!-- 头部统计信息 --start  -->
    <div class="layui-fluid">
        <div class="layui-row layui-col-space5">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-body ">
                        <blockquote class="layui-elem-quote">欢迎：
                            <span class="x-red">${requestScope.customer.cusName}</span>
                        </blockquote>
                    </div>
                </div>
            </div>
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">上传统计</div>
                    <div class="layui-card-body ">
                        <ul class="layui-row layui-col-space10 layui-this x-admin-carousel x-admin-backlog">
                            <li class="layui-col-md3 layui-col-xs6">
                                <a href="javascript:;" class="x-admin-backlog-body">
                                    <h3>入库单数</h3>
                                    <p>
                                        <cite>--</cite></p>
                                </a>
                            </li>
                            <li class="layui-col-md3 layui-col-xs6">
                                <a href="javascript:;" class="x-admin-backlog-body">
                                    <h3>销售单数</h3>
                                    <p>
                                        <cite>--</cite></p>
                                </a>
                            </li>
                            <li class="layui-col-md3 layui-col-xs6">
                                <a href="javascript:;" class="x-admin-backlog-body">
                                    <h3>库存记录</h3>
                                    <p>
                                        <cite>--</cite></p>
                                </a>
                            </li>
                            <li class="layui-col-md3 layui-col-xs6">
                                <a href="javascript:;" class="x-admin-backlog-body">
                                    <h3>药品种类</h3>
                                    <p>
                                        <cite>--</cite></p>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="layui-col-sm12 layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">上传分析
                        <span class="layui-badge layui-bg-cyan layuiadmin-badge">本月</span></div>
                    <div class="layui-card-body  ">
                        <p class="layuiadmin-big-font">本月有--日未上传 / --日正常上传</p>
                        <p>占比
                            <span class="layuiadmin-span-color">--%
                                <i class="layui-inline layui-icon layui-icon-face-smile-b"></i></span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm12 layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">异常预警
                        <span class="layui-badge layui-bg-cyan layuiadmin-badge">本月</span></div>
                    <div class="layui-card-body ">
                        <p class="layuiadmin-big-font">本月数据异常--次</p>
                        <p>目前已停用
                            <span class="layuiadmin-span-color">--
                                <i class="layui-inline layui-icon layui-icon-face-cry"></i></span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm12 layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">解析错误
                        <span class="layui-badge layui-bg-cyan layuiadmin-badge">本月</span></div>
                    <div class="layui-card-body ">
                        <p class="layuiadmin-big-font">--条</p>
                        <p>占总数据量
                            <span class="layuiadmin-span-color">--%
                                <i class="layui-inline layui-icon layui-icon-face-surprised"></i></span>
                        </p>
                    </div>
                </div>
            </div>
    <!-- 表格--start -->
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header">昨日上传明细</div>
            <div class="layui-card-body ">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>入库明细</th>
                            <td>--条</td>
                        </tr>
                        <tr>
                            <th>销售明细</th>
                            <td>--条</td>
                        </tr>
                        <tr>
                            <th>退货记录</th>
                            <td>--条</td>
                        </tr>
                        <tr>
                            <th>库存记录</th>
                            <td>--条</td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
     <!-- 表格--end -->

          <!-- 底部信息 --start -->
          <!--  <div class="layui-col-md12">
            <blockquote class="layui-elem-quote layui-quote-nm">本系统由 淄博亿维数字科技有限公司 提供技术支持。<a href="http://sp2541145.zjbiz.net/" target="_blank">访问官网</a></blockquote>
          </div>
          -->
    </div>
         <!-- 底部信息 --end -->
        </div>
    </div>
    <!-- 头部统计信息 -- end -->


</body>
</html>