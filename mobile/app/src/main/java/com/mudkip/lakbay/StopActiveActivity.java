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

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StopActiveActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY = "bundle_key";
    private int stopId;
    private TextView name, desc;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_active);

        ImageView exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        stopId = getIntent().getExtras().getInt(BUNDLE_KEY);

        name = findViewById(R.id.NameStopText);
        desc = findViewById(R.id.descriptionStopText);
        mButton = findViewById(R.id.button);

        getAndShowNotDoneStops();
    }

    private void getAndShowNotDoneStops() {
        final String url = MainActivity.DOMAIN + "stop_details.php";

        final OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("stop_ID", stopId + "");

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
                        final JSONObject obj = new JSONArray(response.body().string()).getJSONObject(0);
                        final String n = obj.getString("place_name");
                        final String d = obj.getString("description");
                        final double x = obj.getDouble("latitude");
                        final double y = obj.getDouble("longitude");
                        final int p = obj.getInt("points");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                name.setText(n);
                                desc.setText(d);
                                double dist = LocationHandler.getDistanceKilometers(x, y);
                                if (dist < 1.00) {
                                    mButton.setEnabled(true);
                                    mButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Toast.makeText(StopActiveActivity.this, "You just got " + p + " points!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });
                                }
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
