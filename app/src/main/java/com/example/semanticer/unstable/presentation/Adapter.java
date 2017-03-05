package com.example.semanticer.unstable.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.model.GameBoard;

import java.util.List;

import rx.subjects.PublishSubject;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<String> scores;
    private List<GameBoard> gameBoards;

    private OnClickListener listener = null;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView score;
        public TextView turnNumber;

        public ViewHolder(View v) {
            super(v);
            score = (TextView) itemView.findViewById(R.id.text);
            turnNumber = (TextView) itemView.findViewById(R.id.turnNum);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter(List<String> scoreData, List<GameBoard> gameBoardData) {
        scores = scoreData;
        gameBoards = gameBoardData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.score.setText(scores.get(position));
        holder.turnNumber.setText(position + ". kolo");
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.OnClickListener(position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return scores.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}

