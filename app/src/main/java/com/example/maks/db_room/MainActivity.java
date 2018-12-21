package com.example.maks.db_room;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] tasks;
    Database db;

    ListView taskList;

    Button newTask, findTask;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "my_db").allowMainThreadQueries().build();
        taskList = findViewById(R.id.taskList);

        newTask = findViewById(R.id.newTask);
        findTask = findViewById(R.id.findTask);
        name = findViewById(R.id.name);

        findTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Task> findTasks = db.taskDao().getTaskByName(name.getText().toString());
                if(findTasks.size() == 0){
                    tasks = new String[1]; tasks[0] = "No found";
                } else {
                    tasks = new String[findTasks.size()];
                    for(int i = 0; i < findTasks.size(); i++){
                        tasks[i] = findTasks.get(i).getName();
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, tasks);
                taskList.setAdapter(adapter);
            }
        });

        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.taskDao().createTask(new Task(name.getText().toString()));
                if(db.taskDao().getTasksCount() == 0){
                    tasks = new String[1]; tasks[0] = "No data now";
                } else {
                    List<Task> alltasks = db.taskDao().getTasks();
                    tasks = new String[alltasks.size()];
                    for(int i = 0; i < alltasks.size(); i++){
                        tasks[i] = alltasks.get(i).getName();
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, tasks);
                taskList.setAdapter(adapter);
            }
        });

    }
}
