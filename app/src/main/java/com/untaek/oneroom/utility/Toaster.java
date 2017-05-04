package com.untaek.oneroom.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ejdej on 2017-04-16.
 */

public class Toaster {
    private Toaster(){}
    static public void showSomeValue(Context context, Object object){
        Toast.makeText(context, object.toString(), Toast.LENGTH_SHORT).show();
    }
    static public void success(Context context){
        Toast.makeText(context, "성공", Toast.LENGTH_SHORT).show();
    }
    static public void signUpParameterHasBlank(Context context){
        Toast.makeText(context, "빈 칸을 모두 채워주세요", Toast.LENGTH_SHORT).show();
    }
    static public void signUpPasswordNotMatch(Context context){
        Toast.makeText(context, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
    }
    static public void signUpEmailDuplicate(Context context){
        Toast.makeText(context, "이미 가입 된 이메일이 있습니다", Toast.LENGTH_SHORT).show();
    }
    static public void happenedSomethingWrong(Context context){
        Toast.makeText(context, "실패했습니다", Toast.LENGTH_SHORT).show();
    }
    static public void loginFailed(Context context){
        Toast.makeText(context, "로그인에 실패했습니다. 이메일이나 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
    }
}
