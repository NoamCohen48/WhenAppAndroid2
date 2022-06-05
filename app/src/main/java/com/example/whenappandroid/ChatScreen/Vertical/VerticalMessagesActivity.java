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
import com.example.whenappandroid.ChatScreen.MessageList.MessageListAdapter;
import com.example.whenappandroid.ChatScreen.MessageList.MessageViewModel;
import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.databinding.ActivityVerticalMessagesBinding;

import java.util.ArrayList;
import java.util.List;

public class VerticalMessagesActivity extends AppCompatActivity {
    private ActivityVerticalMessagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerticalMessagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final MessageListAdapter adapter = new MessageListAdapter(new MessageListAdapter.MessageDiff());
        binding.recyclerGchat.setAdapter(adapter);
        binding.recyclerGchat.setLayoutManager(new LinearLayoutManager(this));

        MessageViewModel messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
       //Update the cached copy of the words in the adapter.
        messageViewModel.getAllMessages().observe(this, adapter::submitList);

//        List<Message> messageList = new ArrayList<>();
//        messageList.add(new Message(1,"shalom", "1/6/22", true));
//        messageList.add(new Message(2,"hi", "1/6/22", true));
//        messageList.add(new Message(3,"roi", "1/6/22", true));
//        adapter.setMessageList(messageList);
    }
}