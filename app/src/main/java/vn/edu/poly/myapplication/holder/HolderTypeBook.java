package vn.edu.poly.myapplication.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.edu.poly.myapplication.R;

public class HolderTypeBook extends RecyclerView.ViewHolder {
    public TextView tvId;
    public TextView tvName;


    public HolderTypeBook(View itemView) {
        super(itemView);

        tvId = itemView.findViewById(R.id.tvId);
        tvName = itemView.findViewById(R.id.tvTentl);


    }

}
