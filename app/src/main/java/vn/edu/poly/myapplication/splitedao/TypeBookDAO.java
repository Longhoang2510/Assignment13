package vn.edu.poly.myapplication.splitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.myapplication.Constant;
import vn.edu.poly.myapplication.common.Contants;
import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.model.TypeBook;


public class TypeBookDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public TypeBookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public long insertTypeBook(TypeBook typeBook) {
        // xin quyen ghi sqlite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // Su dung ContentValues de dua du lieu vao DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COLUMN_ID, typeBook.id);
        contentValues.put(TB_COLUMN_NAME, typeBook.name);
        contentValues.put(TB_COLUMN_DES, typeBook.description);
        contentValues.put(TB_COLUMN_POS, typeBook.position);
        long result = sqLiteDatabase.insert(TABLE_TYPE_BOOK, null, contentValues);

        if (Contants.isDEBUG) Log.e("insertTypeBook", "insertTypeBook : " + result);

        sqLiteDatabase.close();

        return result;

    }

    public long deleteTypeBook(String typeID) {

        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_TYPE_BOOK,
                TB_COLUMN_ID + "=?", new String[]{typeID});

        sqLiteDatabase.close();
        return result;
    }

    public long updateTypeBook(TypeBook typeBook) {

        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // Su dung ContentValues de dua du lieu vao DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COLUMN_ID, typeBook.id);
        contentValues.put(TB_COLUMN_NAME, typeBook.name);
        contentValues.put(TB_COLUMN_DES, typeBook.description);
        contentValues.put(TB_COLUMN_POS, typeBook.position);

        long result = sqLiteDatabase.update(TABLE_TYPE_BOOK, contentValues,
                TB_COLUMN_ID + "=?", new String[]{typeBook.id});

        sqLiteDatabase.close();
        return result;
    }

    public List<TypeBook> getAllTypeBooks() {

        List<TypeBook> typeBooks = new ArrayList<>();

        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all

        String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_TYPE_BOOK;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USERS, null);
        if (cursor.moveToFirst()) {
            do {

                String id = cursor.getString(cursor.getColumnIndex(TB_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));
                String des = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DES));
                String pos = cursor.getString(cursor.getColumnIndex(TB_COLUMN_POS));
                TypeBook typeBook = new TypeBook();

                typeBook.id = id;
                typeBook.name = name;
                typeBook.description = des;
                typeBook.position = pos;

                typeBooks.add(typeBook);

            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return typeBooks;
    }

    public TypeBook getTypeBookByID(String typeID) {

        TypeBook typeBook = null;
        // xin quyen ghi sqlite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // query tim kiem user voi username = tham so truyen vao

        // 1: ten bang
        //2 : array cot can lay du lieu
        // 3: ten cot dung de query
        // 4 : gia tri can tim
        // 5,6,7 : null. la cac cau lenh xap xep dieu kien...
        Cursor cursor = sqLiteDatabase.query(TABLE_TYPE_BOOK,
                new String[]{TB_COLUMN_ID, TB_COLUMN_NAME, TB_COLUMN_DES, TB_COLUMN_POS},
                TB_COLUMN_ID + "=?", new String[]{typeID},
                null, null, null);

        // neu cursor co gia tri
        if (cursor != null && cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndex(TB_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));
            String des = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DES));
            String pos = cursor.getString(cursor.getColumnIndex(TB_COLUMN_POS));
            typeBook = new TypeBook();

            typeBook.id = id;
            typeBook.name = name;
            typeBook.description = des;
            typeBook.position = pos;

        }
        return typeBook;
    }

    public List<String> getNameTypeBook(){

        List<String> stringTypeBooks = new ArrayList<>();

        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all

        String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_TYPE_BOOK;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USERS, null);
        if (cursor.moveToFirst()) {
            do {

                String id = cursor.getString(cursor.getColumnIndex(TB_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));
                String des = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DES));
                String pos = cursor.getString(cursor.getColumnIndex(TB_COLUMN_POS));
                TypeBook typeBook = new TypeBook();

                typeBook.id = id;
                typeBook.name = name;
                typeBook.description = des;
                typeBook.position = pos;

                stringTypeBooks.add(typeBook.name);

            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return stringTypeBooks;

}





}
