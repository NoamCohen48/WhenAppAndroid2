package com.example.whenappandroid.ChatScreen.Vertical;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.whenappandroid.ChatScreen.MessageList.MessageListAdapter;
import com.example.whenappandroid.ChatScreen.MessageList.MessageViewModel;
import com.example.whenappandroid.Data.AppDB;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.Globals;
import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.Data.MessageDao;
import com.example.whenappandroid.databinding.ActivityVerticalMessagesBinding;
import com.google.type.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class VerticalMessagesActivity extends AppCompatActivity {

    private ActivityVerticalMessagesBinding binding;
    private MessageViewModel viewModel;
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerticalMessagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        currentContact = (Contact) getIntent().getSerializableExtra("contact");

        MessageListAdapter adapter = new MessageListAdapter();
        binding.recyclerGchat.setAdapter(adapter);
        binding.recyclerGchat.setLayoutManager(new LinearLayoutManager(this));


        viewModel.getMessages(currentContact).observe(this, list -> {
            adapter.submitList(list);
            binding.recyclerGchat.scrollToPosition(adapter.getItemCount() - 1);
        });

        binding.buttonGchatSend.setOnClickListener(v -> {
            sendMessage();
        });
    }

    private void sendMessage() {
        String from = Globals.currentUser;
        String content = binding.textInputMessage.getText().toString();

        viewModel.addMessage(from, currentContact, content);

        binding.textInputMessage.setText("");
    }
}