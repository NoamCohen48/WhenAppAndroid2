package com.example.whenappandroid.Register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.whenappandroid.Data.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterViewModel extends AndroidViewModel {
    private UserRepository repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = UserRepository.getInstance(application);
    }

    public void register(String username, String password, Callback<String> callback) {
        repository.register(username, password).enqueue(callback);
        repository.loadUser(username);
    }

}