package com.mudkip.lakbay;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Luis on 1/27/2018.
 */

public class LocationHandler {
    private static double mLatitude = 14.6400405, mLongitude = 121.0747312;
    private static boolean mSet = true;

    public static void initialize(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();
                    mSet = true;
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {
                }

                @Override
                public void onProviderEnabled(String s) {
                }

                @Override
                public void onProviderDisabled(String s) {
                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }
    }

    public static double getLatitude() {
        return mLatitude;
    }

    public static double getLongitude() {
        return mLongitude;
    }

    public static boolean locationIsSet() {
        return mSet;
    }

    public static double getDistanceKilometers(double latitude, double longitude) {
        double R = 6371;
        double dLat = deg2rad(mLatitude - latitude);
        double dLong = deg2rad(mLongitude - longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(deg2rad(latitude)) * Math.cos(deg2rad(mLatitude)) * Math.sin(dLong / 2) * Math.sin(dLong / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // Distance in km

        return d;
    }

    private static double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }
}
