package com.caseystark.HTML;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Casey on 1/23/2015.
 */
public class htmlParser {

    private String url;
    private Elements formElements;
    private ArrayList<htmlElement> elements;

    private static htmlParser instance = null;

    public static htmlParser getNewInstance(String url) {
        instance = new htmlParser(url);
        return instance;
    }

    public static htmlParser getInstance() {
        if(instance == null) {
            instance = new htmlParser("");
        }

        return instance;
    }

    public htmlParser(String url) {
        this.url = url;
        elements = new ArrayList<>();
    }

    public Elements getFormElements() {
        try {
            Document doc = Jsoup.connect(url).get();
            formElements = doc.getElementsByClass("field");

            // Remove the "Search" field
            formElements.remove(0);

            return formElements;
        }catch (IOException e) {
            Log.e("Exception", e.getLocalizedMessage());
        }

        return new Elements();
    }

    public void parseHTML(Element currentElement) {
                if(currentElement.hasClass("text")) {
                    htmlElementText newHtmlElement = createTextElement(currentElement);
                    elements.add(newHtmlElement);
                } else if(currentElement.hasClass("dropdown")) {
                    htmlElementDropdown newHtmlElement = createDropdownElement(currentElement);
                    elements.add(newHtmlElement);
                }
    }

    private htmlElementText createTextElement(Element currentElement) {
        String title = getElementTitle(currentElement);
        String id = getElementId(currentElement);

        htmlElementText retVal = new htmlElementText(title, id);

        //TODO: Add extra info

        Log.i("ADDED", "Text");

        return retVal;
    }

    private htmlElementDropdown createDropdownElement(Element currentElement) {
        String title = getElementTitle(currentElement);
        String id = getElementId(currentElement);

        htmlElementDropdown retVal = new htmlElementDropdown(title, id);

        Elements options = currentElement.getElementsByTag("option");
        for(int i = 0; i < options.size(); i++) {
            String key = options.get(i).val();
            String value = options.get(i).ownText();
            retVal.addOption(key, value);
        }

        //TODO: Add extra info

        Log.i("ADDED", "Dropdown");

        return retVal;
    }

    private String getElementTitle(Element currentElement) {
        if(!currentElement.hasClass("nolabel"))
            return currentElement.getElementsByTag("label").get(0).ownText();
        else
            return "";
    }

    private String getElementId(Element currentElement) {
        return currentElement.getElementsByTag("div").get(0).id();
    }
}
