package com.example.whenappandroid.ChatScreen.Vertical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.whenappandroid.databinding.ActivityVerticalMessagesBinding;

public class VerticalMessagesActivity extends AppCompatActivity {
    private ActivityVerticalMessagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerticalMessagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}