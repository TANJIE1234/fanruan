package com.fr.plugins.democharts.custompie;

import com.fr.chart.chartattr.Charts;
import com.fr.chart.chartdata.TopDefinition;
import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.plugins.democharts.custompie.data.DemoChartData;
import com.fr.plugins.democharts.custompie.data.DemoTableDefinition;
import com.fr.stable.web.Repository;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLReadable;
import com.fr.stable.xml.XMLableReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mengao on 2017/4/26.
 * chart类，需要继承Charts
 * 需要实现方法：getChartID
 * 需要实现方法：writeXML
 * 需要实现方法：readXML
 * 需要实现方法：toJSON
 */
public class PieChartWithCustomPane extends Charts {
    private String customData = "自定义数据配置面板demo";

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    //覆写writeXML，将自定义的属性和数据配置保存到模版中
    @Override
    public void writeXML(XMLPrintWriter xmlPrintWriter) {
        xmlPrintWriter.startTAG("customChartDemo")
                .attr("custom", getCustomData())
                .end();
        writeDefinition(xmlPrintWriter);
    }

    //覆写readXML，将自定义的属性和数据配置从模版中读出
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
            } else if (tagName.equals("customChartDemo")) {
                setCustomData(xmLableReader.getAttrAsString("custom", "111"));
            }
        }
    }

    /**
     * 读出字符串，然后传到前台
     * 注意：需要获取数据配置中的数据，使用getChartData()方法
     */
    @Override
    //tojson的时候注意用getChartData()方法获取当前数据集，然后自主拼接成json
    public JSONObject toJSON(Repository repo) throws JSONException {
        //用自定义的  DemoChartData 进行数据配置
        assert getChartData() instanceof DemoChartData;
        Map<String, String> data = ((DemoChartData) getChartData()).getData();
        List<String[]> dataLst = new ArrayList<>();
        for (Map.Entry<String, String> dataEntry : data.entrySet()) {
            dataLst.add(new String[]{dataEntry.getKey(), dataEntry.getValue()});
        }
        return JSONObject.create().put("data", dataLst);
    }

    //读出使用的Definition
    public TopDefinition readDefinitionXML(XMLableReader xmLableReader) {
        TopDefinition provider = null;
        if (xmLableReader.isChildNode()) {
            String tagName = xmLableReader.getTagName();
            if (tagName.equals(DemoTableDefinition.XML_TAG)) {
                provider = new DemoTableDefinition();
            }
            xmLableReader.readXMLObject(provider);
        }
        return provider;
    }

    @Override
    public String getChartID() {
        return "ChartsPieWithCustomDataPane";
    }

}