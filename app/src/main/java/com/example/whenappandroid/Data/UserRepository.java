package com.example.whenappandroid.Data;

import java.io.IOException;

import retrofit2.Call;

public class UserRepository {
    private String currentUser;

    private String serverUrl = "http://10.0.2.2:5270/";
    private ServerAPI api;

    public Call<String> login(String username, String password) {
        return api.login(new ServerAPI.UtilsPayload(username, password));
    }

    public Call<String> register(String username, String password) {
            return api.register(new ServerAPI.UtilsPayload(username, password));
    }


    private static UserRepository instance;

    private UserRepository() {
        api = RetrofitService.getAPI(serverUrl);
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }


}
