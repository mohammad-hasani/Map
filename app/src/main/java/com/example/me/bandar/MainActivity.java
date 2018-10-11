package com.example.me.bandar;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import MyGoogleMap.GPS.GPS;
import MyGoogleMap.Markers;
import MyGoogleMap.drawpathongooglemap.AsyncDrawPath;
import Menu.LeftMenu;
import Menu.RightMenu;
import Tools.G;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Context context;
    private GoogleApiClient client;
    public static double latitude = 0.0, longitude = 0.0;
    private static DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        initMenus();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        G.mMap  = mMap;
        mMap.setOnMarkerClickListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        mMap.setMyLocationEnabled(true);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        alertAfterClick(marker);
        return false;
    }

    private void alertAfterClick(final Marker marker) {
        String[] alertMessegs = new String[]{getString(R.string.information), getString(R.string.pathtotarget)};
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setItems(alertMessegs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0: {
                        String market_name = marker.getTitle();
                        String market_id = Markers.market_ids.get(market_name);
                        Intent intent = new Intent(context, MarketDetails.class);
                        intent.putExtra("market_id", market_id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break;
                    }
                    case 1: {
                        drawPath(marker);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
        alert.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    private void drawPath(Marker marker)
    {
        double srcLatitude = GPS.getMyLatitude(mMap);
        double srcLongtude = GPS.getMyLongitude(mMap);
        double dstLatitude = marker.getPosition().latitude;
        double dstLongtude = marker.getPosition().longitude;

        AsyncDrawPath asyncDrawPath = new AsyncDrawPath(context, mMap, srcLatitude, srcLongtude, dstLatitude, dstLongtude);
        asyncDrawPath.execute();
    }

    private void initMenus()
    {
        RecyclerView menu_right = (RecyclerView) findViewById(R.id.right_menu);
        new RightMenu(this.context, menu_right);
        new LeftMenu(this);
    }

    public static void hideDrawerLayout(int gravity)
    {
        drawerLayout.closeDrawer(gravity);
    }

    public static void showDrawerLayout(int gravity)
    {
        drawerLayout.openDrawer(gravity);
    }
}
