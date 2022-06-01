package com.example.whenappandroid.ChatScreen.Horizontal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.whenappandroid.ChatScreen.Vertical.VerticalContactsActivity;
import com.example.whenappandroid.databinding.ActivityChatHorizontalBinding;

public class ChatHorizontalActivity extends AppCompatActivity {
    private ActivityChatHorizontalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatHorizontalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            startActivity(new Intent(this, VerticalContactsActivity.class));
        }
    }
}