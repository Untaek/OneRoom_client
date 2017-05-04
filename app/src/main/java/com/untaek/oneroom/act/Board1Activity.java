package com.untaek.oneroom.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.untaek.oneroom.R;
import com.untaek.oneroom.format.ListTitleFormat;
import com.untaek.oneroom.utility.ListAdapter;

import java.util.ArrayList;

public class Board1Activity extends AppCompatActivity {

    ListView listView_board = null;
    ArrayList<ListTitleFormat> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board1);

        listView_board = (ListView) findViewById(R.id.listView_board);
        ListAdapter adapter = new ListAdapter(list, Board1Activity.this);
        listView_board.setAdapter(adapter);
        ListTitleFormat format = new ListTitleFormat();
        format.setTitle("a");
        format.setAuthor("b");
        format.setDate("c");
        list.add(format);list.add(format);list.add(format);list.add(format);list.add(format);
    }
}
