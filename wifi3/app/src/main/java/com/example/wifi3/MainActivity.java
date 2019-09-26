package com.example.wifi3;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private WifiManager manager;
    private WifiInfo info;
    private TextView ability5GView, connectionDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        show5GAbility();
        showConnectionDetails();
    }

    @TargetApi(21)
    public void show5GAbility() {
        ability5GView = findViewById(R.id.ability5GView);
        info = manager.getConnectionInfo();
        boolean isSupport5G = false;
        try {
            Class cls = Class.forName("android.net.wifi.WifiManager");
            Method method = cls.getMethod("isDualBandSupported");
            Object invoke = method.invoke(manager);
            isSupport5G = (boolean) invoke;
        } catch (Exception e){
            e.printStackTrace();
        }

        ability5GView.setText(String.format("This device%s support  5G",
                isSupport5G ?
                        "" :
                        " doesn't"));
    }

    @TargetApi(21)
    public void showConnectionDetails() {
        connectionDetails = findViewById(R.id.connectionDetails);
        float freq = info.getFrequency() / 1000f;
        float speed = info.getLinkSpeed();
        connectionDetails.setText(String.format("Current connection:\nFrequency: %.1f%s; Speed: %.1f%s",
                freq, "GHz", speed, WifiInfo.LINK_SPEED_UNITS));
    }


}