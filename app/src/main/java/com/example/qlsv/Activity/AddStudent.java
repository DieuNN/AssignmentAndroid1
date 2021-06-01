package com.example.qlsv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qlsv.R;

import java.util.Calendar;

public class AddStudent extends AppCompatActivity {
    Spinner spinner;
    DatePickerDialog datePickerDialog;
    EditText chooseDate;
    Button btnChooseDate, btnAddNewStudent;
    int day, month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mapping();

        String[] arr = {"dieu", "diep", "huyng", "tuyet", "vv"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arr );
        spinner.setAdapter(adapter);

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
                        chooseDate.setText(day + "/" + month+ "/" +"/" + year );
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        
        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddStudent.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    private void mapping() {
        spinner = findViewById(R.id.spinnerChooseClassName);
        chooseDate = findViewById(R.id.editChooseDate);
        btnChooseDate = findViewById(R.id.btnChooseDate);
        btnAddNewStudent = findViewById(R.id.btnAddNewStudent);
    }


}