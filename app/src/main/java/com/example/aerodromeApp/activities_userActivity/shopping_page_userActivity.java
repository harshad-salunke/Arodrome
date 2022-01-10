package com.example.aerodromeApp.activities_userActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aerodromeApp.R;
import com.example.aerodromeApp.activities_shopping.custom_ArrayList_shopping_page;


public class shopping_page_userActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_page_user);

        Intent intent = getIntent();

        String headers = intent.getStringExtra("header");
        String descriptions = intent.getStringExtra("description");

        int prices = intent.getIntExtra("price",0);


        ImageView img = findViewById(R.id.shopping_page_image_userActivity);
        TextView header  =  findViewById(R.id.shopping_page_item_name_userActivity);
        TextView description = findViewById(R.id.shopping_page_description_userActivity);
        TextView size = findViewById(R.id.shopping_page_size_userActivity);
        TextView  inStock = findViewById(R.id.shopping_page_stock_userActivity);
        TextView price = findViewById(R.id.shopping_page_price_userActivity);




        custom_ArrayList_shopping_page ob1 = new custom_ArrayList_shopping_page(R.drawable.shopping,100,"Shirt",
                "This is a unlike color shirt with differnet design","Medium","Yes");

        img.setImageResource(ob1.getImage());
        header.setText(headers);
        description.setText(descriptions);
        size.setText(ob1.getSize());
        inStock.setText(ob1.getInStock());
        price.setText("₹"+prices);



    }
}