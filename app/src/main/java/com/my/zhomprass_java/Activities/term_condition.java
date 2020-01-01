package com.my.zhomprass_java.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.my.zhomprass_java.R;

public class term_condition extends AppCompatActivity {

    private WebView webId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_condition);

        webId = findViewById(R.id.webId);

        WebSettings webset = webId.getSettings();

        webset.setJavaScriptEnabled(true);
        webId.setWebViewClient(new WebViewClient());

        webId.loadUrl("https://zhomprass.com/TERMS");
    }
}
