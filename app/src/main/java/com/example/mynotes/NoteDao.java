package com.example.mynotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note ORDER BY dayOfWeek")
    LiveData<List<Note>> getAllNote();

    @Insert
    void insertNote (Note note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM note")
    void deleteAllNotes();
}
