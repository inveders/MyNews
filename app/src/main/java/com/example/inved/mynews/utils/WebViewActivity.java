package com.example.inved.mynews.utils;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inved.mynews.R;

public class WebViewActivity extends AppCompatActivity {

    public static String WebUrl = "url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        String url=getIntent().getStringExtra(WebUrl);

        WebView webView = findViewById(R.id.webview);
        webView.loadUrl(url);
    }
}
