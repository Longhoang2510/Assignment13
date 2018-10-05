package vn.edu.poly.myapplication.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;

import vn.edu.poly.myapplication.MainActivity;
import vn.edu.poly.myapplication.fragment.TaiKhoanFragment;
import vn.edu.poly.myapplication.model.User;
import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.splitedao.UserDAO;

public class DangNhap extends Fragment {

    private View dn;

        //databese
    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
         dn = inflater.inflate (R.layout.dangnhap, container, false);


         databaseHelper = new DatabaseHelper (getActivity ());
         userDAO = new UserDAO (databaseHelper);
//         User user = new User ("Long2", "Long1", "LongA", "0123456789");
//         databaseHelper.insertUser (user);




        initView();
        //gọi thông qua đối tượng
        return dn;




    }

    private void initView() {
        //khai báo
        final EditText edTaiKhoan = dn.findViewById (R.id.edTaiKhoan);
        final EditText edMatKhau = dn.findViewById (R.id.edMatKhau);
        final CheckBox cbDieuKhoan = dn.findViewById (R.id.cbDieukhoan);


        //xử lý



        dn.findViewById (R.id.btDangNhap).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String Taikhoan = edTaiKhoan.getText ().toString ().trim ();
                String Matkhau = edMatKhau.getText ().toString ().trim ();
                Boolean ktra = cbDieuKhoan.isChecked ();



                if(Taikhoan.equals ("")){
                    edTaiKhoan.setError ("Không được để trống");
                    return;



                }else if(Matkhau.length () < 5 || Matkhau.length () > 15){

                    edMatKhau.setError ("Mật khẩu phải lớn hơn 5 và nhỏ hơn 15");
                    return;

                }else if(ktra == false){
                    Toast.makeText (getActivity (), "Yêu cầu đọc điều khoản ", Toast.LENGTH_SHORT).show ();
                    return;

                }else {

                    User user = userDAO.getUserByUsername(Taikhoan);

                    // neu use !=null, tuc la username co tren DB thi so sanh password
                    if (user !=null){
                        String passwordOnDatabase = user.getPassword();

                        // neu password giong nhau thi cho phep dang nhap va mo man hinh ManChinhActivity
                        if (passwordOnDatabase.equals(Matkhau)){
                            Intent intent = new Intent(getActivity (), MainActivity.class);


                            Bundle bundle = new Bundle ();
                            bundle.putString ("userName", Taikhoan);
                            bundle.putString ("passWord", Matkhau);
                            intent.putExtra ("dataUser", bundle);



                            startActivity(intent);


                            //gưi dữ liệu







                        }
                        // neu password ko giong thi thong bao loi
                        else Toast.makeText(getActivity (),getString(R.string.notify_wrong_password),Toast.LENGTH_SHORT).show();
                    }
                    // neu user == null thi thong bao loi
                    else  Toast.makeText(getActivity (),getString(R.string.notify_wrong_password),Toast.LENGTH_SHORT).show();


//                    Timer timer = new Timer ();
//                    timer.schedule (new TimerTask () {
//                        @Override
//                        public void run() {
//
//                            Intent intent = new Intent (getActivity (), MainActivity.class);
//                            startActivity (intent);
//
//
//
//
//
//                        }
//                    }, 1000);


                }




            }
        });

    }
}
