package com.untaek.oneroom.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ejdej on 2017-04-15.
 */

public interface UserAuthService {
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
    class DBStatus{
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
        String nick_name;
        String email;
        String university;
        String login_date;
        String deal_type;
        int code;
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

    @POST("users/new/")
    Call<DBStatus> createUser(@Body SignUpUserInfo userInfo);

    @POST("users/login/")
    Call<UserInfo> logIn(@Body LoginInfo loginInfo);
}

