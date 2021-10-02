package com.example.spaceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton buttonFragment1,buttonFragment2,buttonFragment3;
    FrameLayout bottomSheet;
    BottomSheetBehavior bottomSheetBehavior;
    ConstraintLayout icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomSheet = findViewById(R.id.container_2);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        //bottomSheetBehavior.setPeekHeight(450);

        //PRIMER BOTON AL PRIMER FRAGMENT
        buttonFragment1 = findViewById(R.id.lupa);
        buttonFragment1.setOnClickListener(this);

        //SEGUNDO BOTON AL SEGUNDO FRAGMENT
        buttonFragment2 = findViewById(R.id.satellite);
        buttonFragment2.setOnClickListener(this);

        //TERCERO BOTON AL TERCERO FRAGMENT
        buttonFragment3 = findViewById(R.id.window);
        buttonFragment3.setOnClickListener(this);

        //Constraint Layout
        icons = findViewById(R.id.icons);

        /*//Button
        bt_prueba = findViewById(R.id.prueba);
        bt_prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Probando...", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.lupa){
            getSupportFragmentManager().beginTransaction().replace(R.id.container_2, new PrimerFragmento()).commit();
            //if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            //}
            /*BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                    MainActivity.this, R.style.BottomSheetDialogTheme
            );
            View bottomSheetView = LayoutInflater.from(getApplicationContext())
                    .inflate(
                            R.layout.fragment_primer_fragmento,
                            (FrameLayout)findViewById(R.id.container)
                    );
            bottomSheetView.findViewById(R.id.salir).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Saliendo...", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();*/
        }else if (v.getId()==R.id.satellite){
            getSupportFragmentManager().beginTransaction().replace(R.id.container_2, new SegundoFragmento()).commit();
            //if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            //}
        }else if (v.getId()==R.id.window){
            getSupportFragmentManager().beginTransaction().replace(R.id.container_2, new TercerFragmento()).commit();
            //if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            //}
        }
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset>=0.1){
                    icons.setVisibility(View.INVISIBLE);
                }else if(slideOffset<0.1){
                    icons.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}