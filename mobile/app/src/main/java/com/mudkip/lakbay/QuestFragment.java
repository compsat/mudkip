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
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class QuestFragment extends Fragment {

    private Quest[] doneQuests, ongoingQuests, notDoneQuests;
    private RecyclerView completedRecyclerView, ongoingRecyclerView, availableRecyclerView;
    public QuestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quest, container, false);
        ImageView back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) QuestFragment.this.getActivity()).changeToProfile();
            }
        });

        completedRecyclerView = view.findViewById(R.id.completedQuestRecyclerView);
        ongoingRecyclerView = view.findViewById(R.id.ongoingQuestRecyclerView);
        availableRecyclerView = view.findViewById(R.id.availableQuestRecyclerView);
        LocationHandler.initialize(getActivity());

        getAndShowCompletedQuests();
        getAndShowOngoingQuests();
        getAndShowAvailableQuests();

        return view;
    }


    private void getAndShowCompletedQuests() {
        final String url = MainActivity.DOMAIN + "finished_quests.php";

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
                        String s = response.body().string();
                        Log.e("yay", s + "<<");
                        JSONArray array = new JSONArray(s);
                        doneQuests = new Quest[array.length()];

                        for(int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            doneQuests[i] = new Quest(obj.getInt("x"),
                                    obj.getString("quest_name"),
                                    obj.getInt("y"),
                                    obj.getInt("points"),
                                    new ArrayList<String>());
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                QuestAdapter questAdapter = new QuestAdapter(getActivity(), doneQuests);

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

    private void getAndShowOngoingQuests() {
        final String url = MainActivity.DOMAIN + "ongoing_quests.php";

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
                        ongoingQuests = new Quest[array.length()];

                        for(int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            ongoingQuests[i] = new Quest(obj.getInt("x"),
                                    obj.getString("quest_name"),
                                    obj.getInt("y"),
                                    obj.getInt("points"),
                                    new ArrayList<String>());
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                QuestAdapter questAdapter = new QuestAdapter(getActivity(), ongoingQuests);

                                ongoingRecyclerView.setAdapter(questAdapter);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                ongoingRecyclerView.setLayoutManager(layoutManager);

                                ongoingRecyclerView.setHasFixedSize(true);
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

    private void getAndShowAvailableQuests() {
        final String url = MainActivity.DOMAIN + "notdone_quests.php";

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
                        notDoneQuests = new Quest[array.length()];

                        for(int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            notDoneQuests[i] = new Quest(obj.getInt("x"),
                                    obj.getString("quest_name"),
                                    obj.getInt("y"),
                                    obj.getInt("points"),
                                    new ArrayList<String>());
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                QuestAdapter questAdapter = new QuestAdapter(getActivity(), notDoneQuests);

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
