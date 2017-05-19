package com.untaek.oneroom.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.untaek.oneroom.R;
import com.untaek.oneroom.rest.RetrofitManager;
import com.untaek.oneroom.rest.RoomService;
import com.untaek.oneroom.utility.RoomBoardListAdapter;

import java.util.ArrayList;

public class RoomMyPostListActivity extends AppCompatActivity {
    ListView listView;
    RoomBoardListAdapter adapter;
    ArrayList<RoomService.RoomDetail> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_my_post_list);

        listView = (ListView) findViewById(R.id.listView_my_posted_room);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), RoomPostDetailActivity.class);
                intent.putExtra("bundle", postList.get(i));
                startActivity(intent);
            }
        });
        RetrofitManager.getInstance().getMyRoomPosts(this, new RetrofitManager.RoomPostListListener() {
            @Override
            public void onReceive(ArrayList<RoomService.RoomDetail> list) {
                postList = list;
                adapter = new RoomBoardListAdapter(postList, getApplicationContext());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
