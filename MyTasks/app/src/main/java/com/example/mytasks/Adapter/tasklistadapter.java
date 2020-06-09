package com.example.mytasks.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mytasks.Model.task;
import com.example.mytasks.R;
import com.example.mytasks.Viewholder.tasklistviewholder;

import java.util.ArrayList;
import java.util.List;


public class tasklistadapter extends RecyclerView.Adapter<tasklistviewholder> {

    private ArrayList<task> taskArrayList = new ArrayList<>();
    private Context context;
    private Activity activity;
    private CallBack callBack;

    public tasklistadapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public tasklistviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.task_layout, viewGroup, false);
        return new tasklistviewholder(view, callBack, context, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull tasklistviewholder holder, int position) {
        holder.bindData(taskArrayList.get(position));
    }


    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public void setnotesArrayList(List<task> list) {
        taskArrayList.addAll(list);
        notifyDataSetChanged();
    }

    public task getItemByPosition(int position) {
        return taskArrayList.get(position);
    }

    public void removeAnItem(int position) {
        taskArrayList.remove(position);
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onItemClick(int position, View view);

        void onCheckClick(CheckBox c, task temp, View view);
    }
}
