package vn.edu.poly.myapplication.fragment;

import android.app.Dialog;
import android.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.ArrayList;

import vn.edu.poly.myapplication.MainActivity;
import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.adapter.UserAdapter;
import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.model.User;
import vn.edu.poly.myapplication.splitedao.UserDAO;


public class TaiKhoanFragment extends Fragment implements UserAdapter.OnItemClickListener {

    private View Login;
    private View Infarter;
    private ImageView ivOut;
    private TextView tvShowUser, tvShowPhone;


    private RecyclerView rcvTaiKhoan;
    private DatabaseHelper databaseHelper;
    private ArrayList<User> users;
    private UserAdapter adapter;
    private Cursor cursor;
    private ImageView ivDelete;
    private UserDAO userDAO;
    private ImageView ivEdit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu (true);
        // Inflate the layout for this fragment
        Login = inflater.inflate (R.layout.fragment_tai_khoan, container, false);
        Infarter = inflater.inflate (R.layout.item_taikhoan, container, false);

        rcvTaiKhoan = Login.findViewById (R.id.rcvTaiKhoan);
        databaseHelper = new DatabaseHelper (getActivity ());
        userDAO = new UserDAO (databaseHelper);

        ivOut = Login.findViewById (R.id.ivOut);
        ivDelete = Login.findViewById (R.id.ivDelete);
        ivEdit = Login.findViewById (R.id.ivEdit);


        //set tên tk mật khẩu


        ivOut.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ShowPopUpMenu (ivOut);
            }
        });


//        hientThiTaiKhoan ();


        return Login;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        init ();

    }

    private void init() {
        users = new ArrayList ();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager (getActivity ());
        linearLayoutManager.setOrientation (LinearLayoutManager.VERTICAL);
        userDAO = new UserDAO (databaseHelper);
        rcvTaiKhoan.setLayoutManager (linearLayoutManager);
        users = (ArrayList<User>) userDAO.getAllUser ();
        if (users != null) {
            adapter = new UserAdapter (getActivity (), users, userDAO);
            rcvTaiKhoan.setAdapter (adapter);
            adapter.setItemOnclickListener (this);


        }


    }


    //    private void hientThiTaiKhoan() {
//        databaseHelper.getUser ();
//        cursor = databaseHelper.getUser ();
//        if (cursor != null) {
//            adapter = new SimpleCursorAdapter (getActivity (),
//                    R.layout.item_taikhoan, cursor,
//                    new String[]{"Username", "Password", "Name", "PhoneNumber"},
//                    new int[]{R.id.tvTenDangNhapUser, R.id.tvPassUser, R.id.tvTenUser, R.id.tvPhoneUser});
//            lvTaiKhoan.setAdapter (adapter);
//        }
//
//
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Lấy ra id của item vừa click
        int id = item.getItemId ();
        //Xử lý khi click vào sẽ show ra title của item đó
        if (id == R.id.itemThem) {
            Toast.makeText (getActivity (), "Add clicked fragment", Toast.LENGTH_SHORT).show ();
        }
        if (id == R.id.itemDoiMatKhau) {
            Toast.makeText (getActivity (), "Edit clicked", Toast.LENGTH_SHORT).show ();
        }
        if (id == R.id.itemDangXuat) {
            Toast.makeText (getActivity (), "Delete clicked", Toast.LENGTH_SHORT).show ();
        }
        return super.onOptionsItemSelected (item);
    }


    public void ShowPopUpMenu(View view) {

        //tao popupmenu
        PopupMenu pop = new PopupMenu (getActivity (), ivOut);
        pop.getMenuInflater ().inflate (R.menu.menu_taikhoan, pop.getMenu ());


        pop.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId ()) {

                    case R.id.itemThem:
                        Toast.makeText (getActivity (), "Thêm", Toast.LENGTH_SHORT).show ();
                    case R.id.itemDoiMatKhau:
                        Toast.makeText (getActivity (), "Đổi mật khẩu", Toast.LENGTH_SHORT).show ();
                    case R.id.itemDangXuat:
                        Toast.makeText (getActivity (), "Đăng xuất", Toast.LENGTH_SHORT).show ();
                }


                return false;
            }
        });


        pop.show ();
    }


    @Override
    public void onClickItem(final User user, final int position) {


//        adapter.removeItem (user, position);
//        DialogEdit (position);
        adapter.notifyDataSetChanged ();


    }

//    public void DialogEdit(final int position ) {
//        final Dialog dialog = new Dialog (getActivity ());
//        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
//        dialog.setContentView (R.layout.dialog_edit_user);
//
//        final TextView tvUserName = dialog.findViewById (R.id.tvUserName);
//        final EditText edtPassword = dialog.findViewById (R.id.edtPassword);
//        final EditText edtCPass = dialog.findViewById (R.id.edtConfirmPassword);
//        final EditText edtName = dialog.findViewById (R.id.edtName);
//        final EditText edtPhone = dialog.findViewById (R.id.edtPhone);
//
//        Button btHuyed = dialog.findViewById (R.id.btHuyEd);
//        Button btThemed = dialog.findViewById (R.id.btThemEd);
//
//        tvUserName.setText (users.get (position).getUsername ());
//
//
//        btThemed.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//
//
//
//                String username =  users.get (position).getUsername ();
//                String password = edtPassword.getText ().toString ().trim ();
//                String phone = edtPhone.getText ().toString ().trim ();
//                String name = edtName.getText ().toString ().trim ();
//                String confirmPassword = edtCPass.getText ().toString ().trim ();
//
//
//                if(username == null){
//                    Toast.makeText (getActivity (), "Lỗi !!!", Toast.LENGTH_SHORT).show ();
//                    return;
//                }else if(name.equals ("")){
//
//                    edtName.setError ("Không đc để trống!");
//                    return;
//
//                }else if(password.length () < 5 || password.length () > 15){
//
//                    edtPassword.setError ("Mật khẩu phải lớn hơn 5 và nhỏ hơn 15");
//                    return;
//
//                }else if(!password.equals (confirmPassword)){
//                    edtCPass.setError ("Nhập lại mật khẩu chưa đúng");
//                    return;
//
//                }else if(phone.length () < 5){
//                    edtPhone.setError ("Nhập đủ 10 số");
//                    return;
//
//                }
//                else{
//
//                    User user = new User (username, password, name, phone);
//                    userDAO.updateUser (user);
//
//
//                    adapter.notifyDataSetChanged ();
//
//
//
//
//
//                    dialog.cancel ();
//
//
//                }
//
//
//
//            }
//        });
//
//
//        btHuyed.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText (getActivity (), "Hủy", Toast.LENGTH_SHORT).show ();
//                dialog.cancel ();
//            }
//        });
//
//        //ksich thước của diglog khi hiện lên
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
//
//
////        dialog.show ();
//    }


}








