package com.example.alexandrkuchinsky.myapplication.db;

import android.provider.BaseColumns;

/**
 * Created by Alexandr Kuchinsky on 01.02.2018.
 */

public class task {

    public static final String DB_name = "cas.example.com.todolist.db";
    public static final int Db_versiom = 1;
    public class TaskEntry implements BaseColumns{
public static final String TABLE = "tasks";
public static final String COL_TASK_TITLE = "title";

    }
}
