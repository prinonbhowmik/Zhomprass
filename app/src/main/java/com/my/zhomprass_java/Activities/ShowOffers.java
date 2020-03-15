package com.my.zhomprass_java.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.my.zhomprass_java.Adapters.OfferAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.Offers;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowOffers extends AppCompatActivity {

    private RecyclerView showOfferRecycler;
    private ApiInterface api;
    private List<Offers> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_offers);


        showOfferRecycler = findViewById(R.id.showOfferRecycler);
        api = ApiUtils.getUserService();
        showOfferRecycler.setLayoutManager(new GridLayoutManager(this,2));
        list = new ArrayList<>();

        Call<List<Offers>> call = api.getOffers(1);
        call.enqueue(new Callback<List<Offers>>() {
            @Override
            public void onResponse(Call<List<Offers>> call, Response<List<Offers>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list = response.body();
                        OfferAdapter adapter = new OfferAdapter(ShowOffers.this,list);
                        showOfferRecycler.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Offers>> call, Throwable t) {
                Toast.makeText(ShowOffers.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
