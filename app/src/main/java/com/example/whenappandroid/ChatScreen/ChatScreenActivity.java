package com.example.whenappandroid.ChatScreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.ChatScreen.ContactList.ContactListAdapter;
import com.example.whenappandroid.ChatScreen.ContactList.ContactViewModel;
import com.example.whenappandroid.R;

public class ChatScreenActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.ContactDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(this, contact -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(contact);
        });

    }


}