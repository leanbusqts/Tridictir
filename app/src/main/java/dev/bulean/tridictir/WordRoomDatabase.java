package dev.bulean.tridictir;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
* Room is a database layer on top of an SQLite database.
* Room uses the DAO to issue queries to its database.
* By default, Room doesn't allow you to issue database queries on the main thread.
* LiveData applies this rule by automatically running the query asynchronously on
* a background thread, when needed.
* Room provides compile-time checks of SQLite statements.
* */

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    /*
    * WordRoomDatabase as a singleton to prevent having multiple instances of the database
    * opened at the same time
    */
    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
