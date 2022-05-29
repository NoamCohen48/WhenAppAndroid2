package com.example.whenappandroid;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.whenappandroid.Data.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> index();

    @Query("SELECT * FROM contact WHERE id =:id")
    Contact get(int id);

    void insert(Contact... contacts);

    void update(Contact... contacts);

    void delete(Contact... contacts);

}
