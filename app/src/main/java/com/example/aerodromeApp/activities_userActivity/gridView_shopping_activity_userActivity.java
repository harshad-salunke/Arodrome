package com.example.aerodromeApp.activities_userActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.aerodromeApp.R;
import com.example.aerodromeApp.dataModel.data_model_userActivity_gridView_shopping;
import com.example.aerodromeApp.databinding.ActivityMainBinding;
import com.example.aerodromeApp.userActivity_adapters.user_activity_gridView_shopping_adapter;

import java.util.ArrayList;

public class gridView_shopping_activity_userActivity extends AppCompatActivity {

    GridView coursesGV;
    ArrayList<data_model_userActivity_gridView_shopping> dataModel = new ArrayList<>();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_shopping_user);

        coursesGV = findViewById(R.id.gridView_shopping_activity_userActivity);

        coursesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                data_model_userActivity_gridView_shopping gridView_data = dataModel.get(position);

                Intent intent = new Intent(getApplicationContext(),shopping_page_userActivity.class);

                intent.putExtra("header",gridView_data.getHeader());
                intent.putExtra("description",gridView_data.getDescription());
                intent.putExtra("price",gridView_data.getPrice());


                startActivity(intent);
            }
        });




        data_model_userActivity_gridView_shopping ob1 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"Men Full Sleeves Casual Shirt",
                                                                                                         "This is maroon check casual shirt",1700);
        dataModel.add(ob1);

        data_model_userActivity_gridView_shopping ob2 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"Men Grey Casual Trousers",
                                                                                                 "This is grey textured casual trousers",1254);
        dataModel.add(ob2);

        data_model_userActivity_gridView_shopping ob3 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"Men White Crew Neck T-Shirt",
                                                                       "It is a blue color shirt with checked lines",781);
        dataModel.add(ob3);

        data_model_userActivity_gridView_shopping ob4 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"Men White Crew Neck T-Shirt",
                                                                       "White graphic print crew neck T-shirt",1877);
        dataModel.add(ob4);

        data_model_userActivity_gridView_shopping ob5 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"Men Navy Jeans",
                                                                          "Navy Dark wash jeans ",999);
        dataModel.add(ob5);

        data_model_userActivity_gridView_shopping ob6 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"Men Khaki Chinos",
                                                                       "khaki trousers Made from cotton with stretch",1200);
        dataModel.add(ob6);

        data_model_userActivity_gridView_shopping ob8 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"White leather low sneakers",
                                                                      "It is a white color printed sneakers",1007);
        dataModel.add(ob8);

        data_model_userActivity_gridView_shopping ob9 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"Shirt",
                                                                     "It is a blue color shirt with checked lines and printed design",1000);
        dataModel.add(ob9);

        data_model_userActivity_gridView_shopping ob10 = new data_model_userActivity_gridView_shopping(R.drawable.shopping,"Men Black Reversible Belt",
                                                                        "This black belt which will lend a perfect finish to your ensemble.",100);
        dataModel.add(ob10);


        // gfg code
        user_activity_gridView_shopping_adapter adapter = new user_activity_gridView_shopping_adapter(this,dataModel);
        coursesGV.setAdapter(adapter);



    }
}