package com.untaek.oneroom.utility;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by ejdej on 2017-05-05.
 */

public class MapService {
    public MapService(Context context){
        this.context = context;
    }
    private Context context;
    LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    public void init(){
        
    }
}
