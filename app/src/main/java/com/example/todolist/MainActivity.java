package com.example.todolist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTasks;
    FloatingActionButton addNewTaskBtn;
    FirebaseDatabase database;
    DatabaseReference  databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUi();
    }

    private void setUpUi(){

        task = new Task();
        lvTasks = findViewById(R.id.lvTasks);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Task");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.item_list,R.id.tvTaskName, list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    task = ds.getValue(Task.class);
                    list.add(task.getName().toString());
                }
                lvTasks.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

    private void displayToast(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
