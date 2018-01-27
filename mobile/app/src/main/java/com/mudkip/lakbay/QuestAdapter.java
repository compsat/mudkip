package com.mudkip.lakbay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Luis on 1/27/2018.
 */

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.QuestViewHolder > {

    private Context mContext;
    private Quest[] mQuests;

    public QuestAdapter(Context context, Quest[] quests) {
        mContext = context;
        mQuests = quests;
    }

    @Override
    public QuestViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.quest_list_item, parent, false);
        QuestViewHolder viewHolder = new QuestViewHolder (view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestViewHolder  holder, int position) {
        holder.setView(mQuests[position]);
    }

    @Override
    public int getItemCount() {
        return mQuests.length;
    }

    public class QuestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView, numStopsTextView, tagsTextView;

        public QuestViewHolder (View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            numStopsTextView = itemView.findViewById(R.id.numStopsTextView);
            tagsTextView = itemView.findViewById(R.id.tagsTextView);
        }

        @Override
        public void onClick(View view) {

        }

        public void setView(Quest quest) {
            nameTextView.setText(quest.getName());
            numStopsTextView.setText(quest.getNumStops() + "");
            StringBuilder stringBuilder = new StringBuilder();
            if(quest.getTags().isEmpty())
                stringBuilder.append("none");
            else {
                stringBuilder.append(quest.getTags().get(0));
                for(int i = 1; i < quest.getTags().size() - 1; i++)
                    stringBuilder.append(", " + quest.getTags().get(i));
                if(quest.getTags().size() > 1)
                    stringBuilder.append(" and " + quest.getTags().get(quest.getTags().size() - 1));
            }
            tagsTextView.setText(stringBuilder.toString());
        }
    }
}
