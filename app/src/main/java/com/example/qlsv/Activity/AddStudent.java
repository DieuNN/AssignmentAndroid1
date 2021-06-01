package com.example.qlsv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qlsv.Model.PolyClass;
import com.example.qlsv.Model.Student;
import com.example.qlsv.R;
import com.example.qlsv.SQL.ClassDB;
import com.example.qlsv.SQL.Database;
import com.example.qlsv.SQL.StudentDB;

import java.util.ArrayList;
import java.util.Calendar;

public class AddStudent extends AppCompatActivity {
    Spinner spinner;
    DatePickerDialog datePickerDialog;
    EditText edtChooseDate, edtStudentName, edtStudentId;
    Button btnChooseDate, btnAddNewStudent;
    int day, month, year;
    ArrayList<PolyClass> list;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mapping();


        Database database = new Database(this);
        StudentDB studentDB = new StudentDB(database);
        ClassDB classDB = new ClassDB(database);

        list = classDB.getAllClass();
        String[] arr = new String[list.size()+1];
        Log.e("listsize", String.valueOf(list.size()));
        arr[0] = "Chọn lớp";
        for (int i = 0; i < list.size(); i++) {
            arr[i+1] = list.get(i).getName();
        }

        edtChooseDate.setEnabled(false);

        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr));

        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddStudent.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        edtChooseDate.setText(day + "/" + month+ "/" + year );
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        
        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtStudentId.getText().toString().matches("")||edtStudentName.getText().toString().matches("")){
                    Toast.makeText(AddStudent.this, "Bạn phải nhập đủ các trường!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(AddStudent.this, "Bạn chưa chọn lớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(edtChooseDate.getText().toString().matches("")){
                    Toast.makeText(AddStudent.this, "Bạn chưa chọn ngày sinh!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = new Student();
                student.setId(edtStudentId.getText().toString());
                student.setName(edtStudentName.getText().toString());
                student.setDOB(edtChooseDate.getText().toString());
                student.setClassName(spinner.getSelectedItem().toString());
                Log.e("classname", spinner.getSelectedItem().toString());
                
                if(studentDB.addStudent(student)){
                    Toast.makeText(AddStudent.this, "Thêm sinh viên mới thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddStudent.this, "Thêm sinh viên mới thất bại! Mã sinh viên đã có trong dữ liệu!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        
    }

    private void mapping() {
        spinner = findViewById(R.id.spinnerChooseClassName);
        edtChooseDate = findViewById(R.id.editChooseDate);
        btnChooseDate = findViewById(R.id.btnChooseDate);
        btnAddNewStudent = findViewById(R.id.btnAddNewStudent);
        edtStudentName = findViewById(R.id.edtStudentName);
        edtStudentId = findViewById(R.id.edtStudentId);
    }


}