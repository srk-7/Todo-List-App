package com.example.todolist;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private EditText todoEditText;
    private Button addButton;
    private ListView todoListView;
    private ArrayAdapter<String> todoAdapter;
    private ArrayList<String> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoEditText = findViewById(R.id.todoEditText);
        addButton = findViewById(R.id.addButton);
        todoListView = findViewById(R.id.todoListView);

        todoList = new ArrayList<>();
        todoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList);
        todoListView.setAdapter(todoAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = todoEditText.getText().toString();
                if (!task.isEmpty()) {
                    todoList.add(task);
                    todoAdapter.notifyDataSetChanged();
                    todoEditText.setText("");
                }
            }
        });

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView taskTextView = (TextView) view;
                if ((taskTextView.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) == Paint.STRIKE_THRU_TEXT_FLAG) {
                    taskTextView.setPaintFlags(taskTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                } else {
                    taskTextView.setPaintFlags(taskTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

        todoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoList.remove(position);
                todoAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}