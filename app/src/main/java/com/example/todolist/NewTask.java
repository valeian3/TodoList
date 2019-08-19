package com.example.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewTask extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText inputTaskName;
    Button inputBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        myDb = new DatabaseHelper(this);
        setUpUi();
    }

    private void setUpUi(){
        inputTaskName = findViewById(R.id.editTextTaskName);
        inputBtn = findViewById(R.id.btnAddTask);
        inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(inputTaskName.getText().toString());
                if (isInserted = true){
                    Toast.makeText(NewTask.this, "Data inserted",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(NewTask.this, "Data not inserted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
