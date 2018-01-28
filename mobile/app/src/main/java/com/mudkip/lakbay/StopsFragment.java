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
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StopsFragment extends Fragment {

    private Stop[] doneStops, notDoneStops;
    private RecyclerView completedRecyclerView, availableRecyclerView;

    public StopsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stops, container, false);
        ImageView back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) StopsFragment.this.getActivity()).changeToProfile();
            }
        });

        LocationHandler.initialize(getActivity());

        completedRecyclerView = view.findViewById(R.id.completedStopRecyclerView);
        availableRecyclerView = view.findViewById(R.id.availableStopRecyclerView);

        getAndShowDoneStops();
        getAndShowNotDoneStops();

        return view;
    }

    private void getAndShowDoneStops() {
        final String url = MainActivity.DOMAIN + "done_stops.php";

        final OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("player_ID", MainActivity.USER_KEY + "");

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
                        doneStops = new Stop[array.length()];

                        for(int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            doneStops[i] = new Stop(obj.getInt("stop_ID"),
                                    obj.getString("place_name"),
                                    obj.getString("description"),
                                    obj.getDouble("latitude"),
                                    obj.getDouble("longitude"));
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StopAdapter questAdapter = new StopAdapter(getActivity(), doneStops);

                                completedRecyclerView.setAdapter(questAdapter);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                completedRecyclerView.setLayoutManager(layoutManager);

                                completedRecyclerView.setHasFixedSize(true);
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

    private void getAndShowNotDoneStops() {
        final String url = MainActivity.DOMAIN + "notdone_stops.php";

        final OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("player_ID", MainActivity.USER_KEY + "");

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
                        notDoneStops= new Stop[array.length()];

                        for(int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            notDoneStops[i] = new Stop(obj.getInt("stop_ID"),
                                    obj.getString("place_name"),
                                    obj.getString("description"),
                                    obj.getDouble("latitude"),
                                    obj.getDouble("longitude"));
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StopAdapter questAdapter = new StopAdapter(getActivity(), notDoneStops);

                                availableRecyclerView.setAdapter(questAdapter);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                availableRecyclerView.setLayoutManager(layoutManager);

                                availableRecyclerView.setHasFixedSize(true);
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
}
