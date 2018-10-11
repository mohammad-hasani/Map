package MyGoogleMap;

import android.content.Context;
import android.view.Gravity;

import com.example.me.bandar.MainActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

import Tools.G;
import Tools.HelperJSON;
import WebService.Interface.GetRestResult;
import WebService.RestWebServiceGet;

/**
 * Created by me on 12/19/2016.
 */

public class Markers {

    public static HashMap<String, String> market_ids = new HashMap<String, String>();

    public void change_category_marker(Context context, String category_id) {
        G.mMap.clear();
        market_ids.clear();
        String url = G.urlRestGetCooridinatesFromCategory.replace("@number@", category_id);
        new RestWebServiceGet(context, new GetRestResult() {
            @Override
            public void getData(String data) {
                if (data != null) {
                    ArrayList<HashMap<String, String>> tmp = HelperJSON.parseJson(data);
                    for(HashMap<String, String> coordinate : tmp)
                    {
                        String market_id = coordinate.get("market_id");
                        String market_name = coordinate.get("name");
                        Double latitude = Double.valueOf(coordinate.get("latitude"));
                        Double longitude = Double.valueOf(coordinate.get("longitude"));
                        LatLng latLng = new LatLng(latitude, longitude);
                        G.mMap.addMarker(new MarkerOptions().position(latLng).title(market_name));
                        market_ids.put(market_name, market_id);
                    }
                }
            }
        }).execute(url);
        MainActivity.hideDrawerLayout(Gravity.RIGHT);
    }
}
