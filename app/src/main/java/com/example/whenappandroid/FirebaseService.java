package com.example.whenappandroid;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

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
    public ServerAPI api;
    private ContactDao contactDao;
    private MessageDao messageDao;
    static public String token;

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

            Contact contact = contactDao.getContact(data.get("contact"));

            contact.setLast(data.get("content"));
            contact.setLastdate(data.get("created"));
            contactDao.update(contact);
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
        token = refreshedToken;
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("firebaseToken", refreshedToken).apply();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("firebaseToken", "empty");
    }


}