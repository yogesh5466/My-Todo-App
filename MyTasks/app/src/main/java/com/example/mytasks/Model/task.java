package com.example.mytasks.Model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class task implements Serializable {

    private String id;
    private String note;
    private String comp;
    private String time;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setComp(String comp) { this.comp = comp; }

    public String getComp() { return comp; }

    public void setTime(String time) { this.time = time; }

    public String getTime() { return time; }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id",id);
        result.put("note",note);
        result.put("comp",comp);
        result.put("time",time);
        result.put("name",name);
        return result;
    }
}

