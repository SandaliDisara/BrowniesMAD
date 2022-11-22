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

public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ViewHolder> {
    Context contextv;
    ArrayList<Reservation> ReservationArrayList;
    DatabaseReference databaseReferencev;

    public ResAdapter(Context contextv, ArrayList<Reservation> ReservationArrayList) {
        this.contextv = contextv;
        this.ReservationArrayList = ReservationArrayList;
        databaseReferencev = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(contextv);
        View viewv = layoutInflater.inflate(R.layout.res_item, parent, false);
        return new ViewHolder(viewv);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holderv, int position) {




        Reservation reservations = ReservationArrayList.get(position);


        holderv.textNamev.setText("Name : " + reservations.getNamev());
        holderv.textContactv.setText("Contact No : " + reservations.getContactNov());
        holderv.textTablev.setText("No of Tables : " + reservations.getTablesv());
        holderv.textDatev.setText(("Date:" +reservations.getDatev()));
        holderv.textTimev.setText("Time:"+reservations.getTimev());

        holderv.buttonUpdatev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewv) {
                ViewDialogUpdatev viewDialogUpdatev = new ViewDialogUpdatev();
                viewDialogUpdatev.showDialogv(contextv, reservations.getIDv(), reservations.getNamev(), reservations.getContactNov(), reservations.getTablesv(),reservations.getDatev(),reservations.getTimev());
            }
        });

        holderv.buttonDeletev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewv) {
                ViewDialogConfirmDeletev viewDialogConfirmDeletev = new ViewDialogConfirmDeletev();
                viewDialogConfirmDeletev.showDialogv(contextv, reservations.getIDv());
            }
        });

    }

    @Override
    public int getItemCount() {
        return ReservationArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView textNamev;
        TextView textContactv;
        TextView textTablev;
        TextView textDatev;
        TextView textTimev;

        Button buttonDeletev;
        Button buttonUpdatev;

        public ViewHolder(@NonNull View itemViewv) {
            super(itemViewv);


            textNamev = itemViewv.findViewById(R.id.textNamev);
            textContactv = itemViewv.findViewById(R.id.textContactv);
            textTablev = itemViewv.findViewById(R.id.textTablev);
            textDatev=itemViewv.findViewById(R.id.textDatev);
            textTimev=itemViewv.findViewById(R.id.textTimev);


            buttonUpdatev = itemViewv.findViewById(R.id.buttonUpdatev);
            buttonDeletev = itemViewv.findViewById(R.id.buttonDeletev);
        }
    }

    public class ViewDialogUpdatev {
        public void showDialogv(Context contextv, String idv, String namev, String contactNov , String tablesv,String datev, String timev) {
            final Dialog dialogv = new Dialog(contextv);
            dialogv.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogv.setCancelable(false);
            dialogv.setContentView(R.layout.alert_dialog_add_new_res);


            EditText textNamev = dialogv.findViewById(R.id.textNamev);
            EditText textContactv = dialogv.findViewById(R.id.textContactv);
            EditText textTablev = dialogv.findViewById(R.id.textTablev);
            EditText textDatev = dialogv.findViewById(R.id.textDatev);
            EditText textTimev = dialogv.findViewById(R.id.textTimev);



            textNamev.setText(namev);
            textContactv.setText(contactNov);
            textTablev.setText(tablesv);
            textDatev.setText(datev);
            textTimev.setText(timev);



            Button buttonUpdatev = dialogv.findViewById(R.id.buttonAddv);
            Button buttonCancelv = dialogv.findViewById(R.id.buttonCancelv);

            buttonUpdatev.setText("UPDATE");


            buttonCancelv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewv) {
                    dialogv.dismiss();
                }
            });

            buttonUpdatev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewv) {

                    String newNamev = textNamev.getText().toString();
                    String newContactv = textContactv.getText().toString();
                    String newTablev = textTablev.getText().toString();
                    String newDatev = textDatev.getText().toString();
                    String newTimev=textTimev.getText().toString();

                    if (newNamev.isEmpty() || newContactv.isEmpty() || newTablev.isEmpty()|| newDatev.isEmpty() || newTimev.isEmpty()) {
                        Toast.makeText(contextv, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newNamev.equals(namev) && newContactv.equals(contactNov) && newTablev.equals(tablesv) && newDatev.equals(datev) && newTimev.equals(timev)) {
                            Toast.makeText(contextv, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReferencev.child("Reservation").child(idv).setValue(new Reservation(idv, newNamev, newContactv, newTablev,newDatev,newTimev));
                            Toast.makeText(contextv, "User Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialogv.dismiss();
                        }


                    }
                }
            });

            dialogv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogv.show();

        }
    }



    public class ViewDialogConfirmDeletev {
        public void showDialogv(Context contextv, String IDv) {
            final Dialog dialogv = new Dialog(contextv);
            dialogv.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogv.setCancelable(false);
            dialogv.setContentView(R.layout.view_dialog_confirm_delete_res);

            Button buttonDeletev = dialogv.findViewById(R.id.buttonDeletev);
            Button buttonCancelv = dialogv.findViewById(R.id.buttonCancelv);

            buttonCancelv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewv) {
                    dialogv.dismiss();
                }
            });

            buttonDeletev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewv) {

                    databaseReferencev.child("Reservation").child(IDv).removeValue();
                    Toast.makeText(contextv, "User Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialogv.dismiss();

                }
            });

            dialogv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogv.show();

        }
    }

}
