package com.example.whenappandroid.Register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.whenappandroid.Data.UserRepository;

import retrofit2.Call;

public class RegisterViewModel extends AndroidViewModel {
    private UserRepository repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = UserRepository.getInstance();
    }

    public Call<String> register(String username, String password) {
        return repository.register(username, password);
    }

}