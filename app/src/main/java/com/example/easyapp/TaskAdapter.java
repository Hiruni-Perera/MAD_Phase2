package com.example.easyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class TaskAdapter extends ArrayAdapter<ToDoModel> {
    private Context context;
    private int resource;
    List<ToDoModel> tasks;

    TaskAdapter(Context context, int resource, List<ToDoModel> tasks){
        super(context,resource,tasks);
        this.context = context;
        this.resource = resource;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent, false);

        TextView task = row.findViewById(R.id.txt_task);
        TextView description = row.findViewById(R.id.description);
        ImageView image = row.findViewById(R.id.img_checkbx);

        ToDoModel todo = tasks.get(position);
        task.setText(todo.getTask());
        description.setText(todo.getDescription());
        image.setVisibility(row.INVISIBLE);

        if(todo.getFinished() > 0){
            image.setVisibility(convertView.VISIBLE);
        }

        return row;
    }
}
