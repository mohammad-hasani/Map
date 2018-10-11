package com.example.me.bandar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import MyGoogleMap.GPS.GPS;
import Tools.G;
import Tools.HelperJSON;
import WebService.Interface.GetRestResult;
import WebService.RestWebServiceGet;

/**
 * Created by me on 12/4/2016.
 */

public class MyLocation extends Activity {

    private TextView txtLatitude, txtLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        initViews();
        setData();
    }

    private void initViews() {
        this.txtLatitude = (TextView) findViewById(R.id.txtlatitude);
        this.txtLongitude = (TextView) findViewById(R.id.txtlongitude);
    }

    private void setData()
    {
        String latitude = String.valueOf(GPS.getMyLatitude(G.mMap));
        String longitude = String.valueOf(GPS.getMyLongitude(G.mMap));

        this.txtLatitude.setText(latitude);
        this.txtLongitude.setText(longitude);
    }
}
