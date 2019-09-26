package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.*;
import android.widget.*;
public class MainActivity extends AppCompatActivity {
    private WifiManager wifiManager;
    private ListView listView;
    private ArrayAdapter adapter;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private int size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClick();
            }

        });

            listView = findViewById(R.id.list1);
            wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (!wifiManager.isWifiEnabled()) {
                Toast.makeText(this, "Error!Unavailable!", Toast.LENGTH_SHORT).show();
                wifiManager.setWifiEnabled(true);
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
            startScan();
        }

        public void myClick() {
            startScan();
        }
        private void startScan() {
            arrayList.clear();
            registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        }

        BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                results = wifiManager.getScanResults();
                Comparator<ScanResult> compare = new Comparator<ScanResult>() {
                    @Override
                    public int compare(ScanResult o1, ScanResult o2) {
                        if (o1.level >= o2.level) {
                            return -1;
                        }
                        return 1;
                    }
                };





















                }
        };

































}

