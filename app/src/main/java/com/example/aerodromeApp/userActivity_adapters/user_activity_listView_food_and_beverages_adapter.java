package com.example.aerodromeApp.userActivity_adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aerodromeApp.R;
import com.example.aerodromeApp.activities_userActivity.gridView_food_and_beverages_userActivity_activity;
import com.example.aerodromeApp.dataModel.data_model_userActivity_listView_food_and_beverages;

import java.util.ArrayList;


public class user_activity_listView_food_and_beverages_adapter extends RecyclerView.Adapter<user_activity_listView_food_and_beverages_adapter.myviewholder> {


    ArrayList<data_model_userActivity_listView_food_and_beverages> dataholder;
    Activity context;


    public user_activity_listView_food_and_beverages_adapter(ArrayList<data_model_userActivity_listView_food_and_beverages> dataholder) {
        this.dataholder = dataholder;
    }

    public user_activity_listView_food_and_beverages_adapter(ArrayList<data_model_userActivity_listView_food_and_beverages >dataholder, Activity context){
        this.dataholder = dataholder;
        this.context = context;

    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_food_and_beverage_design_template_user_activity,parent,false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.img.setImageResource(dataholder.get(position).getImage());
        holder.name.setText(dataholder.get(position).getHeader());
        holder.description.setText(dataholder.get(position).getDesc());

        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, gridView_food_and_beverages_userActivity_activity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name, description;
        View mainView;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            mainView = itemView;

            img = itemView.findViewById(R.id.food_beverages_image_listView_userActivity);
            name = itemView.findViewById(R.id.shopName_listView_food_and_beverages_userActivity);
            description = itemView.findViewById(R.id.shopDescription_listView_food_and_beverages_userActivity);

        }
    }
}
