package com.my.zhomprass_java.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.my.zhomprass_java.Activities.Signin;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.Members;
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
public class DashboardFragment extends Fragment {

    private TextView zplTv,positionTv,rankTv,zplmemberTv;
    private List<Members> membersList;
    private ApiInterface api;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        zplTv = view.findViewById(R.id.zplTv);
        positionTv = view.findViewById(R.id.positionTv);
        rankTv = view.findViewById(R.id.rankTv);
        zplmemberTv = view.findViewById(R.id.zplMemberTv);
        membersList = new ArrayList<>();
        api = ApiUtils.getUserService();



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFreeZpl();

    }

    private void getFreeZpl() {

        Call<List<Members>> call = api.getTotalZpl("zpl");
        call.enqueue(new Callback<List<Members>>() {
            @Override
            public void onResponse(Call<List<Members>> call, Response<List<Members>> response) {
                membersList = response.body();
                if (response.isSuccessful()){
                    if (response.body()==null)
                    {
                        return;
                    }
                    else{
                        int zpl = response.body().get(0).getMember();
                        zplmemberTv.setText(String.valueOf(zpl));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Members>> call, Throwable t) {

                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
