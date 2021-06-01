package com.example.qlsv.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv.DAO.PolyClassDAO;
import com.example.qlsv.Model.PolyClass;
import com.example.qlsv.R;
import com.example.qlsv.SQL.ClassDB;
import com.example.qlsv.SQL.Database;

import java.util.ArrayList;

public class AddClass extends AppCompatActivity {
    ImageButton imageButtonAddClass, imageButtonEditClass;
    EditText txtClassName, txtClassId;
    final Database database = new Database(AddClass.this);
    Intent intent;
    Cursor cursor;
    ArrayList<PolyClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        mapping();

        ClassDB classDB = new ClassDB(database);

        imageButtonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PolyClass polyClass = new PolyClass();
                if(txtClassId.getText().toString().matches("")||txtClassName.getText().toString().matches("")){
                    Toast.makeText(AddClass.this, "Bạn phải nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                polyClass.setId(txtClassId.getText().toString());
                polyClass.setName(txtClassName.getText().toString());

                if(classDB.addClass(polyClass)){
                    Toast.makeText(AddClass.this, "Thêm mới thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddClass.this, "Thêm mới thất bại! Mã lớp đã có trong dữ liệu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mapping() {
        imageButtonAddClass = findViewById(R.id.btnAddClass);
        txtClassId = findViewById(R.id.txtClassID);
        txtClassName = findViewById(R.id.txtClassName);

        PolyClassDAO dao = new PolyClassDAO();

        intent = getIntent();
        list = new ArrayList<>();
    }

}