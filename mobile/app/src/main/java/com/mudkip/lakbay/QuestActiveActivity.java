package com.mudkip.lakbay;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class QuestActiveActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY = "bundle_key";
    public static final String BUNDLE_KEY_NAME = "bundle_name";
    private int questId;
    private String string;
    private TextView name;
    private RecyclerView mRecyclerView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_acitve);

        ImageView exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        questId = getIntent().getExtras().getInt(BUNDLE_KEY);
        string = getIntent().getExtras().getString(BUNDLE_KEY_NAME);
        mRecyclerView = findViewById(R.id.recyclerView);
        name = findViewById(R.id.NameStopText);
        mButton = findViewById(R.id.button);

        name.setText(string);

        getAndShowNotDoneStops();
    }

    private void getAndShowNotDoneStops() {
        final String url = MainActivity.DOMAIN + "stops_in_quests.php";

        final OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("quest_ID", questId + "");

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
                        final Stop[] mStops = new Stop[array.length()];

                        for(int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            mStops[i] = new Stop(obj.getInt("stop_ID"),
                                    obj.getString("place_name"),
                                    obj.getString("description"),
                                    obj.getDouble("latitude"),
                                    obj.getDouble("longitude"));
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StopAdapter questAdapter = new StopAdapter(QuestActiveActivity.this, mStops);

                                mRecyclerView.setAdapter(questAdapter);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(QuestActiveActivity.this);
                                mRecyclerView.setLayoutManager(layoutManager);

                                mRecyclerView.setHasFixedSize(true);
                            }
                        });
                    } else {
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
