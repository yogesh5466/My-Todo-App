package com.example.mytasks.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytasks.Adapter.tasklistadapter;
import com.example.mytasks.Model.task;
import com.example.mytasks.R;
import com.example.mytasks.viewmodels.StartActivityViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import in.codeshuffle.typewriterview.TypeWriterListener;
import in.codeshuffle.typewriterview.TypeWriterView;


public class StartActivity extends BaseActivity implements View.OnClickListener {
    private static final String Tag = "StartActivity";
    private com.example.mytasks.Adapter.tasklistadapter tasklistadapter;
    private RecyclerView taskrecylerview;
    private RecyclerView.LayoutManager layoutManager;
    private Button add;
    private LinearLayout linearLayout;
    private FrameLayout frameLayout;
    private StartActivityViewModel startActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.addnotes);
        startActivityViewModel = ViewModelProviders.of(this).get(StartActivityViewModel.class);
        startActivityViewModel.getall().observe(this, new Observer<List<task>>() {
            @Override
            public void onChanged(List<task> tasks) {
                if (tasks.size() != 0) {
                    initRecyclerView();
                    linearLayout.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    tasklistadapter.setnotesArrayList(tasks);
                } else {
                    linearLayout.setVisibility(View.INVISIBLE);
                    frameLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        initRecyclerView();
        add.setOnClickListener(this);
        TypeWriterView typeWriterView = findViewById(R.id.welcome);
        typeWriterView.setTypeWriterListener(new TypeWriterListener() {
            @Override
            public void onTypingStart(String text) {

            }

            @Override
            public void onTypingEnd(String text) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String getCurrentTime = sdf.format(c.getTime());

                if (getCurrentTime.compareTo("12:00") < 0) {
                    TypeWriterView greet = findViewById(R.id.greet);
                    greet.setWithMusic(false);
                    greet.setDelay(200);
                    greet.animateText("Good Morning.");
                } else if (getCurrentTime.compareTo("18:00") < 0) {
                    TypeWriterView greet = findViewById(R.id.greet);
                    greet.setWithMusic(false);
                    greet.setDelay(200);
                    greet.animateText("Good Afternoon.");
                } else {
                    TypeWriterView greet = findViewById(R.id.greet);
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

    private void initRecyclerView() {
        tasklistadapter = new tasklistadapter(this, this);
        linearLayout = findViewById(R.id.layout1);
        frameLayout = findViewById(R.id.layout2);
        taskrecylerview = findViewById(R.id.tasklistRecyclerView);
        taskrecylerview.setAdapter(tasklistadapter);
        layoutManager = new LinearLayoutManager(this);
        taskrecylerview.setHasFixedSize(true);
        taskrecylerview.setLayoutManager(layoutManager);
        tasklistadapter.setCallBack(new tasklistadapter.CallBack() {
            @Override
            public void onItemClick(int position, final View view) {
                final task temp = tasklistadapter.getItemByPosition(position);
                final LayoutInflater inflater = getLayoutInflater();
                final View dialoglayout = inflater.inflate(R.layout.dialog_edit_delete, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
                builder.setView(dialoglayout);
                final AlertDialog alertDialog = builder.create();
                Button edit, delete;
                edit = dialoglayout.findViewById(R.id.edit);
                delete = dialoglayout.findViewById(R.id.delete);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(StartActivity.this, Editnote.class);
                        i.putExtra("taskname", temp.getName());
                        i.putExtra("taskid", temp.getId());
                        i.putExtra("tasknote", temp.getNote());
                        i.putExtra("taskcomp", temp.getComp());
                        i.putExtra("tasktime", temp.getTime());
                        startActivityForResult(i, 2);
                        alertDialog.dismiss();

                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityViewModel.delete(temp);
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }

            @Override
            public void onCheckClick(CheckBox c, task temp, View view) {
                if (c.isChecked()) {
                    temp.setComp("1");
                    startActivityViewModel.update(temp);
                } else {
                    temp.setComp("0");
                    startActivityViewModel.update(temp);
                }
            }

        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addnotes) {
            Intent i = new Intent(StartActivity.this, Addnote.class);
            startActivityForResult(i, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {
        super.onActivityResult(requestCode, resultCode, i);
        if (requestCode == 1 && i != null) {
            task t;
            t = new task(i.getStringExtra("tasknote"), i.getStringExtra("taskcomp"), i.getStringExtra("tasktime"), i.getStringExtra("taskname"));
            t.setId(i.getIntExtra("taskid", 0));
            startActivityViewModel.insert(t);
        }
        if (requestCode == 2 && i != null) {
            task t;
            t = new task(i.getStringExtra("tasknote"), i.getStringExtra("taskcomp"), i.getStringExtra("tasktime"), i.getStringExtra("taskname"));
            t.setId(i.getIntExtra("taskid", 0));
            startActivityViewModel.update(t);
        }
    }

}
