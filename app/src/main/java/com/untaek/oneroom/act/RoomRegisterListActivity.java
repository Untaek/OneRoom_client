package com.untaek.oneroom.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.untaek.oneroom.R;

public class RoomRegisterListActivity extends AppCompatActivity {

    Button button_register = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_register_list);

        button_register = (Button) findViewById(R.id.button_room_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RoomRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
