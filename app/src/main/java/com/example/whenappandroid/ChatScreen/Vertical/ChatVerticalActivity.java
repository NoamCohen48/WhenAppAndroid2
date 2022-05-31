package com.example.whenappandroid.ChatScreen.Vertical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whenappandroid.ChatScreen.AddContactActivity;
import com.example.whenappandroid.ChatScreen.ContactList.ContactListAdapter;
import com.example.whenappandroid.ChatScreen.ContactList.ContactViewModel;
import com.example.whenappandroid.databinding.ActivityChatVerticalBinding;

public class ChatVerticalActivity extends AppCompatActivity {
    private ActivityChatVerticalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatVerticalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.ContactDiff());
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        // Update the cached copy of the words in the adapter.
        contactViewModel.getAllContacts().observe(this, adapter::submitList);

        binding.btnAddContact.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        });
    }
}