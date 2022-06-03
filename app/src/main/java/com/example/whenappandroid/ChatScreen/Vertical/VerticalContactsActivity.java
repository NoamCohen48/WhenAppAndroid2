package com.example.whenappandroid.ChatScreen.Vertical;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whenappandroid.ChatScreen.AddContactActivity;
import com.example.whenappandroid.ChatScreen.ContactList.ContactListAdapter;
import com.example.whenappandroid.ChatScreen.ContactList.ContactViewModel;
import com.example.whenappandroid.ChatScreen.Horizontal.ChatHorizontalActivity;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.RetrofitService;
import com.example.whenappandroid.Data.ServerAPI;
import com.example.whenappandroid.databinding.ActivityVerticalContactsBinding;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerticalContactsActivity extends AppCompatActivity {
    private ActivityVerticalContactsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerticalContactsBinding.inflate(getLayoutInflater());
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

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            startActivity(new Intent(this, ChatHorizontalActivity.class));
        }
    }
}