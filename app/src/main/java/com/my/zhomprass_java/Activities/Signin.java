package com.my.zhomprass_java.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.Members;
import com.my.zhomprass_java.Models.UserInfo;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private ApiInterface api;
    private List<UserInfo> userInfoList;
    private List<Members> membersList;
    private int login_id,cust_id;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        init();

        animation();

        getFreeZpl();

        getFreeMember();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });






    }

    private void getFreeMember() {

        Call<List<Members>> call = api.getTotalZpl("free");
        call.enqueue(new Callback<List<Members>>() {
            @Override
            public void onResponse(Call<List<Members>> call, Response<List<Members>> response) {

                if (response.isSuccessful()){
                    membersList = response.body();
                    if (response.body()==null){
                        return;
                    }
                    else{
                        int free = (response.body().get(0)).getMember();
                        logo2.setText(String.valueOf(free));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Members>> call, Throwable t) {

                Toast.makeText(Signin.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFreeZpl() {
        Call<List<Members>> call = api.getTotalZpl("zpl");
        call.enqueue(new Callback<List<Members>>() {
            @Override
            public void onResponse(Call<List<Members>> call, Response<List<Members>> response) {
                membersList = response.body();
               if (response.isSuccessful()){
                   if (response.body()==null)
                   {
                       return;
                   }
                   else{
                       int zpl = response.body().get(0).getMember();
                    logo1.setText(String.valueOf(zpl));
                   }
               }
            }

            @Override
            public void onFailure(Call<List<Members>> call, Throwable t) {

                Toast.makeText(Signin.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animation() {
        Animation header_anim = AnimationUtils.loadAnimation(Signin.this,R.anim.top_down);
        top_curve.setAnimation(header_anim);

        Animation et_anim = AnimationUtils.loadAnimation(this,R.anim.edittext_anim);
        emailEt.setAnimation(et_anim);

        Animation et_anim2 = AnimationUtils.loadAnimation(this,R.anim.edittext_anim);
        passEt.setAnimation(et_anim);

        Animation logo_anim = AnimationUtils.loadAnimation(this,R.anim.field_name_anim);
        logo.setAnimation(logo_anim);

        Animation logo1_anim = AnimationUtils.loadAnimation(this,R.anim.down_top);
        logo1.setAnimation(logo1_anim);

        Animation button_anim = AnimationUtils.loadAnimation(this,R.anim.center_reveal_anim);
        login_button.setAnimation(button_anim);

        Animation logo2_anim = AnimationUtils.loadAnimation(this,R.anim.down_top);
        logo2.setAnimation(logo2_anim);
    }

    private void login() {

        progressBar.setVisibility(View.VISIBLE);
        String email = emailEt.getText().toString();
        String pass = passEt.getText().toString();

        if (TextUtils.isEmpty(email)){
           emailEt.setError("Enter username or password");
           progressBar.setVisibility(View.GONE);
        }
        else if(TextUtils.isEmpty(pass)){
           passEt.setError("Enter valid password");
            progressBar.setVisibility(View.GONE);
        }else{
            Call<List<UserInfo>> call = api.login(email,pass);
            call.enqueue(new Callback<List<UserInfo>>() {
                @Override
                public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                    if (response.isSuccessful()){
                        userInfoList = response.body();
                        login_id = (userInfoList.get(0)).getLogin_success();

                        if (login_id==1){
                            cust_id = (userInfoList.get(0)).getCustomer_id();
                            sharedPreferences = getSharedPreferences("Customer_Id",MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putInt("cust_id",cust_id);
                            editor.apply();
                           startActivity(new Intent(Signin.this,MainActivity.class));
                           finish();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Signin.this, "Wrong Username or Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<UserInfo>> call, Throwable t) {

                    Toast.makeText(Signin.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
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
        api = ApiUtils.getUserService();
        userInfoList = new ArrayList<>();
        membersList = new ArrayList<>();
    }
}
