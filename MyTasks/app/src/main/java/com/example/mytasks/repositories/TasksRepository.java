package com.example.mytasks.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mytasks.Manager.DatabaseDao;
import com.example.mytasks.Manager.TaskDatabase;
import com.example.mytasks.Model.task;

import java.util.List;

public class TasksRepository {

    private LiveData<List<task>> allnotes;
    private DatabaseDao databaseDao;

    public TasksRepository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        databaseDao = taskDatabase.databaseDao();
        allnotes = databaseDao.getall();
    }

    public void insert(task t) {
        new InsertNoteAsyncTask(databaseDao).execute(t);
    }

    public void update(task t) {
        new UpdateNoteAsyncTask(databaseDao).execute(t);
    }

    public void delete(task t) {
        new DeleteNoteAsyncTask(databaseDao).execute(t);
    }

    public LiveData<List<task>> getall() {
        return allnotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<task, Void, Void> {
        private DatabaseDao databaseDao;

        private InsertNoteAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(task... tasks) {
            databaseDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<task, Void, Void> {
        private DatabaseDao databaseDao;

        private UpdateNoteAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(task... tasks) {
            databaseDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<task, Void, Void> {
        private DatabaseDao databaseDao;

        private DeleteNoteAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(task... tasks) {
            databaseDao.delete(tasks[0]);
            return null;
        }
    }
}
