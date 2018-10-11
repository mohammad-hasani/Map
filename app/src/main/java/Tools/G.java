package Tools;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by me on 12/7/2016.
 */

public class G extends Application {

    public static String urlRestCategories = "http://198.20.123.78/map/getcategories/?format=json";
    public static String urlRestCities = "http://198.20.123.78/map/getcities/?format=json";
    public static String urlRestGetCooridinatesFromCategory = "http://198.20.123.78/map/getcoordinatesfromcat/?categoryid=@number@&format=json";
    public static String urlRestGetMarketInfo = "http://198.20.123.78/map/getmarketinfo/?format=json&marketid=@number@";

    public static final String DIR_APP = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AudioBook";
    public static final String DIR_DATABASE = DIR_APP + "/database/database.db";
    public static GoogleMap mMap;
    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this.getApplicationContext();
    }
}
