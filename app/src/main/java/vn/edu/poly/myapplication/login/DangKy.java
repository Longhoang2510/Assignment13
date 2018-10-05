package vn.edu.poly.myapplication.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.database.DatabaseHelper;
import vn.edu.poly.myapplication.model.User;
import vn.edu.poly.myapplication.splitedao.UserDAO;

public class DangKy extends Fragment {

    private Button btHuyDK,btDangKy;
    private EditText edtTenDangNhap;
    private EditText edtMatKhau;
    private EditText edtHoTen;
    private EditText edSoDT;




    private View dk;
    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dk = inflater.inflate (R.layout.dangky, container, false);


        Khaibao();


        btDangKy.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String Username = edtTenDangNhap.getText ().toString ().trim ();
                String Password = edtMatKhau.getText ().toString ().trim ();
                String Name = edtHoTen.getText ().toString ().trim ();
                String Phonenumber = edSoDT.getText ().toString ().trim ();



                databaseHelper = new DatabaseHelper (getActivity ());
                userDAO = new UserDAO (databaseHelper);


                User user = userDAO.getUserByUsername(Username);


//                Toast.makeText (getActivity (), Username+Password+Name+Phonenumber, Toast.LENGTH_SHORT).show ();

                if (Username.equals ("")) {
                    edtTenDangNhap.setError ("Không được để trống");
                    return;



                }else if(user !=null){

                    edtTenDangNhap.setError ("Đã có tài khoản này rồi");
                    return;

                }else if (Password.length () < 5 || Password.length () > 15) {

                    edtMatKhau.setError ("Mật khẩu phải lớn hơn 5 và nhỏ hơn 15");
                    return;

                } else if (Name.equals ("")) {
                    edtHoTen.setError ("Không được để trống");
                    return;

                } else if (Phonenumber.length () < 5) {
                    edSoDT.setError ("Phải nhập đủ 10 số");
                    return;

                }else {

                    userDAO.insertUser (new User (Username,Password,Name,Phonenumber));
                    Toast.makeText (getActivity (), "Đã tạo thành công", Toast.LENGTH_SHORT).show ();

                    edtTenDangNhap.setText ("");
                    edtMatKhau.setText ("");
                    edtHoTen.setText ("");
                    edSoDT.setText ("");



                }




            }
        });




        btHuyDK.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                edtTenDangNhap.setText ("");
                edtMatKhau.setText ("");
                edtHoTen.setText ("");
                edSoDT.setText ("");

            }
        });



        return dk;
    }

    private void Khaibao() {

        btDangKy = dk.findViewById (R.id.btDangKy);
        btHuyDK = dk.findViewById (R.id.btHuyDK);
        edtTenDangNhap = dk.findViewById (R.id.edtTenDangNhap);
        edtMatKhau = dk.findViewById (R.id.edtMatKhau);
        edtHoTen = dk.findViewById (R.id.edtHoTen);
        edSoDT = dk.findViewById (R.id.edtSoDT);



    }
}
