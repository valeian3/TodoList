package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DescriptionText extends Fragment {

    private TextView tvNameTask;
    private TextView tvDescription;
    private EditText etSubTaskName;
    private Button btnAddSubTask;
    private Button btnDone;
    private SubTask SubTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Layout = inflater.inflate(R.layout.fragment_description_text,container,false);
        setUpUi(Layout);

        return Layout;
    }

    private void setUpUi(View layout){

        tvNameTask = layout.findViewById(R.id.tvNameTask);
        tvDescription = layout.findViewById(R.id.tvDescription);
        etSubTaskName = layout.findViewById(R.id.etSubTaskName);
        btnAddSubTask = layout.findViewById(R.id.btnAddSubTask);
        btnDone = layout.findViewById(R.id.btnDone);

        SubTask = new SubTask();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("SubTask");
        btnAddSubTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = etSubTaskName.getText().toString().trim();
                if(!TextUtils.isEmpty(taskName)){
                    SubTask.setName(taskName);
                    databaseReference.push().setValue(SubTask);
                    displayToast("Task inserted into database");
                    etSubTaskName.getText().clear();
                } else {
                    displayToast("Task name cannot stay empty.");
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getContext(), MainActivity.class);
                startActivity(explicitIntent);
                displayToast("Task finished.");
            }
        });

    }
    private void displayToast(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }
}
