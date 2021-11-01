package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

        Myadapter myadapter = new Myadapter(MainActivity.this, id, title, author, pages);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        readAllData();
    }
    void readAllData(){
        Cursor cursor = db.getData();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                author.add(cursor.getString(2));
                pages.add(cursor.getString(3));
            }
        }
    }
}