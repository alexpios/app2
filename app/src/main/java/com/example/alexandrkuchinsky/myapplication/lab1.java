package com.example.alexandrkuchinsky.myapplication;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexandrkuchinsky.myapplication.Adapter.ListItemAdapter;
import com.example.alexandrkuchinsky.myapplication.Model.ToDo;
import com.example.alexandrkuchinsky.myapplication.db.task;
import com.example.alexandrkuchinsky.myapplication.db.TaskHelper;
import java.util.ArrayList;
import java.util.List;

import com.example.alexandrkuchinsky.myapplication.db.task.TaskEntry;
import com.example.alexandrkuchinsky.myapplication.db.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;

/**
 * Created by Alexandr Kuchinsky on 31.01.2018.
 */

public class lab1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "lab1";
    private TaskHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
private Button btnUpd;
List<ToDo> toDoList = new ArrayList<>();
FirebaseFirestore db;
RecyclerView listItem;
RecyclerView.LayoutManager layoutManager;
FloatingActionButton fab;
ListItemAdapter adapter;
SpotsDialog dialog;

public MaterialEditText title, description;
public boolean isUpdate = false;  // flag na proverku update
public String idUpdate = "";  //id togo, chto obnovlyau

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.lab1);

        //inicial FireStore

        db = FirebaseFirestore.getInstance();
//viuha
        dialog  = new SpotsDialog(this);
        title = (MaterialEditText)findViewById(R.id.title);
        description = (MaterialEditText)findViewById(R.id.description);
fab = (FloatingActionButton) findViewById(R.id.fab);
fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});

listItem = (RecyclerView)findViewById(R.id.listtodo);
listItem.setHasFixedSize(true);
layoutManager = new LinearLayoutManager(this);
listItem.setLayoutManager(layoutManager);
loadData();














        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        mHelper = new TaskHelper(this);
//        mTaskListView = (ListView) findViewById(R.id.list_todo);
//        updateUI();
    }

    private void loadData() {
dialog.show();
        db.collection("ToDoList")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot doc:task.getResult())
                        {
                            ToDo todo = new ToDo(doc.getString("id"),
                                    doc.getString("title"),
                                    doc.getString("description"));
                            toDoList.add(todo);

                        }
                        adapter = new ListItemAdapter(lab1.this, toDoList);
                    }
                })
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//    switch (item.getItemId()){
//        case R.id.action_add_task:
//            final EditText taskEditText = new EditText(this);
//            AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Добавление заметки").setMessage("Введите текст заметки ")
//                    .setView(taskEditText).setPositiveButton(" Добавить   ", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//String task = String.valueOf(taskEditText.getText());
//                            SQLiteDatabase db = mHelper.getWritableDatabase();
//                            ContentValues values = new ContentValues();
//                            values.put(com.example.alexandrkuchinsky.myapplication.db.task.TaskEntry.COL_TASK_TITLE, task);
//                            db.insertWithOnConflict(com.example.alexandrkuchinsky.myapplication.db.task.TaskEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
//                            db.close();
//                            updateUI();
//                        }
//                    })
//
//                    .setNegativeButton("   Отмена   ", null).create();
//            dialog.show();
//            return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//
//    }
//
//    private void updateUI(){
//ArrayList<String> tasklist = new ArrayList<>();
//SQLiteDatabase db = mHelper.getReadableDatabase();
//Cursor cursor = db.query(TaskEntry.TABLE, new String[]{TaskEntry._ID, TaskEntry.COL_TASK_TITLE}, null, null, null, null, null);
//
//        while (cursor.moveToNext()) {
//            int index = cursor.getColumnIndex(TaskEntry.COL_TASK_TITLE);
//            tasklist.add(cursor.getString(index));
//
//        }
//if (mAdapter == null) {
//    mAdapter = new ArrayAdapter<>(this, R.layout.item_todo, R.id.task_title, tasklist);
//    mTaskListView.setAdapter(mAdapter);
//
//} else{
//            mAdapter.clear();
//            mAdapter.addAll(tasklist);
//            mAdapter.notifyDataSetChanged();
//}
//
//cursor.close();
//        db.close();
//    }
//public void deleteTask(View view){
//View parent = (View) view.getParent();
//    TextView taskTextView = (TextView)parent.findViewById(R.id.task_title);
//    String task = String.valueOf(taskTextView.getText());
//    SQLiteDatabase db = mHelper.getWritableDatabase();
//    db.delete(com.example.alexandrkuchinsky.myapplication.db.task.TaskEntry.TABLE, com.example.alexandrkuchinsky.myapplication.db.task.TaskEntry.COL_TASK_TITLE + " = ?", new String[]{task});
//    db.close();
//    updateUI();
//
//}
//
//    public void editTask(View view){
//
//                final EditText taskEdit = new EditText(this);
//                AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Редактирование заметки").setMessage("Введите новый текст заметки ")
//                        .setView(taskEdit).setPositiveButton(" Изменить   ", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String task = String.valueOf(taskEdit.getText());
//                                SQLiteDatabase db = mHelper.getWritableDatabase();
//                                ContentValues newvalues = new ContentValues();
//                                newvalues.put(com.example.alexandrkuchinsky.myapplication.db.task.TaskEntry.COL_TASK_TITLE,task);
//                                String where = TaskEntry._ID + " = ?";
//
//                                db.update(TaskEntry.TABLE, newvalues, where, null);
//
//                              //  ContentValues values = new ContentValues();
//
//                            //      values.put(com.example.alexandrkuchinsky.myapplication.db.task.TaskEntry.COL_TASK_TITLE, task);
//                             //   db.insertWithOnConflict(com.example.alexandrkuchinsky.myapplication.db.task.TaskEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
//                                db.close();
//                                updateUI();
//                            }
//                        })
//
//                        .setNegativeButton("   Отмена   ", null).create();
//                dialog.show();
//
//        }
//


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent serchIntent = new Intent(lab1.this, lab1.class);
            startActivity(serchIntent);
        } else if (id == R.id.nav_gallery) {
            Intent serchIntent = new Intent(lab1.this, lab2.class);
            startActivity(serchIntent);
        } else if (id == R.id.nav_slideshow) {
            Intent serchIntent = new Intent(lab1.this, lab3.class);
            startActivity(serchIntent);
        } else if (id == R.id.nav_manage) {
            Intent serchIntent = new Intent(lab1.this, lab4.class);
            startActivity(serchIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
