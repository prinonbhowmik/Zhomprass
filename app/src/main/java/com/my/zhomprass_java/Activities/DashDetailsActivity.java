package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
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
    private EditText search;
    private ApiInterface api;
    private SharedPreferences sharedPreferences;
    private BottomNavigationView bottomNavigationView;
    private ImageView logoImageViewId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_details);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        details = new ArrayList<>();
        dashRecycler = findViewById(R.id.dashRecycler);
        dashRecycler.setLayoutManager(new LinearLayoutManager(this));
        api = ApiUtils.getUserService();
        search = findViewById(R.id.search);
        logoImageViewId = findViewById(R.id.logoImageId);
        
       memberShowing();
       bottomNav();
       searchData();
       checkConnection();


       
    }



    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

            } else {

            }

        } else {
            Snackbar.make(findViewById(R.id.layoutDashDetailsActivity), R.string.offline_message, Snackbar.LENGTH_LONG).show();
        }
    }

    private void searchData() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<DashboardDetails> filteredList = new ArrayList<>();

                for(DashboardDetails item : details){
                    if (item.getFrom_user_name().toLowerCase().contains(editable.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                    if (item.getBalance().toLowerCase().contains(editable.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                    if (item.getDate().toLowerCase().contains(editable.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                }
                adapter.filterList(filteredList);
            }
        });
    }

    private void bottomNav() {
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

    private void memberShowing() {

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
                    //Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                   // Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                    //Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                    //Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                   // Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                   // Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                    //Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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
                    //Toast.makeText(DashDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
