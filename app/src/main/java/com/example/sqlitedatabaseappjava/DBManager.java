package com.example.sqlitedatabaseappjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
    }

    //Method to open database connection
    public DBManager open() throws SQLException{

        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;
    }

    //Method to close database connection
    public void close(){
        dbHelper.close();
    }

    //Method to insert data into the database table
    public void insert(String name, String desc){

        //The ContentValues class holds the data to be inserted
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        database.insert(DatabaseHelper.TABLE_NAME,
                null,
                contentValues);
    }

    //Method to update records in the database
    public int update(long _id, String name, String desc){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);

        int i = database.update(
                DatabaseHelper.TABLE_NAME,
                contentValues,
                DatabaseHelper._ID + " = " + _id,
                null);

        return i;
    }

    //Method to delete a record from the database
    public void delete(long _id){

        database.delete(
                DatabaseHelper.TABLE_NAME,
                DatabaseHelper._ID + " = " + _id,
                null);
    }

    //Method to fetch data from the database table
    public Cursor fetch(){

        //create a string array of column titles
        String[] columns = new String[] {
                DatabaseHelper._ID,
                DatabaseHelper.SUBJECT,
                DatabaseHelper.DESC
        };

        //Retrieve the data into the cursor object
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }
}
