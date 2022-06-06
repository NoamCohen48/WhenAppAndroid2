package com.example.whenappandroid.Login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.whenappandroid.Data.UserRepository;

import retrofit2.Call;

public class LoginViewModel extends AndroidViewModel {
    private UserRepository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = UserRepository.getInstance();
    }

    public Call<String> login(String username, String password) {
        return repository.login(username, password);
    }
}
