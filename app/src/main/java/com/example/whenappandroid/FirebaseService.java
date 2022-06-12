package com.example.whenappandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.whenappandroid.Data.AppDB;
import com.example.whenappandroid.Data.ContactDao;
import com.example.whenappandroid.Data.Globals;
import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.Data.MessageDao;
import com.example.whenappandroid.Data.RetrofitService;
import com.example.whenappandroid.Data.ServerAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
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

        api = RetrofitService.getAPI(Globals.server);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        Message message = new Message(
                Integer.parseInt(data.get("id")),
                data.get("contact"),
                data.get("content"),
                data.get("created"),
                data.get("sent").equals("true")
        );
        messageDao.insert(message);
    }

    @Override
    public void onNewToken(@NonNull String refreshedToken) {
        super.onNewToken(refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        ServerAPI api = RetrofitService.getAPI(Globals.server);
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