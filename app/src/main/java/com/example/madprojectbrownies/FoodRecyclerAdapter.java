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
import java.util.Date;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.ViewHolderf> {

    Context context;
    ArrayList<FoodItem> foodItemArrayList;
    DatabaseReference databaseReferencef;

    public FoodRecyclerAdapter(Context context, ArrayList<FoodItem> foodItemArrayList) {
        this.context = context;
        this.foodItemArrayList = foodItemArrayList;
        databaseReferencef = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolderf onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflaterf = LayoutInflater.from(context);
        View viewf = layoutInflaterf.inflate(R.layout.food_item, parent, false);
        return new ViewHolderf(viewf);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecyclerAdapter.ViewHolderf holder, int position) {

        FoodItem foodItem = foodItemArrayList.get(position);

        holder.textNamef.setText("Name : " + foodItem.getFoodName());
        holder.textPricef.setText("Price : " + foodItem.getFoodPrice());
        holder.textDescriptionf.setText("Description : " +foodItem.getFoodDescription());

        holder.buttonUpdatef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewf) {
                FoodRecyclerAdapter.ViewDialogUpdatef viewDialogUpdatef = new FoodRecyclerAdapter.ViewDialogUpdatef();
                viewDialogUpdatef.showDialogf(context, foodItem.getFoodID(), foodItem.getFoodName(), foodItem.getFoodPrice(), foodItem.getFoodDescription());
            }
        });

        holder.buttonDeletef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodRecyclerAdapter.ViewDialogConfirmDeletef viewDialogConfirmDeleteff = new FoodRecyclerAdapter.ViewDialogConfirmDeletef();
                viewDialogConfirmDeleteff.showDialogf(context, foodItem.getFoodID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodItemArrayList.size();
    }

    public static class ViewHolderf extends RecyclerView.ViewHolder {

        TextView textNamef;
        TextView textPricef;
        TextView textDescriptionf;

        Button buttonDeletef;
        Button buttonUpdatef;

        public ViewHolderf(@NonNull View itemView) {
            super(itemView);

            textNamef = itemView.findViewById(R.id.textNamef);
            textPricef = itemView.findViewById(R.id.textPricef);
            textDescriptionf = itemView.findViewById(R.id.textDescriptionf);

            buttonDeletef = itemView.findViewById(R.id.buttonDeletef);
            buttonUpdatef = itemView.findViewById(R.id.buttonUpdatef);
        }
    }

    public class ViewDialogUpdatef {
        public void showDialogf(Context context, String idf, String namef, String pricef, String descriptionf) {
            final Dialog dialogf = new Dialog(context);
            dialogf.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogf.setCancelable(false);
            dialogf.setContentView(R.layout.activity_dialog_add_new_item);

            EditText textNamef = dialogf.findViewById(R.id.textNamef);
            EditText textPricef = dialogf.findViewById(R.id.textPricef);
            EditText textDescriptionf = dialogf.findViewById(R.id.textDescriptionf);

            textNamef.setText(namef);
            textPricef.setText(pricef);
            textDescriptionf.setText(descriptionf);


            Button buttonUpdatef = dialogf.findViewById(R.id.buttonAddf);
            Button buttonCancelf = dialogf.findViewById(R.id.buttonCancelf);

            buttonUpdatef.setText("UPDATE");

            buttonCancelf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogf.dismiss();
                }
            });

            buttonUpdatef.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newName = textNamef.getText().toString();
                    String newPrice = textPricef.getText().toString();
                    String newDescription = textDescriptionf.getText().toString();

                    if (namef.isEmpty() || pricef.isEmpty() || descriptionf.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newName.equals(namef) && newPrice.equals(pricef) && newDescription.equals(descriptionf)) {
                            Toast.makeText(context, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReferencef.child("Food").child(idf).setValue(new FoodItem(idf, newName, newPrice, newDescription));
                            Toast.makeText(context, "Food Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialogf.dismiss();
                        }


                    }
                }
            });

            dialogf.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogf.show();

        }
    }


    public class ViewDialogConfirmDeletef {
        public void showDialogf(Context context, String id) {
            final Dialog dialogf = new Dialog(context);
            dialogf.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogf.setCancelable(false);
            dialogf.setContentView(R.layout.view_dialog_confirm_delete);

            Button buttonDeletef = dialogf.findViewById(R.id.buttonDeletef);
            Button buttonCancelf = dialogf.findViewById(R.id.buttonCancelf);

            buttonCancelf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogf.dismiss();
                }
            });

            buttonDeletef.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    databaseReferencef.child("Food").child(id).removeValue();
                    Toast.makeText(context, "Food Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialogf.dismiss();

                }
            });

            dialogf.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogf.show();

        }
    }
}

