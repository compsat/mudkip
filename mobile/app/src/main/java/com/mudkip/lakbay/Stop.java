package com.mudkip.lakbay;

/**
 * Created by Luis on 1/27/2018.
 */

public class Stop {

    private int mId;
    private String mName, mDescription;
    private double mLatitude, mLongitude;

    public Stop(String name, double latitude, double longitude) {
        mName = name;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public Stop(int id, String name, String description, double latitude, double longitude) {
        mId = id;
        mName = name;
        mDescription = description;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
