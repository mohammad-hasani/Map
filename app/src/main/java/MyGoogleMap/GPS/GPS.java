package MyGoogleMap.GPS;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by me on 12/7/2016.
 */

public class GPS {
    public void getMyLocation()
    {

    }
    public static double getMyLatitude(GoogleMap mMap)
    {
        double latitude = mMap.getMyLocation().getLatitude();
        return latitude;
    }
    public static double getMyLongitude(GoogleMap mMap)
    {
        double longtude = mMap.getMyLocation().getLongitude();
        return longtude;
    }
}
