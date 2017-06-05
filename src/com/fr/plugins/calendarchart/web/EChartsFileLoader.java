package com.fr.plugins.calendarchart.web;

import com.fr.stable.EncodeConstants;
import com.fr.stable.fun.impl.AbstractJavaScriptFileHandler;

/**
 * Created by richie on 16/1/29.
 */
public class EChartsFileLoader extends AbstractJavaScriptFileHandler {
    @Override
    public String[] pathsForFiles() {
        return new String[]{
                "/com/fr/plugins/calendarchart/web/echarts.loader.js",
                "/com/fr/plugins/calendarchart/web/echarts.min.js"
        };
    }

    @Override
    public String encode() {
        return EncodeConstants.ENCODING_UTF_8;
    }
}