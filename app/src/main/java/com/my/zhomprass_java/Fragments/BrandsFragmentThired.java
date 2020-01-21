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

import com.my.zhomprass_java.Adapters.ThiredBrandsListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.ThiredBrandList;
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
public class BrandsFragmentThired extends Fragment {

    private RecyclerView recyclerView;
    private List<ThiredBrandList> list;
    ApiInterface api;


    public BrandsFragmentThired() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_brands_fragment_thired, container, false);

       recyclerView=view.findViewById(R.id.thiredBrandsRecycler);
       list = new ArrayList<>();
       api = ApiUtils.getUserService();
       recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

       getBrandsThird();

       return view;
    }

    private void getBrandsThird() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Customer_Id", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("category_id_third",0);

        Call<List<ThiredBrandList>> call = api.getBrandsWithThired(id);
        call.enqueue(new Callback<List<ThiredBrandList>>() {
            @Override
            public void onResponse(Call<List<ThiredBrandList>> call, Response<List<ThiredBrandList>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list=response.body();
                        ThiredBrandsListAdapter adapter = new ThiredBrandsListAdapter(list,getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ThiredBrandList>> call, Throwable t) {

            }
        });
    }

}
