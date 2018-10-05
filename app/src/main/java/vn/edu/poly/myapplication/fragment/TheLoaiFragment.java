package vn.edu.poly.myapplication.fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.model.TypeBook;
import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.adapter.TypeBookAdapter;
import vn.edu.poly.myapplication.splitedao.TypeBookDAO;


public class TheLoaiFragment extends Fragment {

    private View tl;

    //list view
    private RecyclerView lvListTypeBooks;


    private TypeBookAdapter adapterTypeBook;
    private List<TypeBook> typeBooks;
    private DatabaseHelper databaseHelper;
    private TypeBookDAO typeBookDAO;
    private LinearLayoutManager linearLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         tl = inflater.inflate (R.layout.fragment_the_loai, container, false);







     //set adpater
        databaseHelper = new DatabaseHelper(getActivity ());
        typeBookDAO = new TypeBookDAO(databaseHelper);

        typeBooks = typeBookDAO.getAllTypeBooks();

        // add sample data
//
//        for (int i = 0; i < 20; i++) {
//            TypeBook typeBook = new TypeBook();
//            typeBook.id = new Random ().nextInt() + "";
//            typeBook.name = "Hello Moto";
//            typeBook.description = " MMMM";
//            typeBook.position = "1" + i;
//
//            typeBooks.add(typeBook);
//
//        }

        lvListTypeBooks = tl.findViewById(R.id.lvListTypeBooks);

        adapterTypeBook = new TypeBookAdapter (getActivity (), typeBooks, typeBookDAO);

        lvListTypeBooks.setAdapter(adapterTypeBook);
        linearLayoutManager = new LinearLayoutManager (getActivity ());
        lvListTypeBooks.setLayoutManager (linearLayoutManager);




        ImageView ivAddtheloai = tl.findViewById (R.id.ivAddTheLoai);



        ivAddtheloai.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DialogThemTL();
            }


        });




        //listTheLoai
//        ListTheLoai();



        return tl;
    }

//    private void ListTheLoai() {
//
//        lvTheLoai = tl.findViewById (R.id.lvTheLoai);
//        theloais = new ArrayList<> ();
//        adapter = new TypeBookAdapter (getActivity (), R.layout.item_theloai, theloais);
//        lvTheLoai.setAdapter (adapter);
//
//        TypeBook tl = new TypeBook ("Giáo dục",7);
//        TypeBook tl1 = new TypeBook ("Viễn Tưởng",5);
//        TypeBook tl2 = new TypeBook ("Khoa học",2);
//        TypeBook tl3 = new TypeBook ("Truyện",15);
//
//        theloais.add (tl);
//        theloais.add (tl2);
//        theloais.add (tl3);
//        theloais.add (tl1);
//
//
//
//
//        lvTheLoai.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener () {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                DiaglogChiTietTheLoai ();
//                return true;
//            }
//        });
//
//
//
//    }


    private void DialogThemTL() {
        final Dialog dialog = new Dialog (getActivity ());
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.dialog_theloai);



        final EditText tvId;
        final EditText tvName;
        final EditText tvPos;
        final EditText tvDes;


        tvId = dialog.findViewById(R.id.tvMatl);
        tvName = dialog.findViewById(R.id.tvNametl);
        tvPos = dialog.findViewById(R.id.tvPos);
        tvDes = dialog.findViewById(R.id.tvDes);

        Button btHuytl = dialog.findViewById (R.id.btHuytl);
        Button btThemtl = dialog.findViewById (R.id.btThemtl);


        btThemtl.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {



                TypeBook typeBook = typeBookDAO.getTypeBookByID(tvId.getText().toString().trim());

                if (typeBook == null) {
                    typeBook = new TypeBook();
                    String id = tvId.getText().toString().trim();
                    String name = tvName.getText().toString().trim();
                    String des = tvDes.getText().toString().trim();
                    String pos = tvPos.getText().toString().trim();
                    typeBook.id = id;
                    typeBook.name = name;
                    typeBook.description = des;
                    typeBook.position = pos;

                    long result = typeBookDAO.insertTypeBook(typeBook);

                    if (result > 0) {
                        // Thong bao thanh cong va tat dialog
                        typeBooks.add(0, typeBook);

                        // f5 list view
                        adapterTypeBook.notifyDataSetChanged();
                        Toast.makeText (getActivity (), "Đã thêm", Toast.LENGTH_SHORT).show ();

                        dialog.cancel();

                    } else {
                        // Thong bao loi xay ra

                        Toast.makeText(getActivity (), "Co Loi Xay Ra!!!!", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    // Thong bao da ton tai, chon id khac
                    Toast.makeText(getActivity (), "ID da ton tai", Toast.LENGTH_SHORT).show();
                    dialog.cancel ();

                }






            }
        });


        btHuytl.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (getActivity (), "Hủy", Toast.LENGTH_SHORT).show ();
                dialog.cancel ();
            }
        });

        //ksich thước của diglog khi hiện lên
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);


//        dialog.show ();
    }





}
