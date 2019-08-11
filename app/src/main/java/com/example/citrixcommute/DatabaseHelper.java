package com.example.citrixcommute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "Commute.db";
    public static final String TABLE_NAME = "People";

    public static final String COL1 = "ID";
    public static final String COL2 = "Name";
    public static final String COL3 = "Password";
    public static final String COL4 = "EmailID";
    public static final String COL5 = "HomeAddress";
    public static final String COL6 = "Type";
    public static final String COL7 = "CountOfPassengers";
    public static final String COL8 = "Picked";


    public DatabaseHelper(Context context) {

        super (context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME +
               "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PASSWORD PASSWORD, EMAILID TEXT," +
                "HOMEADDRESS TEXT, TYPE TEXT, COUNTOFPASSENGERS INTEGER, PICKED BOOL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);

    }

    public boolean addData(String name, String password, String email, String address,String type, int count, boolean picked) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, password);
        contentValues.put(COL4, email);
        contentValues.put(COL5, address);
        contentValues.put(COL6, type);
        contentValues.put(COL7, count);
        contentValues.put(COL8, picked);


        Log.d(TAG, "addData: Adding to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else
            return true;
    }

    public Cursor queryData(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select password from "+TABLE_NAME +
                " where EMAILID= '" +name +"'" + "and PASSWORD= '" + password + "'", null);

        return cursor;
    }
}
