package com.example.chatapp1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.chatapp1.R;
import com.example.chatapp1.databinding.ActivityProfilBinding;
import com.example.chatapp1.databinding.ActivitySignInBinding;
import com.google.android.material.navigation.NavigationView;

public class Profil extends AppCompatActivity {
    private ActivityProfilBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    // As you can see, an instance of a binding class contains
    // direct reference to all views that have an ID in the corresponding layout
    private void setListeners() {
        binding.back.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(),MainActivity.class)));
    }


}