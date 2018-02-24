package com.example.alexandrkuchinsky.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.alexandrkuchinsky.myapplication.Model.RSSObject;
import com.example.alexandrkuchinsky.myapplication.R;

/**
 * Created by Alexandr Kuchinsky on 08.02.2018.
 */

public class ww extends AppCompatActivity {
//@Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.webo);
//
//        WebView webView = (WebView)findViewById(R.id.webView);
//        Uri data = getIntent().getData();
//        webView.loadUrl(data.toString());
//
//    }
//}

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webo);
        webView = (WebView) findViewById(R.id.webView);

       WebSettings webSettings = webView.getSettings();
       webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
