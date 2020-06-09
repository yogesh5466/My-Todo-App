package com.example.mytasks.Manager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mytasks.Model.task;

@Database(entities = {task.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase instance;

    public abstract DatabaseDao databaseDao();

    public static synchronized TaskDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),TaskDatabase.class, "notes")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return instance;
    }

    private static TaskDatabase.Callback roomcallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //if anything want to be added at the beginning add here such as filldatamanually in async
        }
    };

}
