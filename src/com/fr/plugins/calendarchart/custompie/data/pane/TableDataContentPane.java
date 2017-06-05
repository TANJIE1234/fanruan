package com.fr.plugins.calendarchart.custompie.data.pane;

import com.fr.chart.chartattr.ChartCollection;
import com.fr.design.gui.icombobox.UIComboBox;
import com.fr.design.gui.ilable.UILabel;
import com.fr.design.layout.TableLayout;
import com.fr.design.layout.TableLayoutHelper;
import com.fr.design.mainframe.chart.gui.ChartDataPane;
import com.fr.design.mainframe.chart.gui.data.table.AbstractTableDataContentPane;
import com.fr.plugins.calendarchart.custompie.data.DefaultTableDataDefinition;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by mango on 17/05/18.
 * 用户想自定义数据集数据面板，可以按照此demo定义
 */
public class TableDataContentPane extends AbstractTableDataContentPane {
    private UIComboBox dateComboBox;
    private UIComboBox valueComboBox;

    public TableDataContentPane(ChartDataPane parent) {
        dateComboBox = new UIComboBox();
        valueComboBox = new UIComboBox();
        dateComboBox.setPreferredSize(new Dimension(100, 20));
        valueComboBox.setPreferredSize(new Dimension(100, 20));

        Component[][] components = new Component[][]{
                new Component[]{new UILabel("名称", SwingConstants.RIGHT), dateComboBox},
                new Component[]{new UILabel("值", SwingConstants.RIGHT), valueComboBox}};

        double p = TableLayout.PREFERRED;
        double[] columnSize = {p, p};
        double[] rowSize = {p, p, p};
        JPanel panel = TableLayoutHelper.createTableLayoutPane(components, rowSize, columnSize); //hzzz
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void populateBean(ChartCollection collection) {
        DefaultTableDataDefinition configuration = (DefaultTableDataDefinition) collection.getSelectedChart().getFilterDefinition();
        if (configuration == null) return;
        combineCustomEditValue(dateComboBox, configuration.getDate());
        combineCustomEditValue(valueComboBox, configuration.getValue());
    }

    @Override
    public void updateBean(ChartCollection ob) {
        DefaultTableDataDefinition myConfiguration = new DefaultTableDataDefinition();
        Object wname = dateComboBox.getSelectedItem();
        Object wvalue = valueComboBox.getSelectedItem();

        if (wname != null) {
            myConfiguration.setDate(wname.toString());
        }
        if (wvalue != null) {
            myConfiguration.setValue(wvalue.toString());
        }
        ob.getSelectedChart().setFilterDefinition(myConfiguration);
    }

    @Override
    public void clearAllBoxList() {
        dateComboBox.removeAll();
        valueComboBox.removeAll();
    }

    @Override
    protected void refreshBoxListWithSelectTableData(List columnNameList) {
        refreshBoxItems(dateComboBox, columnNameList);
        refreshBoxItems(valueComboBox, columnNameList);
    }
}
