package com.example.inquallity.exampleproject.api;

import com.example.inquallity.exampleproject.model.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Maxim Radko on 21.05.17.
 */
public interface WeatherApi {

    @GET("forecast/{secret}/{lat},{lon}")
    Call<City> getWeather(@Path("secret") String secret, @Path("lat") double lat, @Path("lon") double lon);
    //https://api.gismeteo.ru/v2/weather/current/744/
}
