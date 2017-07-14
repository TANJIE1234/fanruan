package com.fr.plugins.calendarchart.custompie.data.pane;

import com.fr.chart.chartattr.ChartCollection;
import com.fr.chart.chartattr.Charts;
import com.fr.design.event.UIObserverListener;
import com.fr.design.gui.ilable.UILabel;
import com.fr.design.gui.itextfield.UITextField;
import com.fr.design.mainframe.chart.ChartsConfigPane;
import com.fr.plugins.calendarchart.custompie.PieChartWithCustomPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 图表属性面板
 * Created by hzzz on 2017/6/2.
 */
public class ChartConfigPane extends ChartsConfigPane<PieChartWithCustomPane> {
    private ChartCollection chartCollection;
    private UITextField value;

    public ChartConfigPane() {
        setLayout(new BorderLayout());
        value = new UITextField();
        JPanel northJpane = new JPanel();
        add(northJpane, BorderLayout.NORTH);
        northJpane.setLayout(new GridLayout(3, 2));
        northJpane.add(new UILabel("请输入内容："));
        northJpane.add(value);
        value.registerChangeListener(new UIObserverListener() {
            @Override
            public void doChange() {
                update(chartCollection);
            }
        });
        setSize(200, 200);
        setVisible(true);
    }

    @Override
    public Class<? extends Charts> accptType() {
        return PieChartWithCustomPane.class;
    }

    @Override
    protected void populate(ChartCollection collection, PieChartWithCustomPane selectedChart) {
        chartCollection = collection;
        value.setText(selectedChart.getCustomData());

    }

    @Override
    protected void update(ChartCollection collection, PieChartWithCustomPane selectedChart) {
        selectedChart.setCustomData(value.getText());
    }
}
