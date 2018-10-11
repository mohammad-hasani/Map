package com.example.me.bandar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import Tools.G;
import Tools.HelperJSON;
import WebService.Interface.GetRestResult;
import WebService.RestWebServiceGet;

/**
 * Created by me on 12/4/2016.
 */

public class MarketDetails extends Activity {
    private String market_id;
    private TextView txtName, txtAddress, txtTel, txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_details);
        market_id = getIntent().getStringExtra("market_id");
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        initViews();
        setData();
    }

    private void setData() {
        String url = G.urlRestGetMarketInfo.replace("@number@", market_id);
        new RestWebServiceGet(this, new GetRestResult() {
            @Override
            public void getData(String data) {
                ArrayList<HashMap<String, String>> tmp = HelperJSON.parseJson(data);
                HashMap<String, String> info = tmp.get(0);
                String name = info.get("name");
                String addr = info.get("address");
                String tel = info.get("tel");
                String information = info.get("info");

                txtName.setText(name);
                txtAddress.setText(addr);
                txtTel.setText(tel);
                txtInfo.setText(information);
            }
        }).execute(url);
    }

    private void initViews() {
        this.txtName = (TextView) findViewById(R.id.txtname);
        this.txtAddress = (TextView) findViewById(R.id.txtaddr);
        this.txtTel = (TextView) findViewById(R.id.txttel);
        this.txtInfo = (TextView) findViewById(R.id.txtinfo);
    }
}
