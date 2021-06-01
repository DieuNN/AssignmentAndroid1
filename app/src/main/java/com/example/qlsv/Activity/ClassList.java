package com.example.qlsv.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv.Adapter.ClassAdapter;
import com.example.qlsv.Adapter.ConfirmDeleteDialog;
import com.example.qlsv.Adapter.OptionDialog;
import com.example.qlsv.Model.PolyClass;
import com.example.qlsv.R;
import com.example.qlsv.SQL.ClassDB;
import com.example.qlsv.SQL.Database;

import java.util.ArrayList;

public class ClassList extends AppCompatActivity {
    ListView listViewClass;
    TextView txtIfClassListEmpty;
    ConfirmDeleteDialog confirm;
    OptionDialog confirmEdit;
    Cursor cursor;
    ArrayList<PolyClass> classList;
    int position;

    final Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        mapping();


        ClassDB classDB = new ClassDB(database);
        ArrayList<PolyClass> list = classDB.getAllClass();

        if(list.size()==0){
            listViewClass.setVisibility(View.INVISIBLE);
            txtIfClassListEmpty.setText("Bạn chưa có lớp nào. Hãy thêm một lớp mới!");
        } else {
            txtIfClassListEmpty.setVisibility(View.INVISIBLE);

            listViewClass.setAdapter(new ClassAdapter(ClassList.this, R.layout.class_row, list));
        }

    }

    private void mapping() {
        listViewClass = findViewById(R.id.listViewClass);
        txtIfClassListEmpty = findViewById(R.id.txtIfClassListEmpty);
    }



}