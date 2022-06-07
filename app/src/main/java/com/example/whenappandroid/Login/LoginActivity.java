package com.example.whenappandroid.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.whenappandroid.ChatScreen.Vertical.VerticalContactsActivity;
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

            viewModel.login(username, password, new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 400) {
                        binding.ErrorText.setText("Combination is incorrect");
                        return;
                    }

                    if (response.code() == 200){
                        Intent intent = new Intent(LoginActivity.this, VerticalContactsActivity.class);
                        startActivity(intent);
                        return;
                    }

                    binding.ErrorText.setText("Error connecting to server");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    binding.ErrorText.setText("Error connecting to server");
                }
            });
        });
    }

}