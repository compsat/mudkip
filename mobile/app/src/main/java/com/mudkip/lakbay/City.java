package com.mudkip.lakbay;

import android.support.annotation.NonNull;

/**
 * Created by Luis on 1/28/2018.
 */

public class City implements Comparable<City> {
    private String mName, mDescription;
    private double mLatitude, mLongitude;
    private int mId;

    public City(int id, String name, String description, double latitude, double longitude) {
        mName = name;
        mDescription = description;
        mLatitude = latitude;
        mLongitude = longitude;
        mId = id;
    }
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public int compareTo(@NonNull City city) {
        double a = LocationHandler.getDistanceKilometers(getLatitude(), getLongitude());
        double b = LocationHandler.getDistanceKilometers(city.getLatitude(), city.getLongitude());
        if(a < b)
            return -1;
        if(a == b)
            return 0;
        return 1;
    }
}
