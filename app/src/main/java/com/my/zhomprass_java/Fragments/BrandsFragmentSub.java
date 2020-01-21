package com.my.zhomprass_java.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.zhomprass_java.Adapters.SubBrandsListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
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
public class BrandsFragmentSub extends Fragment {

    private List<SubBrandList> list;
    private RecyclerView recyclerView;
    private ApiInterface api;

    public BrandsFragmentSub() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_brands_fragment_sub, container, false);

        recyclerView=view.findViewById(R.id.subBrandsRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        list=new ArrayList<>();
        api = ApiUtils.getUserService();

       getBrandSub();
       
       return view;
    }

    private void getBrandSub() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Customer_Id", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("category_id",0);

        Call<List<SubBrandList>> call = api.getBrandsWithSub(id);
        call.enqueue(new Callback<List<SubBrandList>>() {
            @Override
            public void onResponse(Call<List<SubBrandList>> call, Response<List<SubBrandList>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list=response.body();
                        SubBrandsListAdapter adapter = new SubBrandsListAdapter(list,getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SubBrandList>> call, Throwable t) {

            }
        });


    }

}
