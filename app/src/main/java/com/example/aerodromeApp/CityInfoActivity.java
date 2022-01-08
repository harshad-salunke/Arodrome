package com.example.aerodromeApp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

public class CityInfoActivity extends AppCompatActivity {
    private TextView headText;
   private LinearLayout city_history;
    private LinearLayout city_hangout;
    private LinearLayout city_shopping;
    private LinearLayout city_restaurants;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);
         headText=findViewById(R.id.head);
         city_history=findViewById(R.id.city_history);
         city_hangout=findViewById(R.id.hangout);
         city_restaurants=findViewById(R.id.restau);
         city_shopping=findViewById(R.id.shoppp);

        Intent intent=getIntent();
        String title=intent.getStringExtra("city_name");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(title);
        headText.setText(title);


        ImageSlider imageSlider=findViewById(R.id.image_slider);
        List<SlideModel> imageList=new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.i4,"1"));
        imageList.add(new SlideModel(R.drawable.i5,"1"));
        imageList.add(new SlideModel(R.drawable.i7,"1"));
        imageList.add(new SlideModel(R.drawable.i16,"1"));
        imageSlider.setImageList(imageList,true);
        imageSlider.startSliding(1000);
        ExpandableTextView expTv1 = (ExpandableTextView)findViewById(R.id.extpandable_text);


// IMPORTANT - call setText on the ExpandableTextView to set the text content to display
        expTv1.setText("Mumbai (formerly called Bombay) is a densely populated city on India’s west coast. A financial center, it's India's largest city. On the Mumbai Harbour waterfront stands the iconic Gateway of India stone arch, built by the British Raj in 1924. Offshore, nearby Elephanta Island holds ancient cave temples dedicated to the Hindu god Shiva. The city's also famous as the heart of the Bollywood film industry");

        city_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String Url=  " https://www.google.com/search?sa=X&rlz=1C1GCEA_enIN977IN977&tbs=lf:1,lf_ui:10&tbm=lcl&sxsrf=AOaemvIKtugvljHsWjCoumTQkpVP6lX3JA:1639737154301&q=shopping+at+"+title;
//                Intent   click_intent=new Intent(Intent.ACTION_VIEW);
//                click_intent.setData(Uri.parse(Url));
//                startActivity(click_intent);
                Toast.makeText(CityInfoActivity.this, "Availabe soon", Toast.LENGTH_SHORT).show();
            }
        });
        city_hangout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent1=new Intent(CityInfoActivity.this,MapActivity.class);
                    intent1.putExtra("city_name2",title);
                    startActivity(intent1);
                    overridePendingTransition(0,0);
                    finishAffinity();

                }
                catch (Exception e){
                    Toast.makeText(CityInfoActivity.this, "Please check your network connections", Toast.LENGTH_SHORT).show();
                }



            }
        });
        city_restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String Url=" https://www.zomato.com";
//              Intent  restorant=new Intent(Intent.ACTION_VIEW);
//                restorant.setData(Uri.parse(Url));
//                startActivity(restorant);
                Toast.makeText(CityInfoActivity.this, "Availabe soon", Toast.LENGTH_SHORT).show();
            }
        });
        city_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String Url="https://en.wikipedia.org/wiki/"+title;
//
//
//                Intent   city_history2=new Intent(Intent.ACTION_VIEW);
//                city_history2.setData(Uri.parse(Url));
//               startActivity(city_history2);
                Toast.makeText(CityInfoActivity.this, "Availabe soon", Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected  void init_navi(){
        bottomNavigationView = findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setSelectedItemId(R.id.Mmap);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.Mhome:
                        intent = new Intent(CityInfoActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mshop:
                        intent = new Intent(CityInfoActivity.this, ShopActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mmap:
                        return true;
                    case R.id.Mrelaxo:
                        intent = new Intent(CityInfoActivity.this, RelaxoActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mprofile:
                        intent = new Intent(CityInfoActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}