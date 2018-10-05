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
import vn.edu.poly.myapplication.model.User;

public class UserDAO implements Constant {







    private DatabaseHelper databaseHelper;

    public UserDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertUser(User user) {

        // xin quyen ghi sqlite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        // Su dung ContentValues de dua du lieu vao DB
        ContentValues contentValues = new ContentValues ();
        contentValues.put (COLUMN_USERNAME, user.getUsername ());
        contentValues.put (COLUMN_PASSWORD, user.getPassword ());
        contentValues.put (COLUMN_NAME, user.getName ());
        contentValues.put (COLUMN_PHONE, user.getPhone ());

        long id = sqLiteDatabase.insert (TABLE_USER, null, contentValues);

        if (Contants.isDEBUG) Log.e ("insertUser", "insertUser : " + id);

        sqLiteDatabase.close ();


    }

    public User getUserByUsername(String username) {
        User user = null;
        // xin quyen ghi sqlite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        // query tim kiem user voi username = tham so truyen vao

        // 1: ten bang
        //2 : array cot can lay du lieu
        // 3: ten cot dung de query
        // 4 : gia tri can tim
        // 5,6,7 : null. la cac cau lenh xap xep dieu kien...
        Cursor cursor = sqLiteDatabase.query (TABLE_USER,
                new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_PHONE},
                COLUMN_USERNAME + "=?", new String[]{username},
                null, null, null);


        // neu cursor co gia tri
        if (cursor != null && cursor.moveToFirst ()) {
            String user_name = cursor.getString (cursor.getColumnIndex (COLUMN_USERNAME));
            String password = cursor.getString (cursor.getColumnIndex (COLUMN_PASSWORD));
            String name = cursor.getString (cursor.getColumnIndex (COLUMN_NAME));
            String phone = cursor.getString (cursor.getColumnIndex (COLUMN_PHONE));
            user = new User (user_name, password, name, phone);

        }
        return user;
    }



    public ArrayList<User> getUser() {
        ArrayList<User> users = new ArrayList ();

        SQLiteDatabase db = databaseHelper.getWritableDatabase ();
        Cursor cursor = db.rawQuery ("SELECT * FROM " + TABLE_USER, null);
        cursor.moveToFirst ();

        while (cursor != null && !cursor.isAfterLast ()) {
            String user_name = cursor.getString (cursor.getColumnIndex (COLUMN_USERNAME));
            String password = cursor.getString (cursor.getColumnIndex (COLUMN_PASSWORD));
            String name = cursor.getString (cursor.getColumnIndex (COLUMN_NAME));
            String phone = cursor.getString (cursor.getColumnIndex (COLUMN_PHONE));
            User user = new User (user_name, password, name, phone);

            users.add (user);
            cursor.moveToNext ();
        }

        if (Contants.isDEBUG) Log.e ("getUser", "getUser : " + users.size ());
        db.close ();

        return users;

    }


    public List<User> getAllUser() {

        List<User> userList = new ArrayList<> ();


        //xin quyen doc xu lieu
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        //viet cau lenh select all

        String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = sqLiteDatabase.rawQuery (SELECT_ALL_USERS, null);

        if (cursor.moveToFirst ()) {
            do {

                String user_name = cursor.getString (cursor.getColumnIndex (COLUMN_USERNAME));
                String password = cursor.getString (cursor.getColumnIndex (COLUMN_PASSWORD));
                String name = cursor.getString (cursor.getColumnIndex (COLUMN_NAME));
                String phone = cursor.getString (cursor.getColumnIndex (COLUMN_PHONE));
                User user = new User (user_name, password, name, phone);

                userList.add (user);


            } while (cursor.moveToNext ());
        }

        return userList;

    }


    public void updateUser(User user) {

        //xin quyen doc xu lieu
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        // Su dung ContentValues de dua du lieu vao DB
        ContentValues contentValues = new ContentValues ();
        contentValues.put (COLUMN_USERNAME, user.getUsername ());
        contentValues.put (COLUMN_PASSWORD, user.getPassword ());
        contentValues.put (COLUMN_NAME, user.getName ());
        contentValues.put (COLUMN_PHONE, user.getPhone ());


        sqLiteDatabase.update (TABLE_USER, contentValues, COLUMN_USERNAME + "=?", new String[]{user.getUsername ()});

        sqLiteDatabase.close ();


    }



    public void deleteUser(String username) {
        //xin quyen doc xu lieu
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();
        sqLiteDatabase.delete (TABLE_USER, COLUMN_NAME + "=?", new String[]{username});

        sqLiteDatabase.close ();





    }



}
