package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.my.zhomprass_java.Adapters.TabPaggerAdapter;
import com.my.zhomprass_java.Fragments.Generation_Tree;
import com.my.zhomprass_java.Fragments.My_Generation;
import com.my.zhomprass_java.Fragments.My_Invoice;
import com.my.zhomprass_java.Fragments.My_Joining;
import com.my.zhomprass_java.Fragments.New_Customer;
import com.my.zhomprass_java.Fragments.ZPL;
import com.my.zhomprass_java.R;

public class TreeView extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.productdetails_viewPager);

        TabPaggerAdapter tabPaggerAdapter = new TabPaggerAdapter(getSupportFragmentManager());
        tabPaggerAdapter.addFragment(new My_Generation());
        tabPaggerAdapter.addFragment(new Generation_Tree());
        tabPaggerAdapter.addFragment(new ZPL());
        viewPager.setAdapter(tabPaggerAdapter);

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