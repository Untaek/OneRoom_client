package com.untaek.oneroom.rest;

import android.app.Activity;

import com.untaek.oneroom.act.MainActivity;
import com.untaek.oneroom.utility.Toaster;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    private UserService userService = null;
    private RoomService roomService = null;

    private static final int OK = 200;
    private static final int SIGN_UP_EMAIL_DUPLICATED = 300;
    private static final int LOGIN_FAILED = 400;

    public interface ListListener{
        void onReceive(ArrayList<RoomService.Room> arrayList);
    }

    public interface RoomPostListListener{
        void onReceive(ArrayList<RoomService.RoomDetail> list);
    }

    private void build(){
        String url = "http://45.32.51.155:8015/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
        roomService = retrofit.create(RoomService.class);
    }

    public void signUp(UserService.SignUpUserInfo signUpUserInfo, final Activity activity){
        Call<UserService.DBStatus> call = userService.createUser(signUpUserInfo);
        call.enqueue(new Callback<UserService.DBStatus>() {
            @Override
            public void onResponse(Call<UserService.DBStatus> call, Response<UserService.DBStatus> response) {
                if(response.isSuccessful()){
                    UserService.DBStatus status = response.body();
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
            public void onFailure(Call<UserService.DBStatus> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
                Toaster.showSomeValue(activity, call.toString());
            }
        });
    }

    public void logIn(String email, String password, final Activity activity){
        Call<UserService.UserInfo> call = userService.logIn(new UserService.LoginInfo(email, password));
        call.enqueue(new Callback<UserService.UserInfo>() {
            @Override
            public void onResponse(Call<UserService.UserInfo> call, Response<UserService.UserInfo> response) {
                if(response.isSuccessful()){
                    UserService.UserInfo userInfo = response.body();
                    if(userInfo.code() == RetrofitManager.OK){
                        Toaster.success(activity);
                        Toaster.showSomeValue(activity, userInfo.email + ", " + userInfo.nick_name + ", " + userInfo.university);
                        MainActivity.logined = userInfo;
                        getPrefer(userInfo.getId(), activity);
                    }
                    else if(userInfo.code() == RetrofitManager.LOGIN_FAILED){
                        Toaster.loginFailed(activity);
                    }
                    Toaster.showSomeValue(activity, response.code());
                }
            }

            @Override
            public void onFailure(Call<UserService.UserInfo> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
                Toaster.showSomeValue(activity, call.toString());
            }
        });
    }

    public void updatePrefer(UserService.Prefer prefer, final Activity activity){
        Call<UserService.DBStatus> call = userService.updatePrefer(prefer.user_id, prefer);
        call.enqueue(new Callback<UserService.DBStatus>() {
            @Override
            public void onResponse(Call<UserService.DBStatus> call, Response<UserService.DBStatus> response) {
                if(response.isSuccessful()){
                    if(response.body().code() == OK){
                        Toaster.success(activity);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserService.DBStatus> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
            }
        });
    }

    public void getPrefer(long id, final Activity activity){
        Call<UserService.Prefer> call = userService.getPrefer(id);
        call.enqueue(new Callback<UserService.Prefer>() {
            @Override
            public void onResponse(Call<UserService.Prefer> call, Response<UserService.Prefer> response) {
                if(response.isSuccessful()){
                    UserService.Prefer body = response.body();
                    if(body.code == OK){
                        MainActivity.prefer = body;
                        activity.setResult(MainActivity.RESULT_OK);
                        activity.finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserService.Prefer> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
            }
        });
    }

    public void registerRoom(RoomService.Room room, final Activity activity){
        Call<RoomService.DBStatus> call = roomService.registerRoom(room);
        call.enqueue(new Callback<RoomService.DBStatus>() {
            @Override
            public void onResponse(Call<RoomService.DBStatus> call, Response<RoomService.DBStatus> response) {
                if(response.isSuccessful()){
                    if(response.body().code == OK)
                        activity.finish();
                    else
                        Toaster.showSomeValue(activity, response.body().code);
                }
            }

            @Override
            public void onFailure(Call<RoomService.DBStatus> call, Throwable t) {}
        });
    }

    public void getRooms(long id, final Activity activity, final ListListener listener){
        Call<RoomService.ResultList> call = roomService.getRooms(id);
        call.enqueue(new Callback<RoomService.ResultList>() {
            @Override
            public void onResponse(Call<RoomService.ResultList> call, Response<RoomService.ResultList> response) {
                if(response.isSuccessful()){
                    if(response.body().code == OK){
                        listener.onReceive(response.body().result);
                        Toaster.showSomeValue(activity, response.body().result.size()+"");
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomService.ResultList> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
            }
        });
    }

    public void postRoom(RoomService.RoomPost post, final Activity activity){
        Call<RoomService.DBStatus> call = roomService.postRoom(post);
        call.enqueue(new Callback<RoomService.DBStatus>() {
            @Override
            public void onResponse(Call<RoomService.DBStatus> call, Response<RoomService.DBStatus> response) {
                if(response.isSuccessful()){
                    if(response.body().code == OK){
                        activity.finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomService.DBStatus> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
            }
        });
    }

    public void getMyRoomPosts(final Activity activity, final RoomPostListListener listener){
        Call<RoomService.RoomDetailList> call = roomService.getMyRoomPosts(MainActivity.logined.getId());
        call.enqueue(new Callback<RoomService.RoomDetailList>() {
            @Override
            public void onResponse(Call<RoomService.RoomDetailList> call, Response<RoomService.RoomDetailList> response) {
                if(response.isSuccessful()){
                    if(response.body().code == OK){
                        listener.onReceive(response.body().result);
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomService.RoomDetailList> call, Throwable t) {
                Toaster.happenedSomethingWrong(activity);
            }
        });
    }
}
