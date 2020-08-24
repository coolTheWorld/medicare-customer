<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html class="x-admin-sm" lang="zh">
<head>
  <meta charset="UTF-8">
  <title>数据预警首页</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport"
    content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <link rel="stylesheet" href="${path}/evolution/css/font.css">
  <link rel="stylesheet" href="${path}/evolution/css/xadmin.css">
  <script src="${path}/evolution/lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="${path}/evolution/js/xadmin.js"></script>
  <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
  <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="${path}/evolution/js/html5.min.js"> </script>
        <script src="${path}/evolution/js/respond.min.js"></script>
</head>

<body>
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <!-- 可拖动3d图-start -->
      <div class="layui-col-md9">
        <div class="layui-card">
          <div id="dataMap" style="width: 100%;height: 620px;"></div>
        </div>

      </div>
      <div class="layui-col-md3">
        <div class="layui-card">
          <div class="layui-card-header">上传异常
            <span class="layui-badge layui-bg-cyan layuiadmin-badge">周</span></div>
          <div class="layui-card-body  ">
            <p class="layuiadmin-big-font">23条</p>
            <p>占总上传数据
              <span class="layuiadmin-span-color">20%
                <i class="layui-inline layui-icon layui-icon-face-smile-b"></i></span>
            </p>
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">入库异常
            <span class="layui-badge layui-bg-cyan layuiadmin-badge">周</span></div>
          <div class="layui-card-body ">
            <p class="layuiadmin-big-font">4条</p>
            <p>占总数据量
              <span class="layuiadmin-span-color">3%
                <i class="layui-inline layui-icon layui-icon-face-cry"></i></span>
            </p>
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">销售异常
            <span class="layui-badge layui-bg-cyan layuiadmin-badge">周</span></div>
          <div class="layui-card-body ">
            <p class="layuiadmin-big-font">3952条</p>
            <p>占总数据量
              <span class="layuiadmin-span-color">5%
                <i class="layui-inline layui-icon layui-icon-face-surprised"></i></span>
            </p>
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">库存异常
            <span class="layui-badge layui-bg-cyan layuiadmin-badge">周</span></div>
          <div class="layui-card-body ">
            <p class="layuiadmin-big-font">322条</p>
            <p>占总数据量
              <span class="layuiadmin-span-color">2%
                <i class="layui-inline layui-icon layui-icon-face-cry"></i></span>
            </p>
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">刷卡异常
            <span class="layui-badge layui-bg-cyan layuiadmin-badge">周</span></div>
          <div class="layui-card-body ">
            <p class="layuiadmin-big-font">3952条</p>
            <p>占总数据量
              <span class="layuiadmin-span-color">5%
                <i class="layui-inline layui-icon layui-icon-face-surprised"></i></span>
            </p>
          </div>
        </div>
      </div>
      <!-- 可拖动3d图  --end -->


      <!-- 定义 图表类型 -->
      <div class="layui-col-md6">
        <div class="layui-card">
          <div class="layui-card-header">上传异常</div>
          <div class="layui-card-body">
            
              <ul class="layui-timeline">
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月6日</h3>
                    <ul>
                      <li>慢性药物上传失败</li>
                      <li>处方药上传失败</li>
                    </ul>
                  </div>
                </li>
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月7日</h3>
                    <ul>
                      <li>慢性药物上传失败</li>
                      <li>处方药上传失败</li>
                      <li>保健品上传失败</li>
                    </ul>
                  </div>
                </li>
              </ul>


           
          </div>

        </div>
      </div>
      <div class="layui-col-md6">
        <div class="layui-card">
          <div class="layui-card-header">入库异常</div>
          <div class="layui-card-body">
            <div class="layui-row layui-col-space10">

              <ul class="layui-timeline">
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月6日</h3>
                    <ul>
                      <li>慢性药物入库失败</li>
                      <li>处方药入库失败</li>
                    </ul>
                  </div>
                </li>
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月7日</h3>
                    <ul>
                      <li>慢性药物入库失败</li>
                      <li>处方药入库失败</li>
                      <li>保健品入库失败</li>
                    </ul>
                  </div>
                </li>
              </ul>

            </div>
          </div>

        </div>
      </div>
      <div class="layui-col-md4">
        <div class="layui-card">
          <div class="layui-card-header">销售异常</div>
          <div class="layui-card-body">
            <div class="layui-row layui-col-space10">

              <ul class="layui-timeline">
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月6日</h3>
                    <ul>
                      <li>慢性药物销售失败</li>
                      <li>处方药销售失败</li>
                    </ul>
                  </div>
                </li>
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月7日</h3>
                    <ul>
                      <li>慢性药物销售失败</li>
                      <li>处方药销售失败</li>
                      <li>保健品销售失败</li>
                    </ul>
                  </div>
                </li>
              </ul>

            </div>
          </div>

        </div>
      </div>
      <div class="layui-col-md4">
        <div class="layui-card">
          <div class="layui-card-header">库存异常</div>
          <div class="layui-card-body">
            <div class="layui-row layui-col-space10">

              <ul class="layui-timeline">
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月6日</h3>
                    <ul>
                      <li>慢性药物销售数据异常</li>
                      <li>处方药销售数据异常</li>
                    </ul>
                  </div>
                </li>
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月7日</h3>
                    <ul>
                      <li>慢性药物销售数据异常</li>
                      <li>处方药销售数据异常</li>
                      <li>保健品销售数据异常</li>
                    </ul>
                  </div>
                </li>
              </ul>

            </div>
          </div>

        </div>
      </div>
      <div class="layui-col-md4">
        <div class="layui-card">
          <div class="layui-card-header">刷卡异常</div>
          <div class="layui-card-body">
            <div class="layui-row layui-col-space10">

              <ul class="layui-timeline">
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月6日</h3>
                    <ul>
                      <li>12:36  - 感冒灵刷卡异常</li>
                      <li>14:26  - 马世龙刷卡异常</li>
                    </ul>
                  </div>
                </li>
                <li   class="layui-timeline-item">
                  <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                  <div  class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">7月7日</h3>
                    <ul>
                      <li>8:40 - 双黄连刷卡异常</li>
                      <li>9:45 - 九九花露水刷卡异常</li>
                      <li>18:20 - 人参药粉刷卡异常</li>
                    </ul>
                  </div>
                </li>
              </ul>

            </div>
          </div>

        </div>
      </div>



    </div>
  </div>
  </div>
</body>
<script src="${path}/evolution/js/echarts.js"></script>
<script src="${path}/evolution/js/echarts-gl.js"></script>
<script type="text/javascript">
  // 获取图表的id实例
  var dataMap = echarts.init(document.getElementById('dataMap'));
  var data = [[0, 0, 22], [0, 1, 11], [0, 2, 66], [0, 3, 55], [0, 4, 99], [0, 5, 23], [0, 6, 44], [0, 7, 123],
  [1, 0, 22], [1, 1, 11], [1, 2, 66], [1, 3, 55], [1, 4, 99], [1, 5, 23], [1, 6, 44], [1, 7, 123],
  [2, 0, 22], [2, 1, 11], [2, 2, 66], [2, 3, 55], [2, 4, 99], [2, 5, 23], [2, 6, 44], [2, 7, 123],
  [3, 0, 22], [3, 1, 11], [3, 2, 66], [3, 3, 55], [3, 4, 99], [3, 5, 23], [3, 6, 44], [3, 7, 123],
  [4, 0, 22], [4, 1, 11], [4, 2, 66], [4, 3, 55], [4, 4, 99], [4, 5, 23], [4, 6, 44], [4, 7, 123],]
  var dataMapOption = {
    tooltip: {},
    visualMap: {
      max: 20,
      inRange: {
        color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
      }
    },
    xAxis3D: {
      type: 'category',
      data: ['7-1', '7-2', "7-3", "7-4", "7-5", "7-6", "7-7", "7-8"]
    },
    yAxis3D: {
      type: 'category',
      data: ['上传异常', '入库异常', '销售异常', '库存异常', "刷卡异常"]
    },
    zAxis3D: {
      type: 'value'
    },
    grid3D: {
      boxWidth: 200,
      boxDepth: 80,
      viewControl: {
        // projection: 'orthographic'
      },
      light: {
        main: {
          intensity: 1.2,
          shadow: true
        },
        ambient: {
          intensity: 0.3
        }
      }
    },
    series: [{
      type: 'bar3D',
      data: this.data.map(function (item) {
        console.log(item)
        return {

          value: [item[1], item[0], item[2]],
        }
      }),
      shading: 'lambert',

      label: {
        textStyle: {
          fontSize: 16,
          borderWidth: 1
        }
      },

      emphasis: {
        label: {
          textStyle: {
            fontSize: 20,
            color: '#900'
          }
        },
        itemStyle: {
          color: '#900'
        }
      }
    }]
  }

  dataMap.setOption(dataMapOption)
</script>
</html>