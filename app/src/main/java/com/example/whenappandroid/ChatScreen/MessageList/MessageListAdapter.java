package com.example.whenappandroid.ChatScreen.MessageList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.R;
import com.example.whenappandroid.databinding.RecycleMessagesByMeBinding;
import com.example.whenappandroid.databinding.RecycleMessagesByOtherBinding;
import com.example.whenappandroid.databinding.RecyclerviewItemBinding;

import java.util.List;

public class MessageListAdapter  extends ListAdapter<Message, MessageViewHolder> {
    private List<Message> messageList;

    public MessageListAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback) {
        super(diffCallback);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(RecycleMessagesByMeBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message current = getItem(position);
        holder.bind(current);
    }

    public void setMessageList(List<Message> m){
        messageList = m;
        notifyDataSetChanged();
    }

    static public class MessageDiff extends DiffUtil.ItemCallback<Message> {

        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    }
}
