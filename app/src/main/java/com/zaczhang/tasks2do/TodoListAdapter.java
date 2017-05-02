package com.zaczhang.tasks2do;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CheckBox;

import com.zaczhang.tasks2do.models.Todo;
import com.zaczhang.tasks2do.utils.UIUtils;

import java.util.List;


public class TodoListAdapter extends BaseAdapter {

    private List<Todo> todos;
    private MainActivity activity;
    private Context context;

    public TodoListAdapter(@NonNull MainActivity activity, @NonNull List<Todo> todos) {
        this.activity = activity;
        this.todos = todos;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.main_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTodoText = (TextView) convertView.findViewById(R.id.main_list_item_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Todo todo = todos.get(position);
        viewHolder.tvTodoText.setText(todo.text);
        viewHolder.doneCheckbox.setChecked(todo.done);
        UIUtils.setTextViewStrikeThrough(viewHolder.tvTodoText, todo.done);

        viewHolder.doneCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                activity.updateTodo(position, isChecked);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Todo.class);
                intent.putExtra(TodoEditActivity.KEY_TODO, todo);
                activity.startActivityForResult(intent, MainActivity.REQ_CODE_TODO_EDIT);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView tvTodoText;
        CheckBox doneCheckbox;
    }
}
