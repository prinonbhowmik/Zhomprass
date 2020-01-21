package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.my.zhomprass_java.Adapters.SubTabPaggerAdapter;
import com.my.zhomprass_java.Fragments.BrandsFragmentThired;
import com.my.zhomprass_java.Fragments.ShopsFragment;
import com.my.zhomprass_java.Fragments.ThiredCategoryFragment;
import com.my.zhomprass_java.R;

public class ThiredCategoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thired_category);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.productdetails_viewPager);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpViewPagger();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.homeId:
                        startActivity(new Intent(ThiredCategoryActivity.this,MainActivity.class));
                        return true;
                    case R.id.orderId:
                        startActivity(new Intent(ThiredCategoryActivity.this,OrderActivity.class));
                        return true;
                    case R.id.cartId:
                        startActivity(new Intent(ThiredCategoryActivity.this,CartActivity.class));
                }
                return false;
            }
        });
    }

    private void setUpViewPagger() {

        SubTabPaggerAdapter adapter = new SubTabPaggerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ThiredCategoryFragment());
        adapter.addFragment(new BrandsFragmentThired());
        adapter.addFragment(new ShopsFragment());

        SharedPreferences sharedPreferences = getSharedPreferences("Customer_Id",MODE_PRIVATE);
        String name = sharedPreferences.getString("category_name",null);

        tabLayout.addTab(tabLayout.newTab().setText(name));
        tabLayout.addTab(tabLayout.newTab().setText("Brands"));
        tabLayout.addTab(tabLayout.newTab().setText("Shops"));

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
