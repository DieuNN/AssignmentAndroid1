package com.example.qlsv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.qlsv.Adapter.ClassAdapter;
import com.example.qlsv.Adapter.ConfirmDeleteDialog;
import com.example.qlsv.Adapter.OptionDialog;
import com.example.qlsv.Model.PolyClass;
import com.example.qlsv.R;

import java.util.ArrayList;

public class ClassList extends AppCompatActivity {
    ListView listViewClass;
    ConfirmDeleteDialog confirm;
    OptionDialog confirmEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        mapping();

        ArrayList<PolyClass> polyClassList = new ArrayList<>();
        polyClassList.add(new PolyClass("ID1", "Lop1"));
        polyClassList.add(new PolyClass("ID2", "Lop2"));

        listViewClass.setAdapter(new ClassAdapter(getApplicationContext(), R.layout.class_row, polyClassList));

        listViewClass.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                OptionDialog optionDialog = new OptionDialog();
                optionDialog.show(getSupportFragmentManager(), "option");
                return false;
            }
        });
    }

    public void mapping() {
        listViewClass = findViewById(R.id.listViewClass);
    }
}