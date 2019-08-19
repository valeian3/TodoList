package com.example.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public void setUpUi(){

        tvEnterName = findViewById(R.id.tvEnterName);
        etEnterName = findViewById(R.id.etEnterName);
        btnAddTask = findViewById(R.id.btnAddTask);
        task = new Task();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Task");
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setName(etEnterName.getText().toString().trim());
                databaseReference.push().setValue(task);
                Toast.makeText(NewTask.this, "data inserted",Toast.LENGTH_LONG).show();
            }
        });

    }
}
