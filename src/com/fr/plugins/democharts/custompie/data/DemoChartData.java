package com.fr.plugins.democharts.custompie.data;

import com.fr.chart.chartdata.NormalChartData;

import java.util.Map;

/**
 * Created by mango on 17/05/18.
 * 获取自定义的数据集
 */
public class DemoChartData extends NormalChartData {
    private final Map<String, String> data;

    public DemoChartData(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }
}
