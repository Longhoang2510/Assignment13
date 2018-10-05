package vn.edu.poly.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.myapplication.Constant;
import vn.edu.poly.myapplication.R;
import vn.edu.poly.myapplication.holder.UserHolder;
import vn.edu.poly.myapplication.model.User;
import vn.edu.poly.myapplication.splitedao.UserDAO;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Constant {
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;


    private Context context;
    private ArrayList<User> users;
    private UserDAO userDAO;


    @SuppressLint("ServiceCast")
    public UserAdapter(Context context, ArrayList<User> users, UserDAO userDAO) {
        this.context = context;
        this.users = users;
        this.userDAO = userDAO;
        inflater = (LayoutInflater) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate (R.layout.item_taikhoan, parent, false);
        return new ViewHolder (view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final User user = users.get (position);
        holder.txtName.setText (user.getName ());
        holder.txtPhone.setText (user.getPhone ());
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickItem (user, position);


            }
        });


        holder.ivDelete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                userDAO.deleteUser (users.get (position).getUsername ());
                notifyItemRemoved (position);

                Toast.makeText (context, "Xóa tk có tên: " + user.getName (), Toast.LENGTH_SHORT).show ();

            }
        });


        holder.ivEdit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DialogEdit (position);


                Toast.makeText (context, "Sửa", Toast.LENGTH_SHORT).show ();
            }
        });


    }

    public void removeItem(User user, int positon) {
        users.remove (positon);
        notifyItemRemoved (positon);

    }

    public void setItemOnclickListener(OnItemClickListener onclickListener) {
        this.onItemClickListener = onclickListener;
    }


    @Override
    public int getItemCount() {
        return users.size ();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtPhone;
        ImageView ivDelete;
        ImageView ivEdit;

        public ViewHolder(View itemView) {
            super (itemView);

            txtName = itemView.findViewById (R.id.tvTenUser);
            txtPhone = itemView.findViewById (R.id.tvPhoneUser);
            ivDelete = itemView.findViewById (R.id.ivDelete);
            ivEdit = itemView.findViewById (R.id.ivEdit);

        }
    }


    public interface OnItemClickListener {
        public void onClickItem(User user, int position);


    }

    public void updateItem(User user, int positon) {
        ContentValues values = new ContentValues ();


    }


    private void DialogEdit(final int position) {
        final Dialog dialog = new Dialog (context);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.dialog_edit_user);

        final TextView tvUserName = dialog.findViewById (R.id.tvUserName);
        final EditText edtPassword = dialog.findViewById (R.id.edtPassword);
        final EditText edtCPass = dialog.findViewById (R.id.edtConfirmPassword);
        final EditText edtName = dialog.findViewById (R.id.edtName);
        final EditText edtPhone = dialog.findViewById (R.id.edtPhone);

        Button btHuyed = dialog.findViewById (R.id.btHuyEd);
        Button btThemed = dialog.findViewById (R.id.btThemEd);

        tvUserName.setText (users.get (position).getUsername ());


        btThemed.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                String username = users.get (position).getUsername ();
                String password = edtPassword.getText ().toString ().trim ();
                String phone = edtPhone.getText ().toString ().trim ();
                String name = edtName.getText ().toString ().trim ();
                String confirmPassword = edtCPass.getText ().toString ().trim ();


                if (username == null) {
                    Toast.makeText (context, "Lỗi !!!", Toast.LENGTH_SHORT).show ();
                    return;
                } else if (name.equals ("")) {

                    edtName.setError ("Không đc để trống!");
                    return;

                } else if (password.length () < 5 || password.length () > 15) {

                    edtPassword.setError ("Mật khẩu phải lớn hơn 5 và nhỏ hơn 15");
                    return;

                } else if (!password.equals (confirmPassword)) {
                    edtCPass.setError ("Nhập lại mật khẩu chưa đúng");
                    return;

                } else if (phone.length () < 5) {
                    edtPhone.setError ("Nhập đủ 10 số");
                    return;

                } else {

                    User user = new User (username, password, name, phone);
                    userDAO.updateUser (user);

//                    users.get (position).setUsername (username);
//                    users.get (position).setPassword (password);
//                    users.get (position).setName (name);
//                    users.get (position).setPhone (phone);

                        users.set (position, user);





                    dialog.cancel ();



                }


            }
        });


        btHuyed.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (context, "Hủy", Toast.LENGTH_SHORT).show ();
                dialog.cancel ();
            }
        });

        //ksich thước của diglog khi hiện lên
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams ();
        lp.copyFrom (dialog.getWindow ().getAttributes ());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show ();
        dialog.getWindow ().setAttributes (lp);


//        dialog.show ();
    }




}
