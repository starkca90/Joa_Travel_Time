package com.caseystark.joatraveltime;

import android.app.Application;

/**
 * Created by Casey on 1/23/2015.
 */
public class ApplicationController extends Application {

    private static String URL = "http://www.joa.com/traveltime/";

    public static String getURL() {
        return URL;
    }
}
