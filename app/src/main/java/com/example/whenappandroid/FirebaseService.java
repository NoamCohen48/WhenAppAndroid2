package com.example.whenappandroid;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.whenappandroid.Data.AppDB;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.ContactDao;
import com.example.whenappandroid.Data.Globals;
import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.Data.MessageDao;
import com.example.whenappandroid.Data.RetrofitService;
import com.example.whenappandroid.Data.ServerAPI;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirebaseService extends FirebaseMessagingService {
    private ServerAPI api;
    private ContactDao contactDao;
    private MessageDao messageDao;

    public FirebaseService() {
        AppDB db = AppDB.getDatabase(this);
        contactDao = db.contactDao();
        messageDao = db.messageDao();

        api = RetrofitService.getAPI(Globals.getServerAndroid());
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();

        if (data.get("type").equals("message")) {
            Message message = new Message(
                    Integer.parseInt(data.get("id")),
                    data.get("contact"),
                    data.get("content"),
                    data.get("created"),
                    data.get("sent").equals("true")
            );
            messageDao.insert(message);

        } else {
            Contact contact = new Contact(
                    data.get("id"),
                    data.get("name"),
                    data.get("server"),
                    "",
                    null
            );
            contactDao.insert(contact);
        }

    }

    @Override
    public void onNewToken(@NonNull String refreshedToken) {
        super.onNewToken(refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        ServerAPI api = RetrofitService.getAPI(Globals.getServerAndroid());
        api.registerToken(new ServerAPI.RegisterTokenPayload("noam", refreshedToken)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("custom:", "sendRegistrationToServer: " + refreshedToken);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                int x;
            }
        });
    }
}