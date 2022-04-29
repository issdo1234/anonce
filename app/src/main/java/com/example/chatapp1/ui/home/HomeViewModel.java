package com.example.chatapp1.ui.home;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chatapp1.activities.HomePage;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public HomeViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Bienvenue");
    }

    public LiveData<String> getText() {
        return mText;
    }
}