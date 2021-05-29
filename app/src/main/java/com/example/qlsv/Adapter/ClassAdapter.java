package com.example.qlsv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlsv.Model.PolyClass;
import com.example.qlsv.R;

import java.util.List;

public class ClassAdapter extends ArrayAdapter<PolyClass> {
    public ClassAdapter(@NonNull Context context, int resource, @NonNull List<PolyClass> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.class_row, parent, false);

        TextView lblClassName = convertView.findViewById(R.id.lblClassName);
        TextView lblClassId = convertView.findViewById(R.id.lblClassId);

        lblClassId.setText(getItem(position).getId());
        lblClassName.setText(getItem(position).getName());
        return convertView;
    }
}
