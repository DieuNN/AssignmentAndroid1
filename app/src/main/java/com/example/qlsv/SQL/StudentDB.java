package com.example.qlsv.SQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qlsv.Model.Student;

import java.util.ArrayList;

public class StudentDB {
    private Database db;
    private SQLiteDatabase database;
    private ArrayList<Student> list;

    public StudentDB(Database db) {
        this.db = db;
    }

    public boolean addStudent(Student student) {
        database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", student.getId());
        values.put("name", student.getName());
        values.put("classid", student.getClassName());
        values.put("DOB", student.getDOB());

        if(database.insert(db.TABLE_STUDENT, null, values)>=0){
            return true;
        }
        return false;
    }

    public ArrayList<Student> getAllStudent() {
        list = new ArrayList<>();
        database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + db.TABLE_STUDENT, null);

        if(cursor.getCount() >0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Student student = new Student();
                student.setId(cursor.getString(0));
                student.setName(cursor.getString(1));
                student.setDOB(cursor.getString(3));
                student.setClassName(cursor.getString(2));

                list.add(student);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public boolean updateStudent(String newName, String newClassName, String newDOB, String currentID){
        database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("classid",  newClassName);
        values.put("dob", newDOB);

        if(database.update(db.TABLE_STUDENT, values, "id = ?", new String[]{currentID})>=0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteStudent(String currentID) {
        database = db.getWritableDatabase();

        if(database.delete(db.TABLE_STUDENT, "id = ?", new String[]{currentID})>=0) {
            return true;
        } else {
            return false;
        }
    }
}
