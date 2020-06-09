package com.example.mytasks.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytasks.Manager.TaskDatabase;
import com.example.mytasks.Model.task;
import com.example.mytasks.R;


public class Editnote extends BaseActivity{
    private Button add;
    private TextView name,note;
    private task t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_note);
        Intent i = getIntent();
        t = new task(i.getStringExtra("tasknote"),i.getStringExtra("taskcomp"),i.getStringExtra("tasktime"),i.getStringExtra("taskname"));
        t.setId(i.getIntExtra("taskid",0));
        add = findViewById(R.id.addnote);
        name = findViewById(R.id.name);
        note = findViewById(R.id.note);
        name.setText(t.getName());
        note.setText(t.getNote());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().matches("")||note.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Enter a value please!!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    task t1 = new task(note.getText().toString(),t.getComp(),t.getTime(),name.getText().toString());
                    t1.setId(t.getId());
                    Intent i = new Intent(Editnote.this,StartActivity.class);
                    i.putExtra("taskname",t1.getName());
                    i.putExtra("taskid",t1.getId());
                    i.putExtra("tasknote",t1.getNote());
                    i.putExtra("taskcomp",t1.getComp());
                    i.putExtra("tasktime",t1.getTime());
                    setResult(1,i);
                    finish();
                }
            }
        });
    }
}
