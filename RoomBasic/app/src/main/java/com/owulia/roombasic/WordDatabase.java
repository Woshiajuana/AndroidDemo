package com.owulia.roombasic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// singleton
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;

    public abstract WordDao getWordDao();

    static synchronized WordDatabase getDatabase (Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    
}
