package com.edward.masih.assistantforteacher;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class databaseHandler {
    SQLiteDatabase database;
    Activity activity;

    public databaseHandler(Activity activity){
        this.activity=activity;
        database=activity.openOrCreateDatabase("RECORD", activity.MODE_PRIVATE,null);
        createTable();
    }

    protected void createTable()
    {
        //for creating STUDENT INFO.
        try {
            String query="CREATE TABLE IF NOT EXISTS STUDENT(name char(100) not null, sem varchar(100), " +
                    "admno varchar(100) unique, contact number(11), roll integer);";
            database.execSQL(query);
        }catch (Exception e)
        {
            Toast.makeText(activity,"Error in creating Student Table", Toast.LENGTH_LONG).show();
        }
        //for ATTENDANCE
        try {
            String query = "CREATE TABLE IF NOT EXISTS ATTENDANCE(datex date, name varchar(50), sem varchar(10)," +
                    " admno varchar(100), roll integer, subject varchar(50), isPresent integer);";
            database.execSQL(query);
        }catch (Exception e)
        {
            Toast.makeText(activity,"Error in creating Attendance Table", Toast.LENGTH_LONG).show();
        }

        //for NOTES
        try {
            String query = "CREATE TABLE IF NOT EXISTS NOTES(title varchar(50) not null," +
                    "body varchar(65500) not null, sem varchar(10), sub varchar(50) ," +
                    "datex varchar(35));";
            database.execSQL(query);
        }catch (Exception e)
        {
            Toast.makeText(activity,"Error in creating Notes Table", Toast.LENGTH_LONG).show();
        }
        //for USERS
        try {
            String query = "CREATE TABLE IF NOT EXISTS USERS(name char(50) not null," +
                    "username varchar(100) not null, pass varchar(100));";
            database.execSQL(query);
        }catch (Exception e)
        {
            Toast.makeText(activity,"Error in creating Notes Table", Toast.LENGTH_LONG).show();
        }
        /*
        //for SCHEDULE
        try {
            String query = "CREATE TABLE IF NOT EXISTS SCHEDULE(sem varchar(100),subject varchar(1000)," +
                    "timex time, day varchar(100));";
            database.execSQL(query);
        }catch (Exception e)
        {
            Toast.makeText(activity,"Error in creating Schedule Table", Toast.LENGTH_LONG).show();
        }
        */
    }
    public boolean execAction(String qu)
    {
        //Log.i("databaseHandler", qu);
        try {
            database.execSQL(qu);
        }catch (Exception e)
        {
            //Log.e("databaseHandler", qu);
            Toast.makeText(activity,"Error during execution of Action (execAction) "+e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    Cursor execQuery(String qu)
    {
        try {
            return database.rawQuery(qu,null);
        }catch (Exception e)
        {
            //Log.e("databaseHandler", qu);
            Toast.makeText(activity,"Error during execution of Query (execAction) "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return null;
    }

}
