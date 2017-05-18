package com.untaek.oneroom.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.untaek.oneroom.R;
import com.untaek.oneroom.rest.RetrofitManager;
import com.untaek.oneroom.rest.RoomService;
import com.untaek.oneroom.utility.RoomListAdapter;

import java.util.ArrayList;

public class RoomRegisterListActivity extends AppCompatActivity {

    Button button_register = null;
    ArrayList<RoomService.Room> arrayList_rooms = null;
    RoomListAdapter adapter_rooms = null;

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

        arrayList_rooms = new ArrayList<>();
        adapter_rooms = new RoomListAdapter(arrayList_rooms, this);

        RetrofitManager.getInstance().getRooms(MainActivity.logined.getId(), this, new RetrofitManager.ListListener() {
            @Override
            public void onReceive(ArrayList arrayList) {
                arrayList_rooms = arrayList;
                adapter_rooms.notifyDataSetChanged();
            }
        });
    }
}
