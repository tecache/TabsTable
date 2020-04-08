package com.example.tabstable.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tabstable.R;

public class TimeOutDialogFragment extends DialogFragment {

    public TimePicker timePicker;

    public static TimeOutDialogFragment newInstance() {
        return new TimeOutDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View timeInflater = getLayoutInflater().inflate(R.layout.timepicker_layout, container, false);

        return timeInflater;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button pickerButton = view.findViewById(R.id.button_picker);
        Button cancelButton = view.findViewById(R.id.cancel_picker);
        timePicker = view.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);


        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String hourPicked = hour +":"+ minute;
                TimePickerViewModel viewModel = new ViewModelProvider(requireActivity()).get(TimePickerViewModel.class);
                viewModel.select(hourPicked);
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
