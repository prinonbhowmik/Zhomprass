package com.my.zhomprass_java.Fragments;


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

import com.my.zhomprass_java.Adapters.BrandsListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.BrandList;
import com.my.zhomprass_java.Models.SubBrandList;
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
public class BrandsFragment extends Fragment {

    private RecyclerView recyclerView;
    List<BrandList> list;
    List<SubBrandList> sublist;
    ApiInterface api;


    public BrandsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_brands, container, false);

       recyclerView =view.findViewById(R.id.brandsRecyclerView);
       recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
       list = new ArrayList<>();
       sublist = new ArrayList<>();
       api= ApiUtils.getUserService();


       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBrands();
    }

    private void getBrands() {

        Call<List<BrandList>> call = api.getBrands();
        call.enqueue(new Callback<List<BrandList>>() {
            @Override
            public void onResponse(Call<List<BrandList>> call, Response<List<BrandList>> response) {
                if(response.isSuccessful()){
                    list=response.body();
                    BrandsListAdapter adapter = new BrandsListAdapter(list,getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<BrandList>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
