package com.example.aerodromeApp.adapters;

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
import com.example.aerodromeApp.activities_shopping.gridView_food_and_beverages_activity;
import com.example.aerodromeApp.dataModel.data_model_listView_food_and_beverages;

import java.util.ArrayList;

public class food_and_beverages_listView_adapter  extends RecyclerView.Adapter<food_and_beverages_listView_adapter.myviewholder>{

    ArrayList<data_model_listView_food_and_beverages> dataholder ;
    Activity context;

    public food_and_beverages_listView_adapter(ArrayList<data_model_listView_food_and_beverages >dataholder, Activity context){
        this.dataholder = dataholder;
        this.context = context;

    }

    public food_and_beverages_listView_adapter(ArrayList<data_model_listView_food_and_beverages> dataHolder) {
        this.dataholder = dataHolder;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design_template_food_beverages_listview,parent,false);
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
                Intent intent = new Intent(context, gridView_food_and_beverages_activity.class);
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

            img = itemView.findViewById(R.id.food_beverages_image_listView);
            name = itemView.findViewById(R.id.shopName_listView_food_and_beverages);
            description = itemView.findViewById(R.id.shopDescription_listView_food_and_beverages);

        }

    }
}
