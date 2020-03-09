package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.my.zhomprass_java.Adapters.MultiProductAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Fragments.Home;
import com.my.zhomprass_java.Fragments.OrderFragment;
import com.my.zhomprass_java.Models.MultiProducts;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private List<MultiProducts> list;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private int id,flags;
    private ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        checkConnection();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.productsRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        api = ApiUtils.getUserService();

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        flags = intent.getIntExtra("flags",0);

        if (flags==1){
            getMultiProductsByBrand(id);
        }
        else if (flags==3){
            getShopProduct(id);
        }


        else{
            getMultiProducts(id);

        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.homeId:
                        startActivity(new Intent(ProductsActivity.this,MainActivity.class));
                        return true;
                    case R.id.orderId:
                        startActivity(new Intent(ProductsActivity.this,OrderActivity.class));
                        return true;
                    case R.id.cartId:
                        startActivity(new Intent(ProductsActivity.this,CartActivity.class));

                }

                return false;
            }
        });
    }

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

            } else {

            }

        } else {
            Snackbar.make(findViewById(R.id.layoutProductsActivity), R.string.offline_message, Snackbar.LENGTH_LONG).show();
        }
    }

    private void getMultiProductsByBrand(int id) {

        Call<List<MultiProducts>> call = api.getMultiProductByBrand(id);
        call.enqueue(new Callback<List<MultiProducts>>() {
            @Override
            public void onResponse(Call<List<MultiProducts>> call, Response<List<MultiProducts>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list = response.body();
                        MultiProductAdapter adapter = new MultiProductAdapter(list,ProductsActivity.this);
                        recyclerView.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<MultiProducts>> call, Throwable t) {
               // Toast.makeText(ProductsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getShopProduct(int id) {
        Call<List<MultiProducts>> call = api.getShopProduct(id,0,20);
        call.enqueue(new Callback<List<MultiProducts>>() {
            @Override
            public void onResponse(Call<List<MultiProducts>> call, Response<List<MultiProducts>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list = response.body();
                        MultiProductAdapter adapter = new MultiProductAdapter(list,ProductsActivity.this);
                        recyclerView.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<MultiProducts>> call, Throwable t) {
                //Toast.makeText(ProductsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getMultiProducts(int id) {
        Call<List<MultiProducts>> call = api.getMultiProduct(id);
        call.enqueue(new Callback<List<MultiProducts>>() {
            @Override
            public void onResponse(Call<List<MultiProducts>> call, Response<List<MultiProducts>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list = response.body();
                        MultiProductAdapter adapter = new MultiProductAdapter(list,ProductsActivity.this);
                        recyclerView.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<MultiProducts>> call, Throwable t) {
                //Toast.makeText(ProductsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
