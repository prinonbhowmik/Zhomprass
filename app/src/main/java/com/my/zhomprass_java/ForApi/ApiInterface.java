package com.my.zhomprass_java.ForApi;

import com.my.zhomprass_java.Models.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("login_api.php")
    Call<List<UserInfo>> login(@Query("user_name") String username, @Query("password") String password);

}
