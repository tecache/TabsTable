package com.example.tabstable.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimePickerViewModel extends ViewModel {

    private final MutableLiveData<String> selected = new MutableLiveData<>();

    public void select(String item) {
        selected.setValue(item);
    }

    public LiveData<String> getSelected() {
        return selected;
    }
}

