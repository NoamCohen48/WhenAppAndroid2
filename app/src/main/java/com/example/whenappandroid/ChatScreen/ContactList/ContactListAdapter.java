package com.example.whenappandroid.ChatScreen.ContactList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.whenappandroid.ChatScreen.ContactList.ContactViewHolder;
import com.example.whenappandroid.Data.Contact;

public class ContactListAdapter extends ListAdapter<Contact, ContactViewHolder> {

    public ContactListAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ContactViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact current = getItem(position);
        holder.bind(current.toString());
    }

    static public class ContactDiff extends DiffUtil.ItemCallback<Contact> {

        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    }
}
