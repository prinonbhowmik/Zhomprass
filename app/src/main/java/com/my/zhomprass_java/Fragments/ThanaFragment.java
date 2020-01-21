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

import com.my.zhomprass_java.Adapters.ThanaListAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.Thana;
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
public class ThanaFragment extends Fragment {

    private List<Thana> list;
    private RecyclerView recyclerView;
    private ApiInterface api;


    public ThanaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thana, container, false);

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

        getThana();
    }

    private void getThana() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Customer_Id", Context.MODE_PRIVATE);
        int thana_id = sharedPreferences.getInt("Thana_id",0);

        Call<List<Thana>> call = api.getThana(thana_id);
        call.enqueue(new Callback<List<Thana>>() {
            @Override
            public void onResponse(Call<List<Thana>> call, Response<List<Thana>> response) {

                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        list = response.body();
                        ThanaListAdapter adapter = new ThanaListAdapter(list,getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Thana>> call, Throwable t) {

            }
        });



    }
}
