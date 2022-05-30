package com.example.whenappandroid.ChatScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whenappandroid.AppDB;
import com.example.whenappandroid.ContactDao;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.databinding.ActivityChatScreenBinding;

import java.util.List;

public class ChatScreenActivity extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    private ActivityChatScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityChatScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_chat_screen);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDao();

        Contact shaked = new Contact(0, "shaked", "a", "hey", "");
        Contact noam = new Contact(0, "noam", "a", "hi", "");

        contactDao.insert(shaked, noam);

        //ListView lvContacts = findViewById(R.id.lvContacts);
        List<Contact> contacts = contactDao.index();

        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);

        binding.lvContacts.setAdapter(adapter);

        binding.btnAddContact.setOnClickListener(v -> {

        });
    }

}