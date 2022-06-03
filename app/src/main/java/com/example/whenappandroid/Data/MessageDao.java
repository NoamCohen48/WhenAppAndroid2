package com.example.whenappandroid.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM message")
    LiveData<List<Message>> getAll();

    @Query("SELECT * FROM message WHERE id =:id")
    Message get(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Message... messages);

    @Update
    void update(Message... messages);

    @Query("DELETE FROM message")
    void deleteAll();

    @Delete
    void delete(Message... messages);
}