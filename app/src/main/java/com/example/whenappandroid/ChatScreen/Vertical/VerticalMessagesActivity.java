package com.example.whenappandroid.ChatScreen.Vertical;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whenappandroid.ChatScreen.MessageList.MessageListAdapter;
import com.example.whenappandroid.ChatScreen.MessageList.MessageViewModel;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.Globals;
import com.example.whenappandroid.databinding.ActivityVerticalMessagesBinding;

public class VerticalMessagesActivity extends AppCompatActivity {
    private ActivityVerticalMessagesBinding binding;
    private MessageViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerticalMessagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Contact currentContact = (Contact) getIntent().getSerializableExtra("contact");

        final MessageListAdapter adapter = new MessageListAdapter(new MessageListAdapter.MessageDiff());
        binding.recyclerGchat.setAdapter(adapter);
        binding.recyclerGchat.setLayoutManager(new LinearLayoutManager(this));

        MessageViewModel messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
       //Update the cached copy of the words in the adapter.
        messageViewModel.getMessages(currentContact).observe(this, list -> {
            adapter.submitList(list);
        });

        binding.buttonGchatSend.setOnClickListener(v -> {
            String from = Globals.currentUser;
            String content = binding.textInputMessage.getText().toString();

            viewModel.insert(from, currentContact, content);
        });

    }
}