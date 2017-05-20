package com.untaek.oneroom.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.untaek.oneroom.R;
import com.untaek.oneroom.rest.RoomService;

import java.util.Locale;

public class RoomPostDetailActivity extends AppCompatActivity {

    TextView textView_title;
    TextView textView_address;
    TextView textView_type;
    TextView textView_size;
    TextView textView_cost;
    TextView textView_cost_add;
    TextView textView_admin_ex;
    TextView textView_infra;
    TextView textView_options;
    TextView textView_parking;
    TextView textView_phone;
    TextView textView_desc;

    RoomService.RoomDetail roomDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_post_detail);

        roomDetail = (RoomService.RoomDetail) getIntent().getSerializableExtra("bundle");

        textView_title = (TextView) findViewById(R.id.textView_title);
        textView_address = (TextView) findViewById(R.id.textView_address);
        textView_type = (TextView) findViewById(R.id.textView_type);
        textView_size = (TextView) findViewById(R.id.textView_size);
        textView_cost = (TextView) findViewById(R.id.textView_cost);
        textView_cost_add = (TextView) findViewById(R.id.textView_cost_add);
        textView_admin_ex = (TextView) findViewById(R.id.textView_admin_ex);
        textView_infra = (TextView) findViewById(R.id.textView_infra);
        textView_options = (TextView) findViewById(R.id.textView_options);
        textView_parking = (TextView) findViewById(R.id.textView_parking);
        textView_phone = (TextView) findViewById(R.id.textView_phone);
        textView_desc = (TextView) findViewById(R.id.textView_desc);

        textView_title.setText(roomDetail.getTitle());
        textView_address.setText(roomDetail.getAddress());
        textView_type.setText(roomDetail.getContract_type().equals("MO") ? "월세" : "전세");
        textView_size.setText(String.format(Locale.KOREAN, "크기 : %d평", roomDetail.getSize()));
        textView_cost.setText(String.format(Locale.KOREAN, " %d만 원 / ", roomDetail.getCost()));
        textView_cost_add.setText(String.format(Locale.KOREAN, "권리금 %d만 원 ", roomDetail.getCost_additional()));
        textView_admin_ex.setText(String.format(Locale.KOREAN, "관리비 %d만 원", roomDetail.getAdmin_expenses()));
        textView_infra.setText(String.format(Locale.KOREAN, "편의 시설 : %s", roomDetail.getInfra()));
        textView_options.setText(String.format(Locale.KOREAN, "옵션 : %s", roomDetail.getOptions()));
        textView_parking.setText(roomDetail.isParking() ? "주차 가능" : "주차 불가");
        textView_desc.setText(String.format(Locale.KOREAN, "임대인의 설명 : %s", roomDetail.getDescription()));
        textView_phone.setText("010-2942-1385");

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment2);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                float coord[] = roomDetail.getCoordinate();
                final LatLng mapPos = new LatLng(coord[0], coord[1]);
                googleMap.getUiSettings().setRotateGesturesEnabled(false);
                googleMap.getUiSettings().setTiltGesturesEnabled(false);
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(mapPos, 16)));
                googleMap.addMarker(new MarkerOptions().position(mapPos).title("위치"));
            }
        });
    }
}
