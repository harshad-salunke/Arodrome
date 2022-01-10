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
import com.example.aerodromeApp.activities_shopping.gridView_shopping_activity;
import com.example.aerodromeApp.dataModel.datamodel;

import java.util.ArrayList;


public class shopping_myadapter extends RecyclerView.Adapter<shopping_myadapter.myviewholder>  {

    ArrayList<datamodel> dataholder;
    Activity context;

    public shopping_myadapter(Activity context) {
        this.context = context;
    }

    public shopping_myadapter(ArrayList<datamodel> dataholder, Activity context) {
        this.dataholder = dataholder;
        this.context = context;
    }



   // CustomItemClickListener listener;

    public shopping_myadapter(ArrayList<datamodel> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design_template,parent,false);

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
                Intent intent = new Intent(context, gridView_shopping_activity.class);
                context.startActivity(intent);
            }
        });

            }


            @Override
            public int getItemCount() {
                return dataholder.size();
            }

            class myviewholder extends RecyclerView.ViewHolder {

                ImageView img;
                TextView name, description;
                View mainView;


                public myviewholder(@NonNull View itemView) {
                    super(itemView);
                    mainView = itemView;

                    img = itemView.findViewById(R.id.shopping_image1);
                    name = itemView.findViewById(R.id.shopName);
                    description = itemView.findViewById(R.id.shopDescription);

                }
            }
        }

