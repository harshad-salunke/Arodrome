package com.example.aerodromeApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.SimpleOnSearchActionListener;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    BottomNavigationView bottomNavigationView;
    private GoogleMap mgoogleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private PlacesClient placesClient;
    private List<AutocompletePrediction> predictionList;
    private Location location;
    private LocationCallback locationCallback;
    private TabLayout tabLayout;
    private MaterialSearchBar searchBar;
    private View mapView;
    private String intent_string;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        init_navi();
        Intent intent=getIntent();
        intent_string=intent.getStringExtra("city_name2");

        CheckLocationPermission();
        Places.initialize(MapActivity.this,"AIzaSyDn4-Dxr07FtO4x2JTYnC6Bh2rvwNA3cXA");
        placesClient=Places.createClient(MapActivity.this);

        AutocompleteSessionToken token=AutocompleteSessionToken.newInstance();
        searchBar=findViewById(R.id.searchBar);
        tabLayout=findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){
                    Intent intent=new Intent(MapActivity.this,CitysActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        searchBar.setOnSearchActionListener(new SimpleOnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

                super.onSearchStateChanged(enabled);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                    try {
                        SearchPlace(text);
                    }
                    catch (Exception e){
                        Toast.makeText(MapActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                super.onSearchConfirmed(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                super.onButtonClicked(buttonCode);
            }
        });


 //  *********  To use auto complete address we need to enable billing on the google cloud ***************//

//        searchBar.addTextChangeListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                FindAutocompletePredictionsRequest predictionsRequest = FindAutocompletePredictionsRequest.builder()
//                        .setTypeFilter(TypeFilter.ADDRESS)
//                        .setSessionToken(token)
//                        .setQuery(s.toString())
//                        .build();
//                placesClient.findAutocompletePredictions(predictionsRequest).addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
//                    @Override
//                    public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
//                        task.addOnSuccessListener(new OnSuccessListener<FindAutocompletePredictionsResponse>() {
//                            @Override
//                            public void onSuccess( FindAutocompletePredictionsResponse predictionsResponse) {
//                                if (predictionsResponse != null) {
//                                    predictionList = predictionsResponse.getAutocompletePredictions();
//                                    List<String> suggestionsList = new ArrayList<>();
//                                    for (int i = 0; i < predictionList.size(); i++) {
//                                        AutocompletePrediction prediction = predictionList.get(i);
//                                        suggestionsList.add(prediction.getFullText(null).toString());
//                                    }
//                                    searchBar.updateLastSuggestions(suggestionsList);
//                                    if (!searchBar.isSuggestionsVisible()) {
//                                        searchBar.showSuggestionsList();
//                                    }
//                                }
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure( Exception e) {
//                                Toast.makeText(MapActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//       *********** To use auto complete address we need to enable billing on the google cloud **********


    }

    private void SearchPlace(CharSequence text) {

        String place=text.toString().toUpperCase();
        List<Address> addressList=null;
        if(place!=null || !location.equals("")){
            Geocoder geocoder=new Geocoder(MapActivity.this);
            try {
                addressList=geocoder.getFromLocationName(place,10);

            }
            catch (Exception e){

            }
            Address address=addressList.get(0);
            Log.d("harshad",String.valueOf(addressList.size()));
            LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
            mgoogleMap.addMarker(new MarkerOptions().position(latLng).title(place));
            mgoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
            Toast.makeText(MapActivity.this, place, Toast.LENGTH_SHORT).show();

        }
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
                         intent = new Intent(MapActivity.this, MainActivity.class);
                         startActivity(intent);
                         overridePendingTransition(0,0);
                         finish();
                         return true;
                     case R.id.Mshop:
                         intent = new Intent(MapActivity.this, ShopActivity.class);
                         startActivity(intent);
                         overridePendingTransition(0,0);
                         finish();
                         return true;
                     case R.id.Mmap:
                         return true;
                     case R.id.Mrelaxo:
                         intent = new Intent(MapActivity.this, RelaxoActivity.class);
                         startActivity(intent);
                         overridePendingTransition(0,0);
                         finish();
                         return true;
                     case R.id.Mprofile:
                         intent = new Intent(MapActivity.this, ProfileActivity.class);
                         startActivity(intent);
                         overridePendingTransition(0,0);
                         finish();
                         return true;
                 }
                 return false;
             }
         });
     }
    private void CheckLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},CAM_PERMI_CODE);
            Dexter.withContext(MapActivity.this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            init_map_file();


                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                            if (permissionDeniedResponse.isPermanentlyDenied()) {
                                AlertDialog.Builder alert_builder = new AlertDialog.Builder(MapActivity.this);
                                alert_builder.setTitle("Permission Denied")
                                        .setMessage("Permission to access device location is permanently denied.you need go to setting to allow the permission.")
                                        .setNegativeButton("Cancel", null)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent();
                                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                intent.setData(Uri.fromParts("package", getPackageName(), null));
                                                startActivity(intent);
                                            }
                                        }).show();

                            } else {
                                Toast.makeText(MapActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                        }
                    }).check();
        } else {
            init_map_file();
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady( GoogleMap googleMap) {
        mgoogleMap=googleMap;
        mgoogleMap.setMyLocationEnabled(true);
        mgoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(20.5937,78.9629),18));
        try {
        if(intent_string!=null){ SearchPlace(intent_string);

        }} catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        if(mapView!=null && mapView.findViewById(Integer.parseInt("1"))!=null){
            View location_but=((View)mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams relativeLayout=(RelativeLayout.LayoutParams)location_but.getLayoutParams();
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
            relativeLayout.setMargins(0,0,40,180);
        }

        LocationRequest locationRequest=LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(MapActivity.this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(MapActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                if(intent_string!=null){

                    SearchPlace(intent_string);
                }
                else{
                    getDeviceLocation();
                }

            }
        });

        task.addOnFailureListener(MapActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(MapActivity.this, 51);
                    } catch (IntentSender.SendIntentException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==51 &&  resultCode==RESULT_OK){
            getDeviceLocation();
        }
    }
private void init_map_file(){
    SupportMapFragment MapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
    MapFragment.getMapAsync(MapActivity.this);
    mapView=MapFragment.getView();
    fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(MapActivity.this);

}

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete( Task<Location> task) {
                if(task.isSuccessful()){
                    location=task.getResult();
                    if(location!=null){
                        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),18));
                    }
                    else {
                        final LocationRequest locationRequest = LocationRequest.create();
                        locationRequest.setInterval(10000);
                        locationRequest.setFastestInterval(5000);
                        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                        locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                super.onLocationResult(locationResult);
                                if (locationResult == null) {
                                    return;
                                }
                                location = locationResult.getLastLocation();
                                mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 18));
                                fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                            }
                        };
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

                    }
                }
            else{
                    Toast.makeText(MapActivity.this, "Unable to get last location", Toast.LENGTH_SHORT).show();             }
            }
        });
    }

    @Override
    protected void onStart() {
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();
        super.onStart();
    }
}