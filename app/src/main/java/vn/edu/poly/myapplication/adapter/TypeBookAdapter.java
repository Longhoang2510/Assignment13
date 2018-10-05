package vn.edu.poly.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.poly.myapplication.holder.HolderTypeBook;
import vn.edu.poly.myapplication.model.TypeBook;
import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.splitedao.TypeBookDAO;

public class    TypeBookAdapter extends RecyclerView.Adapter<HolderTypeBook> {

    private Context context;
    private List<TypeBook> typeBooks;
    private TypeBookDAO typeBookDAO;


    public TypeBookAdapter(Context context, List<TypeBook> typeBooks, TypeBookDAO typeBookDAO) {
        this.context = context;
        this.typeBooks = typeBooks;
        this.typeBookDAO = typeBookDAO;
    }

    @NonNull
    @Override
    public HolderTypeBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_theloai, parent, false);
        return new HolderTypeBook (view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTypeBook holder, final int position) {

        final TypeBook typeBook = typeBooks.get (position);
        holder.tvId.setText (typeBook.id);
        holder.tvName.setText (typeBook.name);

        holder.itemView.setOnLongClickListener (new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View v) {
                DiaglogChiTietTheLoai (position);

                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return typeBooks.size ();
    }


    private void DiaglogChiTietTheLoai(final int position) {
        final TypeBook typeBook = typeBooks.get (position);
        final Dialog dialog = new Dialog (context);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.dialog_chitiettheloai);

        final EditText tvId;
        final EditText tvName;
        final EditText tvPos;
        final EditText tvDes;

        Button btXoatl = dialog.findViewById (R.id.btXoatl);
        Button btcapnhattl = dialog.findViewById (R.id.btcapnhattl);
        Button btcanceltl = dialog.findViewById (R.id.btcanceltl);


        tvId = dialog.findViewById (R.id.edMatl);

        tvName = dialog.findViewById (R.id.edNametl);
        tvPos = dialog.findViewById (R.id.edPos);
        tvDes = dialog.findViewById (R.id.edDes);


        tvName.setText (typeBook.name);

        tvId.setText (typeBook.id);

        tvDes.setText (typeBook.description);

        tvPos.setText (typeBook.position);


        btcapnhattl.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                TypeBook typeBook_ = new TypeBook ();
                String name = tvName.getText ().toString ().trim ();
                String des = tvDes.getText ().toString ().trim ();
                String pos = tvPos.getText ().toString ().trim ();
                typeBook_.id = typeBook.id;
                typeBook_.name = name;
                typeBook_.description = des;
                typeBook_.position = pos;
                long result = typeBookDAO.updateTypeBook (typeBook);
                Toast.makeText (context, result + "", Toast.LENGTH_SHORT).show ();
                if (result > 0) {

                    typeBooks.get (position).id = typeBook.id;

                    typeBooks.get (position).name = name;

                    typeBooks.get (position).description = des;

                    typeBooks.get (position).position = pos;

                    notifyDataSetChanged ();

                    dialog.cancel ();

                } else {
                    // update ko thanh cong
                    Toast.makeText (context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show ();
                }


            }
        });


        btXoatl.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                long result = typeBookDAO.deleteTypeBook (typeBook.id);
                if (result > 0) {
                    String tl = typeBook.name;
                    Toast.makeText (context, "Xóa Thể Loại " + tl, Toast.LENGTH_SHORT).show ();
                    typeBooks.remove (position);
                    notifyDataSetChanged ();
                    dialog.cancel ();

                } else {
                    // ko thanh cong
                    Toast.makeText (context, "Lỗi xóa !!", Toast.LENGTH_SHORT).show ();
                }


            }
        });


        btcanceltl.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.dismiss ();
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
