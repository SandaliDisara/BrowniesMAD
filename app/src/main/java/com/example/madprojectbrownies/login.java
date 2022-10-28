package com.example.madprojectbrownies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class login extends AppCompatActivity {

    private  Toolbar appbar3;
    private Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView username = findViewById(R.id.loginName);
        TextView password = findViewById(R.id.loginPassword);


        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin01") && password.getText().toString().equals("admin123")) {
                    //correct
                    //admin01 and admin123
                    //openMenuActivity();
                } else {
                    //incorrect
                    //display a toast message
                    Toast.makeText(login.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*appbar3 = findViewById(R.id.appBar);
        setSupportActionBar(appbar3);
        getSupportActionBar().setTitle("Login");*/


    }

   /* public  void openMenuActivity(){
        Intent intent5 = new Intent(this,MenuEmp.class);
        startActivity(intent5);
    }*/
}