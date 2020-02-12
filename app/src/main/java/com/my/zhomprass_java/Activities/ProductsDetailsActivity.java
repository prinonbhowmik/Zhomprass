package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.my.zhomprass_java.Adapters.ProductSliderApapter;
import com.my.zhomprass_java.Adapters.RelatedProductAdapter;
import com.my.zhomprass_java.Database.DatabaseHelper;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.RelatedProduct;
import com.my.zhomprass_java.Models.SingleProduct;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;
import com.my.zhomprass_java.Utils.Config;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsDetailsActivity extends AppCompatActivity {

    private SliderView sliderView;
    private TextView productName,productDes,productPrice,productPoint;
    private Button addToCard;
    private Toolbar toolbar;
    private RecyclerView relatedRecyclerView;
    private RecyclerView recyclerView;
    private List<RelatedProduct> relatedProductList;
    private List<SingleProduct> list;
    private ApiInterface api;
    private DatabaseHelper databaseHelper;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        productName=findViewById(R.id.productName);
        productDes=findViewById(R.id.descriptionText);
        productPrice=findViewById(R.id.priceTv);
        productPoint=findViewById(R.id.pointTv);
        addToCard=findViewById(R.id.addToCart);
        sliderView=findViewById(R.id.imageSlided);
        toolbar=findViewById(R.id.toolbar);
        relatedRecyclerView=findViewById(R.id.relatedRecyclerView);
        api = ApiUtils.getUserService();
        relatedRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        databaseHelper= new DatabaseHelper(this);
        recyclerView = findViewById(R.id.imagerecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        int id = intent.getIntExtra("product_id",0);
        getProductDescription(id);

        addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (databaseHelper.checkCart(list.get(0).getId())){
                    Toast.makeText(ProductsDetailsActivity.this, "Product Already in Cart", Toast.LENGTH_LONG).show();
                    return;
                }
                databaseHelper.addToCart(list.get(0));
                Toast.makeText(ProductsDetailsActivity.this, "Product Added To Cart", Toast.LENGTH_LONG).show();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.homeId:
                        startActivity(new Intent(ProductsDetailsActivity.this,MainActivity.class));
                        return true;
                    case R.id.orderId:
                        startActivity(new Intent(ProductsDetailsActivity.this,OrderActivity.class));
                        return true;
                    case R.id.cartId:
                        startActivity(new Intent(ProductsDetailsActivity.this,CartActivity.class));
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void getProductDescription(int id) {

        Call<List<SingleProduct>> call = api.getSingleProduct(id);
        call.enqueue(new Callback<List<SingleProduct>>() {
            @Override
            public void onResponse(Call<List<SingleProduct>> call, Response<List<SingleProduct>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else {
                        list = response.body();

                        productDes.setText(list.get(0).getProduct_description());
                        productName.setText(list.get(0).getProduct_name());
                        productPrice.setText(list.get(0).getPrice()+" TK");
                        productPoint.setText(list.get(0).getPoint()+" PT");
                        setUpImage(Config.IMAGE_LINE+list.get(0).getImage());
                        getRelatedProduct(list.get(0).getThird_cat_id());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SingleProduct>> call, Throwable t) {
                Toast.makeText(ProductsDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getRelatedProduct(int id) {

        Call<List<RelatedProduct>> call = api.getRelatedProduct(id);
        call.enqueue(new Callback<List<RelatedProduct>>() {
            @Override
            public void onResponse(Call<List<RelatedProduct>> call, Response<List<RelatedProduct>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        relatedProductList = response.body();

                        RelatedProductAdapter adapter = new RelatedProductAdapter(relatedProductList,ProductsDetailsActivity.this);
                        relatedRecyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RelatedProduct>> call, Throwable t) {
                Toast.makeText(ProductsDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpImage(String s) {
        ProductSliderApapter apapter = new ProductSliderApapter(this,s);
        recyclerView.setAdapter(apapter);
    }
}
