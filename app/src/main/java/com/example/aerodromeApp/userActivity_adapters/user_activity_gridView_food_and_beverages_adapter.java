package com.example.aerodromeApp.userActivity_adapters;

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
import com.example.aerodromeApp.dataModel.data_model_userActivity_gridView_food_and_beverages;

import java.util.ArrayList;


public class user_activity_gridView_food_and_beverages_adapter extends ArrayAdapter<data_model_userActivity_gridView_food_and_beverages> {


    public user_activity_gridView_food_and_beverages_adapter(@NonNull Context context, ArrayList<data_model_userActivity_gridView_food_and_beverages> courseModelArrayList ) {
        super(context, 0, courseModelArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.single_column_design_food_and_beverages_user_activity, parent, false);
        }


        data_model_userActivity_gridView_food_and_beverages courseModel = getItem(position);
        TextView name = listitemView.findViewById(R.id.shop_item_Name_gridView_food_and_beverage_userActivity);
        TextView description = listitemView.findViewById(R.id.shop_item_description_gridView_food_and_beverages_userActivity);
        TextView price = listitemView.findViewById(R.id.price_gridView_food_and_beverages_userActivity);
        ImageView image = listitemView.findViewById(R.id.gridImage_food_and_beverage_userActivity);

        name.setText(courseModel.getHeader());
        description.setText(courseModel.getDescription());
        price.setText(Integer.toString(courseModel.getPrice()).toString());
        image.setImageResource(courseModel.getImage());
        return listitemView;




    }


}
