package com.example.msrtc.ui.gallery;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.msrtc.R;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();

        mText.setValue("Welcome");
    }

    public LiveData<String> getText() {
        return mText;
    }
}