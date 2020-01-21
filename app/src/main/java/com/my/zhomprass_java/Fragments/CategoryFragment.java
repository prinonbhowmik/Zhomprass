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
import android.widget.TextView;
import android.widget.Toast;

import com.my.zhomprass_java.Adapters.CatagorListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.CatagoryList;
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
public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<CatagoryList> list;
    ApiInterface api;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_category, container, false);

       recyclerView = view.findViewById(R.id.categoriesRecyclerView);
       recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
       list = new ArrayList<>();
       api = ApiUtils.getUserService();

       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCategory();
    }

    private void getCategory() {

        Call<List<CatagoryList>> call = api.getCatagory();
        call.enqueue(new Callback<List<CatagoryList>>() {
            @Override
            public void onResponse(Call<List<CatagoryList>> call, Response<List<CatagoryList>> response) {
                if (response.isSuccessful()){
                    list=response.body();
                    CatagorListAdapter adapter = new CatagorListAdapter(list,getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CatagoryList>> call, Throwable t) {

                Toast.makeText(getContext(),""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
