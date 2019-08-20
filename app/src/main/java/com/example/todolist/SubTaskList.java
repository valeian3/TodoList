package com.example.todolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class SubTaskList extends Fragment {

    private ListView lvSubTasks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Layout = inflater.inflate(R.layout.fragment_sub_task_list, container, false);
        setUpUi(Layout);

        return Layout;
    }

    private void setUpUi(View layout){

        lvSubTasks = layout.findViewById(R.id.lvSubTasks);

    }
}
