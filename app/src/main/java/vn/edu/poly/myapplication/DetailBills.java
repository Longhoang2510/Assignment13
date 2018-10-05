package vn.edu.poly.myapplication;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import java.util.List;

import vn.edu.poly.myapplication.adapter.DetailAdapter;
import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.model.Detail;
import vn.edu.poly.myapplication.splitedao.DetailDAO;

public class DetailBills extends AppCompatActivity {

    private RecyclerView rcvDetail;

    private DetailAdapter detailAdapter;
    private List<Detail> details;
    private DatabaseHelper databaseHelper;
    private DetailDAO detailDAO;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail_bills);
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);


        //setAdapter
        //set adpater
        databaseHelper = new DatabaseHelper (this);
        detailDAO = new DetailDAO (databaseHelper);

        details = detailDAO.getAllDetail ();

        // add sample data


        Detail detail = new Detail ();
        detail.id_Detail = 1;
        detail.id_detail_bill = "DH1";
        detail.id_detail_book = "S1";
        detail.detail_sl = 2;

        details.add (detail);


        rcvDetail = findViewById (R.id.rcvDetail);

        detailAdapter = new DetailAdapter (this, details, detailDAO);

        rcvDetail.setAdapter (detailAdapter);
        linearLayoutManager = new LinearLayoutManager (this);
        rcvDetail.setLayoutManager (linearLayoutManager);

    }

}
