package com.example.whenappandroid.ChatScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whenappandroid.R;
import com.example.whenappandroid.databinding.FragmentAddContactBinding;

public class AddContactFragment extends Fragment {

    private FragmentAddContactBinding binding;

    public AddContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddContactBinding.inflate(inflater, container, false);
        return binding.getRoot();

        //return inflater.inflate(R.layout.fragment_add_contact, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}