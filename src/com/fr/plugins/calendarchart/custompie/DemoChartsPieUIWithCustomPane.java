package com.fr.plugins.calendarchart.custompie;

import com.fr.chart.chartattr.Plot;
import com.fr.design.chart.fun.impl.AbstractIndependentChartsUI;
import com.fr.design.mainframe.chart.ChartsConfigPane;
import com.fr.design.mainframe.chart.gui.ChartDataPane;
import com.fr.design.mainframe.chart.gui.data.table.AbstractTableDataContentPane;
import com.fr.plugins.calendarchart.custompie.data.pane.ChartConfigPane;
import com.fr.plugins.calendarchart.custompie.data.pane.TableDataContentPane;

public class DemoChartsPieUIWithCustomPane extends AbstractIndependentChartsUI {

    /**
     * @return 数据集数据配置面板
     */
    @Override
    public AbstractTableDataContentPane getTableDataSourcePane(Plot plot, ChartDataPane parent) {
        return new TableDataContentPane(parent);
    }

    /**
     * @return 图表样式面板
     */
    @Override
    public ChartsConfigPane getChartConfigPane(String plotID) {
        return new ChartConfigPane();
    }

    @Override
    public int currentAPILevel() {
        return CURRENT_API_LEVEL;
    }
}