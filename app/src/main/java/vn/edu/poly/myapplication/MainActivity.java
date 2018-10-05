package vn.edu.poly.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import vn.edu.poly.myapplication.fragment.HoaDonFragment;
import vn.edu.poly.myapplication.fragment.SachFragment;
import vn.edu.poly.myapplication.fragment.TaiKhoanFragment;
import vn.edu.poly.myapplication.fragment.TheLoaiFragment;
import vn.edu.poly.myapplication.fragment.ThongKeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById (R.id.btNavigation);

        BottomNavigationViewHelper.removeShiftMode (bottomNavigationView);


        bottomNavigationView.setSelectedItemId(R.id.navThongke);
        Fragment fragment = null;
        fragment = new ThongKeFragment ();
        FragmentManager fragmentManager = getFragmentManager ();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        fragmentTransaction.replace (R.id.frameLayout, fragment);
        fragmentTransaction.commit ();


        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment= null;

                if (item.getItemId () == R.id.navThongke) {

                    fragment = new ThongKeFragment ();
                    FragmentManager fragmentManager = getFragmentManager ();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                    fragmentTransaction.replace (R.id.frameLayout, fragment);
                    fragmentTransaction.commit ();


                } else if (item.getItemId () == R.id.navTheloai) {

                    fragment = new TheLoaiFragment ();
                    FragmentManager fragmentManager = getFragmentManager ();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                    fragmentTransaction.replace (R.id.frameLayout, fragment);
                    fragmentTransaction.commit ();


                } else if (item.getItemId () == R.id.navSach) {

                    fragment = new SachFragment ();
                    FragmentManager fragmentManager = getFragmentManager ();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                    fragmentTransaction.replace (R.id.frameLayout, fragment);
                    fragmentTransaction.commit ();

                } else if (item.getItemId () == R.id.navHoadon) {

                    fragment = new HoaDonFragment ();
                    FragmentManager fragmentManager = getFragmentManager ();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                    fragmentTransaction.replace (R.id.frameLayout, fragment);
                    fragmentTransaction.commit ();

                } else if (item.getItemId () == R.id.navTaikhoan) {



                    fragment = new TaiKhoanFragment ();
                    FragmentManager fragmentManager = getFragmentManager ();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                    fragmentTransaction.replace (R.id.frameLayout, fragment);

                    Intent intent = getIntent ();
                    Bundle bundle = intent.getBundleExtra ("dataUser");
                    String TaiKhoanAdmin = bundle.getString ("userName");
                    String MatKhauAdmin = bundle.getString ("passWord");





                    fragmentTransaction.commit ();

                }

                return true;
            }
        });

        //lấy dữ liệu tài khoản mật khẩu
        Intent intent = getIntent ();
        Bundle bundle = intent.getBundleExtra ("dataUser");
        String TaiKhoanAdmin = bundle.getString ("userName");
        String MatKhauAdmin = bundle.getString ("passWord");

//        Toast.makeText (this, TaiKhoanAdmin+MatKhauAdmin, Toast.LENGTH_SHORT).show ();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_taikhoan, menu);
        return super.onCreateOptionsMenu (menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


//        int id = item.getItemId ();
//        if(id== R.id.itemThem){
//            if(id == R.id.itemThem){
//                Toast.makeText (getApplication (), "Actyvity", Toast.LENGTH_SHORT).show ();
//            }
//            return true;
//        }


        return super.onOptionsItemSelected (item);
    }






//    public void ShowPopUpMenu(View view) {
//
//
//        //tao popupmenu
//        PopupMenu pop = new PopupMenu (MainActivity.this, );
//        getMenuInflater ().inflate (R.menu.menu_taikhoan, pop.getMenu ());
//
//        //xu li chon vao 1 item
//        pop.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener () {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                switch (item.getItemId ()) {
//
//                    case R.id.itemThem:
//                        Toast.makeText (MainActivity.this, "Facebook", Toast.LENGTH_SHORT).show ();
//                    case R.id.itemDoiMatKhau:
//                        Toast.makeText (MainActivity.this, "Google", Toast.LENGTH_SHORT).show ();
//                    case R.id.itemDangXuat:
//                        Toast.makeText (MainActivity.this, "Zalo", Toast.LENGTH_SHORT).show ();
//                }
//
//
//                return false;
//            }
//        });
//        pop.show ();
//    }




}
