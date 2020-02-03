package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.my.zhomprass_java.Adapters.BrandsListAdapter;
import com.my.zhomprass_java.Adapters.MultiProductAdapter;
import com.my.zhomprass_java.Adapters.SearchProductAdapter;
import com.my.zhomprass_java.Adapters.ShopListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Fragments.Home;
import com.my.zhomprass_java.Fragments.OrderFragment;
import com.my.zhomprass_java.Models.BrandList;
import com.my.zhomprass_java.Models.MultiProducts;
import com.my.zhomprass_java.Models.SearchProduct;
import com.my.zhomprass_java.Models.ShopList;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private SearchView searchView;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private List<SearchProduct> list;
    private List<BrandList> searchBrand;
    private List<ShopList> searchShop;
    private SearchProductAdapter productAdapter;
    private BrandsListAdapter brandsAdapter;
    private ShopListAdapter shopAdapter;
    private ApiInterface api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       init();


        searchResults();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.homeId:
                        startActivity(new Intent(SearchActivity.this,MainActivity.class));
                        return true;
                    case R.id.orderId:
                        startActivity(new Intent(SearchActivity.this,OrderActivity.class));
                        return true;
                    case R.id.cartId:
                        startActivity(new Intent(SearchActivity.this,CartActivity.class));
                }
                return false;
            }
        });


    }

    private void init() {

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        searchView = findViewById(R.id.searchView);
        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        spinner = findViewById(R.id.spinnerId);
        recyclerView = findViewById(R.id.searchRecycler);
        list = new ArrayList<>();
        searchBrand = new ArrayList<>();
        searchShop = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ArrayAdapter<CharSequence> searchItem = ArrayAdapter.createFromResource(this,R.array.search,android.R.layout.simple_spinner_item);
        searchItem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(searchItem);
        api = ApiUtils.getUserService();

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    private void searchResults() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, final long l) {
                String item = adapterView.getItemAtPosition(i).toString();

                if (spinner.getSelectedItemPosition() == 0){
                   searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                       @Override
                       public boolean onQueryTextSubmit(String query) {

                           return false;
                       }

                       @Override
                       public boolean onQueryTextChange(final String newText) {
                           Call<List<SearchProduct>> call = api.getProduct(newText);
                           call.enqueue(new Callback<List<SearchProduct>>() {
                               @Override
                               public void onResponse(Call<List<SearchProduct>> call, Response<List<SearchProduct>> response) {
                                   if (response.isSuccessful()){
                                       list = response.body();
                                       productAdapter = new SearchProductAdapter(list,SearchActivity.this);
                                       recyclerView.setAdapter(productAdapter);
                                   }
                               }

                               @Override
                               public void onFailure(Call<List<SearchProduct>> call, Throwable t) {

                               }
                           });
                           return false;
                       }
                   });
                }
                if (spinner.getSelectedItemPosition()==1){
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {



                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {

                            Call<List<BrandList>> call = api.searchBrand(newText);
                            call.enqueue(new Callback<List<BrandList>>() {
                                @Override
                                public void onResponse(Call<List<BrandList>> call, Response<List<BrandList>> response) {
                                    if (response.isSuccessful()){
                                        searchBrand = response.body();
                                        brandsAdapter = new BrandsListAdapter(searchBrand,SearchActivity.this);
                                        recyclerView.setAdapter(brandsAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<BrandList>> call, Throwable t) {

                                }
                            });

                            return false;
                        }
                    });

                }
                if (spinner.getSelectedItemPosition()==2){
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            Call<List<ShopList>> call = api.searchShop(newText);
                            call.enqueue(new Callback<List<ShopList>>() {
                                @Override
                                public void onResponse(Call<List<ShopList>> call, Response<List<ShopList>> response) {
                                    if (response.isSuccessful()){
                                        searchShop = response.body();
                                        shopAdapter = new ShopListAdapter(searchShop,SearchActivity.this);
                                        recyclerView.setAdapter(shopAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<ShopList>> call, Throwable t) {

                                }
                            });
                            return false;
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            list.clear();
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}