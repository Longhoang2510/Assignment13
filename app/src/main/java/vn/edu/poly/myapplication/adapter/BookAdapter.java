package vn.edu.poly.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.holder.BookHolder;
import vn.edu.poly.myapplication.model.Book;
import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.splitedao.BookDAO;
import vn.edu.poly.myapplication.splitedao.TypeBookDAO;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    private Context context;
    private List<Book> books;
    private BookDAO bookDAO;


    public BookAdapter(Context context, List<Book> books, BookDAO bookDAO) {
        this.context = context;
        this.books = books;
        this.bookDAO = bookDAO;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_sach, parent, false);
        return new BookHolder (view);

    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, final int position) {
        final Book book = books.get (position);
        holder.tvTenSach.setText (book.TenSach);
        holder.tvBookID.setText (book.MaSach);
        holder.tvGia.setText (book.GiaBia);


        holder.itemView.setOnLongClickListener (new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View v) {
                DialogChitiet (position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size ();
    }


    private void DialogChitiet(final int position) {
        final Book book = books.get (position);
        final Dialog dialog = new Dialog (context);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.dialog_thongtinsach);


        final Spinner spMaTheLoaict;
        final EditText edMaSachct;
        final EditText edTenSachct;
        final EditText edTacGiact;
        final EditText edNXBct;
        final EditText edGiaBiact;
        final EditText edSoLuongct;
        final String[] stTenTheLoai = new String[1];

        Button btXoa = dialog.findViewById (R.id.btXoa);
        Button btcapnhat = dialog.findViewById (R.id.btcapnhat);
        Button btCancel = dialog.findViewById (R.id.btCancel);


        spMaTheLoaict = dialog.findViewById (R.id.spMaTheLoaict);
        edMaSachct = dialog.findViewById (R.id.edMaSachct);
        edTenSachct = dialog.findViewById (R.id.edTenSachct);
        edTacGiact = dialog.findViewById (R.id.edTacGiact);
        edNXBct = dialog.findViewById (R.id.edNXBct);
        edGiaBiact = dialog.findViewById (R.id.edGiaBiact);
        edSoLuongct = dialog.findViewById (R.id.edSoLuongct);


        edMaSachct.setText (book.MaSach);
        edTenSachct.setText (book.TenSach);
        edTacGiact.setText (book.TacGia);
        edNXBct.setText (book.NXB);
        edGiaBiact.setText (book.GiaBia);
        edSoLuongct.setText (book.SoLuong);


        //xu ly Spiner
        final TypeBookDAO manager;
        final DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper (context);
        manager = new TypeBookDAO (databaseHelper);

        List<String> list = new ArrayList<> ();


        list = manager.getNameTypeBook ();

//        list.add("Thiếu nhi");
//        list.add("Android");
//        list.add("PHP");
//        list.add("C#");
//        list.add("ASP.NET");


        final ArrayAdapter<String> adapter = new ArrayAdapter (context, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource (android.R.layout.simple_list_item_single_choice);
        spMaTheLoaict.setAdapter (adapter);

        if (list != null) {

            for (int i = 0; i < list.size (); i++) {

                if (list.get (i).equals (book.MaTheLoai)) {
                    int index = i;
                    spMaTheLoaict.setSelection (index);
                    break;
                }

            }

        }


        spMaTheLoaict.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stTenTheLoai[0] = spMaTheLoaict.getSelectedItem () + "";
                Toast.makeText (context, stTenTheLoai[0], Toast.LENGTH_SHORT).show ();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btcapnhat.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Book book1 = new Book ();
                String tenTheLoai = stTenTheLoai[0].toString ().trim ();
                String tenSach = edTenSachct.getText ().toString ().trim ();
                String tacGia = edTacGiact.getText ().toString ().trim ();
                String nxb = edNXBct.getText ().toString ().trim ();
                String giaBia = edGiaBiact.getText ().toString ().trim ();
                String soLuong = edSoLuongct.getText ().toString ().trim ();

                book1.MaSach = book.MaSach;
                book1.MaTheLoai = tenTheLoai;
                book1.TenSach = tenSach;
                book1.TacGia = tacGia;
                book1.NXB = nxb;
                book1.GiaBia = giaBia;
                book1.SoLuong = soLuong;


                long result = bookDAO.updateBook (book);
                Toast.makeText (context, result + "", Toast.LENGTH_SHORT).show ();
                if (result > 0) {

                    books.get (position).MaSach = book.MaSach;

                    books.get (position).MaTheLoai = tenTheLoai;

                    books.get (position).TenSach = tenSach;

                    books.get (position).TacGia = tacGia;

                    books.get (position).NXB = nxb;

                    books.get (position).GiaBia = giaBia;

                    books.get (position).SoLuong = soLuong;


                    notifyDataSetChanged ();

                    dialog.cancel ();

                } else {
                    // update ko thanh cong
                    Toast.makeText (context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show ();
                }


            }
        });


        btXoa.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                long result = bookDAO.deleteBook (book.MaSach);
                Toast.makeText (context, ""+result, Toast.LENGTH_SHORT).show ();
                if (result > 0) {
                    String sach = book.TenSach;
                    Toast.makeText (context, "Xóa Sách " + sach, Toast.LENGTH_SHORT).show ();
                    books.remove (position);
                    notifyDataSetChanged ();
                    dialog.cancel ();

                } else {
                    // ko thanh cong
                    Toast.makeText (context, "Lỗi xóa !!", Toast.LENGTH_SHORT).show ();
                }

            }
        });


        btCancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
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
