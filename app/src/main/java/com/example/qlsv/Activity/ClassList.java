package com.example.qlsv.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    ClassDB classDB = new ClassDB(database);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        mapping();



        ArrayList<PolyClass> list = classDB.getAllClass();

        if(list.size()==0){
            listViewClass.setVisibility(View.INVISIBLE);
            txtIfClassListEmpty.setText("Bạn chưa có lớp nào. Hãy thêm một lớp mới!");
        } else {
            txtIfClassListEmpty.setVisibility(View.INVISIBLE);
            ClassAdapter adapter = new ClassAdapter(this, R.layout.class_row, list);
            adapter.notifyDataSetChanged();
            listViewClass.setAdapter(adapter);

        }

        listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClassList.this, EditClass.class);
                ArrayList<PolyClass> list = classDB.getAllClass();
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("id", list.get(position).getId());
                startActivity(intent);
            }
        });

        listViewClass.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassList.this);
                builder.setTitle("Thông báo").setMessage("Bạn muốn xóa?").setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(classDB.deleteClass(list.get(position).getId())){
                            Toast.makeText(ClassList.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                            onResume();
                        } else {
                            Toast.makeText(ClassList.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
                return true;
            }
        });





    }

    private void mapping() {
        listViewClass = findViewById(R.id.listViewClass);
        txtIfClassListEmpty = findViewById(R.id.txtIfClassListEmpty);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listViewClass = findViewById(R.id.listViewClass);
        ClassAdapter adapter = new ClassAdapter(this, R.layout.class_row, classDB.getAllClass());
        adapter.notifyDataSetChanged();
        listViewClass.setAdapter(adapter);
    }
}