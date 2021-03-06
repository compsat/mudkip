package com.mudkip.lakbay;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LocationHandler.initialize(this);

        //get stop data here; tas on response yung after this
        Stop[] stops = {new Stop("wll", 21.3123, 12.21321),
                new Stop("wasd", 12.3123, 21.21321),
                new Stop("wasd", 12.3123, 21.21321),
                new Stop("wasd", 12.3123, 21.21321),
                new Stop("wasd", 12.3123, 21.21321),
                new Stop("wasd", 12.3123, 21.21321)
        };

        StopAdapter stopAdapter = new StopAdapter(this, stops);
        recyclerView.setAdapter(stopAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

//        String dataUrl = "http://10.101.1.138/hey.php";
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(dataUrl)
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("meh", "rekt");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String jsonData = response.body().string();
//                Log.e("meh", jsonData);
//            }
//        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }
}
