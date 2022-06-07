package com.example.whenappandroid.ChatScreen.MessageList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.Data.MessageRepository;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository repository;
    private String currentUser = "noam";
    
    public MessageViewModel (Application application) {
        super(application);
        repository = new MessageRepository(application);
    }

    public LiveData<List<Message>> getMessages(Contact currentContact) { return repository.getMessages(currentContact.getId()); }

    public void insert(Contact currentContact, String content) { repository.addMessage(currentUser, currentContact, content); }
}
