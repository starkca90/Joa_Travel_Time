package com.caseystark.HTML;

/**
 * Created by Casey on 1/23/2015.
 */
public class htmlElementText extends htmlElement {

    public htmlElementText(String title, String id, boolean required) {
        super(HtmlTypes.TEXT, title, id, required);
    }

    public htmlElementText(String title, String id) {
        super(HtmlTypes.TEXT, title, id, false);
    }

}
