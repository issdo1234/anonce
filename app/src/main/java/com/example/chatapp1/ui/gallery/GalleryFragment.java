package com.example.chatapp1.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.chatapp1.activities.MainActivity;
import com.example.chatapp1.activities.SignUpActivity;
import com.example.chatapp1.databinding.FragmentGalleryBinding;
import com.example.chatapp1.utilities.Constants;
import com.example.chatapp1.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.appcompat.app.AppCompatActivity;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    PreferenceManager preferenceManager;
    AppCompatActivity getApplication ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.btnvalider;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        setListeners();
        return root;
    }
    private void setListeners() {
               binding.btnvalider.setOnClickListener(v -> {
                valider();
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void valider(){
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult() != null
                            && task.getResult().getDocuments().size()>0) {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        preferenceManager.putString(Constants.KEY_ANNONCE_PRIX,documentSnapshot.getId());
                        preferenceManager.putString(Constants.KEY_ANNONCE_NAME,documentSnapshot.getString(Constants.KEY_NAME));
                        Intent intent = new Intent(getApplication.getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        loading(false);
                        showToast("Unable to Sign In");
                    }
                });
    }
    private void loading(Boolean isLoading){
        if(isLoading){
            binding.btnvalider.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.btnvalider.setVisibility(View.VISIBLE);
        }
    }
    private void showToast(String message){
        Toast.makeText(getApplication.getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}