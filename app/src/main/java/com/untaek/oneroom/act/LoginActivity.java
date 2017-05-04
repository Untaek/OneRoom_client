package com.untaek.oneroom.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.untaek.oneroom.R;
import com.untaek.oneroom.utility.Toaster;
import com.untaek.oneroom.rest.RetrofitManager;

public class LoginActivity extends AppCompatActivity {

    EditText editText_email = null;
    EditText editText_pw = null;
    Button button_login = null;
    Button button_sign_up = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_email = (EditText) findViewById(R.id.editText);
        editText_pw = (EditText) findViewById(R.id.editText2);
        button_login = (Button) findViewById(R.id.button);
        button_sign_up = (Button) findViewById(R.id.button2);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitManager manager = RetrofitManager.getInstance();
                String email = editText_email.getText().toString();
                String password = editText_pw.getText().toString();
                if(!(email.equals("") || password.equals(""))){
                    manager.logIn(email, password, LoginActivity.this);
                }
                else{
                    Toaster.signUpParameterHasBlank(LoginActivity.this);
                }
            }
        });

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }
}
