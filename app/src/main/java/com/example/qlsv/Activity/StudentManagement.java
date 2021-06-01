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

import java.util.ArrayList;

public class StudentManagement extends AppCompatActivity {
    ArrayList<Student> studentArrayList = new ArrayList<>();
    ListView listView;
    ImageButton button;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_management);
        mapping();

        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        studentArrayList.add(new Student("ID1", "Dieu", "Lop 1", "20122002"));
        listView.setAdapter(new StudentAdapter(getApplicationContext(), R.layout.student_row, studentArrayList));

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                OptionDialog dialog = new OptionDialog();
                dialog.show(getSupportFragmentManager(), "edit");
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
    }

    private void mapping() {
        listView = findViewById(R.id.studentManagementListView);
        button = findViewById(R.id.btnAddStudent);
    }
}