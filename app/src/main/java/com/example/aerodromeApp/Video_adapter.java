package com.example.aerodromeApp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.example.aerodromeApp.Model.OnSwipeTouchListener;
import com.example.aerodromeApp.Model.UserObject;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Video_adapter extends RecyclerView.Adapter<Video_adapter.VideoViewHolder> {
        Context context;
        ArrayList<UserObject> userObjects;
    MotionEvent event;
    boolean play=true;
    Video_adapter.VideoViewHolder currentposition;
    public Video_adapter(Context context, ArrayList<UserObject> userObjects) {
        this.context = context;
        this.userObjects = userObjects;
    }

    @Override
    public VideoViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout,parent,false);
        return new VideoViewHolder(view);
    }


    @Override
    public void onBindViewHolder( Video_adapter.VideoViewHolder holder, int position) {
        currentposition=holder;
        holder.playerView.setControllerAutoShow(false);
        UserObject userObject=userObjects.get(position);
        holder.playerView.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(context, "onSwapeleft", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();

            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                holder.playerView.showController();
                return super.onTouch(view, motionEvent);

            }
        });

        holder.gotoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                release_curent_holder();
                ((RelaxoActivity)(context)).Checkpermision();
            }
        });
        holder.username.setText(userObject.getUser_name());
        holder.discription.setText(userObject.getDiscription());

        try{
            if (holder.isPlaying()) {

                holder.exoPlayer.release();
                holder.intiPlayer(userObject.getMedia_url());

            } else {
                holder.intiPlayer(userObject.getMedia_url());
            }

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Glide.with(context).load(R.drawable.djdisc).into(holder.discRoller);

    }

    @Override
    public void onViewAttachedToWindow(@NonNull @NotNull Video_adapter.VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return userObjects.size();
    }

    private void playPlayer(ExoPlayer player) {
        if (player != null) {
            player.setPlayWhenReady(true);
        }
    }

    private void pausePlayer(ExoPlayer player) {
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }
    public  void  release_curent_holder(){
        if (currentposition!=null){
            if (currentposition.exoPlayer != null) {
               currentposition. exoPlayer.setPlayWhenReady(false);
                currentposition.exoPlayer.stop();
                currentposition. exoPlayer.seekTo(0);
                Toast.makeText(context, "not null", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView discRoller;
        PlayerView playerView;
        SimpleExoPlayer exoPlayer;
        private long playbackPosition;
        private int currentWindow;
        private boolean playWhenReady;
        TextView username;
        TextView discription;
        ImageView gotoCamera;
        View view;
        public VideoViewHolder( View itemView) {
            super(itemView);
            view=itemView;
            discRoller=itemView.findViewById(R.id.discRoller);
            playerView =itemView.findViewById(R.id.videoView);
            username=itemView.findViewById(R.id.Username);
            discription=itemView.findViewById(R.id.discription);
            gotoCamera=itemView.findViewById(R.id.gotoCamera);
        }

        private void intiPlayer(String url) {
            try {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
                exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
                Uri videoURI = Uri.parse(url);
                DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);
                playerView.setPlayer(exoPlayer);
                exoPlayer.prepare(mediaSource);

                exoPlayer.setPlayWhenReady(false);
            } catch (Exception e) {
                Log.e("MainAcvtivity", " exoplayer error " + e.toString());
            }


        }
        private boolean isPlaying() {
            return exoPlayer != null
                    && exoPlayer.getPlaybackState() != Player.STATE_ENDED
                    && exoPlayer.getPlaybackState() != Player.STATE_IDLE
                    && exoPlayer.getPlayWhenReady();
        }

        private void releasePlayer() {
            if (exoPlayer != null) {
                playbackPosition = exoPlayer.getCurrentPosition();
                currentWindow = exoPlayer.getCurrentWindowIndex();
                playWhenReady = exoPlayer.getPlayWhenReady();
                exoPlayer.release();
                exoPlayer = null;
            }
        }

    }
}
