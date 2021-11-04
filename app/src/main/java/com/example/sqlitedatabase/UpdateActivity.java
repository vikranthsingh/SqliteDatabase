package com.example.sqlitedatabase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText editTextTitleUpdate, editTextAuthorUpdate, editTextPagesUpdate;
    private Button buttonUpdate, buttonDelete;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editTextTitleUpdate = findViewById(R.id.editTextTitleUpdate);
        editTextAuthorUpdate = findViewById(R.id.editTextAuthorUpdate);
        editTextPagesUpdate = findViewById(R.id.editTextPagesUpdate);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        getAndSetIntentData();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = editTextTitleUpdate.getText().toString();
                author = editTextAuthorUpdate.getText().toString();
                pages = editTextPagesUpdate.getText().toString();
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());
                db.updateData(UpdateActivity.this, id, title, author, pages);
            }
        });
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOneRow();
            }
        });
    }

    private void deleteOneRow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Do you want to Delete " + title + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper db = new MyDatabaseHelper(UpdateActivity.this);
                db.deleteRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
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