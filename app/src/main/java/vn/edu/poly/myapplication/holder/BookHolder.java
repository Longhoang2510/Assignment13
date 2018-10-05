package vn.edu.poly.myapplication.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import vn.edu.poly.myapplication.R;

public class BookHolder extends RecyclerView.ViewHolder {

    public TextView tvTenSach;
    public TextView tvBookID;
    public TextView tvGia;

    public BookHolder(View itemView) {
        super (itemView);

        tvTenSach = itemView.findViewById (R.id.tvTensach);
        tvGia = itemView.findViewById (R.id.tvGia);
        tvBookID = itemView.findViewById (R.id.tvBookID);

    }
}
