package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.michaelgarnerdev.materialsearchview.MaterialSearchView;
import com.my.zhomprass_java.Fragments.DashboardFragment;
import com.my.zhomprass_java.Fragments.Home;
import com.my.zhomprass_java.Fragments.OrderFragment;
import com.my.zhomprass_java.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView search;
    private ImageView logoImageViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        checkConnection();


        FragmentTransaction home = getSupportFragmentManager().beginTransaction();
        home.replace(R.id.fragment_container,new Home());
        home.commit();

        bottomNavOptions();

        sharedPreferences = getSharedPreferences("Customer_Id",MODE_PRIVATE);
        int id = sharedPreferences.getInt("cust_id",0);

        if (id==0){
            navigationView.getMenu().removeItem(R.id.logout);
        }
        if(id!=0){
            navigationView.getMenu().removeItem(R.id.signIn);
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
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
            Snackbar.make(findViewById(R.id.mainActivityLayout), R.string.offline_message, Snackbar.LENGTH_LONG).show();
        }
    }

    private void bottomNavOptions() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.homeId:
                        FragmentTransaction home = getSupportFragmentManager().beginTransaction();
                        home.replace(R.id.fragment_container,new Home());
                        home.commit();
                        return true;
                    case R.id.orderId:
                        FragmentTransaction order = getSupportFragmentManager().beginTransaction();
                        order.replace(R.id.fragment_container,new OrderFragment());
                        order.commit();
                        return true;
                    case R.id.cartId:
                        startActivity(new Intent(MainActivity.this,CartActivity.class));

                }

                return false;
            }
        });
    }

    private void init() {

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        search = findViewById(R.id.search);
        logoImageViewId = findViewById(R.id.logoImageId);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.signIn:
                startActivity(new Intent(MainActivity.this,Signin.class));
                drawerLayout.closeDrawers();
                break;

            case R.id.dashboard:
                FragmentTransaction home1 = getSupportFragmentManager().beginTransaction();
                home1.replace(R.id.fragment_container,new DashboardFragment());
                home1.commit();
                drawerLayout.closeDrawers();
                return true;

            case R.id.orderId:
                FragmentTransaction order = getSupportFragmentManager().beginTransaction();
                order.replace(R.id.fragment_container,new OrderFragment());
                order.commit();
                drawerLayout.closeDrawers();

                return true;
            case R.id.cartId:
                startActivity(new Intent(MainActivity.this,CartActivity.class));
                break;
            case R.id.treeView:
                startActivity(new Intent(MainActivity.this,TreeView.class));
                drawerLayout.closeDrawers();
                break;

            case R.id.ecommerce:
                startActivity(new Intent(MainActivity.this,Ecommerce.class));
                drawerLayout.closeDrawers();
                break;

            case R.id.joinCustomer:
                startActivity(new Intent(MainActivity.this,Customer.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.transaction:
                startActivity(new Intent(MainActivity.this,Transaction.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.tcId:
                startActivity(new Intent(MainActivity.this,term_condition.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.privacyPolicyId:
                startActivity(new Intent(MainActivity.this,PrivacyPolicy.class));
                drawerLayout.closeDrawers();
                break;

            case R.id.contactUsId:
                startActivity(new Intent(MainActivity.this,ContactUs.class));
                drawerLayout.closeDrawers();
                break;

            case R.id.aboutId:
                startActivity(new Intent(MainActivity.this,about.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.logout:
                sharedPreferences = getSharedPreferences("Customer_Id",MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.remove("cust_id");
                editor.apply();
                drawerLayout.closeDrawers();
                finish();
                break;
        }
        return false;
    }
}
