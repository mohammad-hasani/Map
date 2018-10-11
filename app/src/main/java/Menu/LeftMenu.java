package Menu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.me.bandar.MainActivity;
import com.example.me.bandar.MyLocation;
import com.example.me.bandar.R;
import com.google.android.gms.maps.GoogleMap;

import Tools.G;
import WebService.Interface.GetRestResult;
import WebService.RestWebServiceGet;

/**
 * Created by me on 12/15/2016.
 */

public class LeftMenu {
    private MainActivity view;
    private Button btnNormal, btnSatellite, btnHybrid, btnTerrain;
    private Button btnMyLocation;

    public LeftMenu(MainActivity view) {
        this.view = view;
        initViews();
        viewEvents();
    }

    private void initViews() {
        this.btnNormal = (Button) view.findViewById(R.id.btnnormal);
        this.btnSatellite = (Button) view.findViewById(R.id.btnsatellite);
        this.btnHybrid = (Button) view.findViewById(R.id.btnhybrid);
        this.btnTerrain = (Button) view.findViewById(R.id.btnterrain);

        this.btnMyLocation = (Button) view.findViewById(R.id.btnmylocation);

    }

    private void viewEvents() {
        this.btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.hideDrawerLayout(Gravity.LEFT);
                G.mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
        this.btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.hideDrawerLayout(Gravity.LEFT);
                G.mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        this.btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.hideDrawerLayout(Gravity.LEFT);
                G.mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        this.btnTerrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.hideDrawerLayout(Gravity.LEFT);
                G.mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });


        this.btnMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.hideDrawerLayout(Gravity.LEFT);
                Intent intent = new Intent(view.getContext(), MyLocation.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
