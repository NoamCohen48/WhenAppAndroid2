package com.example.whenappandroid.ChatScreen.MessageList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.ContactRepository;
import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.Data.MessageRepository;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository repository;

    private final LiveData<List<Message>> allMessages;

    public MessageViewModel (Application application) {
        super(application);
        repository = new MessageRepository(application);
        allMessages = repository.getAllMessages();
    }

    public LiveData<List<Message>> getAllMessages() { return allMessages; }

    public void insert(Message message) { repository.insert(message); }
}
