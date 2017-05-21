package com.example.inquallity.exampleproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Maxim Radko on 21.05.17.
 */
public class City {

    private long mId;

    @SerializedName("latitude")
    private double mLat;

    @SerializedName("longitude")
    private double mLon;

    @SerializedName("currently")
    private Weather mCurrentlyWeather;

    public Weather getCurrentlyWeather() {
        return mCurrentlyWeather;
    }

    public long getId() {
        return mId;
    }

    public double getLat() {
        return mLat;
    }

    public double getLon() {
        return mLon;
    }
}
