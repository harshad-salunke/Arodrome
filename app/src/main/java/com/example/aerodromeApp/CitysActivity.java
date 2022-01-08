package com.example.aerodromeApp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.aerodromeApp.adapter.City_adapter;
import com.google.android.material.tabs.TabLayout;

public class CitysActivity extends AppCompatActivity {
    GridView gridView;
    City_adapter city_adapter;
    TabLayout tabLayout;
    int city[]={R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citys);


        tabLayout=findViewById(R.id.tabLayout);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Popular Cities");
        String[] myResArray = getResources().getStringArray(R.array.citys);
        city_adapter=new City_adapter(this,myResArray,city);
        gridView=findViewById(R.id.city_grid);
        gridView.setAdapter(city_adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}