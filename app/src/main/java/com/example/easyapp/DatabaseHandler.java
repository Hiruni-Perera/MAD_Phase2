package com.example.easyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DB_NAME = "weeklyplan";
    private static final String TABLE_NAME = "weeklyplan";

    //column names
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " " +
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TASK + " TEXT,"
                + DESCRIPTION + " TEXT,"
                + STARTED + " TEXT,"
                + FINISHED + " TEXT" +
                ");";
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDB, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //Drop older table if existed
        sqLiteDB.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(sqLiteDB);
    }

    public void addToDo(ToDoModel todo) {
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TASK, todo.getTask());
        contentValues.put(DESCRIPTION, todo.getDescription());
        contentValues.put(STARTED, todo.getStarted());
        contentValues.put(FINISHED, todo.getFinished());
        //save to table
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        //close database
        sqLiteDatabase.close();
    }

    public int ToDoCounter() {
        SQLiteDatabase database = getReadableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);
        return cursor.getCount();
    }

    //get all tasks into a list
    public List<ToDoModel> getAllTasks() {
        List<ToDoModel> tasks = new ArrayList();
        SQLiteDatabase database = getReadableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                //create new todo object
                ToDoModel todo = new ToDoModel();
                todo.setId(cursor.getInt(0));
                todo.setTask(cursor.getString(1));
                todo.setDescription(cursor.getString(2));
                todo.setStarted(cursor.getLong(3));
                todo.setFinished(cursor.getLong(4));

                tasks.add(todo);
            } while (cursor.moveToNext());
        }
        return tasks;
    }

    public void deleteTask(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME, ID + " =?", new String[]{String.valueOf(id)});
        database.close();
    }


    //get single task
    public ToDoModel getOneTask(int id) {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TABLE_NAME, new String[]{ID, TASK, DESCRIPTION, STARTED, FINISHED}, ID + "=?",new String[]{String.valueOf(id)},
                null, null, null, null);

        ToDoModel todo;
        if(cursor != null){
            cursor.moveToFirst();


            todo = new ToDoModel( cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getLong(3),cursor.getLong(4));


            return todo;
        }
            return null;
    }
    //update one task
    public int updateOneTask(ToDoModel todo){
        SQLiteDatabase database =  getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK, todo.getTask());
        contentValues.put(DESCRIPTION, todo.getDescription());
        contentValues.put(STARTED, todo.getStarted());
        contentValues.put(FINISHED, todo.getFinished());

        int stat = database.update(TABLE_NAME,contentValues,ID+" =?",new String[]{String.valueOf(todo.getId())});

        database.close();
        return stat;

    }
}





