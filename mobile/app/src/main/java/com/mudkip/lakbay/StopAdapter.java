package com.mudkip.lakbay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Luis on 1/27/2018.
 */

public class StopAdapter extends RecyclerView.Adapter<StopAdapter.StopViewHolder> {

    private Context mContext;
    private Stop[] mStops;

    public StopAdapter(Context context, Stop[] stops) {
        mContext = context;
        mStops = stops;
    }

    @Override
    public StopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.stop_list_item, parent, false);
        StopViewHolder viewHolder = new StopViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StopViewHolder holder, int position) {
        holder.setView(mStops[position]);
    }

    @Override
    public int getItemCount() {
        return mStops.length;
    }

    public class StopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView, distanceTextView;

        public StopViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            distanceTextView = itemView.findViewById(R.id.distanceTextView);
        }

        @Override
        public void onClick(View view) {

        }

        public void setView(Stop stop) {
            nameTextView.setText(stop.getName());
            if(LocationHandler.locationIsSet()) {
                double distance = LocationHandler.getDistanceKilometers(stop.getLatitude(), stop.getLongitude());

                distanceTextView.setText(String.format("%.2f km away", distance));
            }
            else
                distanceTextView.setText("");
        }
    }
}
