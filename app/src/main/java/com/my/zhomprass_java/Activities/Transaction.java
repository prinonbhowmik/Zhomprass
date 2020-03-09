package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.my.zhomprass_java.Adapters.TabPaggerAdapter;
import com.my.zhomprass_java.Fragments.Convert_Balance;
import com.my.zhomprass_java.Fragments.Receive_Balance;
import com.my.zhomprass_java.Fragments.Withdraw_Balance;
import com.my.zhomprass_java.R;

public class Transaction extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        checkConnection();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.options);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.productdetails_viewPager);

        TabPaggerAdapter tabPaggerAdapter = new TabPaggerAdapter(getSupportFragmentManager());
        tabPaggerAdapter.addFragment(new Convert_Balance());
        tabPaggerAdapter.addFragment(new Withdraw_Balance());
        tabPaggerAdapter.addFragment(new Receive_Balance());
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

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

            } else {

            }

        } else {
            Snackbar.make(findViewById(R.id.layoutTransactionActivity), R.string.offline_message, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()){

          case android.R.id.home:
              finish();
              break;

          case R.id.setPin:

              startActivity(new Intent(Transaction.this,Set_transaction_pin.class));
              break;

          case R.id.changepass:
              startActivity(new Intent(Transaction.this,Change_password.class));
              break;

      }
        return super.onOptionsItemSelected(item);

    }
}
