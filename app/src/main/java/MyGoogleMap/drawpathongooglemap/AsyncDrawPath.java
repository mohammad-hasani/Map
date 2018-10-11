package MyGoogleMap.drawpathongooglemap;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.google.android.gms.maps.GoogleMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by me on 12/5/2016.
 */

public class AsyncDrawPath extends AsyncTask<Void, Void, String> {
    private ProgressDialog progressDialog;
    String url;
    private Context context;
    private GoogleMap mMap;

    public AsyncDrawPath(Context context, GoogleMap mMap,
                  double slat, double slog, double dlat, double dlog){
        url = new DrawPathOnGoogleMap().makeURL(slat, slog, dlat, dlog);
        this.context = context;
        this.mMap = mMap;
    }
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage("Fetching route, Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }
    @Override
    protected String doInBackground(Void... params) {
        String json = getJSONFromUrl(url);
        return json;
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressDialog.hide();
        if(result!=null){
            DrawPathOnGoogleMap drawPathOnGoogleMap = new DrawPathOnGoogleMap();
            drawPathOnGoogleMap.drawPath(result, mMap);
        }
    }
    public String getJSONFromUrl(String url) {
        InputStream is = null;
        JSONObject jObj = null;
        String json = "";
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            json = sb.toString();
            is.close();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        return json;

    }
}

