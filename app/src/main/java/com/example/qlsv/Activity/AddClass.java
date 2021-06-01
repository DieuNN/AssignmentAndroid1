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
import com.example.qlsv.SQL.Database;

import java.util.ArrayList;

public class AddClass extends AppCompatActivity {
    ImageButton imageButtonAddClass, imageButtonEditClass;
    EditText txtClassName, txtClassId;
    Database database;
    Intent intent;
    PolyClassDAO dao;
    ClassList classList;
    Cursor cursor;
    ArrayList<PolyClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        mapping();



    }

    private void mapping() {
        imageButtonAddClass = findViewById(R.id.btnAddClass);
        txtClassId = findViewById(R.id.txtClassID);
        txtClassName = findViewById(R.id.txtClassName);
        database = new Database(AddClass.this);
        imageButtonEditClass = findViewById(R.id.btnEditClass);
        PolyClassDAO dao = new PolyClassDAO();

        intent = getIntent();
        list = new ArrayList<>();
    }

}