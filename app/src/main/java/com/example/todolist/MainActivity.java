package com.example.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TASK_ID = "taskId";
    public static final String TASK_NAME = "taskName";

    public static Bundle bundle;

    ListView lvTasks;
    FloatingActionButton addNewTaskBtn;
    FirebaseDatabase database;
    DatabaseReference  databaseReference;
    ArrayList<String> list;
    ArrayList<Task> tasks;
    ArrayAdapter<String> adapter;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.iAbout:
                showAboutDialog();
                return true;
             default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpUi(){

        task = new Task();

        list = new ArrayList<>();
        tasks = new ArrayList<>();

        lvTasks = findViewById(R.id.lvTasks);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Task");
        adapter = new ArrayAdapter<>(this,R.layout.item_list,R.id.tvTaskName, list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    task = ds.getValue(Task.class);
                    list.add(task.getName());
                    tasks.add(task);
                }
                lvTasks.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvTasks.setLongClickable(true);

        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                task = tasks.get(position);

                Intent intent = new Intent(view.getContext(),ActivityTaskDetails.class);

                intent.putExtra(TASK_ID, task.getTaskId());
                intent.putExtra(TASK_NAME, task.getName());

                startActivity(intent);
            }
        });

        lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                task = tasks.get(position);

                showUpdateDialog(task.getTaskId(), task.getName());

                return true;
            }
        });


        addNewTaskBtn = findViewById(R.id.fab);
        addNewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), NewTask.class);
                startActivity(explicitIntent);
                displayToast("Activity for adding tasks opened.");
            }
        });
    }

    private void showAboutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setTitle("About application")
                .setMessage("Application is meant to be used for simple to do lists.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    private void showUpdateDialog(final String id, final String taskName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.taskdialog,null);
        dialogBuilder.setView(dialogView);

        final EditText etNewName;
        final Button btnAddNewName;
        final Button btnDeleteTask;

        etNewName = dialogView.findViewById(R.id.etNewName);
        btnAddNewName = dialogView.findViewById(R.id.btnAddNewName);
        btnDeleteTask = dialogView.findViewById(R.id.btnDeleteTask);

        dialogBuilder.setTitle("Renaming "+ taskName + " name:");

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("Task").child(task.getTaskId());

        btnAddNewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newTaskName = etNewName.getText().toString().trim();

                if(!TextUtils.isEmpty(newTaskName)) {


                    task = new Task(task.getTaskId() ,newTaskName);
                    databaseReference.setValue(task);

                    displayToast("Renamed task.");
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                    alertDialog.dismiss();
                }  else {
                    displayToast("Task name cannot stay empty.");
                }
            }
        });

        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("Task").child(id);
                databaseReference.removeValue();
                databaseReference = FirebaseDatabase.getInstance().getReference("SubTask").child(task.getTaskId());
                databaseReference.removeValue();

                displayToast("Task deleted.");
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                alertDialog.dismiss();
            }
        });
    }

    private void displayToast(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
