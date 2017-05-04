package com.untaek.oneroom.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.untaek.oneroom.R;
import com.untaek.oneroom.format.ListTitleFormat;

import java.util.ArrayList;

/**
 * Created by ejdej on 2017-05-04.
 */

public class ListAdapter extends BaseAdapter {
    public ListAdapter(ArrayList<ListTitleFormat> arrayList, Context context){
        this.list = arrayList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    private ArrayList<ListTitleFormat> list = null;
    private LayoutInflater inflater = null;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vv = inflater.inflate(R.layout.list_board, viewGroup, false);
        TextView textView_title = (TextView) view.findViewById(R.id.textView_title);
        TextView textView_author = (TextView) view.findViewById(R.id.textView_author);
        TextView textView_date = (TextView) view.findViewById(R.id.textView_date);

        textView_title.setText(list.get(i).getTitle());
        textView_author.setText(list.get(i).getAuthor());
        textView_date.setText(list.get(i).getDate());

        return vv;
    }
}