package com.example.whenappandroid.Data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepository {
    private String serverUrl = "http://10.0.2.2:5270/";

    private ServerAPI api;
    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application) {
        AppDB db = AppDB.getDatabase(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAll();

        api = RetrofitService.getAPI(serverUrl);
        Log.d("custom", "ContactRepository created");
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public void addContact(String from, String username, String nickname, String server) {
        api.addContact(new ServerAPI.ContactPayload(username, nickname, server)).enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                if (response.code() == 200) {
                    Contact contact = response.body();
                    contactDao.insert(contact);
                }
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                try {
                    throw new Exception(t.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
