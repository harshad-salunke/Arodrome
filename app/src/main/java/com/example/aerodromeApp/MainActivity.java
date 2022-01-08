package com.example.aerodromeApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    WebView htmlWebView;
    FloatingActionButton floatAction;
    boolean menu_open=true;
    Animation animation;
    CardView cardView;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setSelectedItemId(R.id.Mhome);
        init_navigation();

        floatAction=findViewById(R.id.bot_button);
        cardView=findViewById(R.id.cardView);
        progressBar=findViewById(R.id.bot_progress);
        htmlWebView = (WebView)findViewById(R.id.web_view);



        floatAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menu_open){
                    chatBot_On();
                    menu_open=false;
                }
                else {
                    chatBot_Off();
                    menu_open=true;
                }

            }
        });




    }

    private  void init_navigation(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.Mhome:
                        return true;
                    case R.id.Mshop:
                        intent=new Intent(MainActivity.this,ShopActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mmap:
                        intent=new Intent(MainActivity.this,MapActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mrelaxo:
                        intent=new Intent(MainActivity.this,RelaxoActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return  true;
                    case R.id.Mprofile:
                        intent=new Intent(MainActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                }
                return false;
            }
        });

    }


    private void chatBot_Off() {
        floatAction.setImageResource(R.drawable.bot);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bot_off);
        htmlWebView.setAnimation(animation);
        cardView.setAnimation(animation);

        cardView.setVisibility(View.GONE);
        htmlWebView.setVisibility(View.GONE);

    }

    private void chatBot_On() {
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bot_ani);
            cardView.setAnimation(animation);
            floatAction.setImageResource(R.drawable.ic_baseline_arrow_downward_24);
            cardView.setVisibility(View.VISIBLE);
            htmlWebView.setVisibility(View.VISIBLE);
            htmlWebView.setWebViewClient(new CustomWebViewClient());
            WebSettings webSetting = htmlWebView.getSettings();
            webSetting.setJavaScriptEnabled(true);
            webSetting.setDisplayZoomControls(false);
            htmlWebView.loadUrl("https://webchat.botframework.com/embed/arodrome-bot/gemini?b=arodrome-bot&s=m9RgcqrqxNk.7bajfR0X-RAat_dR43dY9dyF823nrIXGbWhHM_EMOAY&username");

        } else {
            Toast.makeText(MainActivity.this, "No internate connection", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);

        }
    }
}