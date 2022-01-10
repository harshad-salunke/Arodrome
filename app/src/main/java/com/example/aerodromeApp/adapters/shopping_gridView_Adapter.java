package com.example.aerodromeApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aerodromeApp.R;
import com.example.aerodromeApp.dataModel.data_model_gridView;

import java.util.ArrayList;


// gfg code
    public class shopping_gridView_Adapter extends ArrayAdapter<data_model_gridView> {


        public shopping_gridView_Adapter(@NonNull Context context, ArrayList<data_model_gridView> courseModelArrayList) {
            super(context, 0, courseModelArrayList);
        }



        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listitemView = convertView;
            if (listitemView == null) {
                // Layout Inflater inflates each item to be displayed in GridView.
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.single_column_design_template, parent, false);
            }
            data_model_gridView courseModel = getItem(position);
            TextView name = listitemView.findViewById(R.id.shop_item_Name_gridView);
            TextView description = listitemView.findViewById(R.id.shop_item_description_gridView);
            TextView price = listitemView.findViewById(R.id.price_gridView);
            ImageView image = listitemView.findViewById(R.id.gridImage);
            name.setText(courseModel.getHeader());
            description.setText(courseModel.getDescription());
            price.setText(Integer.toString(courseModel.getPrice()).toString());
            image.setImageResource(courseModel.getImage());
            return listitemView;

        }


    }



