package com.mudkip.lakbay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView exit = view.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) ProfileFragment.this.getActivity()).changeToMain();
            }
        });

        TextView quest = view.findViewById(R.id.quest);
        quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) ProfileFragment.this.getActivity()).changeToQuest();
            }
        });

        TextView settings = view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) ProfileFragment.this.getActivity()).changeToSettings();
            }
        });

        TextView stop = view.findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) ProfileFragment.this.getActivity()).changeToStops();
            }
        });

        TextView faqs = view.findViewById(R.id.faqs);
        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) ProfileFragment.this.getActivity()).changeToHelp();
            }
        });

        return view;
    }

}
