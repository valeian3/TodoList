package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class SubTaskList extends Fragment {

    private ListView lvSubTasks;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<String> subList;
    ArrayAdapter<String> SubAdapter;
    ArrayList<SubTask> subTasks;
    SubTask SubTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Layout = inflater.inflate(R.layout.fragment_sub_task_list, container, false);
        setUpUi(Layout);

        return Layout;
    }

    private void setUpUi(View layout){

        lvSubTasks = layout.findViewById(R.id.lvSubTasks);

        SubTask = new SubTask();
        subList = new ArrayList<>();
        subTasks = new ArrayList<>();

        Intent intent = getActivity().getIntent();

        String taskId = intent.getStringExtra(MainActivity.TASK_ID);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("SubTask").child(taskId);
        SubAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, subList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                subList.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {

                    SubTask = ds.getValue(SubTask.class);
                    subList.add(SubTask.getName());
                    subTasks.add(SubTask);
                }
                lvSubTasks.setAdapter(SubAdapter);
                SubAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvSubTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SubTask = subTasks.get(position);

                showDeleteDialog(SubTask.getSubTaskId(), SubTask.getName());
            }
        });

    }

    private void showDeleteDialog(final String id, final String subTaskName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.subtaskdialog,null);
        dialogBuilder.setView(dialogView);

        final Button btnDeleteSubTask;

        btnDeleteSubTask = dialogView.findViewById(R.id.btnDeleteSubTask);

        dialogBuilder.setTitle("Deleting "+ subTaskName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnDeleteSubTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getActivity().getIntent();

                String taskId = intent.getStringExtra(MainActivity.TASK_ID);

                databaseReference = FirebaseDatabase.getInstance().getReference("SubTask").child(taskId);

                databaseReference.removeValue();

                alertDialog.dismiss();
            }
        });
    }

    private void displayToast(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }
}
