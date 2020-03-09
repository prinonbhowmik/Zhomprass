package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.my.zhomprass_java.Adapters.CartProductAdapter;
import com.my.zhomprass_java.Database.DatabaseHelper;
import com.my.zhomprass_java.Models.CartProducts;
import com.my.zhomprass_java.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private List<CartProducts> list;
    private CartProductAdapter adapter;
    private Button placeOrderBtn;
    public static TextView totalPriceTv;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

       init();

        getCartProduct();
        checkConnection();

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,OrderActivity.class));
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
            Snackbar.make(findViewById(R.id.layoutCartActivity), R.string.offline_message, Snackbar.LENGTH_LONG).show();
        }
    }

    private void init() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.cartRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper=new DatabaseHelper(CartActivity.this);
        placeOrderBtn=findViewById(R.id.orderNowBtn);
        totalPriceTv=findViewById(R.id.totalPriceTv);
        list = new ArrayList<>();
    }

    private void getCartProduct() {

        Cursor cursor = databaseHelper.getCart();
      if (cursor!=null){
          while (cursor.moveToNext()){

               id = cursor.getInt(cursor.getColumnIndex(databaseHelper.ID));
              int cat_id = cursor.getInt(cursor.getColumnIndex(databaseHelper.CAT_ID));
              int sub_id = cursor.getInt(cursor.getColumnIndex(databaseHelper.SUB_CAT_ID));
              int third_id = cursor.getInt(cursor.getColumnIndex(databaseHelper.THIRD_CAT_ID));
              String name = cursor.getString(cursor.getColumnIndex(databaseHelper.PRODUCT_NAME));
              int price =  cursor.getInt(cursor.getColumnIndex(databaseHelper.PRICE));
              int point =  cursor.getInt(cursor.getColumnIndex(databaseHelper.POINT));
              int quantity = cursor.getInt(cursor.getColumnIndex(databaseHelper.QUANTITY));
              String image = cursor.getString(cursor.getColumnIndex(databaseHelper.IMAGE));

              CartProducts cartProducts = new CartProducts(id,cat_id,sub_id,third_id,name,price,point,quantity,image);
              
              totalPriceTv.setText("Total : ৳"+total());

              list.add(cartProducts);
              adapter = new CartProductAdapter(CartActivity.this,list);
              recyclerView.setAdapter(adapter);
              adapter.notifyDataSetChanged();
          }
      }

    }

    private int total() {

        Cursor cursor = databaseHelper.getCart();
        int count = 0;

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                count+=cursor.getInt(5)*cursor.getInt(7);
            }
        }

        return count;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }


}
