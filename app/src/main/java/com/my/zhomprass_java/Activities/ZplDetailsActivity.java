package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.my.zhomprass_java.Adapters.ZplDetailsAdapter;
import com.my.zhomprass_java.Database.DatabaseHelper;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.ZplDetails;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZplDetailsActivity extends AppCompatActivity {

    private List<ZplDetails> zplDetails;
    private RecyclerView recyclerView;
    private ZplDetailsAdapter adapter;
    private ApiInterface api;
    private EditText search;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationItemView itemView;
    private BottomNavigationMenuView menuView;
    private DatabaseHelper helper;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zpl_details);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        zplDetails = new ArrayList<>();
        recyclerView = findViewById(R.id.zplDetailsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        api = ApiUtils.getUserService();
        search = findViewById(R.id.search);


        helper = new DatabaseHelper(this);
        menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        itemView = (BottomNavigationItemView) menuView.getChildAt(2);
        View notificationBadge = LayoutInflater.from(this).inflate(R.layout.badge_layout, menuView, false);
        TextView textView = notificationBadge.findViewById(R.id.counter_badge);

        Cursor yourCursor = helper.numberOfrows();

        int i = 0;

        while (yourCursor.moveToNext()) {
            i += 1;
            if (i > 0) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(String.valueOf(i));
            }
        }


        itemView.addView(notificationBadge);


        zplDetailsShowing();
        bottomNav();
        searchData();


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
                ArrayList<ZplDetails> filteredList = new ArrayList<>();

                for (com.my.zhomprass_java.Models.ZplDetails item : zplDetails) {
                    if (item.getLevel().toLowerCase().contains(editable.toString().toLowerCase())) {
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

                switch (menuItem.getItemId()) {
                    case R.id.homeId:
                        startActivity(new Intent(ZplDetailsActivity.this, MainActivity.class));
                        return true;
                    case R.id.orderId:
                        startActivity(new Intent(ZplDetailsActivity.this, OrderActivity.class));
                        return true;
                    case R.id.cartId:
                        startActivity(new Intent(ZplDetailsActivity.this, CartActivity.class));
                }
                return false;
            }
        });
    }


    private void zplDetailsShowing() {

        sharedPreferences = this.getSharedPreferences("Customer_Id", MODE_PRIVATE);
        int id = sharedPreferences.getInt("cust_id", 0);
        Log.d("GetID",String.valueOf(id));

        Call<List<ZplDetails>> call = api.getZplDetails(id);
        call.enqueue(new Callback<List<ZplDetails>>() {
            @Override
            public void onResponse(Call<List<ZplDetails>> call, Response<List<ZplDetails>> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        return;
                    } else {
                        zplDetails = response.body();
                        adapter = new ZplDetailsAdapter(zplDetails);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ZplDetails>> call, Throwable t) {
                Toast.makeText(ZplDetailsActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
