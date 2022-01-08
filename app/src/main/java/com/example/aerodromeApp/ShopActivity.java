package com.example.aerodromeApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ShopActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        bottomNavigationView=findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setSelectedItemId(R.id.Mshop);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.Mhome:
                        intent=new Intent(ShopActivity.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mshop:
                        return true;
                    case R.id.Mmap:
                        intent=new Intent(ShopActivity.this,MapActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mrelaxo:
                        intent=new Intent(ShopActivity.this,RelaxoActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return  true;
                    case R.id.Mprofile:
                        intent=new Intent(ShopActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                }
                return false;
            }
        });
    }
}