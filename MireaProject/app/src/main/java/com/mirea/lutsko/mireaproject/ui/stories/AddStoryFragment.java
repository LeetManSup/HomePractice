package com.mirea.lutsko.mireaproject.ui.stories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mirea.lutsko.mireaproject.database.StoriesDatabase;
import com.mirea.lutsko.mireaproject.R;
import com.mirea.lutsko.mireaproject.database.App;

public class AddStoryFragment extends Fragment {

    private EditText storyEditText;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_story, container, false);
        storyEditText = view.findViewById(R.id.editTextStory);

        view.findViewById(R.id.saveStoryBtn).setOnClickListener(this::onClickSaveStory);

        return view;
    }

    public void onClickSaveStory(View view){

        StoriesDatabase db = App.getInstance().getDatabase();
        StoryDAO storyDAO = db.storyDAO();

        Story story = new Story();
        story.story = storyEditText.getText().toString();

        storyDAO.insert(story);


        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

    }
}