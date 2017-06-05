package com.fr.plugins.democharts.custompie;

import com.fr.chart.chartattr.Plot;
import com.fr.design.chart.fun.impl.AbstractIndependentChartsUI;
import com.fr.design.mainframe.chart.ChartsConfigPane;
import com.fr.design.mainframe.chart.gui.ChartDataPane;
import com.fr.design.mainframe.chart.gui.data.table.AbstractTableDataContentPane;
import com.fr.plugins.democharts.custompie.data.pane.ChartConfigPane;
import com.fr.plugins.democharts.custompie.data.pane.DemoTableDataContentPane;

/**
 * Created by mengao on 2017/4/26.
 * 自定义图表类型设设计界面，需要继承AbstractIndependentChartsUI
 * 需要实现方法：getChartConfigPane
 * 需要实现方法：currentAPILevel
 */
public class DemoChartsPieUIWithCustomPane extends AbstractIndependentChartsUI {

    /**
     * @param plot
     * @param parent
     * @return 数据集数据配置面板，自定义数据集数据配置面板需要重写
     */
    @Override
    public AbstractTableDataContentPane getTableDataSourcePane(Plot plot, ChartDataPane parent) {
        return new DemoTableDataContentPane(parent);
    }

    /**
     * @param plotID
     * @return 图表样式面板，用户需要实现
     */
    @Override
    public ChartsConfigPane getChartConfigPane(String plotID) {
        return new ChartConfigPane();
    }

    /**
     * @return 插件的API等级
     */
    @Override
    public int currentAPILevel() {
        return CURRENT_API_LEVEL;
    }
}