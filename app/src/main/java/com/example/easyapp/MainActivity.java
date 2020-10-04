package com.example.easyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addTask;
    private ListView taskList;
    private TextView taskCount;
    private DatabaseHandler dbase;
    Context context;
    private List<ToDoModel>tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        dbase = new DatabaseHandler(this);

        addTask = findViewById(R.id.add_task);
        taskList = findViewById(R.id.task_list);
        taskCount =findViewById(R.id.task_count);
        tasks = new ArrayList<>();

        tasks = dbase.getAllTasks();
        TaskAdapter taskAdapter = new TaskAdapter(context,R.layout.task,tasks);
        taskList.setAdapter(taskAdapter);

        int countTask = dbase.ToDoCounter();
        taskCount.setText("You Have "+countTask+" tasks");

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,AddTask.class));
            }
        });

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
              final ToDoModel todo = tasks.get(position);

                AlertDialog.Builder alertBuild = new AlertDialog.Builder(context);
                alertBuild.setTitle(todo.getTask());
                alertBuild.setMessage(todo.getDescription());
                             todo.setFinished(System.currentTimeMillis());
                             dbase.updateOneTask(todo);

                alertBuild.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });

                alertBuild.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,EditTask.class);
                        intent.putExtra("id",String.valueOf(todo.getId()) );
                        startActivity(intent);
                    }
                });
                alertBuild.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            dbase.deleteTask(todo.getId());
                            startActivity(new Intent(context,MainActivity.class));
                    }
                });
                alertBuild.show();
            }
        });

    }
}