package com.example.powerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

//Image that lasts a short time, which is considered as the logo and start of the application
public class Splash extends Activity {

    //Duración de espera
    private final int DURACION_SPLASH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(i);
                Splash.this.finish();
            }
        }, DURACION_SPLASH);
    }
}