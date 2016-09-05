package com.example.administrator.volleyandjson.fvotate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.administrator.volleyandjson.R;

/**
 * Created by Administrator on 2016/9/1.
 */
public class Detail extends Activity {
    private WebView detail;
    private ProgressBar pb_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detail = (WebView) findViewById(R.id.detail);
        pb_detail = (ProgressBar) findViewById(R.id.pb_detail);
        String url = getIntent().getStringExtra("url");
        detail.setWebViewClient(new MyClient());//设置连接监听
        detail.setWebChromeClient(new MyChrome());//设置进度监听
        WebSettings settings = detail.getSettings();
        settings.supportZoom();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        if (url != null) {
            detail.loadUrl(url);
        }
    }

    private class MyClient extends WebViewClient {
        @Override//超链接
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class MyChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress < 100) {
                pb_detail.setVisibility(newProgress);
                pb_detail.setVisibility(View.VISIBLE);
            } else {
                pb_detail.setVisibility(View.GONE);
            }
        }
    }

    //返回键的处理   1。 重写onbackpressed;
    @Override
    public void onBackPressed() {
        if (detail.canGoBack()) {
            detail.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
