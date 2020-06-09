package com.example.mytasks.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mytasks.Model.task;
import com.example.mytasks.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Addnote extends BaseActivity {
    private Button add;
    private TextView name, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_note);
        add = findViewById(R.id.addnote);
        name = findViewById(R.id.name);
        note = findViewById(R.id.note);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().matches("") || note.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter a value please!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
                    Intent i = new Intent(Addnote.this, StartActivity.class);
                    task temp = new task(note.getText().toString(), "0", sdf.format(c.getTime()), name.getText().toString());
                    i.putExtra("taskname", temp.getName());
                    i.putExtra("taskid", temp.getId());
                    i.putExtra("tasknote", temp.getNote());
                    i.putExtra("taskcomp", temp.getComp());
                    i.putExtra("tasktime", temp.getTime());
                    setResult(1, i);
                    finish();
                }
            }
        });
    }
}
