package com.example.powerproject;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.powerproject.databinding.ActivityMapGoogleBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//Activity in charge of having all the code regarding the operation and visualization of the map for the
//selection of a location by means of a marker defined by the user
public class MapGoogle extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapGoogleBinding binding;
    Switch Theme;
    EditText Nombre;
    Double latitud_final, longitud_final;

    //We set the initialization of the map activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapGoogleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Once the map is created, a marker is set in the current location of the user, in case the user wants
    //to choose another location, he has two methods to place the marker, the first is by simply touching
    //the screen in the desired place and the second is programmed in the following method.
    //You also have the possibility to change the visual theme of the map, from light to dark or vice versa.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Nombre = (EditText) findViewById(R.id.ubication);

        Bundle bundle = getIntent().getExtras();
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng marcador = new LatLng(bundle.getDouble("lat"), bundle.getDouble("lng"));
        mMap.addMarker(new MarkerOptions().position(marcador).title(bundle.getString("titulo")));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcador,10));
        latitud_final = bundle.getDouble("lat");
        longitud_final = bundle.getDouble("lng");
        Theme=(Switch)findViewById(R.id.switch1);

        Theme.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (Theme.isChecked()) {
                            MapStyleOptions styleOscuro=MapStyleOptions.loadRawResourceStyle(MapGoogle.this,R.raw.oscuro);
                            mMap.setMapStyle(styleOscuro);
                        } else {
                            MapStyleOptions styleClaro=MapStyleOptions.loadRawResourceStyle(MapGoogle.this,R.raw.claro);
                            mMap.setMapStyle(styleClaro);
                        }
                    }

                });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng position) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(position));
                latitud_final = position.latitude;
                longitud_final = position.longitude;
            }
        });
    }

    //method in charge of providing the user with the facility to enter the name of the country or city
    //in which he wishes to place a marker for his previous examination
    public void buscar(View view){
        mMap.clear();
        Geocoder geocoder = new Geocoder(MapGoogle.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(Nombre.getText().toString(),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list.size() > 0){
            Address address = list.get(0);
            LatLng marcador1 = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(marcador1).title(Nombre.getText().toString()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcador1,10));
            latitud_final = address.getLatitude();
            longitud_final = address.getLongitude();
        }
        else{
            Toast.makeText(this, "No se encontró ningún resultado, intente con otro lugar", Toast.LENGTH_SHORT).show();
        }
    }

    //It is used to switch to the activity in which you have the selection of the parameters you want to
    //obtain as final results.
    public void irNext(View view){
        Intent i = new Intent(MapGoogle.this, Parameters.class);
        i.putExtra("Latitud",latitud_final);
        i.putExtra("Longitud",longitud_final);
        startActivity(i);
    }
}