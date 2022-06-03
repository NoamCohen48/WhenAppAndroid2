package com.example.whenappandroid.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;

public class ContactRepository {
    private String serverUrl = "http://10.0.2.2:5270/";

    private ServerAPI api;
    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ContactRepository(Application application) {
        AppDB db = AppDB.getDatabase(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAll();

        api = RetrofitService.getAPI(serverUrl);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
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
