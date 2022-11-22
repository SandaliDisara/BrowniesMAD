package com.example.madprojectbrownies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    // private  Toolbar appbar3;
    private Button loginBtn;

    private Button Buttonb;

    private Button buttonLogOutf;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        getSupportActionBar().setTitle("Login");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        TextView username = findViewById(R.id.loginNamef);
        TextView password = findViewById(R.id.loginPasswordf);


        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin01") && password.getText().toString().equals("admin123")){
                    //correct
                    //admin01 and admin123
                    openMenuActivity();
                }else {
                    //incorrect
                    //display a toast message
                    Toast.makeText(login.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void openMenuActivity(){
        Intent intent5 = new Intent(this,MainActivityF.class);
        startActivity(intent5);
    }

    public void openH(){
        Intent in6 = new Intent(this,Home.class);
    }

}