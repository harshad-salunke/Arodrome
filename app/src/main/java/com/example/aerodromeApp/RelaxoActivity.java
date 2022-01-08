package com.example.aerodromeApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.aerodromeApp.Model.UserObject;
import com.example.aerodromeApp.VideoEditerFolder.PortraitCameraActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class RelaxoActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
float x1,x2,y1,y2;
    static final int MIN_DISTANCE = 150;
 final  int   CAMERA_PERMISION_CODE=1011;
    ShimmerRecyclerView recyclerView;
    Video_adapter video_adapter;
    ArrayList<UserObject> userObjects;
    FloatingActionButton floatingActionButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    //replace this with FirebaseUser to get current user Uid and name because my firebase auth not working so i place temp user
    String firbaseuser="Harshad salunke";


    int PICK_VIDEO_REQUEST=000;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_relaxo);
        Botton_Navigation();
        cardView=findViewById(R.id.cardviewup);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("relaxo");
        firebaseStorage=FirebaseStorage.getInstance("gs://pmpml-5623d.appspot.com");
        storageReference=firebaseStorage.getReference().child("relaxo");
        Intent intent=getIntent();
      String video_pathFile=  intent.getStringExtra("video_path");
      String discription=intent.getStringExtra("discription");
        if(video_pathFile!=null){
            Toast.makeText(this, "video Uploaded", Toast.LENGTH_SHORT).show();
            UserObject video_user=new UserObject(discription,firbaseuser,"",firbaseuser,video_pathFile);
            Upload_Video(video_user);
            cardView.setVisibility(View.VISIBLE);
        }
        recyclerView = findViewById(R.id.recycler_view);
        userObjects = new ArrayList<>();
        video_adapter = new Video_adapter(this, userObjects);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(video_adapter);
        recyclerView.showShimmerAdapter();
        RetriveVideo(databaseReference.child("Videos"));


    }
    private  void Botton_Navigation(){
        bottomNavigationView=findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setSelectedItemId(R.id.Mrelaxo);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.Mhome:
                        intent=new Intent(RelaxoActivity.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mshop:
                        intent=new Intent(RelaxoActivity.this,ShopActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mmap:
                        intent=new Intent(RelaxoActivity.this,MapActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.Mrelaxo:

                        return  true;
                    case R.id.Mprofile:
                        intent=new Intent(RelaxoActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                }
                return false;
            }
        });
        findViewById(R.id.gotoCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkpermision();
            }
        });
    }





    public void Checkpermision() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISION_CODE);

        } else {
            Intent intent = new Intent(RelaxoActivity.this, PortraitCameraActivity.class);
            startActivity(intent);

            Animatoo.animateSwipeRight(this);


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean check = true;

        switch (requestCode) {
            case CAMERA_PERMISION_CODE:

                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        check = false;
                    }
                }
                if (check) {
                    Intent intent = new Intent(RelaxoActivity.this, PortraitCameraActivity.class);
                    startActivity(intent);
                    Animatoo.animateSwipeRight(this);



                } else {
                    Toast.makeText(this, "All permission need", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }





    public  void Upload_Video(UserObject userObject){
        Uri video_url=Uri.parse(userObject.getMedia_url());
        InputStream stream=null;
        try {
             stream = new FileInputStream(new File(userObject.getMedia_url()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            cardView.setVisibility(View.GONE);

        }

        Toast.makeText(this, video_url.toString(), Toast.LENGTH_SHORT).show();

        storageReference.child(video_url.getLastPathSegment()).putStream(stream).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if (taskSnapshot!=null){
                    Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            userObject.setMedia_url(uri.toString());
                            cardView.setVisibility(View.GONE);
//                            Toast.makeText(RelaxoActivity.this, "All done ", Toast.LENGTH_SHORT).show();
                            databaseReference.child("User").child(firbaseuser).push().setValue(userObject);
                            databaseReference.child("Videos").push().setValue(userObject);
                            Toast.makeText(RelaxoActivity.this, "Video Uploaded successfuly", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(RelaxoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("harshad","during uri"+e.getLocalizedMessage());
                            cardView.setVisibility(View.GONE);
                        }
                    });
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(RelaxoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                Log.d("harshad","during video"+e.getLocalizedMessage());
                cardView.setVisibility(View.GONE);
            }
        });
    }
    public void RetriveVideo(DatabaseReference retriveRefrance){
        databaseReference.child("Videos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                userObjects.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    UserObject user = snapshot1.getValue(UserObject.class);
                    userObjects.add(user);
                }
                video_adapter.notifyDataSetChanged();
                recyclerView.hideShimmerAdapter();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}