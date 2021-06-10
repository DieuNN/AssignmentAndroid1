package com.example.qlsv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.qlsv.R;
import com.example.qlsv.SQL.ClassDB;
import com.example.qlsv.SQL.Database;

public class EditClass extends AppCompatActivity {
    EditText className, classID;
    ImageButton btnEditClass;
    ClassDB db =  new ClassDB(new Database(EditClass.this));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        mapping();

        Intent intent = getIntent();
        className.setText(intent.getStringExtra("name"));
        classID.setText(intent.getStringExtra("id"));
        
        btnEditClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.updateClass(classID.getText().toString(), className.getText().toString())) {
                    Toast.makeText(EditClass.this, "Thay đổi thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditClass.this, "Thay đổi thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mapping() {
        className = findViewById(R.id.txtClassName);
        classID = findViewById(R.id.txtClassID);
        btnEditClass = findViewById(R.id.btnEditClass);
    }
}