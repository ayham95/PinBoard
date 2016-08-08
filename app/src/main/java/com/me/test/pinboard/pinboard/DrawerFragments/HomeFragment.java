package com.me.test.pinboard.pinboard.DrawerFragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.me.test.pinboard.pinboard.Activities.NoteActivity;
import com.me.test.pinboard.pinboard.Adapters.NotesRecyclerViewAdapter;
import com.me.test.pinboard.pinboard.Model.FabPath;
import com.me.test.pinboard.pinboard.Model.Note;
import com.me.test.pinboard.pinboard.Model.PathEvaluator;
import com.me.test.pinboard.pinboard.Model.PathPoint;
import com.me.test.pinboard.pinboard.R;

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


    public final static float SCALE_FACTOR      = 10f;
    public final static int ANIMATION_DURATION  = 280;
    public final static int MINIMUN_X_DISTANCE  = 200;

    private boolean mRevealFlag;
    private float fabSize;
    private RelativeLayout notesHolder;
    private RelativeLayout fabContainer;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        fabContainer = (RelativeLayout) rootView.findViewById(R.id.fab_container);
        notesHolder = (RelativeLayout) rootView.findViewById(R.id.notes_holder);

        fabSize = 0;


        setNotesRecyclerView(rootView);
        setFloatingActionButton(rootView);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onFabPressed(fabContainer);

                //Intent intent = new Intent(getActivity(), NoteActivity.class);
                //startActivityForResult(intent, 2);

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
        notes = new ArrayList<>();
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

//this methode called automatically when
//NoteActivity return a result even 'NULL'
    public void onActivityResult(int requestCode, int resultCode, Intent data) //this methode called automatically when Note Activity return a result even 'NULL'...
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            try {
                String message = data.getStringExtra("TITLE");
                String message2 = data.getStringExtra("NOTE");
                notes.add(new Note(message, message2, R.color.primary_color));
            }catch (RuntimeException e){}
        }
    }

    public void onFabPressed(View view) {
        final float startX = floatingActionButton.getX();

        FabPath path = new FabPath();
        path.moveTo(0, 0);
        path.curveTo(-50, 200, -90, 300, -250, 300); //              (x and y are the original location)

        final ObjectAnimator anim = ObjectAnimator.ofObject(this, "fabLoc", new PathEvaluator(), path.getPoints().toArray());

        anim.setInterpolator(new AccelerateInterpolator());
        anim.setDuration(ANIMATION_DURATION);
        anim.start();

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (Math.abs(startX - floatingActionButton.getX()) > MINIMUN_X_DISTANCE) {
                    if (!mRevealFlag) {
                        fabContainer.setY(fabContainer.getY() + fabSize / 2);

                        floatingActionButton.animate()
                                .scaleXBy(SCALE_FACTOR)
                                .scaleYBy(SCALE_FACTOR)
                                .setListener(mEndRevealListener)
                                .setDuration(ANIMATION_DURATION);

                        mRevealFlag = true;
                    }
                }
            }
        });
    }

    private AnimatorListenerAdapter mEndRevealListener = new AnimatorListenerAdapter() {

        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            //notesHolder.setElevation(1f);

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);

            floatingActionButton.setVisibility(View.INVISIBLE);
            fabContainer.setBackgroundColor(getResources()
                    .getColor(R.color.primary_color));

        }
    };

    public void setFabLoc(PathPoint newLoc) {
        floatingActionButton.setTranslationX(newLoc.getX());

        if (mRevealFlag)
            floatingActionButton.setTranslationY(newLoc.getY() - (fabSize / 2));
        else
            floatingActionButton.setTranslationY(newLoc.getY());


    }



}
