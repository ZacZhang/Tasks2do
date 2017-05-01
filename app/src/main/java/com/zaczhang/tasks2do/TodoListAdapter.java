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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.main_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTodoText = (TextView) convertView.findViewById(R.id.main_list_item_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Todo todo = todos.get(position);
        viewHolder.tvTodoText.setText(todo.text);
        return convertView;
    }

    private static class ViewHolder {
        TextView tvTodoText;
    }
}
