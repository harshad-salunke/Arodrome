package com.example.aerodromeApp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.aerodromeApp.authority_fragments.add_item_fragment;
import com.example.aerodromeApp.authority_fragments.food_and_beverages_fragment;
import com.example.aerodromeApp.authority_fragments.shopping_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class authority_activity extends AppCompatActivity {

    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        getSupportActionBar().hide();



        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new shopping_fragment()).commit();



        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.shopping:
                        fragment = new shopping_fragment();
                        break;

                    case R.id.add_items:
                        fragment = new add_item_fragment();
                        break;

                    case R.id.food_beverages:
                        fragment = new food_and_beverages_fragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();

                return true;
            }
        });

    }


}