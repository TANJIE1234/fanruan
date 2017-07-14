package com.fr.plugins.calendarchart.custompie;

import com.fr.chart.chartattr.Charts;
import com.fr.chart.chartdata.TopDefinition;
import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.plugins.calendarchart.custompie.data.DefaultTableDataDefinition;
import com.fr.plugins.calendarchart.custompie.data.TableDataContent;
import com.fr.stable.web.Repository;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLReadable;
import com.fr.stable.xml.XMLableReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieChartWithCustomPane extends Charts {
    private final static String TAG_NAME = "customChartDemo";
    private String customData = "日历活动图表demo";

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    @Override
    public void writeXML(XMLPrintWriter xmlPrintWriter) {
        xmlPrintWriter.startTAG(TAG_NAME)
                .attr("custom", getCustomData())
                .end();
        writeDefinition(xmlPrintWriter);
    }

    @Override
    public void readXML(XMLableReader xmLableReader) {
        if (xmLableReader.isChildNode()) {
            String tagName = xmLableReader.getTagName();
            if (tagName.equals("ChartDefinition")) {
                xmLableReader.readXMLObject(new XMLReadable() {
                    public void readXML(XMLableReader XMLReadable) {
                        setFilterDefinition(readDefinitionXML(XMLReadable));
                    }
                });
            } else if (tagName.equals(TAG_NAME)) {
                setCustomData(xmLableReader.getAttrAsString("custom", "111"));
            }
        }
    }

    @Override
    public JSONObject toJSON(Repository repo) throws JSONException {
        assert getChartData() instanceof TableDataContent;
        Map<String, String> data = ((TableDataContent) getChartData()).getData();
        List<String[]> dataLst = new ArrayList();
        for (Map.Entry<String, String> dataEntry : data.entrySet()) {
            dataLst.add(new String[]{dataEntry.getKey(), dataEntry.getValue()});
        }
        return JSONObject.create().put("data", dataLst)
                .put("title", getCustomData());
    }

    public TopDefinition readDefinitionXML(XMLableReader xmLableReader) {
        TopDefinition provider = null;
        if (xmLableReader.isChildNode()) {
            String tagName = xmLableReader.getTagName();
            if (tagName.equals(DefaultTableDataDefinition.XML_TAG)) {
                provider = new DefaultTableDataDefinition();
                xmLableReader.readXMLObject(provider);
            }
        }
        return provider;
    }

    @Override
    public String getChartID() {
        return "ChartsPieWithCustomDataPane";
    }

}