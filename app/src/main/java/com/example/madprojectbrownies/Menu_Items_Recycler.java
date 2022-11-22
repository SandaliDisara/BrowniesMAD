package com.example.madprojectbrownies;

import static com.example.madprojectbrownies.R.color.orange;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class Menu_Items_Recycler extends AppCompatActivity {

    Button b1;


    RecyclerView recyclerView;
    DatabaseReference database;
    MenuItemRecyclerAdapter menuItemRecyclerAdapter;
    ArrayList<MenuItem> menuList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items_recycler);

        getSupportActionBar().setTitle("Menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(orange)));

        recyclerView = findViewById(R.id.recyclerMenu);
        database = FirebaseDatabase.getInstance().getReference("Food");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        b1 = findViewById(R.id.btnCartCheckout);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNew();
            }
        });

        menuList = new ArrayList<>();
        menuItemRecyclerAdapter = new MenuItemRecyclerAdapter(this,menuList);
        recyclerView.setAdapter(menuItemRecyclerAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    MenuItem menuItem = dataSnapshot.getValue(MenuItem.class);
                    menuList.add(menuItem);

                }

                menuItemRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    public void openNew(){
        Intent g = new Intent(this,Handle_Delivery_Details.class);
        startActivity(g);
    }



}