package com.untaek.oneroom.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.untaek.oneroom.R;
import com.untaek.oneroom.rest.RetrofitManager;
import com.untaek.oneroom.rest.RoomService;
import com.untaek.oneroom.utility.RoomListAdapter;
import com.untaek.oneroom.utility.Toaster;

import java.util.ArrayList;

public class RoomRegisterListActivity extends AppCompatActivity {

    Button button_register = null;
    ArrayList<RoomService.Room> arrayList_rooms = null;
    RoomListAdapter adapter_rooms = null;
    ListView listView_rooms = null;
    LinearLayout layout = null;

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

        listView_rooms = (ListView) findViewById(R.id.listView_room);
        listView_rooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), RoomPostActivity.class);
                intent.putExtra("bundle", arrayList_rooms.get(i));
                Toaster.showSomeValue(getApplicationContext(), arrayList_rooms.get(i));
                startActivity(intent);
            }
        });
        layout = (LinearLayout) findViewById(R.id.linearLayout_if_rooms_not_exists);

        if(MainActivity.logined != null){
            RetrofitManager.getInstance().getRooms(MainActivity.logined.getId(), this, new RetrofitManager.ListListener() {
                @Override
                public void onReceive(ArrayList<RoomService.Room> arrayList) {
                    if(arrayList.size()>0){
                        layout.setVisibility(View.INVISIBLE);
                    }else{
                        layout.setVisibility(View.VISIBLE);
                    }
                    arrayList_rooms = arrayList;
                    adapter_rooms = new RoomListAdapter(arrayList_rooms, getApplicationContext());
                    listView_rooms.setAdapter(adapter_rooms);
                    adapter_rooms.notifyDataSetChanged();
                    Toaster.showSomeValue(getApplicationContext(), arrayList_rooms.get(0).getAddress()+"activity");
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), RoomRegisterActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }
}
