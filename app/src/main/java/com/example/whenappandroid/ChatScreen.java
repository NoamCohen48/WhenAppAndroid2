package com.example.whenappandroid;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whenappandroid.Data.Contact;

import java.util.List;

public class ChatScreen extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDao();

        Contact shaked = new Contact(0, "shaked", "a", "hey", "");
        Contact noam = new Contact(0, "noam", "a", "hi", "");

        contactDao.insert(shaked, noam);

        ListView lvContacts = findViewById(R.id.lvContacts);
        List<Contact> contacts = contactDao.index();

        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);

        lvContacts.setAdapter(adapter);


    }

}