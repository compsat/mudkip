package com.mudkip.lakbay;

import java.util.ArrayList;

/**
 * Created by Luis on 1/27/2018.
 */

public class Quest {

    private int mId;
    private String mName;
    private int mNumStops, mPoints;
    private ArrayList<String> mTags;

    public Quest(int id, String name, int numStops, int points, ArrayList<String> tags) {
        mId = id;
        mName = name;
        mNumStops = numStops;
        mPoints = points;
        mTags = tags;
    }

    public Quest(String name, int numStops, ArrayList<String> tags) {
        mName = name;
        mNumStops = numStops;
        mTags = tags;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getNumStops() {
        return mNumStops;
    }

    public void setNumStops(int numStops) {
        mNumStops = numStops;
    }

    public ArrayList<String> getTags() {
        return mTags;
    }

    public void setTags(ArrayList<String> tags) {
        mTags = tags;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int points) {
        mPoints = points;
    }
}
