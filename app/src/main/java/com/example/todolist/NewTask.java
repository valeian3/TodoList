package com.example.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewTask extends AppCompatActivity {

    TextView tvEnterName;
    EditText etEnterName;
    Button btnAddTask;

    Task task;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

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
            case R.id.iArrow:
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(explicitIntent);
                return true;
            case R.id.iAbout:
                showAboutDialog();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAboutDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setTitle("About application")
                .setMessage("This application is made by student Valerian Bahnik. \n" +
                        "Application is meant to be used for simple to do lists.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    public void setUpUi(){

        tvEnterName = findViewById(R.id.tvEnterName);
        etEnterName = findViewById(R.id.etEnterName);
        btnAddTask = findViewById(R.id.btnAddTask);
        task = new Task();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Task");
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = etEnterName.getText().toString().trim();
                if(!TextUtils.isEmpty(taskName)){
                    task.setName(taskName);
                    databaseReference.push().setValue(task);
                    displayToast("Task inserted into database");

                    Intent explicitIntent = new Intent();
                    explicitIntent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(explicitIntent);
                } else {
                    displayToast("Task name cannot stay empty.");
                }
            }
        });

    }

    private void displayToast(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
