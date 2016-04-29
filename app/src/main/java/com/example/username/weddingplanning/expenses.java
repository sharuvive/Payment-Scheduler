package com.example.username.weddingplanning;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Sharu Vive on 2/08/2016.
 */

public class expenses extends Activity {

    String selected_did = "";
    TabHost tabHost2;
    TextView ed;
    ListView rldlist2;
    SimpleCursorAdapter adapter;
    DBhelper helper;
    SQLiteDatabase db;
    Button btnd;
    String budget;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses);

        helper = new DBhelper(this);

        rldlist2 = (ListView) findViewById(R.id.rldlist2);



        fetchData();


        final TextView menubtn2 = (TextView) findViewById(R.id.e2);
        menubtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent st = new Intent(expenses.this, addPayment.class);
                startActivity(st);
            }
        });



    }


    private void fetchData() {
        db = helper.getReadableDatabase();
        Cursor c = db.query(DBhelper.TABLE1, null, null, null, null, null, null);
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.row,
                c,
                new String[]{DBhelper.Name},
                new int[]{R.id.lblreload});
        rldlist2.setAdapter(adapter);
    }




}