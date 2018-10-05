package vn.edu.poly.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.myapplication.Constant;
import vn.edu.poly.myapplication.model.User;
import vn.edu.poly.myapplication.common.Contants;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant{




    public DatabaseHelper(Context context) {
        super (context, "BookManger01", null, 1);
    }






    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL (CREATE_TABLE_USER);
        sqLiteDatabase.execSQL (CREATE_TABLE_TYPE_BOOK);
        sqLiteDatabase.execSQL (CREATE_TABLE_BOOK);
        sqLiteDatabase.execSQL (CREATE_TABLE_BILL);
        sqLiteDatabase.execSQL (CREATE_TABLE_DETAIL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL ("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL ("DROP TABLE IF EXISTS " + TABLE_TYPE_BOOK);
        sqLiteDatabase.execSQL ("DROP TABLE IF EXISTS " + TABLE_BOOK);
        sqLiteDatabase.execSQL ("DROP TABLE IF EXISTS " + TABLE_BILL);
        sqLiteDatabase.execSQL ("DROP TABLE IF EXISTS " + TABLE_DETAIL);

        onCreate (sqLiteDatabase);

    }




}

