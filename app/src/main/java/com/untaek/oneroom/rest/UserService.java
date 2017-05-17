package com.untaek.oneroom.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by ejdej on 2017-04-15.
 */

public interface UserService {
    class DBStatus{
        private int code;
        public int code(){
            return this.code;
        }
    }

    class SignUpUserInfo{
        public SignUpUserInfo(String email,
                                String nick_name,
                                String university,
                                String password,
                                String deal_type){
            this.email = email;
            this.nick_name = nick_name;
            this.university = university;
            this.password = password;
            this.deal_type = deal_type;
        }
        private String nick_name;
        private String email;
        private String university;
        private String password;
        private String deal_type;
        private int code;
        public int code(){
            return this.code;
        }
    }

    class LoginInfo{
        public LoginInfo(String email, String password) {
            this.email = email;
            this.password = password;
        }

        private String email;
        private String password;
    }

    class UserInfo{
        long id;
        String nick_name;
        String email;
        String university;
        String login_date;
        String deal_type;
        int code;

        public long getId() {
            return id;
        }

        public int code(){
            return this.code;
        }

        public String getNick_name() {
            return nick_name;
        }

        public String getEmail() {
            return email;
        }

        public String getUniversity() {
            return university;
        }

        public String getDeal_type() {
            return deal_type;
        }
    }

    class Prefer{
        public Prefer(long id){
            this.user_id = id;
        }
        String contract_type;
        long user_id;
        int min_cost;
        int max_cost;
        int size;
        boolean parking;
        boolean available;
        int code;

        public String getContract_type() {
            return contract_type;
        }

        public long getUser_id() {
            return user_id;
        }

        public int getMin_cost() {
            return min_cost;
        }

        public int getMax_cost() {
            return max_cost;
        }

        public int getSize() {
            return size;
        }

        public boolean isParking() {
            return parking;
        }

        public boolean isAvailable() {
            return available;
        }

        public int getCode() {
            return code;
        }

        public void setContract_type(String contract_type) {
            this.contract_type = contract_type;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public void setMin_cost(int min_cost) {
            this.min_cost = min_cost;
        }

        public void setMax_cost(int max_cost) {
            this.max_cost = max_cost;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setParking(boolean parking) {
            this.parking = parking;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    @PUT("users/prefer/{id}")
    Call<DBStatus> updatePrefer(@Path("id") long id, @Body Prefer prefer);
    @GET("users/prefer/{id}")
    Call<Prefer> getPrefer(@Path("id") long id);
    @POST("users/new/")
    Call<DBStatus> createUser(@Body SignUpUserInfo userInfo);
    @POST("users/login/")
    Call<UserInfo> logIn(@Body LoginInfo loginInfo);
}