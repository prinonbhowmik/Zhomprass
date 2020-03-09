package com.my.zhomprass_java.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.jsibbold.zoomage.ZoomageView;
import com.my.zhomprass_java.R;

public class ZoomImageActivity extends AppCompatActivity {

    private ZoomageView zoomageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);

        checkConnection();

        zoomageView=findViewById(R.id.zoomImage);


        Intent intent = getIntent();
        String image = intent.getStringExtra("image");


      if (image!=null){
          Glide.with(this).load(image).into(zoomageView);
      }
      else{
          return;
      }

    }

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

            } else {

            }

        } else {
            Snackbar.make(findViewById(R.id.layoutZoomImageActivity), R.string.offline_message, Snackbar.LENGTH_LONG).show();
        }
    }
}
