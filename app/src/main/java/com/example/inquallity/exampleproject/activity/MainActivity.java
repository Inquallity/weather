package com.example.inquallity.exampleproject.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inquallity.exampleproject.R;
import com.example.inquallity.exampleproject.api.WeatherApi;
import com.example.inquallity.exampleproject.loader.ApiDataLoader;
import com.example.inquallity.exampleproject.model.City;
import com.example.inquallity.exampleproject.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Maxim Radko on 21.05.17.
 */

public class MainActivity extends AppCompatActivity {

    private static final String API_URL = "https://api.darksky.net";

    private static final String BANNER_URL = "https://media.timeout.com/images/103042848/image.jpg";

    @BindView(R.id.tvCoordinates) TextView mCoordinates;

    @BindView(R.id.tvVisibility) TextView mVisibility;

    @BindView(R.id.tvWeather) TextView mWeather;

    @BindView(R.id.ivCityBanner) ImageView mBanner;

    private Retrofit mRetrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);

        initRetrofit();

        Picasso.with(this).load(BANNER_URL).into(mBanner);
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .readTimeout(5, TimeUnit.SECONDS)
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .build();
    }

    private void proceedCity(City data) {
        mCoordinates.setText(getString(R.string.lat_lon_pattern, data.getLat(), data.getLon()));
        final Weather weather = data.getCurrentlyWeather();
        mVisibility.setText(getString(R.string.visibility_pattern, weather.getVisibility()));
        mWeather.setText(weather.getSummary());
    }

    @OnClick(R.id.btnProceed)
    void onProceedClick() {
        getLoaderManager().restartLoader(R.id.weather_loader, Bundle.EMPTY, new WeatherLoaderCallbacks());
    }

    private class WeatherLoaderCallbacks implements LoaderManager.LoaderCallbacks<City> {

        @Override
        public Loader<City> onCreateLoader(int id, Bundle args) {
            return new ApiDataLoader(MainActivity.this, mRetrofit.create(WeatherApi.class));
        }

        @Override
        public void onLoadFinished(Loader<City> loader, City data) {
            if (data != null) {
                proceedCity(data);
            } else {
                Log.d("LOG_TAG", "data is null");
            }
        }

        @Override
        public void onLoaderReset(Loader<City> loader) {

        }
    }
}
