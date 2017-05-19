package com.untaek.oneroom.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.untaek.oneroom.R;
import com.untaek.oneroom.rest.RoomService;

import java.util.ArrayList;

/**
 * Created by ejdej on 2017-05-19.
 */

public class RoomBoardListAdapter extends BaseAdapter{
    ArrayList<RoomService.RoomDetail> list;
    LayoutInflater inflater;

    public RoomBoardListAdapter(ArrayList<RoomService.RoomDetail> list, Context context){
        this.list=list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
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
        View v = inflater.inflate(R.layout.list_post, viewGroup, false);
        TextView textView_title = (TextView) v.findViewById(R.id.textView_post_content_title);
        TextView textView_address = (TextView) v.findViewById(R.id.textView_post_content_address);
        TextView textView_size = (TextView) v.findViewById(R.id.textView_post_content_size);
        TextView textView_cost = (TextView) v.findViewById(R.id.textView_post_content_cost);
        TextView textView_cont_type = (TextView) v.findViewById(R.id.textView_post_content_contract_type);

        textView_title.setText(list.get(i).getTitle());
        textView_address.setText(list.get(i).getAddress());
        textView_size.setText(list.get(i).getSize()+"");
        textView_cost.setText(list.get(i).getCost()+"");
        textView_cont_type.setText(list.get(i).getContract_type().equals("MO") ? "월세" : "전세");
        return v;
    }
}
