package com.mudkip.lakbay;

import java.util.ArrayList;

/**
 * Created by Luis on 1/27/2018.
 */

public class Quest {

    private String mName;
    private int mNumStops;
    private ArrayList<String> mTags;

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
}
