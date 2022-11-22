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

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder> {

    Context contextK;
    ArrayList<Recipe> recipeArrayList;
    DatabaseReference databaseReferenceK;

    public RecipeRecyclerAdapter(Context contextK, ArrayList<Recipe> recipeArrayList) {
        this.contextK = contextK;
        this.recipeArrayList = recipeArrayList;
        databaseReferenceK = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflaterK = LayoutInflater.from(contextK);
        View view = layoutInflaterK.inflate(R.layout.recipe_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holderK, int positionK) {

        Recipe recipe = recipeArrayList.get(positionK);

        holderK.recipeName.setText(recipe.getRecipeName());
        holderK.ingredient.setText("Ingredients : " + recipe.getIngredient());
        holderK.method.setText("Method : " + recipe.getMethod());

        holderK.buttonUpdateK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewk) {
                ViewDialogUpdateK viewDialogUpdateK = new ViewDialogUpdateK();
                viewDialogUpdateK.showDialog(contextK, recipe.getRecipeID(), recipe.getRecipeName(), recipe.getIngredient(), recipe.getMethod());
            }
        });

        holderK.buttonDeleteK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewk) {
                ViewDialogConfirmDeleteK viewDialogConfirmDeleteK = new ViewDialogConfirmDeleteK();
                viewDialogConfirmDeleteK.showDialog(contextK, recipe.getRecipeID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipeName;
        TextView ingredient;
        TextView method;

        Button buttonDeleteK;
        Button buttonUpdateK;

        public ViewHolder(@NonNull View itemViewk) {
            super(itemViewk);

            recipeName = itemViewk.findViewById(R.id.recipeName);
            ingredient = itemViewk.findViewById(R.id.ingredient);
            method = itemViewk.findViewById(R.id.method);

            buttonDeleteK = itemViewk.findViewById(R.id.buttonDeleteK);
            buttonUpdateK = itemViewk.findViewById(R.id.buttonUpdateK);
        }
    }

    public class ViewDialogUpdateK {
        public void showDialog(Context contextK, String idK, String nameK, String ingredient, String methodk) {
            final Dialog dialogK = new Dialog(contextK);
            dialogK.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogK.setCancelable(false);
            dialogK.setContentView(R.layout.alert_dialog_add_new_recipe);

            EditText textNameK = dialogK.findViewById(R.id.recipeNameAdd);
            EditText textIngredient = dialogK.findViewById(R.id.ingredientAdd);
            EditText textMethod = dialogK.findViewById(R.id.methodAdd);

            textNameK.setText(nameK);
            textIngredient.setText(ingredient);
            textMethod.setText(methodk);


            Button buttonUpdateK = dialogK.findViewById(R.id.buttonAddK);
            Button buttonCancelK = dialogK.findViewById(R.id.buttonCancelK);

            buttonUpdateK.setText("Update");

            buttonCancelK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewk) {
                    dialogK.dismiss();
                }
            });

            buttonUpdateK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewk) {

                    String newNameK = textNameK.getText().toString();
                    String newIngredient = textIngredient.getText().toString();
                    String newMethod = textMethod.getText().toString();

                    if (newNameK.isEmpty() || newIngredient.isEmpty() || newMethod.isEmpty()) {
                        Toast.makeText(contextK, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newNameK.equals(nameK) && newIngredient.equals(ingredient) && newMethod.equals(methodk)) {
                            Toast.makeText(contextK, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReferenceK.child("CustomerRecipe").child(idK).setValue(new Recipe(idK, newNameK, newIngredient, newMethod));
                            Toast.makeText(contextK, "Recipe Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialogK.dismiss();
                        }


                    }
                }
            });

            dialogK.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogK.show();

        }
    }


    public class ViewDialogConfirmDeleteK {
        public void showDialog(Context contextk, String idK) {
            final Dialog dialogK = new Dialog(contextk);
            dialogK.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogK.setCancelable(false);
            dialogK.setContentView(R.layout.view_dialog_confirm_delete_recipe);

            Button buttonDeleteK = dialogK.findViewById(R.id.buttonDeleteK);
            Button buttonCancelK = dialogK.findViewById(R.id.buttonCancelK);

            buttonCancelK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewk) {
                    dialogK.dismiss();
                }
            });

            buttonDeleteK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewk) {

                    databaseReferenceK.child("CustomerRecipe").child(idK).removeValue();
                    Toast.makeText(contextk, "User Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialogK.dismiss();

                }
            });

            dialogK.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogK.show();

        }
    }
}
