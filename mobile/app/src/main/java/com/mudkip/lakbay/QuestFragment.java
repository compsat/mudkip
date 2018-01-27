package com.mudkip.lakbay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class QuestFragment extends Fragment {


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

        RecyclerView completedRecyclerView = view.findViewById(R.id.completedQuestRecyclerView);
        RecyclerView ongoingRecyclerView = view.findViewById(R.id.ongoingQuestRecyclerView);
        RecyclerView availableRecyclerView = view.findViewById(R.id.availableQuestRecyclerView);
        LocationHandler.initialize(getActivity());

        //get stop data here; tas on response yung after this
        ArrayList<String> a = new ArrayList<>();
        a.add("sdds");
        a.add("sfds2");
        a.add("sfds3");

        ArrayList<String> a2 = new ArrayList<>();
        a2.add("sdds");
        a2.add("sfds2");

        ArrayList<String> a3 = new ArrayList<>();
        a3.add("sdds");

        Quest[] quests = {new Quest("wll", 123, a), new Quest("wll", 123, a2), new Quest("wll", 123, a3)};

        QuestAdapter questAdapter = new QuestAdapter(getActivity(), quests);
        completedRecyclerView.setAdapter(questAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        completedRecyclerView.setLayoutManager(layoutManager);

        completedRecyclerView.setHasFixedSize(true);

        return view;
    }
}
