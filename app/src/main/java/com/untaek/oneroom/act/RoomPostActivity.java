package com.untaek.oneroom.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;

import com.untaek.oneroom.R;
import com.untaek.oneroom.rest.RetrofitManager;
import com.untaek.oneroom.rest.RoomService;

public class RoomPostActivity extends AppCompatActivity {

    EditText editText_title = null;
    RadioButton radioButton_monthly = null;
    RadioButton radioButton_yearly = null;
    EditText editText_cost = null;
    EditText editText_cost_add = null;
    EditText editText_desc = null;
    EditText editText_admin_expen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_post);

        editText_title = (EditText) findViewById(R.id.editText_room_post_title);
        editText_cost = (EditText) findViewById(R.id.editText_room_post_cost);
        editText_cost_add = (EditText) findViewById(R.id.editText_room_post_cost_add);
        editText_desc = (EditText) findViewById(R.id.editText_room_post_desc);
        editText_admin_expen = (EditText) findViewById(R.id.editText_room_post_admin_expen);
        radioButton_monthly = (RadioButton) findViewById(R.id.radioButton_room_post_monthly);
        radioButton_yearly = (RadioButton) findViewById(R.id.radioButton_room_post_yearly);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = editText_title.getText().toString();
        int cost = Integer.parseInt(editText_cost.getText().toString());
        int cost_add = Integer.parseInt(editText_cost_add.getText().toString());
        int admin_expen = Integer.parseInt(editText_admin_expen.getText().toString());
        String contract_type = radioButton_monthly.isChecked() ? "MO" : "YR";
        String desc = editText_desc.getText().toString();
        RoomService.Room room = (RoomService.Room) getIntent().getSerializableExtra("bundle");

        RoomService.RoomPost post = new RoomService.RoomPost(MainActivity.logined.getId(), room.getId(), contract_type, cost, cost_add, title, desc, admin_expen);
        RetrofitManager.getInstance().postRoom(post, this);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_write, menu);
        return true;
    }
}
