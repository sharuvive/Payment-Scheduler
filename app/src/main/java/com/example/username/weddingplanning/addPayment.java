package com.example.username.weddingplanning;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Sharu Vive on 2/06/2016.
 */


public class addPayment extends ActionBarActivity {
    // Declare UI elements
    DBhelper helper;
    SQLiteDatabase db;
    Spinner spinner;
    EditText description;
    static TextView date;
    EditText rs;
    Spinner sp;
    CheckBox chk;
    Button t1, btnView;
    public static String SELECTED_DATE = null;

    @Override
    /** Called when the activity is first created. */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpayment);

        description = (EditText) findViewById(R.id.et_description);
        rs = (EditText) findViewById(R.id.et_rs);
        date = (TextView) findViewById(R.id.et_date1);
        chk = (CheckBox) findViewById(R.id.chk_clear);
        t1 = (Button) findViewById(R.id.btn_save);
        btnView = (Button) findViewById(R.id.btn_view);
        sp = (Spinner) findViewById(R.id.spinner);

        helper = new DBhelper(this);

        ArrayList<category> mArrayList = helper.getCategories(); //set the category object into the arraylist

        ArrayList<String> catStringArray = new ArrayList<String>();

        for (category cat : mArrayList)
            catStringArray.add(cat.getName()); //get the name from the id

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_row, R.id.tv, catStringArray); //set the items into the adapter
        sp.setAdapter(adapter); //set the adapter in to the spinner

        t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                save();  //Calling the save method on button click
                clear();


            }

        });

        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent mInt = new Intent(addPayment.this, viewMonthly.class);
                startActivity(mInt);


            }

        });

    }


    public static void initializeDate() {
        if (SELECTED_DATE != null) {
            date.setText(SELECTED_DATE);
            date.setVisibility(View.VISIBLE);
        } else {
            date.setVisibility(View.INVISIBLE);
        }
    }

    //method to add expenses to the database
    private void save() {

        String desc = description.getText().toString();
        String Rs = rs.getText().toString();
        String category = sp.getSelectedItem().toString();



        ContentValues values = new ContentValues();
        values.put(DBhelper.CATEGORY, category);
        values.put(DBhelper.AMOUNT1, Rs);
        values.put(DBhelper.DETAIL, desc);
        values.put(DBhelper.DATE_T1, date.getText().toString());
        values.put(DBhelper.EX_YEAR, SELECTED_DATE.split("-")[0]);  //Split the date to get the year
        values.put(DBhelper.EX_MONTH, SELECTED_DATE.split("-")[1]);  //split the date to get month

        //Checking for the validation
        if (isValidnum2(Rs) && isValidWord(desc)) {
            db = helper.getWritableDatabase();
            db.insert(DBhelper.TABLE2, null, values); //Insert values to the database
            db.close();

            Toast.makeText(this, "Payment add Successfully", Toast.LENGTH_LONG).show(); //success message

            AlertDialog.Builder builder = new AlertDialog.Builder(addPayment.this); //Alert dialog box
            builder.setTitle("Confirm");
            builder.setMessage("Do you want to add another Payment?");

            //if we click the yes button
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                    dialog.dismiss();
                }

            });

            //if we click the No button in the alert dialog box
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Redirecting to another activity
                    //addPayment.this.finish();
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();


        } else {
            if (!isValidnum2(Rs)) {
                rs.setError("Invalid Amount"); //set error ,if the validations fails
            }
            if (!isValidWord(desc)) {
                description.setError("Invalid detail");//set error ,if the validations fails
            }


        }

    }

    //clear the edit text fields
    public void clear() {

        rs.setText("");
        description.setText("");
        SELECTED_DATE = null;
        initializeDate();
    }

    public void clear(View view) {
        clear();
    }

    //Method to pick date on button click
    public void pickDate(View v) {
        android.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    public boolean isValidnum2(String e) {
        return e.matches("^(?![.0]*$)\\d+(?:\\.\\d{1,2})?$");
    }

    public boolean isValidWord(String w) {
        return w.matches("[A-Za-z][^.]*");
    }


}