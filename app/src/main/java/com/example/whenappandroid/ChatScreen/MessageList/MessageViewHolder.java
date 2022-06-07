package com.example.whenappandroid.ChatScreen.MessageList;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.databinding.RecycleMessagesByMeBinding;



public class MessageViewHolder extends RecyclerView.ViewHolder{
    private RecycleMessagesByMeBinding binding;

    public MessageViewHolder(RecycleMessagesByMeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void bind(Message message) {
//        binding.setText(message.getContent());
//        binding.textGchatDateOther.setText(message.getCreated());
        binding.textGchatMessageMe.setText(message.getContent());
        binding.textGchatTimestampMe.setText(message.getCreated().substring(11, 16));
    }
}
