package com.zaczhang.tasks2do;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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


public class TodoListAdapter extends RecyclerView.Adapter {

    private List<Todo> data;

    public TodoListAdapter(@NonNull List<Todo> data) {
        this.data = data;
    }

    // 产生View并得到ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);

        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Todo todo = data.get(position);
        ((TodoListViewHolder) holder).todoText.setText(todo.text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
