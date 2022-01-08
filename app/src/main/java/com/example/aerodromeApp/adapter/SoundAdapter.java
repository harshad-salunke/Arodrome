package com.example.aerodromeApp.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import  com.example.aerodromeApp.Model.SoundModle;
import com.example.aerodromeApp.R;

import java.io.IOException;
import java.util.List;

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundViewHolder> {

    List<SoundModle> soundModles;
    Context context;
    MediaPlayer mPlayer;
    int CurrentPosition=-1;

    public SoundAdapter(List<SoundModle> soundModles, Context context) {
        this.soundModles = soundModles;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public SoundViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_sound,parent,false);
        return new SoundViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull SoundAdapter.SoundViewHolder holder, int position) {
            SoundModle soundModle=soundModles.get(position);
            System.out.println(soundModle.getSound_sound());
            holder.title.setText(soundModle.getSound_title());
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CurrentPosition=position;
                    Uri myUri = Uri.parse(soundModle.getSound_sound());
                    if(mPlayer!=null){
                        mPlayer.stop();
                        mPlayer=null;
                        mPlayer = new MediaPlayer();
                        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mPlayer.setDataSource(context, myUri);
                            mPlayer.prepare();
                            mPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                   if (mPlayer==null){
                       mPlayer = new MediaPlayer();
                       mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                       try {
                           mPlayer.setDataSource(context, myUri);
                           mPlayer.prepare();
                           mPlayer.start();
                       } catch (IOException e) {
                           e.printStackTrace();

                       }
                   }

                }
            });
        Uri image=Uri.parse(soundModle.getSoound_img());
        Glide.with(context).asBitmap()
                .load(image).thumbnail(0.5f).placeholder(R.drawable.add_sound)
                .into(holder.imageView);
    }



    @Override
    public int getItemCount() {
        return  soundModles.size();    }

    public  class SoundViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        View item;
        public SoundViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            item=itemView;
            title=itemView.findViewById(R.id.song_title);
            imageView=itemView.findViewById(R.id.song_image);
        }
    }

  public   SoundModle getCurrentModel(){
        if(mPlayer!=null){
            mPlayer.stop();
            mPlayer=null;
        }
        SoundModle curent=null;
        if(CurrentPosition>=0){
            curent=soundModles.get(CurrentPosition);
        }
        return  curent;
    }

    public void off_Music(){
        if(mPlayer!=null){
            mPlayer.stop();
            mPlayer=null;
        }
    }
}
