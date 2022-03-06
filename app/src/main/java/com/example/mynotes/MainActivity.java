package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  final ArrayList<Note> notes = new ArrayList<>();
    private MainViewModel mainViewModel;
    private NotesAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        recyclerView = findViewById(R.id.recyclerviewNotes);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        getDataFromDb();

        adapter = new NotesAdapter(notes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnNoteClickListener(new NotesAdapter.onNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "You just clicked on item", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(int position) {
                removeNote(position);

            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeNote(int position) {
        Note note = adapter.getNotes().get(position);
        mainViewModel.deleteNote(note);
    }



    public void addNote(View view) {
        Intent intent = new Intent(this, AddNewActivity.class);
        startActivity(intent);
    }

    public void getDataFromDb () {
        LiveData<List<Note>> notesFromLiveData = mainViewModel.getNotes();
        notesFromLiveData.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notesFromLiveData) {
              adapter.setNotes(notesFromLiveData);
            }
        });

    }

}