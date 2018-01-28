package com.mudkip.lakbay;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainFragment extends Fragment {

    private City[] mCities;
    private Stop[] mStops;
    private Quest[] mQuests;
    private ImageView mCityImageView, mLeftImageView, mRightImageView;
    private TextView mLocationTextView, mDistanceTextView, mQuestButton, mStopButton, mInfoButton;

    private ImageView mAreaSubmenu, mExit;
    private TextView mMenuPopupLabel, mPopupTextView;
    private View mLine;
    private RecyclerView mRecyclerView;

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
        mRightImageView = view.findViewById(R.id.rightButton);
        mQuestButton = view.findViewById(R.id.questMain);
        mStopButton = view.findViewById(R.id.stopMain);
        mInfoButton = view.findViewById(R.id.infoMain);
        mExit = view.findViewById(R.id.exit);

        mAreaSubmenu = view.findViewById(R.id.areaSubmenu);
        mMenuPopupLabel = view.findViewById(R.id.menuPopupLabel);
        mPopupTextView = view.findViewById(R.id.popupTextView);
        mLine = view.findViewById(R.id.line);
        mRecyclerView = view.findViewById(R.id.popupRecyclerView);

        cityOverview();

        mLeftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCities == null)
                    return;
                if (mCurrentlyShown <= 0)
                    Toast.makeText(getActivity(), "There are no nearer cities.", Toast.LENGTH_SHORT).show();
                else
                    setScreen(mCurrentlyShown - 1);
            }
        });

        mRightImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCities == null)
                    return;
                if (mCurrentlyShown >= mCities.length - 1)
                    Toast.makeText(getActivity(), "There are no farther cities.", Toast.LENGTH_SHORT).show();
                else
                    setScreen(mCurrentlyShown + 1);
            }
        });

        mQuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAreaSubmenu.setVisibility(View.VISIBLE);
                mMenuPopupLabel.setVisibility(View.VISIBLE);
                mLine.setVisibility(View.VISIBLE);
                clearRecylerView();
                mRecyclerView.setVisibility(View.VISIBLE);
                mPopupTextView.setVisibility(View.GONE);
                mMenuPopupLabel.setText("QUESTS");
                mExit.setVisibility(View.VISIBLE);
                mLeftImageView.setClickable(false);
                mRightImageView.setClickable(false);
                getAndShowQuests();
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAreaSubmenu.setVisibility(View.VISIBLE);
                mMenuPopupLabel.setVisibility(View.VISIBLE);
                mLine.setVisibility(View.VISIBLE);
                clearRecylerView();
                mRecyclerView.setVisibility(View.VISIBLE);
                mPopupTextView.setVisibility(View.GONE);
                mMenuPopupLabel.setText("STOPS");
                mExit.setVisibility(View.VISIBLE);
                mLeftImageView.setClickable(false);
                mRightImageView.setClickable(false);
                getAndShowStops();
            }
        });

        mInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAreaSubmenu.setVisibility(View.VISIBLE);
                mMenuPopupLabel.setVisibility(View.VISIBLE);
                mLine.setVisibility(View.VISIBLE);
                mPopupTextView.setVisibility(View.VISIBLE);
                mMenuPopupLabel.setText("INFO");
                mRecyclerView.setVisibility(View.GONE);
                mExit.setVisibility(View.VISIBLE);
                mPopupTextView.setText(mCities[mCurrentlyShown].getDescription());
                mLeftImageView.setClickable(false);
                mRightImageView.setClickable(false);
            }
        });

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAreaSubmenu.setVisibility(View.GONE);
                mMenuPopupLabel.setVisibility(View.GONE);
                mLine.setVisibility(View.GONE);
                mPopupTextView.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
                mExit.setVisibility(View.GONE);
                mLeftImageView.setClickable(true);
                mRightImageView.setClickable(true);
            }
        });

        return view;
    }

    private void setScreen(int position) {
        mCurrentlyShown = position;

        City city = mCities[mCurrentlyShown];

        if (city.getName().equals("Manila City"))
            mCityImageView.setImageResource(R.drawable.manila);
        else
            mCityImageView.setImageResource(R.drawable.other_city);

        mLocationTextView.setText(city.getName());
        if (LocationHandler.locationIsSet())
            mDistanceTextView.setText(String.format("%.2f km away", LocationHandler.getDistanceKilometers(city.getLatitude(), city.getLongitude())));
        else
            mDistanceTextView.setText("");
    }


    private void cityOverview() {
        final String dataUrl = MainActivity.DOMAIN + "city_overview.php";

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

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        mCities[i] = new City(obj.getInt("place_ID"),
                                obj.getString("place_name"),
                                obj.getString("description"),
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
                        if (LocationHandler.locationIsSet())
                            Arrays.sort(mCities);

                        setScreen(0);
                    }
                });
            }
        });
    }

    private void getAndShowStops() {
        final String url = MainActivity.DOMAIN + "stops_in_city.php";

        final OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("city_ID", mCities[mCurrentlyShown].getId() + "");

        RequestBody formBody = formBuilder.build();
        Request.Builder requestBuilder = new Request.Builder();
        final Request request = requestBuilder
                .url(url)
                .post(formBody)
                .build();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Response response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        JSONArray array = new JSONArray(response.body().string());
                        mStops = new Stop[array.length()];

                        for(int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            mStops[i] = new Stop(obj.getInt("stop_ID"),
                                    obj.getString("place_name"),
                                    obj.getString("description"),
                                    obj.getDouble("latitude"),
                                    obj.getDouble("longitude"));
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StopAdapter stopAdapter = new StopAdapter(getActivity(), mStops);

                                mRecyclerView.setAdapter(stopAdapter);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                mRecyclerView.setLayoutManager(layoutManager);

                                mRecyclerView.setHasFixedSize(true);
                            }
                        });
                        Log.e("yay", "");
                    }
                    else {
                        Log.e("yay", "well");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
    }

    private void getAndShowQuests() {
        final String url = MainActivity.DOMAIN + "quests_in_city.php";

        final OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("city_ID", mCities[mCurrentlyShown].getId() + "");

        RequestBody formBody = formBuilder.build();
        Request.Builder requestBuilder = new Request.Builder();
        final Request request = requestBuilder
                .url(url)
                .post(formBody)
                .build();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Response response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String s = response.body().string();
                        Log.e("yay", s);
                        JSONArray array = new JSONArray(s);
                        mQuests = new Quest[array.length()];

                        for(int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            mQuests[i] = new Quest(obj.getInt("x"),
                                    obj.getString("quest_name"),
                                    obj.getInt("y"),
                                    obj.getInt("points"),
                                    new ArrayList<String>());
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                QuestAdapter questAdapter = new QuestAdapter(getActivity(), mQuests);
                                mRecyclerView.setAdapter(questAdapter);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                mRecyclerView.setLayoutManager(layoutManager);

                                mRecyclerView.setHasFixedSize(true);
                            }
                        });
                    }
                    else {
                        Log.e("yay", "well");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
    }

    private void clearRecylerView() {
        if(mRecyclerView == null || mRecyclerView.getAdapter() == null)
            return;
        if(mStops != null)
            mStops = new Stop[0];
        if(mQuests != null)
            mQuests= new Quest[0];
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
