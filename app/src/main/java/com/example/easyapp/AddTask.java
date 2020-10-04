package com.example.easyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTask extends AppCompatActivity {
    private EditText task,desc;
    private Button addTask;
    private DatabaseHandler dbHandler;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        task = findViewById(R.id.create_task);
        desc = findViewById(R.id.create_description);
        addTask = findViewById(R.id.add_task);
        context = this;
        dbHandler = new DatabaseHandler(context);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTask = task.getText().toString();
                String userDesc = desc.getText().toString();
                long started = System.currentTimeMillis();

                ToDoModel todo = new ToDoModel(userTask,userDesc,started,0);
                dbHandler.addToDo(todo);

                startActivity(new Intent(context,MainActivity.class));
            }
        });

    }
}