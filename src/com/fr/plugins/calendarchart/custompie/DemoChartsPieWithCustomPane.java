package com.fr.plugins.calendarchart.custompie;

import com.fr.chart.chartattr.Chart;
import com.fr.chart.fun.impl.AbstractIndependentChartsProvider;

public class DemoChartsPieWithCustomPane extends AbstractIndependentChartsProvider {

    public static PieChartWithCustomPane[] charts = new PieChartWithCustomPane[]{
            new PieChartWithCustomPane()
    };

    @Override
    public String getChartName() {
        return "自定义数据配置面板图表";
    }

    @Override
    public Chart[] getChartTypes() {
        return charts;
    }


    /**
     * 图表在web端展现时需要的JS文件
     */
    @Override
    public String[] getRequiredJS() {
        return new String[]{
                "/com/fr/plugins/calendarchart/web/echarts.bridge.js"
        };
    }

    /**
     * JS对象名，该对象一般是一个函数，执行后会在给定的dom中绘制图表
     */
    @Override
    public String getWrapperName() {
        return "EChartsFactory";
    }

    /**
     * 定义在设计器里展现的图的路径
     */
    @Override
    public String getChartImagePath() {
        return "com/fr/plugins/calendarchart/images/pie256.png";
    }

    @Override
    public int currentAPILevel() {
        return CURRENT_API_LEVEL;
    }
}
