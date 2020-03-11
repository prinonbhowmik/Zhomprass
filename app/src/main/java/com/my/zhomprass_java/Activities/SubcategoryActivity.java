package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.my.zhomprass_java.Adapters.SubTabPaggerAdapter;
import com.my.zhomprass_java.Fragments.BrandsFragmentSub;
import com.my.zhomprass_java.Fragments.Home;
import com.my.zhomprass_java.Fragments.OrderFragment;
import com.my.zhomprass_java.Fragments.ShopsFragment;
import com.my.zhomprass_java.Fragments.Subcategory_Fragment;
import com.my.zhomprass_java.R;

public class SubcategoryActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private ImageView logoImageViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.productdetails_viewPager);
        toolbar=findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        logoImageViewId = findViewById(R.id.logoImageId);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        
        setUpViewPager();
        checkConnection();
        showContactUs();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.homeId:
                        startActivity(new Intent(SubcategoryActivity.this,MainActivity.class));
                        return true;
                    case R.id.orderId:
                        startActivity(new Intent(SubcategoryActivity.this,OrderActivity.class));
                        return true;
                    case R.id.cartId:
                        startActivity(new Intent(SubcategoryActivity.this,CartActivity.class));
                }



                return false;
            }
        });


    }
    private void showContactUs() {
        logoImageViewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SubcategoryActivity.this,ContactUs.class);
                startActivity(intent);
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
            Snackbar.make(findViewById(R.id.layoutSubCategoryActivity), R.string.offline_message, Snackbar.LENGTH_LONG).show();
        }
    }

    private void setUpViewPager() {
        SubTabPaggerAdapter adapter = new SubTabPaggerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Subcategory_Fragment());
        adapter.addFragment(new BrandsFragmentSub());
        adapter.addFragment(new ShopsFragment());

        SharedPreferences sharedPreferences = getSharedPreferences("Customer_Id",MODE_PRIVATE);
        String name = sharedPreferences.getString("category_name",null);

        tabLayout.addTab(tabLayout.newTab().setText(name));
        tabLayout.addTab(tabLayout.newTab().setText("Brands"));
        tabLayout.addTab(tabLayout.newTab().setText("Shops"));

        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
