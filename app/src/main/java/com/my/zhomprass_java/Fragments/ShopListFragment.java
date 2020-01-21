package com.my.zhomprass_java.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.my.zhomprass_java.Adapters.ShopListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.Bazar;
import com.my.zhomprass_java.Models.ShopList;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListFragment extends Fragment {

    private List<ShopList> list;
    private RecyclerView recyclerView;
    private ApiInterface api;


    public ShopListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);


        recyclerView = view.findViewById(R.id.divisionRec);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);
        api = ApiUtils.getUserService();
        list = new ArrayList<>();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getShopList();
    }

    private void getShopList() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Customer_Id", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("shop_id",0);

        Call<List<ShopList>> call = api.getShop(id);
        call.enqueue(new Callback<List<ShopList>>() {
            @Override
            public void onResponse(Call<List<ShopList>> call, Response<List<ShopList>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){return;}
                    else{
                        list = response.body();
                        ShopListAdapter adapter = new ShopListAdapter(list,getContext());
                        recyclerView.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<ShopList>> call, Throwable t) {

                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
