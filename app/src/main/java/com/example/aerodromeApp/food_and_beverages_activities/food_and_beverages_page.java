package com.example.aerodromeApp.food_and_beverages_activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.aerodromeApp.R;

import java.util.ArrayList;

public class food_and_beverages_page extends AppCompatActivity {

    ArrayList<custom_arrayList_food_and_beverages_page> dataModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_beverages_page);


        ImageView img = findViewById(R.id.food_and_beverage_page_image);
        TextView header  =  findViewById(R.id.food_and_beverage_page_item_name);
        TextView description = findViewById(R.id.food_and_beverage_page_description);
        TextView size = findViewById(R.id.food_and_beverage_page_size);
        TextView  inStock = findViewById(R.id.food_and_beverage_page_stock);
        TextView price = findViewById(R.id.food_and_beverage_page_price);
        TextView nutrients = findViewById(R.id.food_and_beverage_page_nutrients);

        custom_arrayList_food_and_beverages_page ob1 = new custom_arrayList_food_and_beverages_page(R.drawable.beverage,100,"Burger shop",
                "This is a burger shop which makes tasty burger",
                "Large size burger, Medium size burger, Small size burger",
                "Yes","Protein:10g \nCarbohydrates:8g\nFats:20g\nVitamin:1g");

        img.setImageResource(ob1.getImage());
        header.setText(ob1.getHeader());
        description.setText(ob1.getDescription());
        size.setText(ob1.getSize());
        inStock.setText(ob1.getInStock());
        price.setText("₹"+Integer.toString(ob1.getPrice()));
        nutrients.setText(ob1.getNutrients());
        return  ;

    }
}