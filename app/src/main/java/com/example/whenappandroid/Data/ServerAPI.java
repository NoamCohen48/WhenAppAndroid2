package com.example.whenappandroid.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerAPI {

    class UtilsPayload {
        public String username;
        public String password;

        public UtilsPayload(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    @POST("api/Login")
    Call<String> login(@Body UtilsPayload payload);

    @POST("api/Register")
    Call<String> register(@Body UtilsPayload payload);

    @GET("api/contacts")
    Call<List<Contact>> getContacts();

    class ContactPayload {
        public String id;
        public String name;
        public String server;
    }

    @POST("api/contacts")
    Call<List<Contact>> addContacts(@Body ContactPayload payload);
}
