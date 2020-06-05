package com.example.mytasks.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytasks.Manager.database;
import com.example.mytasks.Model.task;
import com.example.mytasks.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Editnote extends BaseActivity{
    Button add;
    TextView name,note;
    database db;
    task t;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_note);
        Intent i = getIntent();
        t = (task) i.getSerializableExtra("task");
        add = findViewById(R.id.addnote);
        name = findViewById(R.id.name);
        note = findViewById(R.id.note);
        name.setText(t.getName());
        note.setText(t.getNote());
        id = t.getId();
        db = new database(getApplicationContext());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().matches("")||note.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Enter a value please!!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    t.setName(name.getText().toString());
                    t.setNote(note.getText().toString());
                    db.updatevalue(t.getId(),t);
                    Intent i = new Intent(Editnote.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
