package com.example.checklist_app.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checklist_app.MainActivity;
import com.example.checklist_app.Model.ToDoModel;
import com.example.checklist_app.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<ToDoModel> todoList;
    private MainActivity activity;
    private FirebaseFirestore firestore;

    public ToDoAdapter(MainActivity mainActivity, List<ToDoModel> todoList) {
        this.todoList = todoList;
        activity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(activity).inflate(R.layout.sep_task, parent, false);
       firestore = FirebaseFirestore.getInstance();

       return new MyViewHolder(view);
    }

    public void deleteTask(int position) {

        ToDoModel toDoModel = todoList.get(position);
        firestore.collection("task").document(toDoModel.TaskID).delete();
        todoList.remove(position);
        notifyItemRemoved(position);

    }

    public Context getContext() {
        return activity;
    }

    public void editTask(int position) {
        ToDoModel toDoModel = todoList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("task", toDoModel.getTask());
        bundle.putString("due", toDoModel.getDue());
        bundle.putString("id", toDoModel.TaskID);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ToDoModel toDoModel = todoList.get(position);
        holder.mCheckBox.setText(toDoModel.getTask());
        holder.mDueDateTk.setText("Due On " + toDoModel.getDue());

        holder.mCheckBox.setChecked(toBoolean(toDoModel.getStatus()));

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    firestore.collection("task").document(toDoModel.TaskID).update("status" , 1);
                }
                else {
                    firestore.collection("task").document(toDoModel.TaskID).update("status", 0);
                }
            }
        });

    }

    private boolean toBoolean(int status) {

        return status != 0;

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mDueDateTk;
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mDueDateTk = itemView.findViewById(R.id.due_date_tk);
            mCheckBox = itemView.findViewById(R.id.checkbox);
        }
    }
}


