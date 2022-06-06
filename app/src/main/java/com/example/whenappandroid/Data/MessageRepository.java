package com.example.whenappandroid.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;

public class MessageRepository {
    private String serverUrl = "http://10.0.2.2:5270/";

    private String currentContact = null;

    private ServerAPI api;
    private MessageDao messageDao;
    private LiveData<List<Message>> allMessages;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public MessageRepository(Application application) {
        AppDB db = AppDB.getDatabase(application);
        messageDao = db.messageDao();
        allMessages = messageDao.index();

        api = RetrofitService.getAPI(serverUrl);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void addMessage(String from, Contact to, String content) {
        try {
            Message newMessage = api.addMessage(to.getId(), new ServerAPI.MessagePayload(content)).execute().body();

            if (newMessage == null) throw new Exception("server returned null");

            AppDB.databaseWriteExecutor.execute(() -> {
                messageDao.insert(newMessage);
            });

            api.transfer(to.getServer(), new ServerAPI.TransferPayload(from, to.getId(), content)).execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
