package Tools;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HelperJSON {
    public static ArrayList<HashMap<String, String>> parseJson(String rawData) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONArray jsonArray = new JSONArray(rawData);
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(new HashMap<String, String>());
                String currentJSON = jsonArray.get(i).toString();
                JSONObject object = new JSONObject(currentJSON);
                Iterator iterator = object.keys();
                for (int j = 0; j < object.length(); j++) {
                    String key = String.valueOf(iterator.next());
                    String value = String.valueOf(object.get(key));
                    list.get(0).put(key, value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}