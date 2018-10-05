package vn.edu.poly.myapplication.fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.model.Book;
import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.adapter.BookAdapter;
import vn.edu.poly.myapplication.model.TypeBook;
import vn.edu.poly.myapplication.splitedao.BookDAO;
import vn.edu.poly.myapplication.splitedao.TypeBookDAO;


public class SachFragment extends Fragment {

    private View sach;

    //listview

    private RecyclerView lvListBooks;
    private DatabaseHelper databaseHelper;
    private List<Book> books;
    private BookAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private BookDAO bookDAO;

    private String TenTheLoai;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sach = inflater.inflate (R.layout.fragment_sach, container, false);


        ImageView ivAdd = sach.findViewById (R.id.ivAdd);


        ivAdd.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DialogThemSach ();
            }


        });

        databaseHelper = new DatabaseHelper (getActivity ());
        bookDAO = new BookDAO (databaseHelper);
        books = bookDAO.getAllBook ();

//        for (int i = 0; i < 20; i++) {
//            Book book = new Book ();
//            book.MaSach = new Random ().nextInt () + "";
//            book.MaTheLoai = "001";
//            book.TenSach = "Sach";
//            book.NXB = " MMMM";
//            book.GiaBia = "1" + i;
//            book.SoLuong = i + "";
//
//            books.add (book);
//
//        }


        lvListBooks = sach.findViewById (R.id.lvListBooks);

        adapter = new BookAdapter (getActivity (), books, bookDAO);

        lvListBooks.setAdapter (adapter);
        linearLayoutManager = new LinearLayoutManager (getActivity ());
        lvListBooks.setLayoutManager (linearLayoutManager);


        //listview


        return sach;
    }


    private void DialogThemSach() {
        final Dialog dialog = new Dialog (getActivity ());
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.dialog_themsach);


        final Spinner spMaTheLoai;
        final EditText edMaSach;
        final EditText edTenSach;
        final EditText edTacGia;
        final EditText edNXB;
        final EditText edGiaBia;
        final EditText edSoLuong;


        //xu ly spnierr
        final TypeBookDAO manager;


        Button btHuy = dialog.findViewById (R.id.btHuy);
        Button btThem = dialog.findViewById (R.id.btThem);


        spMaTheLoai = dialog.findViewById (R.id.spTheLoai);
        edMaSach = dialog.findViewById (R.id.edMaSach);
        edTenSach = dialog.findViewById (R.id.edTenSach);
        edTacGia = dialog.findViewById (R.id.edTacGia);
        edNXB = dialog.findViewById (R.id.edNXB);
        edGiaBia = dialog.findViewById (R.id.edGiaBia);
        edSoLuong = dialog.findViewById (R.id.edSoLuong);


        //xu ly spnerr
        manager = new TypeBookDAO (databaseHelper);

        List<String> list = new ArrayList<> ();


        list = manager.getNameTypeBook ();

//        list.add("Thiếu nhi");
//        list.add("Android");
//        list.add("PHP");
//        list.add("C#");
//        list.add("ASP.NET");


        final ArrayAdapter<String> adapter = new ArrayAdapter (getActivity (), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource (android.R.layout.simple_list_item_single_choice);
        spMaTheLoai.setAdapter (adapter);


        spMaTheLoai.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TenTheLoai = spMaTheLoai.getSelectedItem ()+"";
                Toast.makeText (getActivity (), spMaTheLoai.getSelectedItem ()+"", Toast.LENGTH_SHORT).show ();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btThem.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                Book book = bookDAO.getBookByID (edMaSach.getText ().toString ().trim ());

                if (book == null) {
                    book = new Book ();
                    String maTheLoai = TenTheLoai.trim ();
                    String maSach = edMaSach.getText ().toString ().trim ();
                    String tenSach = edTenSach.getText ().toString ().trim ();
                    String giaBia = edGiaBia.getText ().toString ().trim ();
                    String nxb = edNXB.getText ().toString ().trim ();
                    String tacGia = edTacGia.getText ().toString ().trim ();
                    String soLuong = edSoLuong.getText ().toString ().trim ();

                    book.MaTheLoai = maTheLoai;
                    book.MaSach = maSach;
                    book.TenSach = tenSach;
                    book.GiaBia = giaBia;
                    book.NXB = nxb;
                    book.TacGia = tacGia;
                    book.SoLuong = soLuong;

                    long result = bookDAO.insertBook (book);

                    if (result > 0) {
                        // Thong bao thanh cong va tat dialog
                        books.add (0, book);

                        // f5 list view
                        adapter.notifyDataSetChanged ();
                        Toast.makeText (getActivity (), "Đã thêm", Toast.LENGTH_SHORT).show ();

                        dialog.cancel ();

                    } else {
                        // Thong bao loi xay ra

                        Toast.makeText (getActivity (), "Co Loi Xay Ra!!!!", Toast.LENGTH_SHORT).show ();
                    }

                } else {

                    // Thong bao da ton tai, chon id khac
                    Toast.makeText (getActivity (), "ID da ton tai", Toast.LENGTH_SHORT).show ();
                    dialog.cancel ();

                }

            }
        });


        btHuy.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (getActivity (), "Hủy", Toast.LENGTH_SHORT).show ();
                dialog.cancel ();
            }
        });

        //ksich thước của diglog khi hiện lên
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams ();
        lp.copyFrom (dialog.getWindow ().getAttributes ());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show ();
        dialog.getWindow ().setAttributes (lp);


//        dialog.show ();


    }


}
