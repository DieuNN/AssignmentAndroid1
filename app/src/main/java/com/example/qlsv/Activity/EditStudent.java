package com.example.qlsv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class EditStudent extends AppCompatActivity {
    Spinner spinner;
    EditText dateOfBirth, studentName, studentId;
    Button btnChooseDate, btnEditStudent;
    StudentDB studentDB = new StudentDB(new Database(this));
    ClassDB classDB = new ClassDB(new Database(this));
    ArrayList<PolyClass> list = new ArrayList<>();
    int day, month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        mapping();

        Intent intent = getIntent();
        studentName.setText(intent.getStringExtra("name"));
        studentId.setText(intent.getStringExtra("id"));
        dateOfBirth.setText(intent.getStringExtra("dob"));

        list = classDB.getAllClass();
        String[] arr = new String[list.size()+1];
        Log.e("listsize", String.valueOf(list.size()));
        arr[0] = "Chọn lớp";
        for (int i = 0; i < list.size(); i++) {
            arr[i+1] = list.get(i).getName();
        }

        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr));

        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditStudent.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        dateOfBirth.setText(day + "/" + month+ "/" + year );
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        btnEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentId.getText().toString().matches("")||studentName.getText().toString().matches("")){
                    Toast.makeText(EditStudent.this, "Bạn phải nhập đủ các trường!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(EditStudent.this, "Bạn chưa chọn lớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(dateOfBirth.getText().toString().matches("")){
                    Toast.makeText(EditStudent.this, "Bạn chưa chọn ngày sinh!", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        btnEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentDB.updateStudent(studentName.getText().toString(), spinner.getSelectedItem().toString(), dateOfBirth.getText().toString(), studentId.getText().toString())) {
                    Toast.makeText(EditStudent.this, "Thay đổi thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditStudent.this, "Thay đổi thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void mapping() {
        spinner = findViewById(R.id.spinnerChooseClassName);
        dateOfBirth = findViewById(R.id.editChooseDate);
        btnChooseDate = findViewById(R.id.btnChooseDate);

        studentName = findViewById(R.id.edtStudentName);
        studentId = findViewById(R.id.edtStudentId);
        btnEditStudent =  findViewById(R.id.btnEditStudent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}