package com.example.mytasks.Activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytasks.Adapter.tasklistadapter;
import com.example.mytasks.Manager.database;
import com.example.mytasks.Model.task;
import com.example.mytasks.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import in.codeshuffle.typewriterview.TypeWriterListener;
import in.codeshuffle.typewriterview.TypeWriterView;

public class MainActivity extends BaseActivity {
    database db;
    ArrayList<task> t;
    tasklistadapter tasklistadapter;
    RecyclerView taskrecylerview;
    private RecyclerView.LayoutManager layoutManager;
    Button add;
    LinearLayout l;
    FrameLayout f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.addnotes);
        l = findViewById(R.id.layout1);
        f = findViewById(R.id.layout2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Addnote.class);
                startActivity(i);
            }
        });
        taskrecylerview = findViewById(R.id.tasklistRecyclerView);
        db = new database(getApplicationContext());
        tasklistadapter = new tasklistadapter(this,this);
        tasklistadapter.setCallBack(new tasklistadapter.CallBack() {
            @Override
            public void onItemClick(int position, final View view) {
                final task temp = tasklistadapter.getItemByPosition(position);
                LayoutInflater inflater = getLayoutInflater();
                final View dialoglayout = inflater.inflate(R.layout.dialog_edit_delete, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialoglayout);
                Button edit,delete,cancel;
                edit = dialoglayout.findViewById(R.id.edit);
                delete = dialoglayout.findViewById(R.id.delete);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,Editnote.class);
                        i.putExtra("task",temp);
                        startActivity(i);
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.delete(temp.getId());
                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                });
                builder.show();
            }
        });
        taskrecylerview.setAdapter(tasklistadapter);
        layoutManager=new LinearLayoutManager(this);
        taskrecylerview.setHasFixedSize(true);
        taskrecylerview.setLayoutManager(layoutManager);
        t=db.list();
        if(t.size()!=0){
            l.setVisibility(View.VISIBLE);
            f.setVisibility(View.INVISIBLE);
            tasklistadapter.setProfessorsArrayList(t);
        }
        TypeWriterView typeWriterView=findViewById(R.id.welcome);
        typeWriterView.setTypeWriterListener(new TypeWriterListener() {
            @Override
            public void onTypingStart(String text) {

            }

            @Override
            public void onTypingEnd(String text) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String getCurrentTime = sdf.format(c.getTime());

                if (getCurrentTime .compareTo("12:00") < 0)

                {
                    TypeWriterView greet=findViewById(R.id.greet);
                    greet.setWithMusic(false);
                    greet.setDelay(200);
                    greet.animateText("Good Morning.");
                }
                else if(getCurrentTime .compareTo("18:00") < 0)
                {
                    TypeWriterView greet=findViewById(R.id.greet);
                    greet.setWithMusic(false);
                    greet.setDelay(200);
                    greet.animateText("Good Afternoon.");
                }
                else{
                    TypeWriterView greet=findViewById(R.id.greet);
                    greet.setWithMusic(false);
                    greet.setDelay(200);
                    greet.animateText("Good Night.");
                }
            }

            @Override
            public void onCharacterTyped(String text, int position) {

            }

            @Override
            public void onTypingRemoved(String text) {

            }
        });
        typeWriterView.setWithMusic(false);
        typeWriterView.setDelay(200);
        typeWriterView.animateText("Hello Yogesh,");
    }

}
