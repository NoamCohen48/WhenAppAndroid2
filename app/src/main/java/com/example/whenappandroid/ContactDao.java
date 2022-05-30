package com.example.whenappandroid;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whenappandroid.Data.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> index();

    @Query("SELECT * FROM contact WHERE id =:id")
    Contact get(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Query("DELETE FROM contact")
    void deleteAll();

    @Delete
    void delete(Contact... contacts);

}
