package com.example.todolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DescriptionText extends Fragment {

    private TextView tvNameTask;
    private TextView tvDescription;

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

    }
}
