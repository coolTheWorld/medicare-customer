var symptomName = last_month_day();
//var t2 = window.setInterval("addImg()",3000);//每隔几秒钟执行一次
var a = 0;

$(function(){

  //addImg();
  init();
  //init2();
  /*$("#el-dialog").addClass("hide");
  $(".close").click(function(event) {
    $("#el-dialog").addClass("hide");
  });

  var date = new Date();
     var numble = date.getDate();
     var today = getFormatMonth(new Date());
     $("#date1").html(today);
     $("#date2").html(today);
     $("#date3").html(today);
     $("#date4").html(today);

  lay('.demo-input').each(function(){
     laydate.render({
        type: 'month',
         elem: this,
         trigger: 'click',
         theme: '#95d7fb',
         calendar: true,
         showBottom: true,
         done: function () {
            console.log( $("#startDate").val())
         }
     })
 });*/

})

function addImg(){
	$.ajax({
		type: "GET",
	    url: "/face/showImg?time="+3000,
	    dataType: 'json',
	    success: function(datas) {
	    	for(var i=0;i<datas.length;i++){
	    		var cbevents = datas[i];
	    		//var tmstart = treatment.cardid;
	    		//var tmstartStr = tmstart.format("yyyy/MM/dd hh:mm:ss");
	    		var sex = "";
	    		if(cbevents.sex=='1'){
	    			sex="男";
	    		}else{
	    			sex="女";
	    		}
	    		var imagstart = "<div class='aa'><img class='face-img' src='"+cbevents.imageurl+"' style='width:50%;height:60%;display: inline-block;padding-left: 25%;padding-top:2.2%'/>"
		    		+"<div class='capture-text'><div class='face-time'>"+cbevents.detecttime+"</div><div class='face-name'>姓名:"+cbevents.usrname+"</div><div class='face-sex'>性别:"+sex+"</div></div></div>";
		    	a++;
		    	$("#imagShow").prepend(imagstart);
	    	}
	    }
	});
	if(a>16){
		a=0;
		$("#imagShow").html("");
	}
}

function init(){
	
	//$("#imagShow").html("");
	/*var imagstart = "<div class='aa'><img class='face-img' src='C:/Users/Administrator/Desktop/1234.png' style='width:80%;height:70%;display: inline-block;padding-left: 10%;padding-top:2.2%'/>"
		+"<div class='capture-text'><div class='face-time'>时间</div><div class='face-sex'>性别:"+10+"</div><div class='face-age'>年龄:"+1+"</div></div></div>";
	$("#imagShow").prepend(imagstart);*/
	optionOne = {
			color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
			calculable : false,
			 tooltip : {
		      trigger: 'item',
		      formatter: "{a}<br/>{b}<br/>{c} 人次"
		 	 },
			legend:{y : 'top',padding:[20,0,0,0],textStyle : {color : '#ffffff',},},
			grid:{
		        left: '5%',
		        right: '5%',
		        bottom: '20%',
		        containLabel: true
			},
			xAxis: [{data : ['周一','周二','周三','周四','周五','周六','周日'],
				axisLine:{
	                lineStyle:{
	                    color: '#fff'
	                },
            	},
				axisLabel: {
                    textStyle: {color: '#fff'},
                }}],
			yAxis: [
		        {type: 'value',
		            axisLine : {onZero: false},
		            axisLine:{
		                lineStyle:{color: '#fff'},
		          },
		            axisLabel: {
		                textStyle: {color: '#fff'},
		                formatter: function (value) {
		                    return value + "人次"
		                },
		            },
		            splitLine:{
		                lineStyle:{width:0,type:'solid'}
		            }
		        }
		    ],
		    series : []
		};
	myChart=echarts.init(document.getElementById('pieChart1'));//获取容器
	$.ajax({
		type: "GET",
	    url: "/face/lineChart",
	    dataType: 'json',
	    success: function(data) {
	    	var Item = function(){  
	            return {  
	                name:'',  
	                type:'line',  
	                itemStyle: {normal: {lineStyle: {shadowColor : 'rgba(0,0,0,0.4)'}}},  
	                data:[]  
	            }  
	        };  
	        var legends = [];  
	        var Series = []; 
	        var jsons = data.lineChart;
	        for(var i=0;i < jsons.length;i++){  
	            var it = new Item();  
	            it.name = jsons[i].name;  
	            legends.push(jsons[i].name);  
	            it.data = jsons[i].data;  
	            Series.push(it);  
	        }  
//        	var it = new Item();  
//            it.name = "淄博市中西医结合医院";  
//            legends.push("淄博市中西医结合医院");  
//            it.data = data.y8;  
//            Series.push(it); 
//	        
//        	var it = new Item();  
//            it.name = "淄博市职业病防治院";  
//            legends.push("淄博市职业病防治院");  
//            it.data = data.y6;  
//            Series.push(it); 
            
	        optionOne.legend.data = legends;  
	        optionOne.series = Series; // 设置图表  
	        myChart.setOption(optionOne);// 重新加载图表  
	    }
	});
	
	optionTwo = {
			color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
			calculable : false,
			 tooltip : {
		      trigger: 'item',
		      formatter: "{a}<br/>{b}<br/>{c}条"
		 	 },
			legend:{y : '240',textStyle : {color : '#ffffff',},},
			grid:{
		        left: '5%',
		        right: '5%',
		        bottom: '20%',
		        containLabel: true
			},
			xAxis: [{data : ['0-8点','8-12点','12-16点','16-24点'],
				axisLine:{
	                lineStyle:{
	                    color: '#fff'
	                },
            	},
				axisLabel: {
                    textStyle: {color: '#fff'},
                }}],
			yAxis: [
		        {type: 'value',
		            axisLine : {onZero: false},
		            axisLine:{
		                lineStyle:{color: '#fff'},
		          },
		            axisLabel: {
		                textStyle: {color: '#fff'},
		                formatter: function (value) {
		                    return value + "条"
		                },
		            },
		            splitLine:{
		                lineStyle:{width:0,type:'solid'}
		            }
		        }
		    ],
		    series : []
		};
	var lineChart = echarts.init(document.getElementById('lineChart'));
	$.ajax({
		type: "GET",
	    url: "/face/lineChartVO",
	    dataType: 'json',
	    success: function(data) {
	    	var Item = function(){  
	            return {  
	                name:'',  
	                type:'line',  
	                itemStyle: {normal: {lineStyle: {shadowColor : 'rgba(0,0,0,0.4)'}}},  
	                data:[]  
	            }  
	        };  
	        var legends = [];  
	        var Series = [];  
	        var jsons = data.lineChart;
	        for(var i=0;i < jsons.length;i++){  
	            var it = new Item();  
	            it.name = jsons[i].name;  
	            legends.push(jsons[i].name);  
	            it.data = jsons[i].data;  
	            Series.push(it);  
	        }  
	        optionTwo.legend.data = legends;  
	        optionTwo.series = Series; // 设置图表  
	        lineChart.setOption(optionTwo);// 重新加载图表  
	    }
	});
	
	/*var pieChart1 = echarts.init(document.getElementById('pieChart1'));
	var names = [];
    var num = [];
	$.ajax({
		type: "GET",
	    url: "/face/hemodialysis?judge="+1,
	    dataType: 'json',
	    success: function(datas) {
	    	for(var i=0;i<datas.length;i++){
	    		names.push(datas[i].name);
	    		num[i] = {value: datas[i].num, name: datas[i].name};
	    	}
	    	pieChart1.setOption({
	    		//color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
	    		legend: {
	    			y : '260',
	    			x : 'center',
	    			textStyle : {
	    				color : '#ffffff',
	    			},
	    			data : names,
	    		},
	    		tooltip : {
	    			trigger: 'item',
	    			formatter: "{a}<br/>{b}<br/>{c}次 ({d}%)"
	    		},
	    		calculable : false,
	    		series : [{
	    				name:'采集数据量',
	    				type:'pie',
	    				radius : ['40%', '70%'],
	    				center : ['50%', '45%'],
	    				itemStyle : {
	    					normal : {
	    						label : {
	    							show : false
	    						},
	    						labelLine : {
	    							show : false
	    						}
	    					},
	    					emphasis : {
	    						label : {
	    							show : true,
	    							position : 'center',
	    							textStyle : {
	    								fontSize : '20',
	    								fontWeight : 'bold'
	    							}
	    						}
	    					}
	    				},
	    				data:num,
	    		}]
	    	});   
		}
	});*/
	
	var histogramChart = echarts.init(document.getElementById('histogramChart'));
	$.getJSON('/face/ranking',function(data){
    	var names = [];  
	    var nums = [];  
    	for(var i=0;i<5;i++){
    		names.push(data[i].name); 
    		nums.push(data[i].num); 
    	}
    	histogramChart.setOption({
            title: {
            	textStyle: {
                    color: '#fff'
                },
                text: '血透数据'
            },
            tooltip: {},
            legend: {
            	textStyle: {
                    color: '#fff'
                },
                data:['血透次数']
            },
            xAxis: {
            	axisLine:{
	                lineStyle:{
	                    color: '#fff'
	                },
            	},
            	axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                data: names
            },
            yAxis: [
            	{type: 'value',
		            axisLine : {onZero: false},
		            axisLine:{
		                lineStyle:{
		                    color: '#fff'
		                },
		          },
		            axisLabel: {
		                textStyle: {
		                    color: '#fff'
		                },
		                formatter: function (value) {
		                    return value + "次"
		                },
		            },
		            splitLine:{
		                lineStyle:{
		                    width:0,
		                    type:'solid'
		                }
		            }
		        }
            ],
            series: [{
            	axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                name: '血透次数',
                type: 'bar',
                data: nums
            }]
        });
    });
	/*var namesTwo = [];
    var numTwo = [];
	$.ajax({
		type: "GET",
	    url: "/face/hemodialysis?judge="+2,
	    dataType: 'json',
	    success: function(datas) {
	    	for(var i=0;i<datas.length;i++){
	    		namesTwo.push(datas[i].name);
	    		numTwo[i] = {value: datas[i].num, name: datas[i].name};
	    	}
	    	histogramChart.setOption({
	    		color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
	            legend: {
	                y : '260',
	                x : 'center',
	                textStyle : {
	                    color : '#ffffff',
	                },
	                 data : namesTwo,
	            },
	            tooltip : {
	                trigger: 'item',
	                formatter: "{a}<br/>{b}<br/>{c}次 ({d}%)"
	            },
	            calculable : false,
	            series : [
	                {
	                    name:'采集数据量',
	                    type:'pie',
	                    radius : ['40%', '70%'],
	                    center : ['50%', '45%'],
	                    itemStyle : {
	                        normal : {
	                            label : {
	                                show : false
	                            },
	                            labelLine : {
	                                show : false
	                            }
	                        },
	                        emphasis : {
	                            label : {
	                                show : true,
	                                position : 'center',
	                                textStyle : {
	                                    fontSize : '20',
	                                    fontWeight : 'bold'
	                                }
	                            }
	                        }
	                    },
	                    data:numTwo,
	                }
	            ]
	       });
	    }
	});
	var lineChart = echarts.init(document.getElementById('lineChart'));
    lineChart.setOption({
      color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
      legend: {
          y : '260',
          x : 'center',
          textStyle : {
              color : '#ffffff',
          },
           data : ['宏仁堂王府井店','立健众康康城店','博山景泰成原山国际大药店','漱玉平民中心路店',],
      },
      calculable : false,
      tooltip : {
          trigger: 'item',
          formatter: "{a}<br/>{b}<br/>{c}条"
      },
      yAxis: [
            {
                type: 'value',
                axisLine : {onZero: false},
                axisLine:{
                    lineStyle:{
                        color: '#034c6a'
                    },
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + "k条"
                    },
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                }
            }
        ],
        xAxis: [
            {
                type: 'category',
                data : ['8:00','10:00','12:00','14:00','16:00','18:00','20:00','22:00'],
                axisLine:{
                    lineStyle:{
                        color: '#034c6a'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                },
            }
        ],
        grid:{
                left: '5%',
                right: '5%',
                bottom: '20%',
                containLabel: true
        },
        series : [
          {
              name:'宏仁堂王府井店',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[15, 0, 20, 45, 22.1, 25, 70, 55, 76]
          },
          {
              name:'立健众康康城店',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[25, 10, 30, 55, 32.1, 35, 80, 65, 76]
          },
          {
              name:'博山景泰成原山国际大药店',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[35, 20, 40, 65, 42.1, 45, 90, 75, 96]
          },
          {
              name:'漱玉平民中心路店',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[45, 30, 50, 75, 52.1, 55, 100, 85, 106]
          }
      ]
    });*/

   var lineChart2 = echarts.init(document.getElementById('lineChart2'));
   lineChart2.setOption({

     color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
     legend: {
         y : '260',
         x : 'center',
         textStyle : {
             color : '#ffffff',

         },
          data : ['淄博市中西医结合医院','淄博市职业病防治院',],
     },
     calculable : false,
     tooltip : {
         trigger: 'item',
         formatter: "{a}<br/>{b}<br/>{c}条"
     },
     yAxis: [
           {
               type: 'value',
               axisLine : {onZero: false},
               axisLine:{
                   lineStyle:{
                       color: '#034c6a'
                   },
               },

               axisLabel: {
                   textStyle: {
                       color: '#fff'
                   },
                   formatter: function (value) {
                       return value + "k条"
                   },
               },
               splitLine:{
                   lineStyle:{
                       width:0,
                       type:'solid'
                   }
               }
           }
       ],
       xAxis: [
           {
               type: 'category',
               data : ['8:00','10:00','12:00','14:00','16:00','18:00'],
               axisLine:{
                   lineStyle:{
                       color: '#034c6a'
                   },
               },
               splitLine: {
                   "show": false
               },
               axisLabel: {
                   textStyle: {
                       color: '#fff'
                   },
                   formatter: function (value) {
                       return value + ""
                   },
               },
               splitLine:{
                   lineStyle:{
                       width:0,
                       type:'solid'
                   }
               },
           }
       ],
       grid:{
               left: '5%',
               right: '5%',
               bottom: '20%',
               containLabel: true
       },
       series : [
         {
             name:'博山景泰成原山国际大药店',
             type:'line',
             smooth:true,
             itemStyle: {
                 normal: {
                     lineStyle: {
                         shadowColor : 'rgba(0,0,0,0.4)'
                     }
                 }
             },
             data:[35, 20, 40, 65, 42.1, 45, ].reverse()
         },
         {
             name:'漱玉平民中心路店',
             type:'line',
             smooth:true,
             itemStyle: {
                 normal: {
                     lineStyle: {
                         shadowColor : 'rgba(0,0,0,0.4)'
                     }
                 }
             },
             data:[45, 30, 50, 75, 52.1, 55, 6].reverse()
         }
     ]
   });



}

function init2(){
  var lineChart3 = echarts.init(document.getElementById('lineChart3'));
  lineChart3.setOption({

    color:["#87cefa","#ff7f50",],
    legend: {
        y : 'top',
        x : 'center',
        textStyle : {
            color : '#ffffff',

        },
         data : ['门诊人次','住院人次'],
    },
    calculable : false,
    tooltip : {
        trigger: 'item',
        formatter: "{a}<br/>{b}<br/>{c}人"
    },
    dataZoom: {
         show: true,
         realtime : true,
         start: 0,
         end: 18,
         height: 20,
         backgroundColor: '#f8f8f8',
         dataBackgroundColor: '#e4e4e4',
         fillerColor: '#87cefa',
         handleColor: '#87cefa',
     },
    yAxis: [
          {
              type: 'value',
              axisLine : {onZero: false},
              axisLine:{
                  lineStyle:{
                      color: '#034c6a'
                  },
              },

              axisLabel: {
                  textStyle: {
                      color: '#fff'
                  },
                  formatter: function (value) {
                      return value + "人"
                  },
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              }
          }
      ],
      xAxis: [
          {
              type: 'category',
              data : symptomName,
              boundaryGap : false,
              axisLine:{
                  lineStyle:{
                      color: '#034c6a'
                  },
              },
              splitLine: {
                  "show": false
              },
              axisLabel: {
                  textStyle: {
                      color: '#fff'
                  },
                  formatter: function (value) {
                      return value + ""
                  },
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              },
          }
      ],
      grid:{
              left: '5%',
              right: '5%',
              bottom: '20%',
              containLabel: true
      },
      series : [
        {
            name:'门诊费用',
            type:'line',
            smooth:true,
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)'
                    }
                }
            },
            data:[1150, 180, 2100, 2415, 1212.1, 3125,1510, 810, 2100, 2415, 1122.1, 3215,1510, 801, 2001, 2245, 1232.1, 3245,1520, 830, 2200, 2145, 1223.1, 3225,150, 80, 200, 245, 122.1, 325]
        },
        {
            name:'住院费用',
            type:'line',
            smooth:true,
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)'
                    }
                }
            },
            data:[2500, 1000, 3000, 5005, 3200.1, 3005, 2500, 1000, 3000, 5005, 3200.1, 3005,2500, 1000, 3000, 5005, 3200.1, 3005,2500, 1000, 3000, 5005, 3200.1, 3005, 2500, 1000, 3000, 5005, 3200.1, 3005,2500, 1000, 3000, 5005, 3200.1, 3005,]
        },
    ]
  });


  var lineChart4 = echarts.init(document.getElementById('lineChart4'));
  lineChart4.setOption({

    color:["#87cefa","#ff7f50",],
    calculable : false,
    tooltip : {
        trigger: 'item',
        formatter: "{a}<br/>{b}<br/>{c}元"
    },
    dataZoom: {
         show: true,
         realtime : true,
         start: 0,
         end: 18,
         height: 20,
         backgroundColor: '#f8f8f8',
         dataBackgroundColor: '#e4e4e4',
         fillerColor: '#87cefa',
         handleColor: '#87cefa',
     },
    yAxis: [
          {
              type: 'value',
              axisLine : {onZero: false},
              axisLine:{
                  lineStyle:{
                      color: '#034c6a'
                  },
              },

              axisLabel: {
                  textStyle: {
                      color: '#fff'
                  },
                  formatter: function (value) {
                      return value + "元"
                  },
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              }
          }
      ],
      xAxis: [
          {
              type: 'category',
              data : symptomName,
              boundaryGap : false,
              axisLine:{
                  lineStyle:{
                      color: '#034c6a'
                  },
              },
              splitLine: {
                  "show": false
              },
              axisLabel: {
                  textStyle: {
                      color: '#fff'
                  },
                  formatter: function (value) {
                      return value + ""
                  },
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              },
          }
      ],
      grid:{
              left: '5%',
              right: '5%',
              bottom: '20%',
              containLabel: true
      },
      series : [
        {
            name:'医疗费用',
            type:'line',
            smooth:true,
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)'
                    }
                }
            },
            data:[1500, 800, 1200, 2450, 1122.1, 1325,1150, 180, 1200, 1245, 1122.1, 1325,150, 180, 1200, 2145, 1212.1, 3215,1510, 180, 2100, 2415, 122.1, 325,150, 80, 200, 245, 122.1, 325].reverse()
        },
    ]
  });

  //年龄分布
  var pieChart2 = echarts.init(document.getElementById('pieChart2'));
  pieChart2.setOption({
    color:["#32cd32","#ff7f50","#87cefa","#FD6C88","#4b5cc4","#faff72"],
    tooltip : {
     trigger: 'item',
     formatter: "{a}<br/>{b}<br/>{c}人"
    },
    calculable : true,
    series : [
        {
            name:'发病人数',
            type:'pie',
            radius : [30, 110],
            center : ['50%', '50%'],
            roseType : 'area',
            x: '50%',



            sort : 'ascending',
            data:[
                {value:10, name:'婴儿(1-3岁)'},
                {value:5, name:'少儿(4-10岁)'},
                {value:15, name:'少年(10-18岁)'},
                {value:25, name:'青年(18-45岁)'},
                {value:125, name:'中年(45-60岁)'},
                {value:175, name:'老年(60岁以上)'},
            ]
        }
    ]
  })

  //医疗费用组成
  var pieChart3 = echarts.init(document.getElementById('pieChart3'));
  pieChart3.setOption({
    color:["#32cd32","#ff7f50","#87cefa","#FD6C88","#4b5cc4","#faff72"],
    tooltip : {
     trigger: 'item',
     formatter: "{a}<br/>{b}<br/>{c}元"
    },
    calculable : true,
    series : [
        {
            name:'发病人数',
            type:'pie',
            radius : [30, 110],
            center : ['50%', '50%'],
            roseType : 'area',
            x: '50%',



            sort : 'ascending',
            data:[
                {value:10, name:'诊察费用'},
                {value:500, name:'检查费用'},
                {value:150, name:'检验费用'},
                {value:250, name:'西药费用'},
                {value:125, name:'中药费用'},
                {value:1750, name:'手术费用'},
            ]
        }
    ]
  })
}


