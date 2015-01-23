package com.caseystark.joatraveltime;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.concurrent.ExecutionException;

/**
 * Created by Casey on 1/23/2015.
 */
public class WebActivity extends ActionBarActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebChromeClient(new WebChromeClient());

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
//                String javascript="javascript:alert('Hello! I am an alert box!!');";
                String javascript = "javascript:(function() {document.getElementById('Form_Form_Name').value='Hello World!';})()";
                String script = "document.getElementById('Form_Form_Name').value='Hello World!';";
                ValueCallback<String> callback = new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.i("Hello", value);
                    }
                };
                myWebView.evaluateJavascript(script, callback);
                myWebView.loadUrl(javascript);
            }
        });
        refreshWebView();

    }

    private void refreshWebView() {
        myWebView.loadUrl(ApplicationController.getURL());
    }
}
