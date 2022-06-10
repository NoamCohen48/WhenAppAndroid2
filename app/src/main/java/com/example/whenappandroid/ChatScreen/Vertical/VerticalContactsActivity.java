package com.example.whenappandroid.ChatScreen.Vertical;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.whenappandroid.R;
import com.example.whenappandroid.SettingsScreen.SettingsActivity;
import com.example.whenappandroid.databinding.ActivityVerticalContactsBinding;

import java.util.List;

public class VerticalContactsActivity extends AppCompatActivity {
    private ActivityVerticalContactsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerticalContactsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.ContactDiff());
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter.setItemClickListener((v, contact) -> {
            Intent intent = new Intent(this, VerticalMessagesActivity.class);
            intent.putExtra("contact", contact);
            startActivity(intent);
        });

        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.getAllContacts().observe(this, (List<Contact> list) -> {
            adapter.submitList(list);
            adapter.notifyDataSetChanged();
        });


        binding.btnAddContact.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Intent intent = new Intent(this, ChatHorizontalActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
