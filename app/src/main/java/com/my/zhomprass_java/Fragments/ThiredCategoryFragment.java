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

import com.my.zhomprass_java.Adapters.ThiredCategoryListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.ThiredCategoryList;
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
public class ThiredCategoryFragment extends Fragment {

    private List<ThiredCategoryList> list;
    private RecyclerView recyclerView;
    ApiInterface api;

    public ThiredCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_thired_category, container, false);

        recyclerView=view.findViewById(R.id.ThiredCategoryRec);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        list=new ArrayList<>();
        api = ApiUtils.getUserService();

       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getThirdCategory();
    }

    private void getThirdCategory() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Customer_Id", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("category_id_third",0);

        Call<List<ThiredCategoryList>> call = api.getThiredCategory(id);
        call.enqueue(new Callback<List<ThiredCategoryList>>() {
            @Override
            public void onResponse(Call<List<ThiredCategoryList>> call, Response<List<ThiredCategoryList>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list = response.body();
                        ThiredCategoryListAdapter adapter = new ThiredCategoryListAdapter(list,getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ThiredCategoryList>> call, Throwable t) {

            }
        });

    }
}
