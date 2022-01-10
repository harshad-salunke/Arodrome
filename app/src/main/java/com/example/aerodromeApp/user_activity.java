package com.example.aerodromeApp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.aerodromeApp.userActivity_fragment.userActivity_food_and_beverages_listView_fragment;
import com.example.aerodromeApp.userActivity_fragment.userActivity_shopping_fragment_listView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class user_activity extends AppCompatActivity {

    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer_userActivity, new userActivity_shopping_fragment_listView()).commit();

        bottom_navigation = findViewById(R.id.bottom_navigation_userActivity);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.shopping_userActivity:
                        fragment = new userActivity_shopping_fragment_listView();
                        break;

                    case R.id.food_beverages_userActivity:
                        fragment = new userActivity_food_and_beverages_listView_fragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer_userActivity, fragment).commit();

                return true;
            }
        });




    }
}