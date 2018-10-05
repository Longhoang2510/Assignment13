package vn.edu.poly.myapplication.fragment;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.adapter.BillAdapter;
import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.model.Bill;
import vn.edu.poly.myapplication.splitedao.BillDAO;


public class HoaDonFragment extends Fragment {

    private View hd;

    //listview
    private RecyclerView rcvBill;
    private BillAdapter adapterBill;
    private List<Bill> bills;
    private DatabaseHelper databaseHelper;
    private BillDAO billDAO;
    private LinearLayoutManager linearLayoutManager;


    //



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        hd = inflater.inflate (R.layout.fragment_hoa_don, container, false);


        //set adapter
        databaseHelper = new DatabaseHelper (getActivity ());
        billDAO = new BillDAO (databaseHelper);

        bills = billDAO.getAllBills ();

        Bill bill = new Bill ("123", 4);

        bills.add (bill);

        rcvBill = hd.findViewById (R.id.rcvBill);

        adapterBill = new BillAdapter (getActivity (), bills, billDAO);

        rcvBill.setAdapter (adapterBill);
        linearLayoutManager = new LinearLayoutManager (getActivity ());
        rcvBill.setLayoutManager (linearLayoutManager);


        //add bill
        ImageView imvAddBill = hd.findViewById (R.id.imvAddBill);

        imvAddBill.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DialogAddBill ();
            }
        });


        return hd;

    }

    private void DialogAddBill() {

        final Dialog dialog = new Dialog (getActivity ());
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.dialog_add_bill);


        final EditText edIDBill;
        final TextView tvDateBill;


        edIDBill = dialog.findViewById (R.id.edAddIDBill);
        tvDateBill = dialog.findViewById (R.id.tvAddDateBill);

        Button btnOpenDatePicker = dialog.findViewById (R.id.btnOpenDatePicker);
        Button btnAddBill = dialog.findViewById (R.id.btnAddBill);


        btnOpenDatePicker.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                showDatePicker (tvDateBill);
            }
        });



        btnAddBill.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Bill bill = billDAO.getItemByID (edIDBill.getText ().toString ().trim ());
                if(bill == null){

                    String idBill = edIDBill.getText ().toString ().trim ();
                    long date = 1;


                    bill = new Bill (idBill, date);

                    long result = billDAO.insertBill (bill);

                    if (result > 0) {
                        // Thong bao thanh cong va tat dialog
                        bills.add (0, bill);


                        // f5 list view
                        adapterBill.notifyDataSetChanged();
                        Toast.makeText (getActivity (), "Đã thêm", Toast.LENGTH_SHORT).show ();

                        dialog.cancel();

                    } else {
                        // Thong bao loi xay ra

                        Toast.makeText(getActivity (), "Co Loi Xay Ra!!!!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText (getActivity (), "ID đã tồn tại", Toast.LENGTH_SHORT).show ();
                    dialog.cancel ();
                }





            }
        });




        //ksich thước của diglog khi hiện lên
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams ();
        lp.copyFrom (dialog.getWindow ().getAttributes ());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show ();
        dialog.getWindow ().setAttributes (lp);


    }


    private void showDatePicker(final TextView tvDate) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // thiet lap thong tin cho date picker

        DatePickerDialog datePicker = new DatePickerDialog(getActivity (), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Integer yy = year ;
                Integer mm = month;
                Integer dd = dayOfMonth;

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                //
                long startTime = calendar.getTimeInMillis();

                tvDate.setText(new Date(startTime).toString());


            }
        }, year, month, day);

        datePicker.show();

    }


}
