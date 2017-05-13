package com.untaek.oneroom.rest;

import android.app.Activity;
import android.content.Intent;

import com.untaek.oneroom.act.MainActivity;
import com.untaek.oneroom.utility.Toaster;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ejdej on 2017-04-15.
 */

public class RetrofitManager {
    private static RetrofitManager instance = null;
    private RetrofitManager(){
        this.build();
    }
    static public RetrofitManager getInstance(){
        if(instance == null){
            return new RetrofitManager();
        }
        return instance;
    }

    private UserAuthService userAuthService = null;

    static public final int OK = 200;
    static public final int SIGN_UP_EMAIL_DUPLICATED = 300;
    static public final int LOGIN_FAILED = 400;

    private void build(){
        String url = "http://45.32.51.155:8015/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userAuthService = retrofit.create(UserAuthService.class);
    }

    public void signUp(UserAuthService.SignUpUserInfo signUpUserInfo, final Activity activity){
        Call<UserAuthService.DBStatus> call = userAuthService.createUser(signUpUserInfo);
        call.enqueue(new Callback<UserAuthService.DBStatus>() {
            @Override
            public void onResponse(Call<UserAuthService.DBStatus> call, Response<UserAuthService.DBStatus> response) {
                if(response.isSuccessful()){
                    UserAuthService.DBStatus status = response.body();
                    // 성공
                    if(status.code() == RetrofitManager.OK){
                        Toaster.success(activity);
                        activity.finish();
                    }
                    // 오류
                    else if(status.code() == RetrofitManager.SIGN_UP_EMAIL_DUPLICATED){
                        Toaster.signUpEmailDuplicate(activity);
                    }
                    Toaster.showSomeValue(activity, response.body().code());
                }
            }

            @Override
            public void onFailure(Call<UserAuthService.DBStatus> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
                Toaster.showSomeValue(activity, call.toString());
            }
        });
    }

    public void logIn(String email, String password, final Activity activity){
        Call<UserAuthService.UserInfo> call = userAuthService.logIn(new UserAuthService.LoginInfo(email, password));
        call.enqueue(new Callback<UserAuthService.UserInfo>() {
            @Override
            public void onResponse(Call<UserAuthService.UserInfo> call, Response<UserAuthService.UserInfo> response) {
                if(response.isSuccessful()){
                    UserAuthService.UserInfo userInfo = response.body();
                    if(userInfo.code() == RetrofitManager.OK){
                        Toaster.success(activity);
                        Toaster.showSomeValue(activity, userInfo.email + ", " + userInfo.nick_name + ", " + userInfo.university);
                        MainActivity.logined = userInfo;
                        activity.setResult(MainActivity.RESULT_OK);
                        activity.finish();
                    }
                    else if(userInfo.code() == RetrofitManager.LOGIN_FAILED){
                        Toaster.loginFailed(activity);
                    }
                    Toaster.showSomeValue(activity, response.code());
                }
            }

            @Override
            public void onFailure(Call<UserAuthService.UserInfo> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
                Toaster.showSomeValue(activity, call.toString());
            }
        });
    }
}
