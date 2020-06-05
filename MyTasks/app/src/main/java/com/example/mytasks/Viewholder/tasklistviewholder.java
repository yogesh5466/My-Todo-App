package com.example.mytasks.Viewholder;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytasks.Adapter.tasklistadapter;
import com.example.mytasks.Manager.database;
import com.example.mytasks.Model.task;
import com.example.mytasks.R;


public class tasklistviewholder extends RecyclerView.ViewHolder {

    private TextView names,notes,time;
    private CheckBox tg;
    private database db;
    private task temp;

    public tasklistviewholder(@NonNull View itemView, final tasklistadapter.CallBack callBack, final Context context, Activity activity) {
        super(itemView);
        names = itemView.findViewById(R.id.names);
        notes = itemView.findViewById(R.id.tasks);
        time = itemView.findViewById(R.id.time);
        tg =itemView.findViewById(R.id.toggle);
        db=new database(context);

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
            public void onClick(View v)
            {
                if(tg.isChecked())
                {   temp.setComp("1");
                    db.updatevalue(temp.getId(),temp);
                }
                else
                {
                    temp.setComp("0");
                    db.updatevalue(temp.getId(),temp);
                }
            }
        });

    }

    public void bindData(task t) {
        temp = t;
        names.setText(t.getName());
        notes.setText(t.getNote());
        time.setText(t.getTime());
        Log.d("hello",t.getComp());
        if(t.getComp().matches("1")){
            tg.setChecked(true);
        }
        else
            tg.setChecked(false);
    }

}
