package com.example.tabstable.ui.main;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TaskTimeDialog extends DialogFragment {

    public Calendar calender;
    public int hour;
    public int minutes;
    public String timePicked;
    // private TimePicked mPicked;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        calender = Calendar.getInstance();
        hour = calender.get(Calendar.HOUR_OF_DAY);
        minutes = calender.get(Calendar.MINUTE);
        return new TimePickerDialog(getContext(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minutes, true);
    }
}
