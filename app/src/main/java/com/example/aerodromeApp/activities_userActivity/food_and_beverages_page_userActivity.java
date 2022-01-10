package com.example.aerodromeApp.activities_userActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aerodromeApp.R;


public class food_and_beverages_page_userActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_beverages_page_user);

       /* Intent intent = getIntent();
        String headers = intent.getStringExtra("header");
        String descriptions = intent.getStringExtra("description");*/

      //  int prices = intent.getIntExtra("price",0);

        ImageView img = findViewById(R.id.food_and_beverage_page_image_userActivity);
        TextView header  =  findViewById(R.id.food_and_beverage_page_item_name_userActivity);
        TextView description = findViewById(R.id.food_and_beverage_page_description_userActivity);
        TextView size = findViewById(R.id.food_and_beverage_page_size_userActivity);
        TextView  inStock = findViewById(R.id.food_and_beverage_page_stock_userActivity);
        TextView price = findViewById(R.id.food_and_beverage_page_price_userActivity);
        TextView nutrients = findViewById(R.id.food_and_beverage_page_nutrients_userActivity);


        custom_ArrayList_food_and_beverages_user_activity ob1 = new custom_ArrayList_food_and_beverages_user_activity(R.drawable.beverage,100,"Burger shop",
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


    }
}