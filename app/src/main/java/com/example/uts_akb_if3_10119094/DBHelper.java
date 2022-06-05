package com.example.uts_akb_if3_10119094;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_note";
    public static final String table_name = "table_note";

    public static final String row_id = "_id";
    public static final String row_title = "Title";
    public static final String row_note = "Notes";
    public static final String row_created = "Created";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_title + " TEXT," + row_note + " TEXT," + row_created + "TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int x) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    public Notes getNote(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(database_name, new String[]{row_id, row_title, row_note,
                        row_created}, row_id+"=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
        }

        Notes note = new Notes(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3));

        return note;
    }

    public List<Notes> getNotes()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Notes> index = new ArrayList<>();

        String query = "SELECT * FROM "+table_name;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Notes note = new Notes();
                note.setId(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setDesc(cursor.getString(2));

                index.add(note);

            }while(cursor.moveToNext());
        }

        return index;
    }

    //get all SQLite data
    public Cursor allData() {
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " ORDER BY " + row_id + " DESC ", null);
        return cur;
    }

    //get 1 data by ID
    public Cursor oneData(long id){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " WHERE " + row_id + "=" + id, null);
        return cur;
    }

    //insert data
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }

    //update data
    public void updateData(ContentValues values, long id){
        db.update(table_name, values, row_id + "=" + id, null);
    }

    //delete data
    public void deleteData(long id){
        db.delete(table_name, row_id + "=" + id, null);
    }
}
