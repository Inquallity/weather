package com.example.inquallity.exampleproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Maxim Radko on 21.05.17.
 */
public class Weather {

    @SerializedName("time")
    private long mTime;

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("icon")
    private String mIcon;

    @SerializedName("visibility")
    private double mVisibility;

    public long getTime() {
        return mTime;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getIcon() {
        return mIcon;
    }

    public double getVisibility() {
        return mVisibility;
    }
}
