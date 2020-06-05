package com.example.mytasks.Manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mytasks.Model.task;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    public static final String DB_NAME = "db";
    public static final String TABLE_NAME = "notes";
    public static final String COlUMN_NAME_id = "id";
    public static final String COlUMN_NAME_name = "name";
    public static final String COlUMN_NAME_note = "note";
    public static final String COlUMN_NAME_comp = "comp";
    public static final String COlUMN_NAME_time = "time";
    SQLiteDatabase db;


    //database version
    private static final int DB_VERSION = 1;

    //Constructor
    public database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    //creating the database
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE IF NOT EXISTS notes(id VARCHAR,name VARCHAR,note VARCHAR,comp VARCHAR,time VARCHAR,PRIMARY KEY(id));");
        Log.d("myApp", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addvalue(task t) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("INSERT INTO notes VALUES('" + t.getId() +"','"+t.getName() +"','"+t.getNote() +"','"+t.getComp()+"','"+t.getTime() + "');");
        Log.d("myApp", "Inserted");
        db.close();
        return true;
    }
    public boolean updatevalue(String id,task t) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE notes set "+COlUMN_NAME_name+"='"+t.getName()+"',"+COlUMN_NAME_note+"='"+t.getNote()+"',"+COlUMN_NAME_comp+"='"+t.getComp()+"' where id='"+id+"';");
        Log.d("myApp", "Inserted");
        db.close();
        return true;
    }
    public boolean delete(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+COlUMN_NAME_id+"='"+id+"';");
        Log.d("myApp", "deleted");
        db.close();
        return true;
    }
    public boolean check(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT EXISTS (SELECT * FROM notes WHERE id='"+id+"' LIMIT 1)";
        Cursor cursor = db.rawQuery(Query, null);
        cursor.moveToFirst();

        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            Log.d("Hello",id);
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM notes ", null);
        if (c.moveToFirst()) {
            do {
                // Passing values
                String column1 = c.getString(0);
                Log.d("myApp", column1+"saasdasd");
                // Do something Here with values
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return true;
    }

    public ArrayList<task> list() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM notes ", null);
        ArrayList<task> list = new ArrayList<>();
        if(c.getCount()!=0){
            while(c.moveToNext()){
                task temp = new task();
                temp.setId(c.getString(c.getColumnIndexOrThrow(COlUMN_NAME_id)));
                temp.setNote(c.getString(c.getColumnIndexOrThrow(COlUMN_NAME_note)));
                temp.setTime(c.getString(c.getColumnIndexOrThrow(COlUMN_NAME_time)));
                temp.setComp(c.getString(c.getColumnIndexOrThrow(COlUMN_NAME_comp)));
                temp.setName(c.getString(c.getColumnIndexOrThrow(COlUMN_NAME_name)));
                list.add(temp);
            }
        }
        return list;
    }







}