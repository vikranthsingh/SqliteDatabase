package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText editTextTitleUpdate, editTextAuthorUpdate, editTextPagesUpdate;
    private Button buttonUpdate;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editTextTitleUpdate = findViewById(R.id.editTextTitleUpdate);
        editTextAuthorUpdate = findViewById(R.id.editTextAuthorUpdate);
        editTextPagesUpdate = findViewById(R.id.editTextPagesUpdate);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        getAndSetIntentData();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());
                db.updateData(UpdateActivity.this, id, editTextTitleUpdate.getText().toString(), editTextAuthorUpdate.getText().toString(), editTextPagesUpdate.getText().toString());
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            editTextTitleUpdate.setText(title);
            editTextAuthorUpdate.setText(author);
            editTextPagesUpdate.setText(pages);
        } else {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}