package com.example.qlsv.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
    TextView txtIfStudentListEmpty;



    final Database database = new Database(this);
    StudentDB studentDB = new StudentDB(database);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_management);
        mapping();
        StudentDB studentDB = new StudentDB(database);
        StudentAdapter studentAdapter = new StudentAdapter(this, R.layout.student_row, studentDB.getAllStudent());


        if (studentDB.getAllStudent().size() == 0) {
            listView.setVisibility(View.INVISIBLE);
            txtIfStudentListEmpty.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(studentAdapter);
            txtIfStudentListEmpty.setVisibility(View.INVISIBLE);
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentManagement.this);
                builder.setTitle("Thông báo").setMessage("Bạn muốn xóa?").setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Student> list = studentDB.getAllStudent();
                        if(studentDB.deleteStudent(list.get(position).getId())){
                            Toast.makeText(StudentManagement.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                            onResume();
                        } else {
                            Toast.makeText(StudentManagement.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
                return true;
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Student> list = new ArrayList<>();
                list = studentDB.getAllStudent();
                Intent intent = new Intent(StudentManagement.this, EditStudent.class);
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("classname","");
                intent.putExtra("dob", list.get(position).getDOB());
                startActivity(intent);
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
        txtIfStudentListEmpty = findViewById(R.id.txtIfStudentListEmpty);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView = findViewById(R.id.studentManagementListView);
        StudentAdapter studentAdapter = new StudentAdapter(this, R.layout.student_row, studentDB.getAllStudent());
        studentAdapter.notifyDataSetChanged();
        listView.setAdapter(studentAdapter);
    }
}