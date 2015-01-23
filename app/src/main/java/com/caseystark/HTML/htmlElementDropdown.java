package com.caseystark.HTML;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Casey on 1/23/2015.
 */
public class htmlElementDropdown extends htmlElement {

    private LinkedHashMap<String, String> options;

    public htmlElementDropdown(String title, String id, boolean required) {
        super(HtmlTypes.DROPDOWN, title, id, required);
    }

    public htmlElementDropdown(String title, String id) {
        super(HtmlTypes.DROPDOWN, title, id, false);
        options = new LinkedHashMap<>();
    }

    public ArrayList<String> getOptions() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.addAll(options.values());
        Log.i("HELLO", "HALP");
        return retVal;
    }

    public void addOption(String key, String option) {
        options.put(key, option);
    }

}
