package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
    EditText noteEditText;
    int notesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        this.setTitle("Add Note");
        noteEditText = (EditText) findViewById(R.id.noteEdittext);
        Intent intent = getIntent();
        notesId = intent.getIntExtra("notesId", -1);
        if (notesId != -1) {
            noteEditText.setText(MainActivity.notes.get(notesId));

        } else {
            MainActivity.notes.add("");
            notesId = MainActivity.notes.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
            SharedPreferences sharedPreferences = getApplication().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
            HashSet<String> set = new HashSet(MainActivity.notes);
            sharedPreferences.edit().putStringSet("notes", set).apply();

        }
        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                MainActivity.notes.set(notesId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_layout));
        }

    }
}