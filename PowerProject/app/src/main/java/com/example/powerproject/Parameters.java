package com.example.powerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.maps.model.LatLng;

//Activity corresponding to the selection of the parameters that the user will want to know or visualize
//as results in graphs with respect to the location they had selected
public class Parameters extends AppCompatActivity {

    CheckBox ch2,ch3,ch4,ch5;
    Double lat, lng;

    //Setting and initialization of all the visual components used in the xml file corresponding to this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        ch2 = (CheckBox)findViewById(R.id.checkBox2);
        ch3 = (CheckBox)findViewById(R.id.checkBox3);
        ch4 = (CheckBox)findViewById(R.id.checkBox4);
        ch5 = (CheckBox)findViewById(R.id.checkBox5);

        ch2.setChecked(true);
        ch3.setChecked(true);
        ch4.setChecked(true);
        ch5.setChecked(true);

        Bundle bundle = getIntent().getExtras();
        lat = bundle.getDouble("Latitud");
        lng = bundle.getDouble("Longitud");
    }

    //method used for the button in charge of switching to the activity where the user will be asked for some
    //brief information necessary to obtain the required results
    public void irValores(View view){
        Intent i = new Intent(Parameters.this, InformationReceived.class);
        i.putExtra("Irradiacion",ch2.isChecked());
        i.putExtra("Temperatura",ch3.isChecked());
        i.putExtra("Humedad",ch4.isChecked());
        i.putExtra("Viento",ch5.isChecked());
        i.putExtra("Latitud",lat);
        i.putExtra("Longitud",lng);
        startActivity(i);
    }
}