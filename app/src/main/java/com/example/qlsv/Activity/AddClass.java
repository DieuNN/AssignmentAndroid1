package com.example.qlsv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.qlsv.Adapter.ConfirmDeleteDialog;
import com.example.qlsv.R;

public class AddClass extends AppCompatActivity {
    ImageButton imageButtonAddClass;
    EditText txtClassName, txtClassId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        mapping();

        imageButtonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void mapping() {
        imageButtonAddClass = findViewById(R.id.btnAddClass);
        txtClassId = findViewById(R.id.txtClassID);
        txtClassName = findViewById(R.id.txtClassName);
    }

    public void openDialog(){
        ConfirmDeleteDialog dialog = new ConfirmDeleteDialog();
        dialog.show(getSupportFragmentManager(), "Example dialog");
    }


}