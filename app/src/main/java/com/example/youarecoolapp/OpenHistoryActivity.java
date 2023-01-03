package com.example.youarecoolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OpenHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton deleteAllButton;
    FloatingActionButton addDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        recyclerView = findViewById(R.id.rv_database);
        deleteAllButton = findViewById(R.id.btn_delete_all);
        addDataButton = findViewById(R.id.btn_add_history);

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity();
            }
        });

    }

    public void openActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}



