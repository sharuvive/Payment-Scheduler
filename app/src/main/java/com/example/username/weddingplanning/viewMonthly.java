package com.example.username.weddingplanning;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * Created by Sharu Vive on 2/28/2016.
 */
public class viewMonthly extends ActionBarActivity {

    EditText etMonth;
    Button btnView;
    ListView lstMonthly;

    SimpleCursorAdapter adapter;
    DBhelper helper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_month);

        helper = new DBhelper(this); //initialize the helper
        etMonth = (EditText)findViewById(R.id.month);
        btnView = (Button)findViewById(R.id.btn_viewPayments);



        lstMonthly = (ListView)findViewById(R.id.lstMonth);

        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String month = etMonth.getText().toString();
                fetchData(month);  //Calling the fetchData method on button click


            }

        });

        //

    }


    private void fetchData(String m) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = helper.getMonthlyPayments(m);
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.row3,
                c,
                new String[]{DBhelper.DATE_T1, DBhelper.DETAIL, DBhelper.AMOUNT1},
                new int[]{R.id.tv_date, R.id.tv_desc, R.id.tv_amount});
        lstMonthly.setAdapter(adapter); //set the adapter to listview
    }


}
