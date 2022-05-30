package com.example.whenappandroid.ChatScreen.ContactList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.R;

class ContactViewHolder extends RecyclerView.ViewHolder {
    private final TextView contactItemView;

    private ContactViewHolder(View itemView) {
        super(itemView);
        contactItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        contactItemView.setText(text);
    }

    static ContactViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(view);
    }
}