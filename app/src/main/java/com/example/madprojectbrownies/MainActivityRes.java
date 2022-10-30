package com.example.madprojectbrownies;

import static com.example.madprojectbrownies.R.color.orange;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class MainActivityRes extends AppCompatActivity {

    DatabaseReference databaseReferencev;

    RecyclerView recyclerViewv;
    ArrayList<Reservation> ReservationArrayList;
    ResAdapter adapterv;

    Button buttonAddv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_res);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true); // work offline
        //Objects.requireNonNull(getSupportActionBar()).hide();


        getSupportActionBar().setTitle("Reservation Summary");;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(orange)));

        databaseReferencev = FirebaseDatabase.getInstance().getReference();

        recyclerViewv = findViewById(R.id.recyclerViewv);
        recyclerViewv.setHasFixedSize(true);
        recyclerViewv.setLayoutManager(new LinearLayoutManager(this));

        ReservationArrayList = new ArrayList<>();

        buttonAddv = findViewById(R.id.buttonAddv);
        buttonAddv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewv) {
                ViewDialogAddv viewDialogAddv = new ViewDialogAddv();
                viewDialogAddv.showDialogv(MainActivityRes.this);
            }
        });

        readDatav();
    }

    private void readDatav() {

        databaseReferencev.child("Reservation").orderByChild("userName").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotv) {
                ReservationArrayList.clear();
                for (DataSnapshot dataSnapshotv : snapshotv.getChildren()) {
                    Reservation res = dataSnapshotv.getValue(Reservation.class);
                    ReservationArrayList.add(res);
                }
                adapterv = new ResAdapter(MainActivityRes.this, ReservationArrayList);
                recyclerViewv.setAdapter(adapterv);
                adapterv.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public class ViewDialogAddv {
        public void showDialogv(Context contextv) {

            final Dialog dialogv = new Dialog(contextv);
            dialogv.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogv.setCancelable(false);
            dialogv.setContentView(R.layout.alert_dialog_add_new_res);

            //EditText textId = dialog.findViewById(R.id.textId);
            EditText textNamev = dialogv.findViewById(R.id.textNamev);
            EditText textContactv = dialogv.findViewById(R.id.textContactv);
            EditText textTablesv = dialogv.findViewById(R.id.textTablev);
            EditText textDatev = dialogv.findViewById(R.id.textDatev);
            EditText textTimev = dialogv.findViewById(R.id.textTimev);


            Button buttonAddv = dialogv.findViewById(R.id.buttonAddv);
            Button buttonCancelv = dialogv.findViewById(R.id.buttonCancelv);

            buttonAddv.setText("ADD");
            buttonCancelv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogv.dismiss();
                }
            });

            buttonAddv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewv) {
                    String idv = "user" + new Date().getTime();
                    String namev = textNamev.getText().toString();
                    String contactv = textContactv.getText().toString();
                    String tablev = textTablesv.getText().toString();
                    String datev = textDatev.getText().toString();
                    String timev = textTimev.getText().toString();

                    if (namev.isEmpty() || contactv.isEmpty() || tablev.isEmpty()|| datev.isEmpty() || timev.isEmpty()) {
                        Toast.makeText(contextv, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReferencev.child("Reservation").child(idv).setValue(new Reservation(idv, namev, contactv, tablev,datev,timev));
                        Toast.makeText(contextv, "DONE!", Toast.LENGTH_SHORT).show();
                        dialogv.dismiss();

                    }
                }
            });


            dialogv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogv.show();

        }
    }
}