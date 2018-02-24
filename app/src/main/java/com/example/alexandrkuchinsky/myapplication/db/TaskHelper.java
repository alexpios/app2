package com.example.alexandrkuchinsky.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alexandr Kuchinsky on 01.02.2018.
 */

public class TaskHelper extends SQLiteOpenHelper {

public TaskHelper(Context context){
    super(context, task.DB_name, null, task.Db_versiom);

}
    @Override
    public void onCreate(SQLiteDatabase db) {
String createTable = "CREATE TABLE " + task.TaskEntry.TABLE + " ( " +
                                        task.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        task.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);";
db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + task.TaskEntry.TABLE);
onCreate(db);
    }


}
