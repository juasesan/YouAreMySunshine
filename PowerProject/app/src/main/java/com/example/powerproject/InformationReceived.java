package com.example.powerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;
import java.util.Date;

//Activity in which one has the ability to choose the start and end day for the analysis, in addition to the
//of whether it is required to know the energy saving and in case it is used, its input of the necessary data
public class InformationReceived extends AppCompatActivity {

    Boolean ch2,ch3,ch4,ch5;
    Double lat, lng;
    EditText start,end;
    DatePickerDialog.OnDateSetListener setListener;
    CheckBox checkAhorro;
    EditText power, value, area;

    //Setting and initialization of variables and visual elements used, in addition to the ability to enter
    //the start and end dates for the calculation of the parameters and to know when to uncheck the checkbox
    //to disable the input option in those variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_received);

        Bundle bundle = getIntent().getExtras();

        ch2 = bundle.getBoolean("Ch2");
        ch3 = bundle.getBoolean("Ch3");
        ch4 = bundle.getBoolean("Ch4");
        ch5 = bundle.getBoolean("Ch5");
        lat = bundle.getDouble("Latitud");
        lng = bundle.getDouble("Longitud");

        start = findViewById(R.id.date_start);
        end = findViewById(R.id.end_date);

        Calendar calendar = Calendar.getInstance();
        final int year1 = calendar.get(Calendar.YEAR);
        final int month1 = calendar.get(Calendar.MONTH);
        final int day1 = calendar.get(Calendar.DAY_OF_MONTH);

        final int year2 = calendar.get(Calendar.YEAR);
        final int month2 = calendar.get(Calendar.MONTH);
        final int day2 = calendar.get(Calendar.DAY_OF_MONTH);

        checkAhorro = (CheckBox) findViewById(R.id.checkBox);
        checkAhorro.setChecked(true);

        power = (EditText) findViewById(R.id.power);
        value = (EditText) findViewById(R.id.value);
        area = (EditText) findViewById(R.id.area);

        power.setHintTextColor(Color.parseColor("#0000FF"));
        value.setHintTextColor(Color.parseColor("#0000FF"));
        area.setHintTextColor(Color.parseColor("#0000FF"));

        checkAhorro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(checkAhorro.isChecked()))
                {
                    power.setEnabled(false);
                    value.setEnabled(false);
                    area.setEnabled(false);
                    power.setHintTextColor(Color.parseColor("#CBCDCB"));
                    value.setHintTextColor(Color.parseColor("#CBCDCB"));
                    area.setHintTextColor(Color.parseColor("#CBCDCB"));
                }else{
                    power.setEnabled(true);
                    value.setEnabled(true);
                    area.setEnabled(true);
                    power.setHintTextColor(Color.parseColor("#0000FF"));
                    value.setHintTextColor(Color.parseColor("#0000FF"));
                    area.setHintTextColor(Color.parseColor("#0000FF"));
                }
            }

        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InformationReceived.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = year + "/" + month + "/" +dayOfMonth;
                        start.setText(date);
                    }
                },day1,month1,year1);
                datePickerDialog.show();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InformationReceived.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = year + "/" + month + "/" +dayOfMonth;
                        end.setText(date);
                    }
                },day2,month2,year2);
                datePickerDialog.show();
            }
        });
    }

    //Method in charge of going to the display of results for the user
    public void irValores(View view){
        Intent i = new Intent(InformationReceived.this, Results.class);
        i.putExtra("Latitud",lat);
        i.putExtra("Longitud",lng);
        i.putExtra("Ch2",ch2);
        i.putExtra("Ch3",ch3);
        i.putExtra("Ch4",ch4);
        i.putExtra("Ch5",ch5);
        i.putExtra("Inicio",start.getText().toString());
        i.putExtra("Fin",end.getText().toString());
        i.putExtra("Power",power.getText().toString());
        i.putExtra("Value",value.getText().toString());
        i.putExtra("Area",area.getText().toString());
        startActivity(i);
    }
}