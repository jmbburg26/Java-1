package com.johnbrandenburg.myfirstapp.app;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by SPCbrandenburg on 6/26/14.
 */
public class ConnectionCheck extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_check);
        Map<String, String> networkDetails = getConnectionDetails();
        if (networkDetails.isEmpty()) {
            findViewById(R.id.tableRow1).setVisibility(0);
            TextView value = (TextView) findViewById(R.id.status);
            value.setText("Connection unavailable");
            Toast.makeText(getApplicationContext(), "Connection unavailable", Toast.LENGTH_SHORT).show();
        } else {
            if (networkDetails.containsKey("State")) {
                findViewById(R.id.tableRow1).setVisibility(0);
                TextView value = (TextView) findViewById(R.id.status);
                value.setText(networkDetails.get("State"));
                Toast.makeText(getApplicationContext(), "Connection available", Toast.LENGTH_SHORT).show();
            }
            if (networkDetails.containsKey("Type")) {
                findViewById(R.id.tableRow2).setVisibility(0);
                TextView value = (TextView) findViewById(R.id.type);
                value.setText(networkDetails.get("Type"));
                Toast.makeText(getApplicationContext(), "Connection available", Toast.LENGTH_SHORT).show();
            }
            if (networkDetails.containsKey("Sub type")) {
                findViewById(R.id.tableRow3).setVisibility(0);
                TextView value = (TextView) findViewById(R.id.subtype);
                value.setText(networkDetails.get("Sub type"));
                Toast.makeText(getApplicationContext(), "Connection available", Toast.LENGTH_SHORT).show();
            }
            if (networkDetails.containsKey("Roaming")) {
                findViewById(R.id.tableRow4).setVisibility(0);
                TextView value = (TextView) findViewById(R.id.roaming);
                value.setText(networkDetails.get("Roaming"));
                Toast.makeText(getApplicationContext(), "Connection available", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /**
     * Get all connection details 1. Status 2. Type: Mobile/wifi 3. Sub type 4.
     * Roaming status (Only for mobile network)
     *
     * @return Map<String, String>
     */
    private Map<String, String> getConnectionDetails() {
        Map<String, String> networkDetails = new HashMap<String, String>();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null && wifiNetwork.isConnected()) {

                networkDetails.put("Type", wifiNetwork.getTypeName());
                networkDetails.put("Sub type", wifiNetwork.getSubtypeName());
                networkDetails.put("State", wifiNetwork.getState().name());
            }

            NetworkInfo mobileNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null && mobileNetwork.isConnected()) {
                networkDetails.put("Type", mobileNetwork.getTypeName());
                networkDetails.put("Sub type", mobileNetwork.getSubtypeName());
                networkDetails.put("State", mobileNetwork.getState().name());
                if (mobileNetwork.isRoaming()) {
                    networkDetails.put("Roaming", "YES");
                } else {
                    networkDetails.put("Roaming", "NO");
                }
            }
        } catch (Exception e) {
            networkDetails.put("Status", e.getMessage());
        }
        return networkDetails;
    }
}
