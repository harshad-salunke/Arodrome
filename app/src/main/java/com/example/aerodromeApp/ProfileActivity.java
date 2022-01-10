package com.example.aerodromeApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView=findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setSelectedItemId(R.id.Mprofile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.Mhome:
                        intent=new Intent(ProfileActivity.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mshop:
                        Intent userItn = new Intent(ProfileActivity.this,user_activity.class);
                        startActivity(userItn);
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.Mmap:
                        intent=new Intent(ProfileActivity.this,MapActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mrelaxo:
                        intent=new Intent(ProfileActivity.this,RelaxoActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return  true;
                    case R.id.Mprofile:

                        return true ;
                }
                return false;
            }
        });
    }
}