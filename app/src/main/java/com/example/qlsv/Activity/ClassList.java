package com.example.qlsv.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv.Adapter.ClassAdapter;
import com.example.qlsv.Adapter.ConfirmDeleteDialog;
import com.example.qlsv.Adapter.OptionDialog;
import com.example.qlsv.Model.PolyClass;
import com.example.qlsv.R;
import com.example.qlsv.SQL.Database;

import java.util.ArrayList;

public class ClassList extends AppCompatActivity {
    ListView listViewClass;
    ConfirmDeleteDialog confirm;
    OptionDialog confirmEdit;
    Database database;
    Cursor cursor;
    ArrayList<PolyClass> classList;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        mapping();


    }

    private void mapping() {
        listViewClass = findViewById(R.id.listViewClass);
        database = new Database(ClassList.this);

    }




    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }


}