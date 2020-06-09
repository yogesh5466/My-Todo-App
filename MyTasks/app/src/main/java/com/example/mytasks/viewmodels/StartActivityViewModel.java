package com.example.mytasks.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mytasks.Model.task;
import com.example.mytasks.repositories.TasksRepository;

import java.util.List;

public class StartActivityViewModel extends AndroidViewModel {
    private LiveData<List<task>> mtask;
    private TasksRepository repository;

    public StartActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new TasksRepository(application);
        mtask = repository.getall();
    }

    public void insert(task t) {
        repository.insert(t);
    }

    public void update(task t) {
        repository.update(t);
    }

    public void delete(task t) {
        repository.delete(t);
    }

    public LiveData<List<task>> getall() {
        return mtask;
    }
}
