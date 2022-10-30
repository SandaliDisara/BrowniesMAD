package com.example.madprojectbrownies;

import static com.example.madprojectbrownies.R.color.orange;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {


    private Button homeReservationBtn;
    private Button homeRecipesBtn;

    private Button adm;

    private  Button btnV;

    private Button order;

    private Button hh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("The Chef");
       getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(orange)));

       adm = findViewById(R.id.Adminloginbtn);
       adm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openAdm();
           }
       });

       hh= findViewById(R.id.button);
       hh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openh();
           }
       });

       btnV = findViewById(R.id.homePlaceOrderbtn2);
       btnV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openV();
           }
       });

       order = findViewById(R.id.homePlaceOrderbtn);
       order.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openorder();
           }
       });



       homeReservationBtn = findViewById(R.id.homeReservationbtn);
       homeReservationBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openResAct();
           }
       });


        homeRecipesBtn = (Button) findViewById(R.id.homeRecipebtn);
        homeRecipesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //openActivityRecipeList();
            }
        });

    }

    public void openV(){
        Intent oo = new Intent(this,Menu_Items_Recycler.class);
        startActivity(oo);
    }

    public  void openResAct(){
        Intent inttt = new Intent(this,MainActivity2Res.class);
        startActivity(inttt);
    }

    public void openAdm(){
        Intent inttttttt=new Intent(this,login.class);
        startActivity(inttttttt);
    }

    public void  openorder(){
        Intent iii= new Intent(this, Menu_Items_Recycler.class);
        startActivity(iii);
    }

    public void openh(){
        Intent pp =new Intent(this, Handle_Delivery_Details.class);
        startActivity(pp);
    }


   /* public void openActivityRecipeList(){
        Intent intentK = new Intent(this, recipeMain.class);
        startActivity(intentK);
    }*/

}