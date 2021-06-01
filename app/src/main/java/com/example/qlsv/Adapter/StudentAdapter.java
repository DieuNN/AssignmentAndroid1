package com.example.qlsv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlsv.Model.Student;
import com.example.qlsv.R;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    ArrayList<Student>  list = new ArrayList<>();
    TextView name, id, className, DOB;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView =  inflater.inflate(R.layout.student_row, parent, false);

        name = convertView.findViewById(R.id.lblStudentName);
        id = convertView.findViewById(R.id.lblStudentId);
        className = convertView.findViewById(R.id.lblStudentClassName);
        DOB = convertView.findViewById(R.id.lblStudentDOB);

        name.setText("Tên: "+getItem(position).getName());
        id.setText("Mã số SV: " + getItem(position).getId());
        className.setText("Tên lớp: " + getItem(position).getClassName());
        DOB.setText("Ngày sinh: "+ getItem(position).getDOB());

        return convertView;
    }
}
