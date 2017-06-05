/**
 * Created by richie on 16/1/29.
 */
EChartsFactory = function (options, $dom) {
    this.options = options;
    this.$dom = $dom;
    this.chartID = options.chartID;
    this.autoRefreshTime = options.autoRefreshTime || 0;

    this.width = options.width || $dom.width();// 补充从dom获取.
    this.height = options.height || $dom.height();
    this.sheetIndex = options.sheetIndex || 0;
    this.ecName = options.ecName || '';

    FR.Chart.WebUtils._installChart(this, this.chartID);
};

EChartsFactory.prototype = {

    constructor: EChartsFactory,

    inits: function () {
        debugger;
        //后台传过来的数据或者样式都在 this.options.chartAttr中
        var ct = this.options.chartAttr;
        // function clear(str){return str.replace(/\s/g, '');}
        // var cleanct = clear(ct);
        // console.log(cleanct);
        // var json = JSON.parse(cleanct);

        var myChart = echarts.init(this.$dom[0]);

        function getVirtulData(year) {
            year = year || '2017';
            var date = +echarts.number.parseDate(year + '-01-01');
            var end = +echarts.number.parseDate((+year + 1) + '-01-01');
            var dayTime = 3600 * 24 * 1000;
            var data = [];
            for (var time = date; time < end; time += dayTime) {
                data.push([
                    echarts.format.formatTime('yyyy-MM-dd', time),
                    Math.floor(Math.random() * 10000)
                ]);
            }
            return data;
        }

        var data = ct.data;
        var max = 0
        for (var i = 0; i < data.length; i += 1) {
            if (parseInt(max) < parseInt(data[i][1])) {
                max = data[i][1]
            }
        }
        max = max / 20

        option = {
            backgroundColor: '#404a59',

            title: {
                top: 30,
                text: '2016年某人每天的步数',
                subtext: '数据纯属虚构',
                left: 'center',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '30',
                left: '100',
                data: ['步数', 'Top 12'],
                textStyle: {
                    color: '#fff'
                }
            },
            calendar: [{
                top: 150,
                left: 'center',
                range: ['2012-01-01', '2012-06-30'],
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#000',
                        width: 4,
                        type: 'solid'
                    }
                },
                yearLabel: {
                    formatter: '{start}  1st',
                    textStyle: {
                        color: '#fff'
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#323c48',
                        borderWidth: 1,
                        borderColor: '#111'
                    }
                }
            }, {
                top: 340,
                left: 'center',
                range: ['2012-07-01', '2012-12-31'],
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#000',
                        width: 4,
                        type: 'solid'
                    }
                },
                yearLabel: {
                    formatter: '{start}  2nd',
                    textStyle: {
                        color: '#fff'
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#323c48',
                        borderWidth: 1,
                        borderColor: '#111'
                    }
                }
            }],
            series: [
                {
                    name: '步数',
                    type: 'scatter',
                    calendarIndex: 0,
                    coordinateSystem: 'calendar',
                    data: data,
                    symbolSize: function (val) {
                        return val[1] / max;
                    },
                    itemStyle: {
                        normal: {
                            color: '#ddb926'
                        }
                    }
                },
                {
                    name: '步数',
                    type: 'scatter',
                    coordinateSystem: 'calendar',
                    calendarIndex: 1,
                    data: data,
                    symbolSize: function (val) {
                        return val[1] / max;
                    },
                    itemStyle: {
                        normal: {
                            color: '#ddb926'
                        }
                    }
                },
                {
                    name: 'Top 12',
                    type: 'effectScatter',
                    coordinateSystem: 'calendar',
                    calendarIndex: 1,
                    data: data.sort(function (a, b) {
                        return b[1] - a[1];
                    }).slice(0, 12),
                    symbolSize: function (val) {
                        return val[1] / max;
                    },
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    itemStyle: {
                        normal: {
                            color: '#f4e925',
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                },
                {
                    name: 'Top 12',
                    type: 'effectScatter',
                    coordinateSystem: 'calendar',
                    data: data.sort(function (a, b) {
                        return b[1] - a[1];
                    }).slice(0, 12),
                    symbolSize: function (val) {
                        return val[1] / max;
                    },
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    itemStyle: {
                        normal: {
                            color: '#f4e925',
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                }
            ]
        };
        myChart.setOption(option);
    },

    resize: function () {
        this.newCharts.resize();
    },
    refresh: function () {

    },

    refreshData: function (options) {

    },

    //数据监控的刷新方式
    setData: function (options, aimation) {

    }
};