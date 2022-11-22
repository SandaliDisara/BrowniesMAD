package com.example.madprojectbrownies;

import android.app.Dialog;
import android.content.Context;
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

public class MenuItemRecyclerAdapter extends RecyclerView.Adapter<MenuItemRecyclerAdapter.MenuViewHolder> {

    Context context;
    ArrayList<MenuItem> menuList;

    public MenuItemRecyclerAdapter(Context context, ArrayList<MenuItem> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.menu_item,parent,false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        MenuItem menuitem = menuList.get(position);

        holder.foodName.setText(menuitem.getFoodName());
        holder.foodPrice.setText(menuitem.getFoodPrice());
        holder.foodDescription.setText(menuitem.getFoodDescription());


    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{


        TextView foodName, foodPrice, foodDescription;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodDescription = itemView.findViewById(R.id.foodDescription);


        }
    }


}

