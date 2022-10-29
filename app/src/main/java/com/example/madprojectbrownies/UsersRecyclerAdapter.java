package com.example.madprojectbrownies;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<UsersItem> usersItemArrayList;
    DatabaseReference databaseReference;

    public UsersRecyclerAdapter(Context context, ArrayList<UsersItem> usersItemArrayList) {
        this.context = context;
        this.usersItemArrayList = usersItemArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UsersItem users = usersItemArrayList.get(position);

        holder.textNamec.setText("Name : " + users.getUserName());
        holder.textPhonec.setText("Phone : " + users.getUserPhone());
        holder.textAddressc.setText("Address : " + users.getUserAddress());
        holder.textNotec.setText("Note : " + users.getUserNote());


        holder.buttonUpdatec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context, users.getUserID(), users.getUserName(), users.getUserPhone(), users.getUserAddress(), users.getUserNote());
            }
        });

        holder.buttonDeletec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, users.getUserID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNamec;
        TextView textPhonec;
        TextView textAddressc;
        TextView textNotec;

        Button buttonDeletec;
        Button buttonUpdatec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNamec = itemView.findViewById(R.id.textNamec);
            textPhonec = itemView.findViewById(R.id.textPhonec);
            textAddressc = itemView.findViewById(R.id.textAddressc);
            textNotec = itemView.findViewById(R.id.textNotec);

            buttonDeletec = itemView.findViewById(R.id.buttonDeletec);
            buttonUpdatec = itemView.findViewById(R.id.buttonUpdatec);
        }
    }

    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String name, String phone, String address, String note) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_user);

            EditText textNamec = dialog.findViewById(R.id.textNamec);
            EditText textPhonec = dialog.findViewById(R.id.textPhonec);
            EditText textAddressc = dialog.findViewById(R.id.textAddressc);
            EditText textNotec = dialog.findViewById(R.id.textNotec);

            textNamec.setText(name);
            textPhonec.setText(phone);
            textAddressc.setText(address);
            textNotec.setText(note);


            Button buttonUpdatec = dialog.findViewById(R.id.buttonAddc);
            Button buttonCancelc = dialog.findViewById(R.id.buttonCancelc);

            buttonUpdatec.setText("UPDATE");

            buttonCancelc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdatec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newNamec = textNamec.getText().toString();
                    String newPhonec = textPhonec.getText().toString();
                    String newAddressc = textAddressc.getText().toString();
                    String newNotec = textNotec.getText().toString();

                    if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || note.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newNamec.equals(name) && newPhonec.equals(phone) && newAddressc.equals(address) && newNotec.equals(note)) {
                            Toast.makeText(context, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("USERS").child(id).setValue(new UsersItem(id, newNamec, newPhonec, newAddressc, newNotec));
                            Toast.makeText(context, "User Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }


    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete);

            Button buttonDeletec = dialog.findViewById(R.id.buttonDeletec);
            Button buttonCancelc = dialog.findViewById(R.id.buttonCancelc);

            buttonCancelc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDeletec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    databaseReference.child("USERS").child(id).removeValue();
                    Toast.makeText(context, "User Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }
}
