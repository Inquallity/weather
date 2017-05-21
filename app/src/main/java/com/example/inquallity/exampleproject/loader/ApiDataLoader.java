package com.example.inquallity.exampleproject.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.inquallity.exampleproject.api.WeatherApi;
import com.example.inquallity.exampleproject.model.City;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Maxim Radko on 21.05.17.
 */
public class ApiDataLoader extends AsyncTaskLoader<City> {

    private static final String SECRET = "bf97fd34738c804c25d3b4d54ac1e30c";

    private final WeatherApi mApi;

    private City mCityCache = null;

    public ApiDataLoader(Context context, WeatherApi api) {
        super(context);
        mApi = api;
    }

    @Override
    public void deliverResult(City data) {
        if (mCityCache == null) {
            mCityCache = data;
        }
        super.deliverResult(data);
    }

    @Override
    public City loadInBackground() {
        final Call<City> cityCall = mApi.getWeather(SECRET, 51.5285582, -0.2417003);
        City city = null;
        try {
            final Response<City> cityResponse = cityCall.execute();
            city = cityResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    protected void onStartLoading() {
        if (mCityCache != null) {
            deliverResult(mCityCache);
        } else {
            forceLoad();
        }
    }
}
