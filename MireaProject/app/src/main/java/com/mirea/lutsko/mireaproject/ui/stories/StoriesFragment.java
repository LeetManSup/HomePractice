package com.mirea.lutsko.mireaproject.ui.stories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import com.mirea.lutsko.mireaproject.database.App;
import com.mirea.lutsko.mireaproject.database.StoriesDatabase;
import com.mirea.lutsko.mireaproject.R;

public class StoriesFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            AddStoryFragment addStoryFragment = new AddStoryFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(((ViewGroup)getView().getParent()).getId(), addStoryFragment, null)
                    .addToBackStack(null)
                    .commit();
        });

        StoriesDatabase db = App.getInstance().getDatabase();
        StoryDAO storyDAO =  db.storyDAO();

        List<Story> storyList = storyDAO.getAll();

        StoryAdapter adapter =  new StoryAdapter(getActivity(), storyList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onClickAddStory(View view){

        AddStoryFragment addStoryFragment = new AddStoryFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(((ViewGroup)getView().getParent()).getId(), addStoryFragment, "findThisFragment")
                .addToBackStack(null)
                .commit();

        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}