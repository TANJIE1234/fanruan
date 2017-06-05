package com.fr.plugins.democharts.custompie.data;

import com.fr.base.chart.chartdata.ChartData;
import com.fr.base.chart.chartdata.model.DataProcessor;
import com.fr.chart.chartdata.TableDataDefinition;
import com.fr.data.core.DataCoreUtils;
import com.fr.general.ComparatorUtils;
import com.fr.general.FRLogger;
import com.fr.general.data.DataModel;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLableReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mango on 17/05/18.
 * 数据集数据
 */
public class DemoTableDefinition extends TableDataDefinition {
    public static final String XML_TAG = "DemoTableDefinition";

    private String date;
    private String value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 从数据定义属性生成 图表数据的结果集
     *
     * @param resultSet     数据集DataModel
     * @param dataProcessor
     * @return 图表数据
     * @date 2014-9-21-下午10:36:47
     */
    @Override
    public ChartData createChartData(DataModel resultSet, DataProcessor dataProcessor) {
        Map<String, String> data = new HashMap<>();
        try {
            int wordNameCol = DataCoreUtils.getColumnIndexByName(resultSet, getDate());
            int wordValueCol = DataCoreUtils.getColumnIndexByName(resultSet, getValue());
            Map<Object, List<Object>> map = new HashMap<>();

            for (int rowIndex = 0, tableRowCount = resultSet.getRowCount(); rowIndex < tableRowCount; rowIndex++) {
                Object wordName = resultSet.getValueAt(rowIndex, wordNameCol);
                Object wordValue = resultSet.getValueAt(rowIndex, wordValueCol);

                if (wordName == null || wordValue == null) {
                    continue;
                }

                List<Object> list = map.get(wordName);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(wordName, list);
                }

                list.add(wordValue);
            }

            for (Object key : map.keySet()) {
                List<Object> valueList = map.get(key);
                data.put(key.toString(), valueList.get(0).toString());
            }
        } catch (Exception e) {
            FRLogger.getLogger().error(e.getMessage(), e);
        }
        return new DemoChartData(data);
    }

    public void writeXML(XMLPrintWriter writer) {
        writer.startTAG(XML_TAG)
                .attr("date", getDate())
                .attr("value", getValue());

        super.writeXML(writer);

        writer.end();
    }

    public void readXML(XMLableReader reader) {
        super.readXML(reader);
        if (reader.isAttr()) {
            String tmpVal;

            if ((tmpVal = reader.getAttrAsString("date", null)) != null) {
                this.setDate(tmpVal);
            }
            if ((tmpVal = reader.getAttrAsString("value", null)) != null) {
                this.setValue(tmpVal);
            }
        }
    }

    public boolean equals(Object ob) {
        return ob instanceof DemoTableDefinition
                && ComparatorUtils.equals(((DemoTableDefinition) ob).getDate(), this.getDate())
                && ComparatorUtils.equals(((DemoTableDefinition) ob).getValue(), this.getValue())
                && super.equals(ob);
    }

    public Object clone() throws CloneNotSupportedException {
        DemoTableDefinition cloned = (DemoTableDefinition) super.clone();

        cloned.setDate(getDate());
        cloned.setValue(getValue());

        return cloned;
    }
}
