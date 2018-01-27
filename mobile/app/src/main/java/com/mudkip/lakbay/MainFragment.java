package com.mudkip.lakbay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainFragment extends Fragment {
    public static final String DOMAIN = "http://10.100.65.41/";
    private City[] mCities;
    private ImageView mCityImageView, mLeftImageView, mRightImageView;
    private TextView mLocationTextView, mDistanceTextView;

    private int mCurrentlyShown;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView hamburger = view.findViewById(R.id.hamburgerImageView);
        hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) MainFragment.this.getActivity()).changeToProfile();
            }
        });

        LocationHandler.initialize(getActivity());

        mCityImageView = view.findViewById(R.id.cityImage);
        mLocationTextView = view.findViewById(R.id.location);
        mDistanceTextView = view.findViewById(R.id.distance);
        mLeftImageView = view.findViewById(R.id.leftButton);
        mRightImageView= view.findViewById(R.id.rightButton);

        mLeftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCities == null)
                    return;
                if(mCurrentlyShown <= 0)
                    Toast.makeText(getActivity(), "There are no nearer cities.", Toast.LENGTH_SHORT).show();
                else
                    setScreen(mCurrentlyShown - 1);
            }
        });

        mRightImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCities == null)
                    return;
                if(mCurrentlyShown >= mCities.length - 1)
                    Toast.makeText(getActivity(), "There are no farther cities.", Toast.LENGTH_SHORT).show();
                else
                    setScreen(mCurrentlyShown + 1);
            }
        });

        final String dataUrl = DOMAIN + "city_overview.php";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(dataUrl)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("wow", "rekt");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                try {
                    JSONArray array = new JSONArray(jsonData);
                    mCities = new City[array.length()];

                    for(int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        mCities[i] = new City(obj.getInt("place_ID"),
                                obj.getString("place_name"),
                                obj.getDouble("latitude"),
                                obj.getDouble("longitude"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(LocationHandler.locationIsSet())
                            Arrays.sort(mCities);

                        setScreen(0);
                    }
                });
            }
        });

        return view;
    }

    private void setScreen(int position) {
        mCurrentlyShown = position;

        City city = mCities[mCurrentlyShown];

        if(city.getName().equals("Manila"))
            mCityImageView.setImageResource(R.drawable.manila);
        else
            mCityImageView.setImageResource(R.drawable.other_city);

        mLocationTextView.setText(city.getName());
        if(LocationHandler.locationIsSet())
            mDistanceTextView.setText(String.format("%.2f km away", LocationHandler.getDistanceKilometers(city.getLatitude(), city.getLongitude())));
        else
            mDistanceTextView.setText("");
    }
}
