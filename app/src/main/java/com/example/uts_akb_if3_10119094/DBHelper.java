package com.example.uts_akb_if3_10119094;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_note";
    public static final String table_name = "table_noter";

    public static final String row_id = "_id";
    public static final String row_title = "Title";
    public static final String row_note = "Detail";
    public static final String row_created = "Created";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 6);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_title + " TEXT," + row_note + " TEXT," + row_created + " TEXT)";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ table_name);
        onCreate(sqLiteDatabase);
    }

    public Notes getNote(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_name, new String[]{row_id, row_title, row_note,
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
                note.setCreated(cursor.getString(3));

                index.add(note);
                String desc = note.getDesc();
                Log.d("Inserted!", "desc : "+ desc);

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
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(table_name, null, values);
    }

    //update data
    public void updateData(ContentValues values, long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(table_name, values, row_id + "=" + id, null);
    }

    //delete data
    public void deleteData(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, row_id + " = " + id, null);
    }
}
