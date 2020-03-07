package com.my.zhomprass_java.Fragments;


import android.content.SharedPreferences;
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
import com.my.zhomprass_java.Models.DashBoard_Model;
import com.my.zhomprass_java.Models.Members;
import com.my.zhomprass_java.Models.UserShortInfo;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private TextView zplTv,positionTv,rankTv,zplmemberTv,fullNameTv,usernameTv
            ,phoneNoTv,referralAmountTv,generationamountTv,zplamountTv,msPointTv,zplPointTv,msamountTv,rankamountTv
            ,weekamountTv,dailyamountTv,monthlyamountTv,dealerSpotTv,dealerroyalityTv,dealerReferralTv,
            totalEarningTv, totalConvertTv, totalWithdrawTv, availableBalanceTv;
    private List<Members> membersList;
    private List<UserShortInfo> userShortInfos;
    private List<DashBoard_Model> dashBoardModels;
    private ApiInterface api;
    private SharedPreferences sharedPreferences;
    public DashboardFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        fullNameTv = view.findViewById(R.id.fullnameTv);
        usernameTv = view.findViewById(R.id.usernameTv);
        phoneNoTv = view.findViewById(R.id.phoneNoTv);
        zplTv = view.findViewById(R.id.zplTv);
        positionTv = view.findViewById(R.id.positionTv);
        rankTv = view.findViewById(R.id.rankTv);
        zplmemberTv = view.findViewById(R.id.zplMemberTv);
        referralAmountTv = view.findViewById(R.id.referralAmountTv);
        generationamountTv = view.findViewById(R.id.generationamountTv);
        zplamountTv = view.findViewById(R.id.zplamountTv);
        msPointTv = view.findViewById(R.id.msPointTv);
       // zplPointTv = view.findViewById(R.id.zplPointTv);
       // msamountTv = view.findViewById(R.id.msamountTv);
        //msamountTv = view.findViewById(R.id.msamountTv);
        rankamountTv = view.findViewById(R.id.rankamountTv);
        weekamountTv = view.findViewById(R.id.weekamountTv);
        dailyamountTv = view.findViewById(R.id.dailyamountTv);
        monthlyamountTv = view.findViewById(R.id.monthlyamountTv);
        dealerSpotTv = view.findViewById(R.id.dealerSpotTv);
        dealerroyalityTv = view.findViewById(R.id.dealerroyalityTv);
        dealerReferralTv = view.findViewById(R.id.dealerReferralTv);

        totalEarningTv = view.findViewById(R.id.totalEarningTv);
        totalConvertTv = view.findViewById(R.id.totalConvertTv);
        totalWithdrawTv = view.findViewById(R.id.totalWithdrawTv);
        availableBalanceTv = view.findViewById(R.id.availableBlanceTv);

        membersList = new ArrayList<>();
        dashBoardModels = new ArrayList<>();
        userShortInfos = new ArrayList<>();
        api = ApiUtils.getUserService();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFreeZpl();
        dashData();

    }

    private void dashData() {
        sharedPreferences = getContext().getSharedPreferences("Customer_Id",MODE_PRIVATE);
        int id = sharedPreferences.getInt("cust_id",0);

        if (id==0){
            Toast.makeText(getContext(), "Please Sign In!", Toast.LENGTH_LONG).show();
        }
        if (id!=0){
            Call<List<UserShortInfo>> call = api.getUserInfo(id);
                    call.enqueue(new Callback<List<UserShortInfo>>() {
                        @Override
                        public void onResponse(Call<List<UserShortInfo>> call, Response<List<UserShortInfo>> response) {
                            if (response.isSuccessful()){
                                if (response.body()==null){
                                    return;
                                }
                                else {
                                    userShortInfos=response.body();
                                    fullNameTv.setText(userShortInfos.get(0).getFull_name());
                                    String username = userShortInfos.get(0).getUser_name();
                                    usernameTv.setText(username);
                                    phoneNoTv.setText(userShortInfos.get(0).getMobile_no());
                                    zplTv.setText("Zpl Level : "+userShortInfos.get(0).getZpl());
                                    positionTv.setText("Position : "+String.valueOf(userShortInfos.get(0).getPosition()));
                                    rankTv.setText("Rank : "+String.valueOf(userShortInfos.get(0).getRank()));
                                    referralAmountTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getRefer()));
                                    generationamountTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getGeneration()));
                                    zplamountTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getZpl_balance()));
                                    msPointTv.setText(String.valueOf(userShortInfos.get(0).getTotal_point()));
                                    rankamountTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getRank_balance()));
                                    weekamountTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getWeekly()));
                                    dailyamountTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getDaily()));
                                    monthlyamountTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getMonthly()));
                                    dealerSpotTv.setText(String.valueOf(userShortInfos.get(0).getDealer_spot()));
                                    dealerroyalityTv.setText(String.valueOf(userShortInfos.get(0).getDealer_royalty()));
                                    dealerReferralTv.setText(String.valueOf(userShortInfos.get(0).getDealer_referal()));

                                    totalEarningTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getTotal_earn()));
                                    totalConvertTv.setText("৳ "+userShortInfos.get(0).getTotal_convert());
                                    totalWithdrawTv.setText("৳ "+userShortInfos.get(0).getTotal_withdraw());
                                    availableBalanceTv.setText("৳ "+String.valueOf(userShortInfos.get(0).getAvailable_balance()));

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<UserShortInfo>> call, Throwable t) {
                            Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
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
