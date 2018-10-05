package vn.edu.poly.myapplication.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.edu.poly.myapplication.R;

public class DetailHolder extends RecyclerView.ViewHolder {

    public TextView tvDetailMaSach;
    public TextView tvDetailSoLuong;
    public TextView tvDetailGiaBia;
    public TextView tvDetailThanhTien;



    public DetailHolder(View itemView) {
        super (itemView);
        tvDetailMaSach = itemView.findViewById (R.id.tvDetailMaSach);
        tvDetailSoLuong = itemView.findViewById (R.id.tvDetailSoLuong);
        tvDetailGiaBia = itemView.findViewById (R.id.tvDetailGiaBia);
        tvDetailThanhTien = itemView.findViewById (R.id.tvDetailThanhTien);

    }
}
