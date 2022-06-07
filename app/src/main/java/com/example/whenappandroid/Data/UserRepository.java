package com.example.whenappandroid.Data;

import android.app.Application;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private String currentUser;

    private String serverUrl = "http://10.0.2.2:5270/";
    private ServerAPI api;
    private AppDB db;
    private ContactDao contactDao;
    private MessageDao messagesDao;

    public Call<String> login(String username, String password) {
        return api.login(new ServerAPI.UtilsPayload(username, password));
    }

    public Call<String> register(String username, String password) {
        return api.register(new ServerAPI.UtilsPayload(username, password));
    }

    public void loadUser(String username) {

        api.getContacts().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.code() == 200){
                    db.clearAllTables();
                    List<Contact> contacts = response.body();
                    contactDao.insertAllOrders(contacts);
                    assert contacts != null;
                    for (Contact c : contacts) {
                        api.getMessages(c.getId()).enqueue(new Callback<List<Message>>() {
                            @Override
                            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                                if(response.code() == 200){
                                    List<Message> messages = response.body();
                                    messagesDao.insertAllOrders(messages);
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Message>> call, Throwable t) {

                            }
                        });
                    }
                    Log.d("custom", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });

    }

    private static UserRepository instance;

    private UserRepository(Application application) {
        api = RetrofitService.getAPI(serverUrl);

        db = AppDB.getDatabase(application);
        contactDao = db.contactDao();
        messagesDao = db.messageDao();
    }

    public static UserRepository getInstance(Application application) {
        if (instance == null) {
            instance = new UserRepository(application);
        }
        return instance;
    }


}
