package vn.edu.poly.myapplication.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.myapplication.R;

public class BillHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public  TextView date;
        public ImageView imgDelBill;

    public BillHolder(View itemView) {
        super (itemView);

        id = itemView.findViewById (R.id.tvIdBill);
        date = itemView.findViewById (R.id.tvDateBill);
        imgDelBill = itemView.findViewById (R.id.imgDelBill);
    }
}
