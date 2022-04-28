package com.mirea.lutsko.viewmodel;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgressViewModel extends ViewModel {
    private static MutableLiveData<Boolean> isShowProgress = new MutableLiveData<>();

    public ProgressViewModel(){}

    void showProgress() {
        isShowProgress.postValue(true);
        new Handler().postDelayed(() -> isShowProgress.postValue(false),
                10000);
    }

    MutableLiveData<Boolean> getProgressState() {
        return isShowProgress;
    }
}