package com.example.qlsv.SQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qlsv.Model.PolyClass;

import java.util.ArrayList;

public class ClassDB {
    private Database db;
    private SQLiteDatabase database;
    private ArrayList<PolyClass> list;

    public ClassDB(Database db) {
        this.db = db;
    }

    public boolean addClass(PolyClass polyClass) {
        database = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", polyClass.getId());
        values.put("name", polyClass.getName());

        if (database.insert(db.TABLE_CLASS, null, values) >= 0) {
            database.close();
            return true;
        }
        return false;
    }

    public ArrayList<PolyClass> getAllClass() {
        list = new ArrayList<>();
        database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + db.TABLE_CLASS, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                PolyClass polyClass = new PolyClass();
                polyClass.setId(cursor.getString(0));
                polyClass.setName(cursor.getString(1));

                list.add(polyClass);

                cursor.moveToNext();
            }
        }

        cursor.close();
        database.close();
        return list;
    }

    public boolean updateClass(String currentID, String newName) {
        database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", newName);

        if(database.update(db.TABLE_CLASS, values, "id = ?", new String[]{currentID})>=0){
            return true;
        }
        return false;
    }

    public boolean deleteClass(String currentID) {
        database = db.getWritableDatabase();

        if(database.delete(db.TABLE_CLASS, "id = ?", new String[]{currentID})>=0) {
            database.close();
            return true;
        } else {
            return false;
        }

    }
}
