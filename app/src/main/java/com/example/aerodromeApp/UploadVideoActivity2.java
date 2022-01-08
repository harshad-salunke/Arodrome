package com.example.aerodromeApp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.aerodromeApp.VideoEditerFolder.PortraitCameraActivity;


import java.io.File;

public class UploadVideoActivity2 extends AppCompatActivity {
    String Video_Path=null;
    VideoView videoView;
    EditText editText;
    LinearLayout linearLayout;
    Button upload_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video2);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Post");
        videoView=findViewById(R.id.uploaded_vido);
        editText=findViewById(R.id.discribstion);
        linearLayout=findViewById(R.id.upload_share_video);
        upload_btn=findViewById(R.id.upload_btn);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent=getIntent();
        Video_Path=intent.getStringExtra("Video_Path");

        if(Video_Path!=null){
            File file=new File(Video_Path.trim());
            try {
              CountDownTimer  countDownTimer=new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                    @Override
                    public void onFinish() {
                        playMyVideoView(file,true);
                    }
                }.start();
            }
            catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Something is error you can not upload video !!", Toast.LENGTH_SHORT).show();
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f=new File(Video_Path.trim());
                Uri uri2 = Uri.fromFile(f);
                Intent videoshare = new Intent(Intent.ACTION_SEND);
                videoshare.setType("video/*");
                videoshare.putExtra(Intent.EXTRA_STREAM,uri2);
                startActivity(videoshare);
            }
        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String discription_string=editText.getText().toString();
                Intent intent=new Intent(UploadVideoActivity2.this, RelaxoActivity.class);
                intent.putExtra("video_path",Video_Path);
                intent.putExtra("discription",discription_string);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(UploadVideoActivity2.this, PortraitCameraActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeRight(this);
        finish();

        super.onBackPressed();
    }

//    private File mainMashupVideoFileName = "your_file_path/file_name.mp4";

    private void playMyVideoView(File fileName, boolean isMainMashup) {
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.fromFile(fileName));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //Resource has been loaded to the video view player and ready to play.
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //Can change play pause icon
            }
        });

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                playPauseVideos();
                return false;
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                //Do nothing
                return true;
            }
        });

        videoView.requestFocus();
        videoView.start();
    }
    //End of  run VideoView

    private void playPauseVideos() {
        if (videoView.isPlaying()) {
            videoView.pause();
        } else {
            videoView.requestFocus();
            videoView.start();
        }
    }


}