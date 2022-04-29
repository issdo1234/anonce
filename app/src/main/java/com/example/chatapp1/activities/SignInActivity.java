package com.example.chatapp1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp1.databinding.ActivitySignInBinding;
import com.example.chatapp1.utilities.Constants;
import com.example.chatapp1.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInActivity extends AppCompatActivity {
    
// as we've enable viewBinding for our project, 
// the binding class for each XML will be generated automatically.
// Here 'ActivitySignInBinding' class is automatically
// generated from our layout file : 'activity_sign_in'
    
    private ActivitySignInBinding binding;
    private PreferenceManager preferenceManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            preferenceManager = new PreferenceManager(getApplicationContext());

            //pour rester connecter
//            if(preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)){
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
//                finish();
//            }

            binding = ActivitySignInBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            setListeners();
        }

        // As you can see, an instance of a binding class contains
        // direct reference to all views that have an ID in the corresponding layout
        private void setListeners() {
            binding.textCreateNewAccount.setOnClickListener(v ->
                    startActivity(new Intent(getApplicationContext(),SignUpActivity.class)));
            binding.buttonSignIn.setOnClickListener(v -> {
//                if(isValidSignIpDetails())
                    signIn();
            });
        }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
    private void signIn(){
            loading(true);
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Constants.KEY_COLLECTION_USERS)
                    .whereEqualTo(Constants.KEY_EMAIL,binding.inputEmail.getText().toString())
                    .whereEqualTo(Constants.KEY_PASSWORD,binding.inputPassword.getText().toString())
                    .get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful() && task.getResult() != null
                            && task.getResult().getDocuments().size()>0) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN,true);
                            preferenceManager.putString(Constants.KEY_USER_ID,documentSnapshot.getId());
                            preferenceManager.putString(Constants.KEY_NAME,documentSnapshot.getString(Constants.KEY_NAME));
                            preferenceManager.putString(Constants.KEY_IMAGE,documentSnapshot.getString(Constants.KEY_IMAGE));
                            Intent intent = new Intent(getApplicationContext(),HomePage.class);
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
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignIn.setVisibility(View.VISIBLE);
        }
    }

    private Boolean isValidSignIpDetails(){
        if(binding.inputEmail.getText().toString().trim().isEmpty()) {
            showToast("Enter Email");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()){
            showToast("Enter valid email");
            return false;
        }else if(binding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Enter password");
            return false;
        }else{return true;}
    }
}