package com.example.mytasks.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes")
public class task{

    @PrimaryKey(autoGenerate = true)
    private int id;

    public task(String note, String comp, String time, String name) {
        this.note = note;
        this.comp = comp;
        this.time = time;
        this.name = name;
    }

    private String note;
    private String comp;
    private String time;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public String getComp() { return comp; }

    public String getTime() { return time; }

    public String getName() { return name; }

    public void setComp(String comp) {
        this.comp = comp;
    }
}

