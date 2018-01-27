package com.mudkip.lakbay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class StopsFragment extends Fragment {

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

        RecyclerView completedRecyclerView = view.findViewById(R.id.completedStopRecyclerView);
        RecyclerView availableRecyclerView = view.findViewById(R.id.availableStopRecyclerView);

        //get stop data here; tas on response yung after this
        Stop[] stops = {new Stop("wll", 21.3123, 12.21321),
                new Stop("wasd", 12.3123, 21.21321),
                new Stop("wasd", 12.3123, 21.21321),
                new Stop("wasd", 12.3123, 21.21321),
                new Stop("wasd", 12.3123, 21.21321),
                new Stop("wasd", 12.3123, 21.21321)
        };

        StopAdapter stopAdapter = new StopAdapter(getActivity(), stops);
        completedRecyclerView.setAdapter(stopAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        completedRecyclerView.setLayoutManager(layoutManager);

        completedRecyclerView.setHasFixedSize(true);

        return view;
    }

}
