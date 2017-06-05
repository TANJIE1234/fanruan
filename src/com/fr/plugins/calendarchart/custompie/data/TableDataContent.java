package com.fr.plugins.calendarchart.custompie.data;

import com.fr.chart.chartdata.NormalChartData;

import java.util.Map;

/**
 *
 * Created by hzzz on 2017/6/2.
 */
public class TableDataContent extends NormalChartData {
    private final Map<String, String> data;

    TableDataContent(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }
}
