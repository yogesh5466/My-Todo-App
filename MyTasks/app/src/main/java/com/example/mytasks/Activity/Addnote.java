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

public class Addnote extends BaseActivity{
    Button add;
    TextView name,note;
    database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_note);
        add = findViewById(R.id.addnote);
        name = findViewById(R.id.name);
        note = findViewById(R.id.note);
        db = new database(getApplicationContext());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().matches("")||note.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Enter a value please!!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    task temp = new task();
                    temp.setTime(sdf.format(c.getTime()));
                    temp.setId(c.getTime().toString());
                    temp.setName(name.getText().toString());
                    temp.setNote(note.getText().toString());
                    db.addvalue(temp);
                    Intent i = new Intent(Addnote.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
