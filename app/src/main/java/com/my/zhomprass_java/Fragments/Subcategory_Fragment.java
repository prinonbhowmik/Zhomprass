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

import com.my.zhomprass_java.Adapters.SubCategoryListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.SubCategoryList;
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
public class Subcategory_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private List<SubCategoryList> list;
    private ApiInterface api;


    public Subcategory_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_subcategory_, container, false);

        recyclerView=view.findViewById(R.id.subCategoryRec);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        api = ApiUtils.getUserService();
        list= new ArrayList<>();

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getSub();

    }

    private void getSub() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Customer_Id", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("category_id",0);

        Call<List<SubCategoryList>> call = api.getSubCategory(id);
        call.enqueue(new Callback<List<SubCategoryList>>() {
            @Override
            public void onResponse(Call<List<SubCategoryList>> call, Response<List<SubCategoryList>> response) {

                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else {
                        list=response.body();
                        SubCategoryListAdapter adapter = new SubCategoryListAdapter(list,getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SubCategoryList>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
