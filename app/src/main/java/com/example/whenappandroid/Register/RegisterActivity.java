package com.example.whenappandroid.Register;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.whenappandroid.ChatScreen.Vertical.VerticalContactsActivity;
import com.example.whenappandroid.Login.LoginActivity;
import com.example.whenappandroid.Login.LoginViewModel;
import com.example.whenappandroid.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.registerBtn.setOnClickListener(v -> {
            register();
        });

        ActivityResultLauncher<String[]> imagePicker =
                registerForActivityResult(new ActivityResultContracts.OpenDocument(), uri -> {
                    //contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    binding.image.setImageURI(uri);
                });

        binding.image.setOnClickListener(v -> {
            imagePicker.launch(new String[]{"image/*"});
        });
    }

    void register() {
        String username = binding.usernameInputBox.getText().toString();
        String nickname = binding.nicknameInputBox.getText().toString();
        String password = binding.passwordInputBox.getText().toString();
        String confirmPassword = binding.confirmPasswordInputBox.getText().toString();

        if(username.isEmpty()) {
            binding.usernameInputBox.setError("you must enter user name");
        }
        if(nickname.isEmpty()) {
            binding.nicknameInputBox.setError("you must enter nick name!");
        }
        if(password.isEmpty()) {
            binding.passwordInputBox.setError("you must enter password!");
        }
        if(confirmPassword.isEmpty()) {
            binding.confirmPasswordInputBox.setError("you must confirm your password!");
        }

        //TODO: check if password is indeed passord

        if(password.equals(confirmPassword)) {
            binding.confirmPasswordInputBox.setError("the password is not the same");
        }

        viewModel.register(username, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() == null) {
                    binding.ErrorText.setText("this username already exist");
                }

                Intent intent = new Intent(RegisterActivity.this, VerticalContactsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                binding.ErrorText.setText("Error connecting to server");
            }
        });
    }
}