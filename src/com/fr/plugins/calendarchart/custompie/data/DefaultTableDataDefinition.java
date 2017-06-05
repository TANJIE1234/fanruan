package com.fr.plugins.calendarchart.custompie.data;

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
 * Created by hzzz on 2017/6/2.
 */
public class DefaultTableDataDefinition extends TableDataDefinition {
    public static final String XML_TAG = "DefaultTableDataDefinition";

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

    @Override
    public ChartData createChartData(DataModel resultSet, DataProcessor dataProcessor) {
        Map<String, String> data = new HashMap<>();
        try {
            int wordNameCol = DataCoreUtils.getColumnIndexByName(resultSet, getDate());
            int wordValueCol = DataCoreUtils.getColumnIndexByName(resultSet, getValue());
            Map<Object, List<Object>> map = new HashMap<>();

            for (int rowIndex = 0; rowIndex < resultSet.getRowCount(); rowIndex++) {
                Object wordName = resultSet.getValueAt(rowIndex, wordNameCol);
                Object wordValue = resultSet.getValueAt(rowIndex, wordValueCol);

                if (wordName != null && wordValue != null) {
                    if (!map.containsKey(wordName)) {
                        map.put(wordName, new ArrayList<>());
                    }
                    map.get(wordName).add(wordValue);
                }
            }
            for (Map.Entry<Object, List<Object>> entry : map.entrySet()) {
                data.put(entry.getKey().toString(),
                        entry.getValue().get(0).toString());
            }
        } catch (Exception e) {
            FRLogger.getLogger().error(e.getMessage(), e);
        }
        return new TableDataContent(data);
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
        return ob instanceof DefaultTableDataDefinition
                && ComparatorUtils.equals(((DefaultTableDataDefinition) ob).getDate(), this.getDate())
                && ComparatorUtils.equals(((DefaultTableDataDefinition) ob).getValue(), this.getValue())
                && super.equals(ob);
    }

    public Object clone() throws CloneNotSupportedException {
        DefaultTableDataDefinition cloned = (DefaultTableDataDefinition) super.clone();

        cloned.setDate(getDate());
        cloned.setValue(getValue());

        return cloned;
    }
}
