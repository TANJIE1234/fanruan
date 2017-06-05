package com.fr.plugins.democharts.custompie;

import com.fr.chart.chartattr.Chart;
import com.fr.chart.fun.impl.AbstractIndependentChartsProvider;

/**
 * Created by mengo on 16/5/11.
 * 自定义图表，需要继承AbstractIndependentChartsProvider
 * 需要实现方法：getChartName
 * 需要实现方法：getWrapperName
 * 需要实现方法：getChartTypes
 * 需要实现方法：getChartImagePath
 * 需要实现方法：getRequiredJS
 * 需要实现方法：currentAPILevel
 */
public class DemoChartsPieWithCustomPane extends AbstractIndependentChartsProvider {

    public static PieChartWithCustomPane[] charts = new PieChartWithCustomPane[]{
            new PieChartWithCustomPane(),
    };

    /**
     * 图表的国际化的名字的key
     *
     * @return 图表国际化的名字的key
     */
    @Override
    public String getChartName() {
        return "自定义数据配置面板图表";
    }

    /**
     * 该种图表所有的图表类型，比如柱形图就有堆积柱形图，百分比堆积柱形图等等
     *
     * @return 所有的图表类型
     */
    @Override
    public Chart[] getChartTypes() {
        return charts;
    }


    /**
     * 图表在web端展现时需要的JS文件
     *
     * @return JS文件数组
     */
    @Override
    public String[] getRequiredJS() {
        return new String[]{
                "/com/fr/plugins/democharts/web/echarts.bridge.js"
        };
    }

    /**
     * JS对象名，该对象一般是一个函数，执行后会在给定的dom中绘制图表
     *
     * @return JS对象名
     */
    @Override
    public String getWrapperName() {
        return "EChartsFactory";
    }

    /**
     * 定义在设计器里展现的图的路径
     *
     * @return 图的路径
     */
    @Override
    public String getChartImagePath() {
        return "com/fr/plugins/democharts/images/pie256.png";
    }

    /**
     * @return 插件的API等级
     */
    @Override
    public int currentAPILevel() {
        return CURRENT_API_LEVEL;
    }
}
