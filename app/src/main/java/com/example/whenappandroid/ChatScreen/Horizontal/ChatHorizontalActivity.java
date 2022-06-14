package com.example.whenappandroid.ChatScreen.Horizontal;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whenappandroid.ChatScreen.ContactList.ContactListAdapter;
import com.example.whenappandroid.ChatScreen.ContactList.ContactViewModel;
import com.example.whenappandroid.ChatScreen.MessageList.MessageListAdapter;
import com.example.whenappandroid.ChatScreen.MessageList.MessageViewModel;
import com.example.whenappandroid.ChatScreen.Vertical.VerticalContactsActivity;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.Globals;
import com.example.whenappandroid.databinding.ChatHorizontalWithMessagesBinding;

import java.util.List;

public class ChatHorizontalActivity extends AppCompatActivity {
    private ChatHorizontalWithMessagesBinding binding;

    private MessageViewModel viewModel;
    private Contact currentContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChatHorizontalWithMessagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);


        ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.ContactDiff());
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter.setItemClickListener((v, contact) -> {
            currentContact = contact;
            MessageListAdapter messageAdapter = new MessageListAdapter();
            binding.recyclerGchat.setAdapter(messageAdapter);
            binding.recyclerGchat.setLayoutManager(new LinearLayoutManager(this));
            binding.contactName.setText(contact.getName());


            viewModel.getMessages(contact).observe(this, list -> {
                messageAdapter.submitList(list);
                binding.recyclerGchat.scrollToPosition(messageAdapter.getItemCount() - 1);
            });

            binding.textInputMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_GO) {
                        sendMessage();
                        return true;
                    }
                    return false;
                }
            });
        });

        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.getAllContacts().observe(this, (List<Contact> list) -> {
            adapter.submitList(list);
            adapter.notifyDataSetChanged();
        });


    }

    private void sendMessage() {
        String from = Globals.currentUser;
        String content = binding.textInputMessage.getText().toString();
        if (content.isEmpty()) {
            return;
        }
        viewModel.addMessage(from, currentContact, content);

        binding.textInputMessage.setText("");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            startActivity(new Intent(this, VerticalContactsActivity.class));
        }
    }
}