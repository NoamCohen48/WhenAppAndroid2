package com.example.whenappandroid.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.whenappandroid.ChatScreen.Vertical.VerticalContactsActivity;
import com.example.whenappandroid.Data.CurrentUser;
import com.example.whenappandroid.Register.RegisterActivity;
import com.example.whenappandroid.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.signinBtn.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });

        binding.loginBtn.setOnClickListener(v -> {
            String username = binding.usernameInputBox.getText().toString();
            String password = binding.passwordInputBox.getText().toString();
            CurrentUser.currentUser = username;
            viewModel.login(username, password).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.body() == null) {
                        binding.ErrorText.setText("Combination is incorrect");
                    }

                    Intent intent = new Intent(LoginActivity.this, VerticalContactsActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    binding.ErrorText.setText("Error connecting to server");
                }
            });
        });
    }

}