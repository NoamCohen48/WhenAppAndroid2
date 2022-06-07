package com.example.whenappandroid.ChatScreen.ContactList;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.databinding.RecyclerviewContactBinding;

class ContactViewHolder extends RecyclerView.ViewHolder {
    private final RecyclerviewContactBinding binding;

    public ContactViewHolder(RecyclerviewContactBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Contact contact) {
        binding.lastDate.setText(contact.getLastdate());
        binding.lastMessage.setText(contact.getLast());
        binding.contactName.setText(contact.getName());

    }

}