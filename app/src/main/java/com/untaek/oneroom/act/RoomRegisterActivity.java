package com.untaek.oneroom.act;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.untaek.oneroom.R;
import com.untaek.oneroom.rest.RetrofitManager;
import com.untaek.oneroom.rest.RoomService;

public class RoomRegisterActivity extends AppCompatActivity {

    Button button_addImage = null;
    EditText editText_building_type = null;
    EditText editText_address = null;
    EditText editText_floor = null;
    EditText editText_size = null;
    EditText editText_infra = null;
    CheckBox checkBox_parking = null;
    EditText editText_options = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_register);
        button_addImage = (Button) findViewById(R.id.button_add_image);
        button_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivity(intent);
            }
        });

        editText_building_type = (EditText) findViewById(R.id.editText_room_building_type);
        editText_address = (EditText) findViewById(R.id.editText_room_address);
        editText_floor = (EditText) findViewById(R.id.editText_room_floor);
        editText_size = (EditText) findViewById(R.id.editText_room_size);
        editText_infra = (EditText) findViewById(R.id.editText_room_infra);
        checkBox_parking = (CheckBox) findViewById(R.id.checkbox_room_parking);
        editText_options = (EditText) findViewById(R.id.editText_room_options);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RoomService.Room room = new RoomService.Room(editText_address.getText().toString(),
                editText_building_type.getText().toString(),
                editText_floor.getText().toString(),
                Integer.parseInt(editText_size.getText().toString()));
        if(!editText_infra.getText().toString().equals(""))
            room.setInfra(editText_infra.getText().toString());
        if(checkBox_parking.isChecked())
            room.setParking(true);
        if(editText_options.getText().toString().equals(""))
            room.setOptions(editText_options.getText().toString());

        RetrofitManager.getInstance().registerRoom(room, this);
        return true;
    }
}
