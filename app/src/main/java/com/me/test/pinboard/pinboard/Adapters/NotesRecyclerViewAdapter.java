package com.me.test.pinboard.pinboard.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.test.pinboard.pinboard.Model.Note;
import com.me.test.pinboard.pinboard.R;

import java.util.ArrayList;

/**
 * Created by ayham on 3/18/16.
 */
public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Note> notes;

    public NotesRecyclerViewAdapter( ArrayList<Note> notes)
    {
        this.notes = notes;
    }

    public NotesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
    int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());
        holder.card_container.setBackgroundResource(notes.get(position).getColor());

    }


    public int getItemCount() {
        return notes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, description;
        public LinearLayout card_container;
        public ViewHolder(View v)
        {
            super(v);
            this.title = (TextView) v.findViewById(R.id.note_title);
            this.description = (TextView) v.findViewById(R.id.note_description);
            this.card_container = (LinearLayout) v.findViewById(R.id.card_container);


        }

    }
}
