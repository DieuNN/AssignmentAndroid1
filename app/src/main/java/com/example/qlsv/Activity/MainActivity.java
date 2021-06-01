package com.example.qlsv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qlsv.Model.PolyClass;
import com.example.qlsv.R;
import com.example.qlsv.SQL.Database;

import java.util.Observer;

public class MainActivity extends AppCompatActivity {
    Button addClass, classList, studentManagement;
    Intent intent;
    Observer observer;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        Database database = new Database(getApplicationContext());
        /*
         TODO:
             + Design Student management activity
             + Dialogs box for Student management activity
             + Create databases
        */

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), AddClass.class);
                startActivity(intent);
            }
        });

        classList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ClassList.class);
                startActivity(intent);
            }
        });

        studentManagement.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), StudentManagement.class);
            startActivity(intent);
        });
    }

    private void mapping() {
        addClass = findViewById(R.id.btnAddNewClass);
        classList = findViewById(R.id.btnClassList);
        studentManagement = findViewById(R.id.btnStudentManagement);
    }
}

//Added some comments and testing
//Added more comments