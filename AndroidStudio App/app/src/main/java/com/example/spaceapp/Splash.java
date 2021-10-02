package com.example.spaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.security.KeyStore;

public class Splash extends Activity {

    /** Duracion de espera **/
    private final int DURACION_SPLASH = 1500;
    /**Unico splash por ciclo de vida de la app**/
    //private static boolean splashLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (!splashLoaded) {
        setContentView(R.layout.activity_splash);
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(i);
                Splash.this.finish();
            }
        }, DURACION_SPLASH);
            //splashLoaded = true;
        /*} else {
            Intent toMainActivity = new Intent(Splash.this, MainActivity.class);
            toMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(toMainActivity);
            finish();
        }*/
    }
}