package com.example.aerodromeApp.VideoEditerFolder;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLException;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.daasuu.gpuv.camerarecorder.CameraRecordListener;
import com.daasuu.gpuv.camerarecorder.GPUCameraRecorder;
import com.daasuu.gpuv.camerarecorder.GPUCameraRecorderBuilder;
import com.daasuu.gpuv.camerarecorder.LensFacing;
import com.example.aerodromeApp.R;
import com.example.aerodromeApp.RelaxoActivity;
import com.example.aerodromeApp.SoundActivity;
import com.example.aerodromeApp.UploadVideoActivity2;
import com.example.aerodromeApp.VideoEditerFolder.widget.SampleCameraGLView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

public class BaseCameraActivity extends AppCompatActivity {

    private SampleCameraGLView sampleGLView;
    protected GPUCameraRecorder GPUCameraRecorder;
    private String filepath;
    private ImageView recordBtn;
    private ImageView pause_record;
    protected LensFacing lensFacing = LensFacing.BACK;
    protected int cameraWidth = 1280;
    protected int cameraHeight = 720;
    protected int videoWidth = 720;
    protected int videoHeight = 720;

    private boolean toggleClick = false;

    private ListView lv;
    private ImageView photo_fileter_list ,filter_list_down;
    private Button add_sound;
    String soundTitle;
    String soundLocation;
    MediaPlayer mPlayer;
    CountDownTimer countDownTimer;
    TextView video_counter;
    ImageView cancel_activity;

    protected void onCreateActivity() {
        recordBtn = findViewById(R.id.btn_record);
        pause_record=findViewById(R.id.btn_record_pause);
        photo_fileter_list=findViewById(R.id.photo_filter);
        filter_list_down=findViewById(R.id.photo_filter_down);
        add_sound=findViewById(R.id.addSong);
        lv = findViewById(R.id.filter_list);
        video_counter=findViewById(R.id.video_counter);
        cancel_activity=findViewById(R.id.close_activity);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Intent intent=getIntent();
         soundTitle=intent.getStringExtra("sound_title");
         soundLocation=intent.getStringExtra("sound_sound");

       if(soundTitle!=null){
           Toast.makeText(this, "Sound Added", Toast.LENGTH_SHORT).show();
           add_sound.setText(soundTitle);
           mPlayer = new MediaPlayer();
           mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
           try {
               Uri location_uri=Uri.parse(soundLocation);
               mPlayer.setDataSource(this, location_uri);
               mPlayer.prepare();
           } catch (IOException e) {
               Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

           }
       }

        Glide.with(this).load(R.drawable.record).into(recordBtn);

        recordBtn.setOnClickListener(v -> {
            cancel_activity.setClickable(false);
            filepath = getVideoFilePath();
            GPUCameraRecorder.start(filepath);
            recordBtn.setVisibility(View.GONE);
            Toast.makeText(this, "Recording Started", Toast.LENGTH_SHORT).show();
            if(mPlayer!=null){
                mPlayer.start();
            }
            Glide.with(this).load(R.drawable.pause).into(pause_record);
            pause_record.setVisibility(View.VISIBLE);

            add_sound.setClickable(false);

            if(countDownTimer!=null){
                countDownTimer.cancel();
                countDownTimer=null;
            }
            countDownTimer=new CountDownTimer(30000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long time=millisUntilFinished/1000;
                    video_counter.setText(time+"s");
                }

                @Override
                public void onFinish() {
                    Pause_video();
                }
            }.start();

        });

        pause_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pause_video();
            }
        });

        findViewById(R.id.btn_flash).setOnClickListener(v -> {
            if (GPUCameraRecorder != null && GPUCameraRecorder.isFlashSupport()) {
                GPUCameraRecorder.switchFlashMode();
                GPUCameraRecorder.changeAutoFocus();
            }
        });

        findViewById(R.id.btn_switch_camera).setOnClickListener(v -> {
            releaseCamera();
            if (lensFacing == LensFacing.BACK) {
                lensFacing = LensFacing.FRONT;
            } else {
                lensFacing = LensFacing.BACK;
            }
            toggleClick = true;
        });

        findViewById(R.id.btn_image_capture).setOnClickListener(v -> {
            captureBitmap(bitmap -> {
                new Handler().post(() -> {
                    String imagePath = getImageFilePath();
                    saveAsPngImage(bitmap, imagePath);
                    exportPngToGallery(getApplicationContext(), imagePath);
                });
            });
        });



      photo_fileter_list.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              photo_fileter_list.setVisibility(View.GONE);
              filter_list_down.setVisibility(View.VISIBLE);
              lv.setVisibility(View.VISIBLE);
          }
      });

      filter_list_down.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              photo_fileter_list.setVisibility(View.VISIBLE);
              filter_list_down.setVisibility(View.GONE);
              lv.setVisibility(View.GONE);

          }
      });

        final List<FilterType> filterTypes = FilterType.createFilterList();
        lv.setAdapter(new FilterAdapter(this, R.layout.row_white_text, filterTypes).whiteMode());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (GPUCameraRecorder != null) {
                    GPUCameraRecorder.setFilter(FilterType.createGlFilter(filterTypes.get(position), getApplicationContext()));
                }
            }
        });



        add_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BaseCameraActivity.this, SoundActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(BaseCameraActivity.this);

            }
        });

        cancel_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

private void Pause_video(){
    cancel_activity.setClickable(false);
    if(GPUCameraRecorder!=null){
    GPUCameraRecorder.stop();
    }
    countDownTimer.cancel();
    Toast.makeText(BaseCameraActivity.this, "You can shoot max 30 sec video", Toast.LENGTH_SHORT).show();
    recordBtn.setVisibility(View.VISIBLE);
    pause_record.setVisibility(View.GONE);
    if(mPlayer!=null){
        mPlayer.stop();
    }

    add_sound.setClickable(true);
    video_counter.setText("30s");

    Intent intent=new Intent(BaseCameraActivity.this, UploadVideoActivity2.class);
    intent.putExtra("Video_Path",filepath);
    startActivity(intent);
    Animatoo.animateSwipeLeft(this);
    finishAffinity();
}

    @Override
    protected void onResume() {
        super.onResume();
        setUpCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseCamera();
    }

    private void releaseCamera() {
        if (sampleGLView != null) {
            sampleGLView.onPause();
        }

        if (GPUCameraRecorder != null) {
            GPUCameraRecorder.stop();
            GPUCameraRecorder.release();
            GPUCameraRecorder = null;
        }

        if (sampleGLView != null) {
            ((FrameLayout) findViewById(R.id.wrap_view)).removeView(sampleGLView);
            sampleGLView = null;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(BaseCameraActivity.this, RelaxoActivity.class);
        startActivity(intent);
        finishAffinity();
        Animatoo.animateSwipeLeft(this);
    }

    private void setUpCameraView() {
        runOnUiThread(() -> {
            FrameLayout frameLayout = findViewById(R.id.wrap_view);
            frameLayout.removeAllViews();
            sampleGLView = null;
            sampleGLView = new SampleCameraGLView(getApplicationContext());
            sampleGLView.setTouchListener((event, width, height) -> {
                if (GPUCameraRecorder == null) return;
                GPUCameraRecorder.changeManualFocusPoint(event.getX(), event.getY(), width, height);
            });
            frameLayout.addView(sampleGLView);
        });
    }


    private void setUpCamera() {
        setUpCameraView();

        GPUCameraRecorder = new GPUCameraRecorderBuilder(this, sampleGLView)
                //.recordNoFilter(true)
                .cameraRecordListener(new CameraRecordListener() {
                    @Override
                    public void onGetFlashSupport(boolean flashSupport) {
                        runOnUiThread(() -> {
                            findViewById(R.id.btn_flash).setEnabled(flashSupport);
                        });
                    }

                    @Override
                    public void onRecordComplete() {
                        exportMp4ToGallery(getApplicationContext(), filepath);
                    }

                    @Override
                    public void onRecordStart() {
                        runOnUiThread(() -> {
                            lv.setVisibility(View.GONE);
                        });
                    }

                    @Override
                    public void onError(Exception exception) {
                        Log.e("GPUCameraRecorder", exception.toString());
                    }

                    @Override
                    public void onCameraThreadFinish() {
                        if (toggleClick) {
                            runOnUiThread(() -> {
                                setUpCamera();
                            });
                        }
                        toggleClick = false;
                    }

                    @Override
                    public void onVideoFileReady() {

                    }
                })
                .videoSize(videoWidth, videoHeight)
                .cameraSize(cameraWidth, cameraHeight)
                .lensFacing(lensFacing)
                .build();


    }

//    private void changeFilter(Filters filters) {
//        GPUCameraRecorder.setFilter(Filters.getFilterInstance(filters, getApplicationContext()));
//    }


    private interface BitmapReadyCallbacks {
        void onBitmapReady(Bitmap bitmap);
    }

    private void captureBitmap(final BitmapReadyCallbacks bitmapReadyCallbacks) {
        sampleGLView.queueEvent(() -> {
            EGL10 egl = (EGL10) EGLContext.getEGL();
            GL10 gl = (GL10) egl.eglGetCurrentContext().getGL();
            Bitmap snapshotBitmap = createBitmapFromGLSurface(sampleGLView.getMeasuredWidth(), sampleGLView.getMeasuredHeight(), gl);

            runOnUiThread(() -> {
                bitmapReadyCallbacks.onBitmapReady(snapshotBitmap);
            });
        });
    }

    private Bitmap createBitmapFromGLSurface(int w, int h, GL10 gl) {

        int bitmapBuffer[] = new int[w * h];
        int bitmapSource[] = new int[w * h];
        IntBuffer intBuffer = IntBuffer.wrap(bitmapBuffer);
        intBuffer.position(0);

        try {
            gl.glReadPixels(0, 0, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, intBuffer);
            int offset1, offset2, texturePixel, blue, red, pixel;
            for (int i = 0; i < h; i++) {
                offset1 = i * w;
                offset2 = (h - i - 1) * w;
                for (int j = 0; j < w; j++) {
                    texturePixel = bitmapBuffer[offset1 + j];
                    blue = (texturePixel >> 16) & 0xff;
                    red = (texturePixel << 16) & 0x00ff0000;
                    pixel = (texturePixel & 0xff00ff00) | red | blue;
                    bitmapSource[offset2 + j] = pixel;
                }
            }
        } catch (GLException e) {
            Log.e("CreateBitmap", "createBitmapFromGLSurface: " + e.getMessage(), e);
            return null;
        }

        return Bitmap.createBitmap(bitmapSource, w, h, Bitmap.Config.ARGB_8888);
    }

    public void saveAsPngImage(Bitmap bitmap, String filePath) {
        try {
            File file = new File(filePath);
            FileOutputStream outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void exportMp4ToGallery(Context context, String filePath) {
        final ContentValues values = new ContentValues(2);
        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        values.put(MediaStore.Video.Media.DATA, filePath);
        context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                values);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + filePath)));
    }

    public static String getVideoFilePath() {
        return getAndroidMoviesFolder().getAbsolutePath() + "/" + new SimpleDateFormat("yyyyMM_dd-HHmmss").format(new Date()) + "GPUCameraRecorder.mp4";
    }

    public static File getAndroidMoviesFolder() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
    }

    private static void exportPngToGallery(Context context, String filePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(filePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    public static String getImageFilePath() {
        return getAndroidImageFolder().getAbsolutePath() + "/" + new SimpleDateFormat("yyyyMM_dd-HHmmss").format(new Date()) + "GPUCameraRecorder.png";
    }

    public static File getAndroidImageFolder() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    }

}
