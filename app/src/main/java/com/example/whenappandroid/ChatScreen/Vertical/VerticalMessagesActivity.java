package com.example.whenappandroid.ChatScreen.Vertical;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

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
    private MessageDao messageDao;
    private AppDB db;
    List<Message> allMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerticalMessagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = Room.databaseBuilder(this, AppDB.class, "WhenAppDB").allowMainThreadQueries().build();
        messageDao = db.messageDao();


        currentContact = (Contact) getIntent().getSerializableExtra("contact");
        allMessages = messageDao.Index();

        final MessageListAdapter adapter = new MessageListAdapter(this,allMessages);
        binding.recyclerGchat.setAdapter(adapter);
        binding.recyclerGchat.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        //Update the cached copy of the words in the adapter.
  /*      viewModel.getMessages(currentContact).observe(this, list -> {
            adapter.submitlist(list);
            adapter.notifyDataSetChanged();
        });*/

//        binding.textInputMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_GO) {
//                    sendMessage();
//                    return true;
//                }
//                return false;
//            }
//        });

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