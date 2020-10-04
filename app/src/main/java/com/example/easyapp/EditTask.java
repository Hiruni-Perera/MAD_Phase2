package com.example.easyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTask extends AppCompatActivity {

    private EditText task,desc;
    private Button updateTask;
    private DatabaseHandler database;
    private Context context;
    private Long editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        context = this;
        database = new DatabaseHandler(context);

        task = findViewById(R.id.edit_task);
        desc = findViewById(R.id.edit_description);
        updateTask = findViewById(R.id.update_task);


        final String id = getIntent().getStringExtra("id");
        ToDoModel todo = database.getOneTask(Integer.parseInt(id));
        task.setText(todo.getTask());
        desc.setText(todo.getDescription());

        updateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myTask = task.getText().toString();
                String myDesc = desc.getText().toString();
                editDate = System.currentTimeMillis();

                ToDoModel todo = new ToDoModel(Integer.parseInt(id),myTask,myDesc,editDate,0);
                int stat =database.updateOneTask(todo);
                startActivity(new Intent(context,MainActivity.class));
            }
        });


    }
}