package com.my.zhomprass_java.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.my.zhomprass_java.R;

public class Signin extends AppCompatActivity {

    private ImageView top_curve;
    private EditText emailEt;
    private EditText passEt;
    private TextView zipMember;
    private TextView freeMember;
    private TextView logo1;
    private TextView logo2;
    private TextView logo;
    private Button login_button;
    private ImageView multiWaveeHeader;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        init();




    }

    private void init() {
        top_curve = findViewById(R.id.top_curve);
        emailEt = findViewById(R.id.ed_name);
        passEt = findViewById(R.id.ed_pass);
        logo = findViewById(R.id.logo);
        freeMember = findViewById(R.id.new_user_text);
        zipMember = findViewById(R.id.tv_or);
        login_button = findViewById(R.id.nxt);
        logo1 = findViewById(R.id.logo1);
        logo2 = findViewById(R.id.logo2);
        multiWaveeHeader=findViewById(R.id.waveHeader);
        progressBar=findViewById(R.id.progressBar);
    }
}
