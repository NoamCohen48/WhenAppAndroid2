package com.example.whenappandroid.Data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static ServerAPI api = null;

    public static ServerAPI getAPI(String url) {
        if (api == null) {
            OkHttpClient.Builder client = new OkHttpClient.Builder().cookieJar(new UvCookieJar());

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client.build())
                    .build();

            api = retrofit.create(ServerAPI.class);
        }
        return api;
    }

    private static class UvCookieJar implements CookieJar {
        private List<Cookie> cookies = new ArrayList<>();

        @NonNull
        @Override
        public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
            return cookies;
        }

        @Override
        public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
            cookies.addAll(list);
        }
    }
}
