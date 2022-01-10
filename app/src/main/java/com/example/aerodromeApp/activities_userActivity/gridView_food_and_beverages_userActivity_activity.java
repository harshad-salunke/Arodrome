package com.example.aerodromeApp.activities_userActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.aerodromeApp.R;
import com.example.aerodromeApp.dataModel.data_model_userActivity_gridView_food_and_beverages;
import com.example.aerodromeApp.userActivity_adapters.user_activity_gridView_food_and_beverages_adapter;

import java.util.ArrayList;

public class gridView_food_and_beverages_userActivity_activity extends AppCompatActivity {

    GridView coursesGV;
    ArrayList<data_model_userActivity_gridView_food_and_beverages> dataModel = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_food_and_beverages_user_activity);

        coursesGV = findViewById(R.id.gridView_food_and_beverages_activity_userActivity);

        coursesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getApplicationContext(),new food_and_beverages_page_userActivity().getClass());


                startActivity(intent);
            }
        });


        data_model_userActivity_gridView_food_and_beverages ob1 =
                new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,"Crispy veg burger","It has some good variety of vegan burgers",50);
        dataModel.add(ob1);

        data_model_userActivity_gridView_food_and_beverages ob2 = new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,
                                              "Classic veg burger","It has veg patty with american flavours",254);
        dataModel.add(ob2);

        data_model_userActivity_gridView_food_and_beverages ob3 = new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,
                                                                    "Paneer burger","It has patty with panner sandwiched with spices and vegies ",81);
        dataModel.add(ob3);

        data_model_userActivity_gridView_food_and_beverages ob4 = new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,"Chicken burger",
                                                                                            "It has crisp burger with chicken patty and lots of vegies",177);
        dataModel.add(ob4);

        data_model_userActivity_gridView_food_and_beverages ob5 = new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,"Veg-Pizza",
                                                                                            "Veg pizza has double thin crisp layer of crust with lots of veggie's",100);
        dataModel.add(ob5);

        data_model_userActivity_gridView_food_and_beverages ob6 = new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,"Special Mushroom Burger",
                                                                                            "It is a unique burger which consist of veggies and mushroom",100);
        dataModel.add(ob6);

        data_model_userActivity_gridView_food_and_beverages ob8 = new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,"Espresso",
                                                                                            "Start you day with power pack coffee",107);
        dataModel.add(ob8);

        data_model_userActivity_gridView_food_and_beverages ob9 = new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,"Cafe latte",
                                                                                           "It has espresso mixed with streamed milk",100);
        dataModel.add(ob9);

        data_model_userActivity_gridView_food_and_beverages ob10 = new data_model_userActivity_gridView_food_and_beverages(R.drawable.beverage,"Chai",
                                                                                            "It is a tea beverage ",100);
        dataModel.add(ob10);



        user_activity_gridView_food_and_beverages_adapter adapter = new user_activity_gridView_food_and_beverages_adapter(this,dataModel);
        coursesGV.setAdapter(adapter);



    }
}