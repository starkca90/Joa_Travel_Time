package com.caseystark.HTML;

/**
 * Created by Casey on 1/23/2015.
 */
public abstract class htmlElement {

    private HtmlTypes type;
    private String title;
    private String id;
    private boolean required;

    public enum HtmlTypes {
        TEXT, DROPDOWN
    }

    public htmlElement(HtmlTypes type, String title, String id, boolean required) {
        this.type = type;
        this.title = title;
        this.id = id;
        this.required = required;
    }

    public HtmlTypes getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public boolean isRequired() {
        return required;
    }

}
