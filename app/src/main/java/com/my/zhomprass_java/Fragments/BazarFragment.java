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

import com.my.zhomprass_java.Adapters.BazarListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.Bazar;
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
public class BazarFragment extends Fragment {

    private List<Bazar> list;
    private RecyclerView recyclerView;
    private ApiInterface api;


    public BazarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_bazar, container, false);

        recyclerView = view.findViewById(R.id.divisionRec);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);
        api = ApiUtils.getUserService();
        list = new ArrayList<>();

        getBazar();


       return view;
    }

    private void getBazar() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Customer_Id", Context.MODE_PRIVATE);
       int id = sharedPreferences.getInt("bazar_id",0);

        Call<List<Bazar>> call = api.getBazar(id);
        call.enqueue(new Callback<List<Bazar>>() {
            @Override
            public void onResponse(Call<List<Bazar>> call, Response<List<Bazar>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list = response.body();
                        BazarListAdapter adapter = new BazarListAdapter(list,getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Bazar>> call, Throwable t) {

            }
        });

    }

}
