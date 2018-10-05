package vn.edu.poly.myapplication;

public interface Constant {

    //table user

    String TABLE_USER = "USER";

    String COLUMN_USERNAME = "Username";
    String COLUMN_PASSWORD = "Password";
    String COLUMN_NAME = "Name";
    String COLUMN_PHONE = "PhoneNumber";


    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USERNAME + " VARCHAR PRIMARY KEY," +
            COLUMN_PASSWORD + " VARCHAR," +
            COLUMN_NAME + " VARCHAR," +
            COLUMN_PHONE + " VARCHAR" +
            ")";


    //table boook

    //CREAT TABLE TypeBook (MaTheLoai  CHAR(5) PRIMARY KEY NOT NULL;
    //TYPENAME NVARCHAR(50) NOT NULL;
    //Position


    String TABLE_TYPE_BOOK = "TypeBook";

    String TB_COLUMN_ID = "MaTheLoai";
    String TB_COLUMN_NAME = "TypeName";
    String TB_COLUMN_DES = "Description";
    String TB_COLUMN_POS = "Position";

    String CREATE_TABLE_TYPE_BOOK = "CREATE TABLE " + TABLE_TYPE_BOOK + "(" +
            "" + TB_COLUMN_ID + " PRIMARY KEY NOT NULL," +
            "" + TB_COLUMN_NAME + " NVARCHAR(50) NOT NULL," +
            "" + TB_COLUMN_DES + " NVARCHAR(255)," +
            "" + TB_COLUMN_POS + " INT" +
            ")";


    //table book

    //CREATE TABLE Book (MaSach NCHAR(5) RIMARY KEY NOT NULL;
    //MaTheLoai   NCHAR(50)   FK,  NOT  NULL
    String TABLE_BOOK = "Book";

    String TB_COLUMN_MASACH = "MaSach";
    String TB_COLUMN_MATHELOAI = "MaTheLoai";
    String TB_COLUMN_TENSACH = "TenSach";
    String TB_COLUMN_TACGIA = "TacGia";
    String TB_COLUMN_NXB = "NXB";
    String TB_COLUMN_GIABIA = "GiaBia";
    String TB_COLUMN_SL = "SoLuong";


    String CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_BOOK + "(" +
            "" + TB_COLUMN_MASACH + " PRIMARY KEY NOT NULL," +
            "" + TB_COLUMN_MATHELOAI + " NVARCHAR(50)," +
            "" + TB_COLUMN_TENSACH + " NVARCHAR(50) NOT NULL," +
            "" + TB_COLUMN_TACGIA + " NVARCHAR(50)," +
            "" + TB_COLUMN_NXB + " NVARCHAR(50)," +
            "" + TB_COLUMN_GIABIA + " FLOAT NOT NULL," +
            "" + TB_COLUMN_SL + " INT NOT NULL" +
            ")";


    //table bill
    //create table bill (MaHoaDon NVARCHAR(7) NOT NULL, NgayMua LONG NOT NULL

    String TABLE_BILL = "Bill";
    String B_ID = "MaHoaDon";
    String B_DATE = "NgayMua";

    String CREATE_TABLE_BILL = "CREATE TABLE " + TABLE_BILL + "(" +
            "" + B_ID + " NCHAR(7) NOT NULL," +
            "" + B_DATE + " LONG NOT NULL" +
            ")";



    //tabele detaiBills
    String TABLE_DETAIL ="Detail";
    String B_DETAIL_ID = "MaHoaDonChiTiet";
    String B_BILL_ID ="MaHoaDon";
    String B_BOOK_ID = "MaSach";
    String B_DETAIL_SL = "SoLuongMua";

    String CREATE_TABLE_DETAIL = "CREATE TABLE " + TABLE_DETAIL + "(" +
            "" + B_DETAIL_ID + " INT PRIMARY KEY," +
            "" + B_BILL_ID  + " NCHAR(7) NOT NULL," +
            "" + B_BOOK_ID + " NCHAR(5) NOT NULL," +
            "" + B_DETAIL_SL + " INT NOT NULL" +
            ")";


}
