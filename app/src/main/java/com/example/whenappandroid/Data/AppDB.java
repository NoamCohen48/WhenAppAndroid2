package com.example.whenappandroid.Data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class, Message.class}, version = 2, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();

    private static volatile AppDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDB.class, "WhenAppDB")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ContactDao dao = INSTANCE.contactDao();
                MessageDao messageDao = INSTANCE.messageDao();
                dao.deleteAll();

                Contact shaked = new Contact("1", "shaked", "a", "hey", "now");
                Contact noam = new Contact("2", "noam", "a", "hi", "then");
                Contact roi = new Contact("3", "roi", "a", "helo", "year ago");
                dao.insert(shaked);
                dao.insert(noam);
                dao.insert(roi);
                Message message1 = new Message(1,"shaked", "hello","1/6/22", true);
                Message message2 = new Message(2,"shaked", "world","1/6/22", false);
                Message message3 = new Message(3,"noam", "hi","1/6/22", true);
                messageDao.insert(message1);
                messageDao.insert(message2);
                messageDao.insert(message3);
            });
        }
    };
}
