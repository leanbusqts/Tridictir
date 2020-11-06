package dev.bulean.tridictir;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/*
* The data access object, or Dao, is an annotated class where you specify SQL queries
* and associate them with method calls.
* The compiler checks the SQL for errors, then generates queries from the annotations.
* LiveData is a lifecycle library class for data observation,
* can help your app respond to data changes.
* If you use a return value of type LiveData in your method description,
* Room generates all necessary code to update the LiveData when the database is updated.
* */

@Dao
public interface WordDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}
