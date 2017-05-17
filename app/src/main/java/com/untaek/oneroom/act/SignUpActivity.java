package com.untaek.oneroom.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.untaek.oneroom.R;
import com.untaek.oneroom.utility.Toaster;
import com.untaek.oneroom.rest.RetrofitManager;
import com.untaek.oneroom.rest.UserService;

public class SignUpActivity extends AppCompatActivity {

    RadioButton radioButton_buyer = null;
    RadioButton radioButton_seller = null;
    EditText editText_email = null;
    EditText editText_pw = null;
    EditText editText_pw_confirm = null;
    EditText editText_nick_name = null;
    EditText editText_university = null;
    Button button_sign_up = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        radioButton_buyer = (RadioButton) findViewById(R.id.radioButton);
        radioButton_seller = (RadioButton) findViewById(R.id.radioButton2);
        editText_email = (EditText) findViewById(R.id.editText3);
        editText_pw = (EditText) findViewById(R.id.editText4);
        editText_pw_confirm = (EditText) findViewById(R.id.editText5);
        editText_nick_name = (EditText) findViewById(R.id.editText6);
        editText_university = (EditText) findViewById(R.id.editText7);
        button_sign_up = (Button) findViewById(R.id.button3);

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_email.getText().toString();
                String pw = editText_pw.getText().toString();
                String pw_confirm = editText_pw_confirm.getText().toString();
                String nick_name = editText_nick_name.getText().toString();
                String university = editText_university.getText().toString();
                String deal_type = radioButton_buyer.isChecked() ? "buyer" : "seller";

                if(!email.equals("") && !pw.equals("") && !pw_confirm.equals("")
                        && !nick_name.equals("") && !university.equals("")) {
                    if(editText_pw.getText().toString().equals(editText_pw_confirm.getText().toString())){
                        RetrofitManager retrofitManager = RetrofitManager.getInstance();

                        UserService.SignUpUserInfo user = new UserService.SignUpUserInfo(email, nick_name, university, pw, deal_type);
                        retrofitManager.signUp(user, SignUpActivity.this);
                    }
                    else{
                        Toaster.signUpPasswordNotMatch(SignUpActivity.this);
                    }
                }
                else{
                    Toaster.signUpParameterHasBlank(SignUpActivity.this);
                }
            }
        });
    }
}
