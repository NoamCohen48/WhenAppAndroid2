package com.example.whenappandroid;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whenappandroid.Data.Contact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository repository;

    private final LiveData<List<Contact>> allContacts;

    public ContactViewModel (Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }

    LiveData<List<Contact>> getAllContacts() { return allContacts; }

    public void insert(Contact contact) { repository.insert(contact); }
}
