package dev.bulean.tridictir;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
* The data for this app is words, and each word is represented by an entity in the database.
* To make the Word class meaningful to a Room database, you must annotate it.
* Annotations identify how each part of the Word class relates to an entry in the database.
* Room uses this information to generate code.
* */
/*
* @Entity(tableName = "word_table")
* Each @Entity class represents an entity in a table.
* Annotate your class declaration to indicate that the class is an entity.
* Specify the name of the table if you want it to be different from the name of the class.
* @PrimaryKey
* Every entity needs a primary key.
* To keep things simple, each word in the RoomWordsSample app acts as its own primary key.
* @NonNull
* Denotes that a parameter, field, or method return value can never be null.
* The primary key should always use this annotation.
* Use this annotation for any mandatory fields in your rows.
* @ColumnInfo(name = "word")
* Specify the name of a column in the table,
* if you want the column name to be different from the name of the member variable.
* Every field that's stored in the database must either be public or have a "getter" method.
* This app provides a getWord() "getter" method rather than exposing member variables directly.
* */
@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;
    private String mWird;

    public Word(@NonNull String word, @NonNull String wird){
        this.mWord = word;
        this.mWird = wird;
    }
    public String getWord(){
        return this.mWord;
    }
    public String getWird() {
        return this.mWird;
    }
}