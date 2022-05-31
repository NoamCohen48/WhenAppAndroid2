package com.example.whenappandroid.ChatScreen.ContactList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.R;
import com.example.whenappandroid.databinding.RecyclerviewItemBinding;

class ContactViewHolder extends RecyclerView.ViewHolder {
    private RecyclerviewItemBinding binding;

    public ContactViewHolder(RecyclerviewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Contact contact) {
        binding.dateText.setText(contact.getLastdate());
        binding.lastText.setText(contact.getLast());
        binding.nameText.setText(contact.getName());
        binding.Impo.setText(contact.getName());
    }

}