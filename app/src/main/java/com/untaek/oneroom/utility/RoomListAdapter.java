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

public class RoomListAdapter extends BaseAdapter {
    ArrayList<RoomService.Room> list;
    LayoutInflater inflater;
    public RoomListAdapter(ArrayList<RoomService.Room> list, Context context){
        this.list = list;
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
        View v = inflater.inflate(R.layout.list_room, viewGroup, false);
        TextView textView_building_type = (TextView) v.findViewById(R.id.textView_list_content_building_type);
        TextView textView_address = (TextView) v.findViewById(R.id.textView_list_content_address);
        TextView textView_floor = (TextView) v.findViewById(R.id.textView_list_content_floor);
        TextView textView_size = (TextView) v.findViewById(R.id.textView_list_content_size);
        TextView textView_posted = (TextView) v.findViewById(R.id.textView_list_content_posted);
        textView_building_type.setText(list.get(i).getBuilding_type());
        textView_address.setText(list.get(i).getAddress());
        textView_floor.setText(list.get(i).getFloor());
        textView_size.setText(String.valueOf(list.get(i).getSize()) + "평");
        textView_posted.setText("게시 안됨");

        return v;
    }
}
