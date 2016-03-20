package com.me.test.pinboard.pinboard.DrawerFragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.test.pinboard.pinboard.Activities.NoteActivity;
import com.me.test.pinboard.pinboard.Adapters.NotesRecyclerViewAdapter;
import com.me.test.pinboard.pinboard.Model.Note;
import com.me.test.pinboard.pinboard.R;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RecyclerView notesRecyclerView;
    private NotesRecyclerViewAdapter notesRecyclerViewAdapter;
    private ArrayList<Note> notes;
    private FloatingActionButton floatingActionButton;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        setNotesRecyclerView(rootView);
        setFloatingActionButton(rootView);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), NoteActivity.class);
                startActivity(myIntent);
            }
        });

        return rootView;
    }


    private void setNotesRecyclerView(View rootView) {
        notesRecyclerView = (RecyclerView) rootView.findViewById(R.id.notes_recycler_view);
        notesRecyclerView.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        notesRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        setNotes();
        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(notes);
        notesRecyclerView.setAdapter(notesRecyclerViewAdapter);

    }

    private void setNotes() {
        notes = new ArrayList<Note>();
        notes.add(new Note("test #1", "this is a description of wut would happened", R.color.light_blue_card));
        notes.add(new Note("The second one", "wow look at all these colors!",R.color.gray_card));
        notes.add(new Note("HELLO!", "this is going great so far! :D", R.color.blue_card));
        notes.add(new Note("ok??", "So after a long day of programming big time! i did do it it's way more that awesome still need some designing shit but it's nice :P", R.color.light_blue_card));
        notes.add(new Note("anyway.", "and this is another description you might want to see xD", R.color.gray_card));
        notes.add(new Note("So?", "you can go now :D", R.color.blue_card));
    }

    private void setFloatingActionButton(View rootView)
    {
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.action_button);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


}
