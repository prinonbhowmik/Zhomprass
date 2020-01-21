package com.my.zhomprass_java.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;
import com.my.zhomprass_java.R;

public class ZoomImageActivity extends AppCompatActivity {

    private ZoomageView zoomageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");


      if (image!=null){
          Glide.with(this).load(image).into(zoomageView);
      }
      else{
          return;
      }

    }
}
