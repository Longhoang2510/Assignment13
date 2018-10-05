package vn.edu.poly.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.holder.DetailHolder;
import vn.edu.poly.myapplication.model.Detail;
import vn.edu.poly.myapplication.splitedao.BookDAO;
import vn.edu.poly.myapplication.splitedao.DetailDAO;

public class DetailAdapter extends RecyclerView.Adapter<DetailHolder> {

    private Context context;
    private List<Detail> details;
    private DetailDAO detailDAO;

    private BookDAO bookDAO;


    public DetailAdapter(Context context, List<Detail> details, DetailDAO detailDAO) {
        this.context = context;
        this.details = details;
        this.detailDAO = detailDAO;
    }


    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_detail, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {

        final Detail detail = details.get (position);
        holder.tvDetailMaSach.setText (detail.id_detail_book);
        holder.tvDetailSoLuong.setText (detail.detail_sl);

        float price = bookDAO.getGiaBiaByID (detail.id_detail_book);

        holder.tvDetailGiaBia.setText (price+"");

        float thanhTien = price*detail.detail_sl;

        holder.tvDetailThanhTien.setText (thanhTien+"");



    }

    @Override
    public int getItemCount() {
            return details.size ();
    }
}
