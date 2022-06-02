package com.example.whenappandroid.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.whenappandroid.Data.AppDB;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.ContactDao;

import java.util.List;

public class ContactRepository {
    private String serverUrl = "http://10.0.2.2:5270";

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
        allContacts = contactDao.index();

        api = RetrofitService.getAPI(serverUrl);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Contact contact) {
        AppDB.databaseWriteExecutor.execute(() -> {
            contactDao.insert(contact);
        });
    }
}
