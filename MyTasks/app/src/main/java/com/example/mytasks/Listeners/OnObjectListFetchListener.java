package com.example.mytasks.Listeners;

import java.util.ArrayList;

public interface OnObjectListFetchListener<T> {
    void onListChanged(ArrayList<T> list, boolean isEmpty);
}
