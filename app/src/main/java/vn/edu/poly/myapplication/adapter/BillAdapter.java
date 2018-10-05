package vn.edu.poly.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import vn.edu.poly.myapplication.DetailBills;
import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.holder.BillHolder;
import vn.edu.poly.myapplication.model.Bill;
import vn.edu.poly.myapplication.splitedao.BillDAO;

public class BillAdapter extends RecyclerView.Adapter<BillHolder> {

    private Context context;
    private List<Bill> billList;
    private BillDAO billDAO;

    public BillAdapter(Context context, List<Bill> billList, BillDAO billDAO) {
        this.context = context;
        this.billList = billList;
        this.billDAO = billDAO;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_hoadon, parent, false);
        return new BillHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, final int position) {

        final Bill bill = billList.get (position);
        holder.id.setText (bill.id);
        holder.date.setText (bill.date+"");

        holder.imgDelBill.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                long result = billDAO.delBill (bill.id);
                if(result > 0){
                    Toast.makeText (context, "Đã xóa", Toast.LENGTH_SHORT).show ();
                    billList.remove (position);
                    notifyDataSetChanged ();
                }else{
                    Toast.makeText (context, "Lỗi xóa !!", Toast.LENGTH_SHORT).show ();
                }

            }
        });


        holder.itemView.setOnLongClickListener (new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent (context, DetailBills.class);
                context.startActivity (intent);
                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return billList.size ();
    }


}
