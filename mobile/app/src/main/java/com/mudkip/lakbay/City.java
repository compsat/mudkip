package com.mudkip.lakbay;

import android.support.annotation.NonNull;

/**
 * Created by Luis on 1/28/2018.
 */

public class City implements Comparable<City> {
    private String mName;
    private double mLatitude, mLongitude;
    private int mId;

    public City(int id, String name, double latitude, double longitude) {
        mId = id;
        mName = name;
        mLatitude = latitude;
        mLongitude = longitude;
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
