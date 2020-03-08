package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.my.zhomprass_java.Adapters.DashboardDetailsAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.DashboardDetails;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashDetailsActivity extends AppCompatActivity {

    List<DashboardDetails> details;
    RecyclerView dashRecycler;
    DashboardDetailsAdapter adapter;
    private ApiInterface api;
    private SharedPreferences sharedPreferences;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_details);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        details = new ArrayList<>();
        dashRecycler = findViewById(R.id.dashRecycler);
        dashRecycler.setLayoutManager(new LinearLayoutManager(this));
        api = ApiUtils.getUserService();

        Intent intent = getIntent();
       int type = intent.getIntExtra("type",0);

        sharedPreferences = this.getSharedPreferences("Customer_Id",MODE_PRIVATE);
        int id = sharedPreferences.getInt("cust_id",0);

        if (type==1){
            Call<List<DashboardDetails>> call = api.getDetails(id,type);
            call.enqueue(new Callback<List<DashboardDetails>>() {
                @Override
                public void onResponse(Call<List<DashboardDetails>> call, Response<List<DashboardDetails>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            return;
                        }else{
                            details = response.body();
                            adapter = new DashboardDetailsAdapter(details);
                            dashRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<DashboardDetails>> call, Throwable t) {
                    Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (type==5){
            Call<List<DashboardDetails>> call = api.getDetails(id,type);
            call.enqueue(new Callback<List<DashboardDetails>>() {
                @Override
                public void onResponse(Call<List<DashboardDetails>> call, Response<List<DashboardDetails>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            return;
                        }else{
                            details = response.body();
                            adapter = new DashboardDetailsAdapter(details);
                            dashRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<DashboardDetails>> call, Throwable t) {
                    Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (type==6){
            Call<List<DashboardDetails>> call = api.getDetails(id,type);
            call.enqueue(new Callback<List<DashboardDetails>>() {
                @Override
                public void onResponse(Call<List<DashboardDetails>> call, Response<List<DashboardDetails>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            return;
                        }else{
                            details = response.body();
                            adapter = new DashboardDetailsAdapter(details);
                            dashRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<DashboardDetails>> call, Throwable t) {
                    Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (type==7){
            Call<List<DashboardDetails>> call = api.getDetails(id,type);
            call.enqueue(new Callback<List<DashboardDetails>>() {
                @Override
                public void onResponse(Call<List<DashboardDetails>> call, Response<List<DashboardDetails>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            return;
                        }else{
                            details = response.body();
                            adapter = new DashboardDetailsAdapter(details);
                            dashRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<DashboardDetails>> call, Throwable t) {
                    Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (type==8){
            Call<List<DashboardDetails>> call = api.getDetails(id,type);
            call.enqueue(new Callback<List<DashboardDetails>>() {
                @Override
                public void onResponse(Call<List<DashboardDetails>> call, Response<List<DashboardDetails>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            return;
                        }else{
                            details = response.body();
                            adapter = new DashboardDetailsAdapter(details);
                            dashRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<DashboardDetails>> call, Throwable t) {
                    Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (type==9){
            Call<List<DashboardDetails>> call = api.getDetails(id,type);
            call.enqueue(new Callback<List<DashboardDetails>>() {
                @Override
                public void onResponse(Call<List<DashboardDetails>> call, Response<List<DashboardDetails>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            return;
                        }else{
                            details = response.body();
                            adapter = new DashboardDetailsAdapter(details);
                            dashRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<DashboardDetails>> call, Throwable t) {
                    Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (type==10){
            Call<List<DashboardDetails>> call = api.getDetails(id,type);
            call.enqueue(new Callback<List<DashboardDetails>>() {
                @Override
                public void onResponse(Call<List<DashboardDetails>> call, Response<List<DashboardDetails>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            return;
                        }else{
                            details = response.body();
                            adapter = new DashboardDetailsAdapter(details);
                            dashRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<DashboardDetails>> call, Throwable t) {
                    Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (type==12){
            Call<List<DashboardDetails>> call = api.getDetails(id,type);
            call.enqueue(new Callback<List<DashboardDetails>>() {
                @Override
                public void onResponse(Call<List<DashboardDetails>> call, Response<List<DashboardDetails>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            return;
                        }else{
                            details = response.body();
                            adapter = new DashboardDetailsAdapter(details);
                            dashRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<DashboardDetails>> call, Throwable t) {
                    Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.homeId:
                        startActivity(new Intent(DashDetailsActivity.this,MainActivity.class));
                        return true;
                    case R.id.orderId:
                        startActivity(new Intent(DashDetailsActivity.this,OrderActivity.class));
                        return true;
                    case R.id.cartId:
                        startActivity(new Intent(DashDetailsActivity.this,CartActivity.class));
                }
                return false;
            }
        });
    }

}
