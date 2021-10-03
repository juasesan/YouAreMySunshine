package com.example.powerproject;

import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Peticion {

    //Bundle bundle=getIntent().getExtras();

    private void findData(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://power.larc.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ParametroAPI parametroAPI=retrofit.create(ParametroAPI.class);
        //Call<Response> call=parametroAPI.find();

        /*call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });*/
    }
}
