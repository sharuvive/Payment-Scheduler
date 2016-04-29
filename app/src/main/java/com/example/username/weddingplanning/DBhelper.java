package com.example.username.weddingplanning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Sharu Vive on 2/07/2016.
 */
public class DBhelper extends SQLiteOpenHelper {

    static final String DATABASE = "FinanceManageDB.db";
    static final int VERSION = 9;
    static final String TABLE1 = "ExCategoryDB";
    static final String TABLE2 = "PaymentScheduler";

    static final String C_ID = "_id";
    static final String Name = "name";


    public static final String ID1 = "_id";
    public static final String DATE_T1 = "date1";
    public static final String CATEGORY = "category";
    public static final String DETAIL = "detail";
    public static final String AMOUNT1 = "amount1";
    public static final String STATUS = "status";
    public static final String EX_YEAR = "exyear";
    public static final String EX_MONTH = "exmonth";

    public DBhelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE1 + "(" + C_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " text unique not null)");



        db.execSQL("CREATE TABLE " + TABLE2 + " ( "
                + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_T1 + " text, "
                + CATEGORY + " text, "
                + DETAIL + " text, "
                + STATUS + " text, "
                + EX_YEAR + " text, "
                + EX_MONTH + " text, "
                + AMOUNT1 + " text, FOREIGN KEY (" + CATEGORY + ") REFERENCES " + TABLE1 + "(" + Name + "));");

        db.execSQL("insert into " +  TABLE1 +  "(" + Name +") values('Food')");
        db.execSQL("insert into " +  TABLE1 +  "(" + Name +") values('Medicine')");
        db.execSQL("insert into " + TABLE1 + "(" + Name + ") values('Clothing')");


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table " + TABLE1);
        onCreate(db);
    }


    public ArrayList<category> getCategories() {
        ArrayList<category> arrayList = new ArrayList<category>(); //create a araaylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE1, null); //cursor contain collection of data for particula query
        while (c.moveToNext()) {
            category cat = new category(c.getInt(0), c.getString(1)); //get the name and id
            arrayList.add(cat);//assign those name and id to the arraylist

        }

        return arrayList;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE2, null);
        return result;
    }

    public Cursor getAllPaymentsData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE1, null);
        return result;
    }

    //Done button
    public void markDone(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor row = db.rawQuery("select "+DETAIL+"from"+TABLE1+"where "+ID1+"="+id , null);
        String done = row.toString()+"done";
        db.execSQL("update " + TABLE1 + "set " + DETAIL + "=" + done + "where" + ID1 + "=" + id);
    }

    public Cursor getMonthlyPayments(String month)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int monthVal = getMonthInt(month);
        Cursor result = db.rawQuery("select * from " + TABLE2 + "where" +EX_MONTH+ "=" + monthVal, null);
        return result;
    }

    public int getMonthInt(String month)
    {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        int val = 0;
        for(int i=0; i<12; i++){
            if(month.equals(months[i])){
                val = i;
            }
        }
        return val+1;
    }












}