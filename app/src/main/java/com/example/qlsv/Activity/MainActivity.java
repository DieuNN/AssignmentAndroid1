package com.example.qlsv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qlsv.R;

public class MainActivity extends AppCompatActivity {
    Button addClass, classList, studentManagement;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

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