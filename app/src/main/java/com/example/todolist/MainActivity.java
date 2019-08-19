package com.example.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addNewTaskBtn;
    DatabaseHelper myDb;
    ListView taskList;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUi();
    }

    private void setUpUi(){

//        ArrayList<Task> tasks = this.loadTasks();
//        TaskAdapter taskAdapter = new TaskAdapter(tasks);
//        taskList.setAdapter(taskAdapter);

        viewData();

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String text = taskList.getItemAtPosition(position).toString();
                displayToast(text);
            }
        });


        addNewTaskBtn = findViewById(R.id.fab);
        addNewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), NewTask.class);
                startActivity(explicitIntent);
                displayToast("NewTask Activity opened.");
            }
        });
    }

//    private ArrayList<Task> loadTasks(){
//        return DatabaseHelper.getInstance(this).getAllData();
//    }

    public void viewData(){

        taskList = findViewById(R.id.taskList);
        Cursor cursor = myDb.viewData();

        if(cursor.getCount() == 0){
            displayToast("No data to show");
        } else {
            while(cursor.moveToNext()) {
                listItem.add(cursor.getString(1));

                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
                taskList.setAdapter(adapter);
            }
        }
    }

    private void displayToast(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
