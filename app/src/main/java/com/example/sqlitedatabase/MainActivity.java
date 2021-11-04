package com.example.sqlitedatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    MyDatabaseHelper db;
    ArrayList<String> id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });
        db = new MyDatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        title = new ArrayList<>();
        author = new ArrayList<>();
        pages = new ArrayList<>();

        Myadapter myadapter = new Myadapter(MainActivity.this, this, id, title, author, pages);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        readAllData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void readAllData() {
        Cursor cursor = db.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {    //read data from cursor
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                author.add(cursor.getString(2));
                pages.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnDelete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete");
            builder.setMessage("Are you sure Do you want to Delete " + title + "?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Cursor cursor = db.getData();
                    if (cursor.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                    } else {
                        MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());
                        db.deleteAllData();
                        Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}