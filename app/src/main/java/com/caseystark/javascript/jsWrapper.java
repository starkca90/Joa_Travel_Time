package com.caseystark.javascript;

/**
 * Created by Casey on 1/23/2015.
 */
public class jsWrapper {

    public String setTextValue(String id, String value) {
        return "javascript:(function() {document.getElementById('" + id + "').value='" + value + "';})()";
    }
}
