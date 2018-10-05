package vn.edu.poly.myapplication.splitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.myapplication.Constant;
import vn.edu.poly.myapplication.common.Contants;
import vn.edu.poly.myapplication.database.DatabaseHelper;

import vn.edu.poly.myapplication.model.Detail;

public class DetailDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public DetailDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertDetail(Detail detail) {
        // xin quyen ghi sqlite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // Su dung ContentValues de dua du lieu vao DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(B_DETAIL_ID, detail.id_Detail);
        contentValues.put(B_BILL_ID, detail.id_detail_bill);
        contentValues.put(B_BOOK_ID, detail.id_detail_book);
        contentValues.put(B_DETAIL_SL, detail.detail_sl);

        long result = sqLiteDatabase.insert(TABLE_DETAIL, null, contentValues);

        if (Contants.isDEBUG) Log.e("Detail", "Detail : " + result);

        sqLiteDatabase.close();

        return result;

    }

    public long deleteTypeBook(String typeID) {

        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_DETAIL,
                B_DETAIL_ID + "=?", new String[]{typeID});
        sqLiteDatabase.close();
        return result;
    }




    public List<Detail> getAllDetail() {

        List<Detail> details = new ArrayList<> ();

        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all

        String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_DETAIL;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USERS, null);
        if (cursor.moveToFirst()) {
            do {

                int id_detail = cursor.getInt (cursor.getColumnIndex(B_DETAIL_ID));
                String id_bill = cursor.getString(cursor.getColumnIndex(B_BILL_ID));
                String id_book = cursor.getString(cursor.getColumnIndex(B_BOOK_ID));
                int soLuong = cursor.getInt (cursor.getColumnIndex(B_DETAIL_SL));
                Detail detail = new Detail ();

                detail.id_Detail = id_detail;
                detail.id_detail_bill = id_bill;
                detail.id_detail_book = id_book;
                detail.detail_sl =  soLuong ;

                details.add (detail);

            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return details;
    }




    public float getGiaBiaByID(String typeID) {

        float giaBia = 0;
        // xin quyen ghi sqlite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // query tim kiem user voi username = tham so truyen vao

        // 1: ten bang
        //2 : array cot can lay du lieu
        // 3: ten cot dung de query
        // 4 : gia tri can tim
        // 5,6,7 : null. la cac cau lenh xap xep dieu kien...
        Cursor cursor = sqLiteDatabase.query(TABLE_BOOK,
                new String[]{TB_COLUMN_GIABIA},
                TB_COLUMN_MASACH+ "=?", new String[]{typeID},
                null, null, null);

        // neu cursor co gia tri
        if (cursor != null && cursor.moveToFirst()) {
            float giabia = cursor.getFloat (cursor.getColumnIndex(TB_COLUMN_GIABIA));

            giaBia = giabia;

        }
        return giaBia;
    }









}
