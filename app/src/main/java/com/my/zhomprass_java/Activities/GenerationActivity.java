package com.my.zhomprass_java.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.my.zhomprass_java.Adapters.GenerationAdapter;
import com.my.zhomprass_java.Database.DatabaseHelper;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.CartProducts;
import com.my.zhomprass_java.Models.DashboardDetails;
import com.my.zhomprass_java.Models.GenerationModel;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerationActivity extends AppCompatActivity {

    private List<GenerationModel> modelList;
    private RecyclerView generationrecycler;
    private GenerationAdapter adapter;
    private EditText search;
    private ApiInterface api;
    private SharedPreferences sharedPreferences;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationItemView itemView;
    BottomNavigationMenuView menuView;
    private DatabaseHelper helper;
    private List<CartProducts> list;
    private ImageView logoImageViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generation);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        modelList = new ArrayList<>();
        generationrecycler = findViewById(R.id.generationRecycler);
        generationrecycler.setLayoutManager(new LinearLayoutManager(this));
        api = ApiUtils.getUserService();
        search = findViewById(R.id.search);
        logoImageViewId = findViewById(R.id.logoImageId);
        list = new ArrayList<>();

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

        sharedPreferences = this.getSharedPreferences("Customer_Id", MODE_PRIVATE);
        int id = sharedPreferences.getInt("cust_id", 0);
        Log.d("idGet",String.valueOf(id));

        Call<List<GenerationModel>> call = api.getGeneration(id);
        call.enqueue(new Callback<List<GenerationModel>>() {
            @Override
            public void onResponse(Call<List<GenerationModel>> call, Response<List<GenerationModel>> response) {
                if (response.isSuccessful()){
                   if (response.body()==null){
                       return;
                   }
                   else{
                       modelList = response.body();
                       adapter = new GenerationAdapter(GenerationActivity.this,modelList);
                       generationrecycler.setAdapter(adapter);
                       adapter.notifyDataSetChanged();
                   }
                }
            }

            @Override
            public void onFailure(Call<List<GenerationModel>> call, Throwable t) {
                Toast.makeText(GenerationActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
