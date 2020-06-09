package com.example.mytasks.Viewholder;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytasks.Adapter.tasklistadapter;
import com.example.mytasks.Model.task;
import com.example.mytasks.R;


public class tasklistviewholder extends RecyclerView.ViewHolder {

    private TextView names, notes, time;
    private CheckBox tg;
    private task temp;

    public tasklistviewholder(@NonNull View itemView, final tasklistadapter.CallBack callBack, final Context context, Activity activity) {
        super(itemView);
        names = itemView.findViewById(R.id.names);
        notes = itemView.findViewById(R.id.tasks);
        time = itemView.findViewById(R.id.time);
        tg = itemView.findViewById(R.id.toggle);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = getAdapterPosition();
                if (callBack != null && position != RecyclerView.NO_POSITION) {
                    callBack.onItemClick(position, v);
                }
                return true;
            }
        });


        tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onCheckClick(tg, temp, v);
                }
            }
        });

    }

    public void bindData(task t) {
        temp = t;
        names.setText(t.getName());
        notes.setText(t.getNote());
        time.setText(t.getTime());
        if (t.getComp().matches("1")) {
            tg.setChecked(true);
        } else
            tg.setChecked(false);
    }

}
