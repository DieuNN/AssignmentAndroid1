package com.example.qlsv.DAO;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.qlsv.Model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO implements DAO<Student> {
    private List<Student> studentList = new ArrayList<>();


    @Override
    public List<Student> getAll() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<Student> get(String id) {
        return studentList.stream().filter(student -> student.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Student student) {
        studentList.add(student);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void update(Student student) {
       get(student.getId()).ifPresent(existStudent -> {
           existStudent.setId(student.getId());
           existStudent.setDOB(student.getDOB());
           existStudent.setClassName(student.getClassName());
           existStudent.setName(student.getName());
       });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void delete(Student student) {
        get(student.getId()).ifPresent(student1 -> studentList.remove(student));
    }
}
