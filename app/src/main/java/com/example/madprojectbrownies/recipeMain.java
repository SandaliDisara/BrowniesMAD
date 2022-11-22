package com.example.madprojectbrownies;

import static com.example.madprojectbrownies.R.color.orange_for_buttons;

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
import java.util.Objects;

public class recipeMain extends AppCompatActivity {

    DatabaseReference databaseReferenceK;

    RecyclerView recyclerViewK;
    ArrayList<Recipe> recipeArrayList;
    RecipeRecyclerAdapter adapterK;

    Button buttonAddK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_k);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true); // work offline
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseReferenceK = FirebaseDatabase.getInstance().getReference();

        recyclerViewK = findViewById(R.id.recyclerViewK);
        recyclerViewK.setHasFixedSize(true);
        recyclerViewK.setLayoutManager(new LinearLayoutManager(this));

        recipeArrayList = new ArrayList<>();

        buttonAddK = findViewById(R.id.buttonAddK);
        buttonAddK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogAddK viewDialogAddK = new ViewDialogAddK();
                viewDialogAddK.showDialog(recipeMain.this);
            }
        });

        readDataK();
    }

    private void readDataK() {

        databaseReferenceK.child("CustomerRecipe").orderByChild("recipeName").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotK) {
                recipeArrayList.clear();
                for (DataSnapshot dataSnapshotK : snapshotK.getChildren()) {
                    Recipe recipe = dataSnapshotK.getValue(Recipe.class);
                    recipeArrayList.add(recipe);
                }
                adapterK = new RecipeRecyclerAdapter(recipeMain.this, recipeArrayList);
                recyclerViewK.setAdapter(adapterK);
                adapterK.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public class ViewDialogAddK {
        public void showDialog(Context contextK) {
            final Dialog dialogK = new Dialog(contextK);
            dialogK.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogK.setCancelable(false);
            dialogK.setContentView(R.layout.alert_dialog_add_new_recipe);

            EditText recipeName = dialogK.findViewById(R.id.recipeNameAdd);
            EditText ingredient = dialogK.findViewById(R.id.ingredientAdd);
            EditText method = dialogK.findViewById(R.id.methodAdd);


            Button buttonAddK = dialogK.findViewById(R.id.buttonAddK);
            Button buttonCancelK = dialogK.findViewById(R.id.buttonCancelK);

            buttonAddK.setText("Add");
            buttonCancelK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogK.dismiss();
                }
            });

            buttonAddK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String idK = "recipe" + new Date().getTime();
                    String nameK = recipeName.getText().toString();
                    String ingre = ingredient.getText().toString();
                    String meth = method.getText().toString();

                    if (nameK.isEmpty() || ingre.isEmpty() || meth.isEmpty()) {
                        Toast.makeText(contextK, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReferenceK.child("CustomerRecipe").child(idK).setValue(new Recipe(idK, nameK, ingre, meth));
                        Toast.makeText(contextK, "Added Successfully!", Toast.LENGTH_SHORT).show();
                        dialogK.dismiss();
                    }
                }
            });

            dialogK.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogK.show();

        }
    }
}