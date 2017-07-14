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
        //后台传过来的数据或者样式都在 this.options.chartAttr中
        var ct = this.options.chartAttr;
        var myChart = echarts.init(this.$dom[0]);
        var data = ct.data;
        var max = 0
        for (var i = 0; i < data.length; i += 1) {
            if (parseInt(max) < parseInt(data[i][1])) {
                max = data[i][1]
            }
        }
        var year = echarts.number.parseDate(data[0][0]).getFullYear()
        var title = ct.title
        max = max / 20

        option = {
            backgroundColor: '#404a59',

            title: {
                top: 30,
                text: title,
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
                data: ['step count', 'Top 12'],
                textStyle: {
                    color: '#fff'
                }
            },
            calendar: [{
                top: 100,
                left: 'center',
                range: [year + '-01-01', year + '-6-30'],
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
                top: 290,
                left: 'center',
                range: [year + '-07-01', year + '-12-31'],
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
                    name: 'step count',
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
                    name: 'step count',
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