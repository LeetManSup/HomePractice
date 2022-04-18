package com.mirea.lutsko.mireaproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MusicFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_music, container, false);
        view.findViewById(R.id.buttonPlay).setOnClickListener(this::startMusicOnClick);
        view.findViewById(R.id.buttonPause).setOnClickListener(this::stopMusicOnClick);

        return view;
    }

    public void startMusicOnClick(View view){
        getActivity().startService(new Intent(getActivity(), MusicService.class));
    }

    public void stopMusicOnClick(View view){
        getActivity().stopService(new Intent(getActivity(), MusicService.class));
    }
}