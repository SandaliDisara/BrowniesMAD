package com.example.madprojectbrownies;

import static com.example.madprojectbrownies.R.color.orange;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2Res extends AppCompatActivity {
    Button buttonNewv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_res);
        getSupportActionBar().setTitle("New Reservation");;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(orange)));


        buttonNewv=(Button)findViewById(R.id.buttonNewv);
        buttonNewv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewv) {
                openMainActivity();
            }
        });


    }
    public void openMainActivity(){
        Intent intentv = new Intent(this, MainActivityRes.class);
        startActivity(intentv);
    }

}