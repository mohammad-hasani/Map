package Tools;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by me on 12/7/2016.
 */

public class Database {
    public static void copyDB(Context context) {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("db/database.db");
            HelperIO.copyFile(inputStream, G.DIR_DATABASE);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
