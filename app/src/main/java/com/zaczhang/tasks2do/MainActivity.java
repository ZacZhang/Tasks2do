package com.zaczhang.tasks2do;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.zaczhang.tasks2do.models.Todo;
import com.zaczhang.tasks2do.utils.DateUtils;
import com.zaczhang.tasks2do.utils.ModelUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_TODO_EDIT = 100;

    private static final String TODOS = "todos";

    private TodoListAdapter adapter;
    private List<Todo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TodoEditActivity.class);
                startActivityForResult(intent, REQ_CODE_TODO_EDIT);
            }
        });

        loadData();

        //adapter = new TodoListAdapter(this, todos);
        //((ListView) findViewById(R.id.main_list_view)).setAdapter(adapter);
        setupUI(todos);
    }

    private void setupUI(@NonNull List<Todo> todos) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TodoListAdapter(todos));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_TODO_EDIT && resultCode == Activity.RESULT_OK) {
            String todoID = data.getStringExtra(TodoEditActivity.KEY_TODO_ID);
            if (todoID != null) {
                deleteTodo(todoID);
            } else {
                Todo todo = data.getParcelableExtra(TodoEditActivity.KEY_TODO);
                updateTodo(todo);
            }
        }
    }

    private void updateTodo(Todo todo) {
        boolean found = false;
        for (int i = 0; i <todos.size(); i++) {
            Todo item = todos.get(i);
            if (TextUtils.equals(item.id, todo.id)) {
                found = true;
                todos.set(i, todo);
                break;
            }
        }

        if (!found) {
            todos.add(todo);
        }

        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);

    }

    public void updateTodo(int index, boolean done) {
        todos.get(index).done = done;

        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);
    }

    private void deleteTodo(@NonNull String todoID) {
        for (int i = 0; i < todos.size(); i++) {
            Todo item = todos.get(i);
            if (TextUtils.equals(item.id, todoID)) {
                todos.remove(i);
                break;
            }
        }

        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);
    }

    private void loadData() {
        todos = ModelUtils.read(this, TODOS,new TypeToken<List<Todo>>(){});
        if (todos == null) {
            todos = new ArrayList<>();
        }
    }


    private List<Todo> mockData() {
        List<Todo> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Todo("todo " + i, DateUtils.stringToDate("2016 11 19 0:00")));
        }
        return list;
    }
}
