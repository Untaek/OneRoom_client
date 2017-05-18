package com.untaek.oneroom.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.untaek.oneroom.R;
import com.untaek.oneroom.format.ListTitleFormat;
import com.untaek.oneroom.utility.BoardListAdapter;

import java.util.ArrayList;

public class Board1Activity extends AppCompatActivity {

    ListView listView_board = null;
    BoardListAdapter adapter = null;
    ArrayList<ListTitleFormat> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board1);

        listView_board = (ListView) findViewById(R.id.listView_board);
        adapter = new BoardListAdapter(list, Board1Activity.this);
        listView_board.setAdapter(adapter);
        ListTitleFormat format = new ListTitleFormat();
        format.setTitle("제목제목제목제목");
        format.setAuthor("작성자작성자");
        format.setDate("30분 전 (2017-05-05 11:11:11)");
        format.setDescription("글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. 글내용 입니다. ");
        list.add(format);list.add(format);list.add(format);list.add(format);list.add(format);

        listView_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListTitleFormat data = (ListTitleFormat) adapter.getItem(position);
                Intent intent = new Intent(Board1Activity.this, BoardPostActivity.class);
                intent.putExtra(ListTitleFormat.BUNDLE, data);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_board_write:
                Intent intent = new Intent(getApplicationContext(), BoardWriteActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
