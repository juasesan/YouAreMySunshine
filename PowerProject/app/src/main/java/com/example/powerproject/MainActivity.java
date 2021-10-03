package com.example.powerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//Main class in which the instructions will be shown to the user and will have a button that leads
//to the application
public class MainActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;

    //Here we have the ability to put links and request the permission of the gps to the user is set.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupHyperlink();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    //In this method we make available to the user the links of relevant information about solar panels
    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.textView10);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView2 = findViewById(R.id.textView11);
        linkTextView2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //For the irMap method, it is possible to create permission for the user to use their GPS and locate the
    //starting point in their current location in order to facilitate the process to find the desired position.
    public void irMap(View view){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        else {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(
                    new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location != null) {
                                try {
                                    Intent i = new Intent(MainActivity.this, MapGoogle.class);
                                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                                    List<Address> list = geocoder.getFromLocation(location.getLatitude(),
                                            location.getLongitude(), 1);

                                    i.putExtra("titulo", list.get(0).getLocality());
                                    i.putExtra("lat", list.get(0).getLatitude());
                                    i.putExtra("lng", list.get(0).getLongitude());
                                    startActivity(i);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        }
    }
}