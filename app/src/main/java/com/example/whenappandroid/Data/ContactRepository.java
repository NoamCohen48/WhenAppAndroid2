package com.example.whenappandroid.Data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;

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
        try{
            Contact newContact = api.addContact(new ServerAPI.ContactPayload(username, nickname, server)).execute().body();

            if (newContact == null) throw new Exception("server returned null");

            AppDB.databaseWriteExecutor.execute(() -> {
                contactDao.insert(newContact);
            });

            api.invitations(server, new ServerAPI.InvitationsPayload(from, username, serverUrl)).execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
