package com.example.username.weddingplanning;


import android.os.Bundle;
import android.widget.TabHost;
import android.app.TabActivity;
import android.content.Intent;

public class MainActivity extends TabActivity {

    DBhelper helper;
    public static TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabHost = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("1");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("2");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Add Payment");
        Intent intent = new Intent(MainActivity.this, addPayment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab1.setContent(new Intent(intent));

        tab2.setIndicator("View Payment");
        Intent intent2 = new Intent(MainActivity.this, Expenses_activity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab2.setContent(new Intent(intent2));


        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

    }
}