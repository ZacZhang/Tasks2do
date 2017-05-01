package com.zaczhang.tasks2do;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zaczhang.tasks2do.models.Todo;

import java.util.List;


public class TodoListAdapter extends BaseAdapter {

    private List<Todo> todos;
    private Context context;

    public TodoListAdapter(@NonNull Context context, @NonNull List<Todo> todos) {
        this.context = context;
        this.todos = todos;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("zac", String.valueOf(position));

        View view = LayoutInflater.from(context).inflate(R.layout.main_list_item, parent, false);
        Todo todo = todos.get(position);

        ((TextView) view.findViewById(R.id.main_list_item_text)).setText(todo.text);
        return view;

    }
}
