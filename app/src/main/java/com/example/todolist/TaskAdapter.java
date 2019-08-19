package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {

    private ArrayList<Task> mTasks;

    public TaskAdapter(ArrayList<Task> tasks){
        mTasks = tasks;
    }

    @Override
    public int getCount() {
        return this.mTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder taskViewHolder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_task, parent, false);
            taskViewHolder = new ViewHolder(convertView);
            convertView.setTag(taskViewHolder);
        }
        else{
            taskViewHolder = (ViewHolder) convertView.getTag();
        }

        Task task = this.mTasks.get(position);
        taskViewHolder.tvTaskName.setText(task.getName());
        return convertView;
    }

    public static class ViewHolder {
        public TextView tvTaskName;

        public ViewHolder(View taskView){
            tvTaskName = taskView.findViewById(R.id.tvTaskName);
        }
    }
}
