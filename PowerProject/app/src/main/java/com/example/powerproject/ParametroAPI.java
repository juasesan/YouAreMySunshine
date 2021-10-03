package com.example.powerproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ParametroAPI {
    @GET("/api/temporal/daily/point?parameters={siglas}&community=RE&longitude={long}" +
            "latitude={lat}&start={starD}&end={endD}&format=JSON")
    Call<Response> find(@Query("siglas") String[] sigla,
                        @Query("long") int longitud, @Query("lat") int latitud, @Query("startD") int starD,
                        @Query("endD") int endD
    );
}
