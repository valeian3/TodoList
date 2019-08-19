package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

//    // Singleton
//    private static DatabaseHelper mDatabaseDBHelper = null;


    public static final String DATABASE_NAME = "Task_db";
    public static final String TABLE_NAME = "task_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

//    public static synchronized DatabaseHelper getInstance(Context context){
//        if(mDatabaseDBHelper == null){
//            mDatabaseDBHelper = new DatabaseHelper(context);
//        }
//        return mDatabaseDBHelper;
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }
//    public ArrayList<Task> getAllData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
//        ArrayList<Task> tasks = new ArrayList<>();
//        if(res.moveToFirst()){
//            do{
//                String name = res.getString(1);
//                tasks.add(new Task(name));
//            }while (res.moveToNext());
//        }
//        res.close();
//        db.close();
//        return tasks;
//    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        return  data;
    }
}
