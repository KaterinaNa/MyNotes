package com.example.mynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNewActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerdayOfMounth;
    private RadioGroup radiogroup;
    private NotesBDHelper notesBDHelper;
    private SQLiteDatabase database;

   private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescroption);
        spinnerdayOfMounth = findViewById(R.id.spinner);
        radiogroup = findViewById(R.id.radiogroup);
        notesBDHelper = new NotesBDHelper(this);
        database = notesBDHelper.getWritableDatabase();

    }

    public void addNewNote(View view) {
        String title = editTextTitle.getText().toString().trim();
        String  description = editTextDescription.getText().toString().trim();
        String dayOfWeek = spinnerdayOfMounth.getSelectedItem().toString();
        int radioButoonId = radiogroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButoonId);
        int priorety = Integer.parseInt(radioButton.getText().toString());

        if (isField (title, description)) {
            Note note = new Note(title, description, dayOfWeek, priorety);
            mainViewModel.insertNote(note);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else  Toast.makeText(this,
                R.string.fieldIsEmpty,
                Toast.LENGTH_SHORT).show();
    }

    private boolean isField (String title, String description) {
    return  !title.isEmpty() && !description.isEmpty();
    }

}