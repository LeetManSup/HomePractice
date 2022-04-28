package com.mirea.lutsko.mireaproject.ui.stories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.mirea.lutsko.mireaproject.R;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Story> stories;

    StoryAdapter(Context context, List<Story> stories){
        this.stories = stories;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {

        Story story =  stories.get(position);
        holder.idStoryView.setText(String.valueOf(story.getId()));
        holder.storyView.setText(story.getStory());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView idStoryView, storyView;
        ViewHolder(View view){
            super(view);
            idStoryView = view.findViewById(R.id.idStoryRec);
            storyView = view.findViewById(R.id.storyTextRec);

        }
    }
}
