package com.example.aerodromeApp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.aerodromeApp.adapter.SoundAdapter;
import com.example.aerodromeApp.Model.SoundModle;
import com.example.aerodromeApp.VideoEditerFolder.PortraitCameraActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SoundActivity extends AppCompatActivity {

    SoundAdapter soundAdapter;
    RecyclerView recyclerView;
    List<SoundModle> soundModleList;
    FloatingActionButton add_songBTN;
    ImageView close_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        recyclerView=findViewById(R.id.sound_list_recy);
        add_songBTN=findViewById(R.id.add_song_btn);
        close_activity=findViewById(R.id.close_activity);

        soundModleList=new ArrayList<>();
        getListOfSong1();
        soundAdapter=new SoundAdapter(soundModleList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(soundAdapter);
        soundAdapter.notifyDataSetChanged();


        add_songBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundModle soundModle=soundAdapter.getCurrentModel();
                if(soundModle!=null){
                    Intent intent=new Intent(SoundActivity.this, PortraitCameraActivity.class);
                    intent.putExtra("sound_sound",soundModle.getSound_sound());
                    intent.putExtra("sound_title",soundModle.getSound_title());
                    startActivity( intent);
                    Animatoo.animateSlideUp(SoundActivity.this);
                    finishAffinity();
                }
                else{
                    Toast.makeText(SoundActivity.this, "Please Select Some Song..", Toast.LENGTH_SHORT).show();
                }

            }
        });

        close_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });
    }


    public void getListOfSong1() {
        ContentResolver contentResolver=getContentResolver();
        Uri songUri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor_song=contentResolver.query(songUri,null,null,null,null);
        if(cursor_song!=null && cursor_song.moveToFirst()){
            int songTitle=cursor_song.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int song_loc=cursor_song.getColumnIndex(MediaStore.Audio.Media.DATA);
//            Long albumId = cursor_song.getLong(cursor_song.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
            Toast.makeText(this, "not empty", Toast.LENGTH_SHORT).show();

            do{
                String Title=cursor_song.getString(songTitle);
                String Path=cursor_song.getString(song_loc);
                Long albumId = cursor_song.getLong(cursor_song.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                Uri sArtworkUri = Uri
                        .parse("content://media/external/audio/albumart");
                Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri,
                        albumId);
                soundModleList.add(new SoundModle(albumArtUri.toString(),Path,Title));

            }while (cursor_song.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        soundAdapter.off_Music();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SoundActivity.this, PortraitCameraActivity.class);
        startActivity( intent);
        Animatoo.animateSlideUp(SoundActivity.this);
        finishAffinity();
        super.onBackPressed();
    }
}