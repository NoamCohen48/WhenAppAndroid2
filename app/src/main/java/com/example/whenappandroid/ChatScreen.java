package com.example.whenappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whenappandroid.Data.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatScreen extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    ArrayAdapter<Contact> adapter;
    List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDao();

        FloatingActionButton btnAdd = findViewById(R.id.btnAddContact);
        btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(this, AddContactFragment.class);
            startActivity(i);
        });

        Contact shaked = new Contact(0, "shaked", "a", "hey", "");
        Contact noam = new Contact(0, "noam", "a", "hi", "");

        contactDao.insert(shaked, noam);

        ListView lvContacts = findViewById(R.id.lvContacts);
        contacts = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);

        lvContacts.setAdapter(adapter);

    }

    @Override
    protected void onResume() {

        super.onResume();
        contacts.clear();
        contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();
    }

}