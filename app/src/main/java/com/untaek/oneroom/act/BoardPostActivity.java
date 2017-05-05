package com.untaek.oneroom.act;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

import com.untaek.oneroom.R;
import com.untaek.oneroom.format.ListTitleFormat;

/**
 * Created by ejdej on 2017-05-04.
 */

public class BoardPostActivity extends AppCompatActivity {

    TextView textView_title = null;
    TextView textView_desc = null;
    TextView textView_author = null;
    TextView textView_date = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_post);

        ListTitleFormat data = (ListTitleFormat) getIntent().getSerializableExtra(ListTitleFormat.BUNDLE);

        textView_title = (TextView) findViewById(R.id.textView_post_title);
        textView_desc = (TextView) findViewById(R.id.textView_post_desc);
        textView_author = (TextView) findViewById(R.id.textView_post_author);
        textView_date = (TextView) findViewById(R.id.textView_post_date);

        textView_title.setText(data.getTitle());
        textView_desc.setText(data.getDescription());
        textView_author.setText(data.getAuthor());
        textView_date.setText(data.getDate());
    }
}
