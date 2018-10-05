package vn.edu.poly.myapplication.splitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.myapplication.Constant;
import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.model.Bill;

public class BillDAO implements Constant {

    public DatabaseHelper databaseHelper;

    public BillDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertBill(Bill bill) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        ContentValues contentValues = new ContentValues ();
        contentValues.put (B_ID, bill.id);
        contentValues.put (B_DATE, bill.date);

        long result = sqLiteDatabase.insert (TABLE_BILL, null, contentValues);


        sqLiteDatabase.close ();

        return result;
    }

    public long uptadeBill(Bill bill) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        ContentValues contentValues = new ContentValues ();
        contentValues.put (B_DATE, bill.date);

        long result = sqLiteDatabase.update (TABLE_BILL, contentValues, B_ID + "=?", new String[]{bill.id});


        sqLiteDatabase.close ();


        return result;
    }

    public long delBill(String bill) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        long result = sqLiteDatabase.delete (TABLE_BILL, B_ID + "=?", new String[]{bill});

        sqLiteDatabase.close ();

        return result;


    }

    public List<Bill> getAllBills() {

        List<Bill> bills = new ArrayList<> ();

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_BILL;
        Cursor cursor = sqLiteDatabase.rawQuery (SELECT_ALL_USERS, null);
        if(cursor.moveToFirst ()){
            do{

                String id = cursor.getString (cursor.getColumnIndex (B_ID));
                long date = cursor.getLong (cursor.getColumnIndex (B_DATE));

                Bill bill = new Bill (id, date);

                bills.add (bill);




            }while (cursor.moveToNext ());


        }

        sqLiteDatabase.close ();

            return bills;
    }


    public Bill getItemByID(String billID){

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase ();

        Bill bill = null;

        Cursor cursor = sqLiteDatabase.query(TABLE_BILL,
                new String[]{B_ID, B_DATE}, B_ID + "=?",
                new String[]{billID}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            String id = cursor.getString(cursor.getColumnIndex(B_ID));
            long date = cursor.getLong(cursor.getColumnIndex(B_DATE));
            bill = new Bill (id, date);

        }

        sqLiteDatabase.close ();

        return bill;
    }


}
