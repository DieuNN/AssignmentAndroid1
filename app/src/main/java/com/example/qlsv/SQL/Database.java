package com.example.qlsv.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.qlsv.Model.PolyClass;
import com.example.qlsv.Model.Student;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "STUDENT_DATABASE";
    public static final String TABLE_STUDENT = "STUDENT";
    public static final String TABLE_CLASS = "CLASS";
    public static final int DATABASE_VERSION = 3;

    SQLiteDatabase database;
    ContentValues values;
    Cursor cursor;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String classQuery = "CREATE TABLE " + TABLE_CLASS + " (" +
                "id text primary key, name text)";
        db.execSQL(classQuery);

        String studentQuery = "CREATE TABLE " + TABLE_STUDENT + "(" +
                "id text primary key," +
                "name text," +
                "classid text," +
                "dob text," +
                "foreign key (classid) references " + TABLE_CLASS + "(id)" + ")";
        db.execSQL(studentQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        onCreate(db);
    }


}
