package com.example.aerodromeApp.activities_shopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.aerodromeApp.R;

import java.util.ArrayList;

public class shopping_page extends AppCompatActivity {



    ArrayList<custom_ArrayList_shopping_page> dataModels = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_page);

        Intent intent = getIntent();

        String headers = intent.getStringExtra("header");
        String descriptions = intent.getStringExtra("description");

        int prices = intent.getIntExtra("price",0);




       ImageView img = findViewById(R.id.shopping_page_image);
       TextView header  =  findViewById(R.id.shopping_page_item_name);
       TextView description = findViewById(R.id.shopping_page_description);
       TextView size = findViewById(R.id.shopping_page_size);
       TextView  inStock = findViewById(R.id.shopping_page_stock);
       TextView price = findViewById(R.id.shopping_page_price);




        custom_ArrayList_shopping_page ob1 = new custom_ArrayList_shopping_page(R.drawable.shopping,100,"Shirt",
                "This is a unlike color shirt with different design","Medium, small","Yes");

       img.setImageResource(ob1.getImage());
       header.setText(headers);
       description.setText(descriptions);
       size.setText(ob1.getSize());
       inStock.setText(ob1.getInStock());
       price.setText("₹"+prices);


    }
}