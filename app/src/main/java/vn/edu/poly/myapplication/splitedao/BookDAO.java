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
import vn.edu.poly.myapplication.model.Book;

public class BookDAO implements Constant {

    private DatabaseHelper databaseHelper;


    public BookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public long insertBook(Book book){

        // xin quyen ghi sqlite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // Su dung ContentValues de dua du lieu vao DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COLUMN_MASACH, book.MaSach);
        contentValues.put(TB_COLUMN_MATHELOAI, book.MaTheLoai);
        contentValues.put(TB_COLUMN_TENSACH, book.TenSach);
        contentValues.put(TB_COLUMN_TACGIA, book.TacGia);
        contentValues.put(TB_COLUMN_NXB, book.NXB);
        contentValues.put(TB_COLUMN_GIABIA, book.GiaBia);
        contentValues.put(TB_COLUMN_SL, book.SoLuong);

        long result = sqLiteDatabase.insert(TABLE_BOOK, null, contentValues);

        if (Contants.isDEBUG) Log.e("insertBook", "insertBook : " + result);

        sqLiteDatabase.close();

        return result;

    }


    public long deleteBook(String bookID){

        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_BOOK,
                TB_COLUMN_MASACH + "=?", new String[]{bookID});

        sqLiteDatabase.close();
        return result;

    }


    public long updateBook(Book book){


        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // Su dung ContentValues de dua du lieu vao DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COLUMN_MASACH, book.MaSach);
        contentValues.put(TB_COLUMN_MATHELOAI, book.MaTheLoai);
        contentValues.put(TB_COLUMN_TENSACH, book.TenSach);
        contentValues.put(TB_COLUMN_TACGIA, book.TacGia);
        contentValues.put(TB_COLUMN_NXB, book.NXB);
        contentValues.put(TB_COLUMN_GIABIA, book.GiaBia);
        contentValues.put(TB_COLUMN_SL, book.SoLuong);

        long result = sqLiteDatabase.update(TABLE_BOOK, contentValues,
                TB_COLUMN_MASACH + "=?", new String[]{book.MaSach});

        sqLiteDatabase.close();
        return result;

    }

    public List<Book> getAllBook(){

        List<Book> Books = new ArrayList<> ();

        // xin quyen doc du lieu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all

        String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_BOOK;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USERS, null);
        if (cursor.moveToFirst()) {
            do {

                String masach = cursor.getString(cursor.getColumnIndex(TB_COLUMN_MASACH));
                String matheloai = cursor.getString(cursor.getColumnIndex(TB_COLUMN_MATHELOAI));
                String tensach = cursor.getString(cursor.getColumnIndex(TB_COLUMN_TENSACH));
                String tacgia = cursor.getString(cursor.getColumnIndex(TB_COLUMN_TACGIA));
                String nxb = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NXB));
                String giabia = cursor.getString(cursor.getColumnIndex(TB_COLUMN_GIABIA));
                String soluong = cursor.getString(cursor.getColumnIndex(TB_COLUMN_SL));
                Book book = new Book ();

                book.MaSach = masach;
                book.MaTheLoai = matheloai;
                book.TenSach = tensach;
                book.TacGia = tacgia;
                book.NXB = nxb;
                book.GiaBia = giabia;
                book.SoLuong = soluong;

                Books.add(book);

            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return Books;


    }


    public Book getBookByID(String bookID){

        Book book = null;
        // xin quyen ghi sqlite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // query tim kiem user voi username = tham so truyen vao

        // 1: ten bang
        //2 : array cot can lay du lieu
        // 3: ten cot dung de query
        // 4 : gia tri can tim
        // 5,6,7 : null. la cac cau lenh xap xep dieu kien...
        Cursor cursor = sqLiteDatabase.query(TABLE_BOOK,
                new String[]{TB_COLUMN_MASACH, TB_COLUMN_MATHELOAI, TB_COLUMN_TENSACH, TB_COLUMN_TACGIA, TB_COLUMN_NXB, TB_COLUMN_GIABIA, TB_COLUMN_SL},
                TB_COLUMN_MASACH + "=?", new String[]{bookID},
                null, null, null);

        // neu cursor co gia tri
        if (cursor != null && cursor.moveToFirst()) {
            String masach = cursor.getString(cursor.getColumnIndex(TB_COLUMN_MASACH));
            String matheloai = cursor.getString(cursor.getColumnIndex(TB_COLUMN_MATHELOAI));
            String tensach = cursor.getString(cursor.getColumnIndex(TB_COLUMN_TENSACH));
            String tacgia = cursor.getString(cursor.getColumnIndex(TB_COLUMN_TACGIA));
            String nxb = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NXB));
            String giabia = cursor.getString(cursor.getColumnIndex(TB_COLUMN_GIABIA));
            String soluong = cursor.getString(cursor.getColumnIndex(TB_COLUMN_SL));
            book = new Book ();

            book.MaSach = masach;
            book.MaTheLoai = matheloai;
            book.TenSach = tensach;
            book.TacGia = tacgia;
            book.NXB = nxb;
            book.GiaBia = giabia;
            book.SoLuong = soluong;

        }
        return book;


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
