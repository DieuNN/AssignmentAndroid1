package com.example.qlsv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv.Adapter.OptionDialog;
import com.example.qlsv.Adapter.StudentAdapter;
import com.example.qlsv.Model.Student;
import com.example.qlsv.R;
import com.example.qlsv.SQL.Database;
import com.example.qlsv.SQL.StudentDB;

import java.util.ArrayList;

public class StudentManagement extends AppCompatActivity {
    ArrayList<Student> studentArrayList = new ArrayList<>();
    ListView listView;
    ImageButton button;
    Intent intent;

    final Database database = new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_management);
        mapping();
        StudentDB studentDB = new StudentDB(database);
        StudentAdapter studentAdapter = new StudentAdapter(StudentManagement.this, R.layout.class_row, studentDB.getAllStudent());
        listView.setAdapter(studentAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), AddStudent.class);
                startActivity(intent);
            }
        });
        studentAdapter.notifyDataSetChanged();
    }

    private void mapping() {
        listView = findViewById(R.id.studentManagementListView);
        button = findViewById(R.id.btnAddStudent);
    }
}