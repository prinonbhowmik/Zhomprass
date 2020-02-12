package com.my.zhomprass_java.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.snackbar.Snackbar;
import com.my.zhomprass_java.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Generation_Tree extends Fragment {

    private WebView webId;

    public Generation_Tree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generation__tree, container, false);

        webId = view.findViewById(R.id.webId);


        WebSettings webset = webId.getSettings();

        webset.setJavaScriptEnabled(true);
        webId.setWebViewClient(new WebViewClient());

        webId.loadUrl("https://zhomprass.com/app1/generationView.aspx");

        return view;
    }

}
