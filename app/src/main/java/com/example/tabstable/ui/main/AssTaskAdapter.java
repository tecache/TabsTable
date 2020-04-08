package com.example.tabstable.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabstable.R;
import com.example.tabstable.model.TaskEntity;

import java.util.List;


public class AssTaskAdapter extends RecyclerView.Adapter<AssTaskAdapter.MyAssViewModel> {


    public List<TaskEntity> task_entityList;

    @NonNull
    @Override
    public AssTaskAdapter.MyAssViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asstask_layout, parent, false);
        return new AssTaskAdapter.MyAssViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssTaskAdapter.MyAssViewModel holder, int position) {
        TaskEntity taskEntity = task_entityList.get(position);
        holder.taskView.setText(taskEntity.getTaskName());
        holder.timeTask.setText(taskEntity.getTimePicked());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyAssViewModel extends RecyclerView.ViewHolder {

        TextView taskView;
        TextView timeTask;
        public MyAssViewModel(@NonNull View itemView) {
            super(itemView);

            taskView = itemView.findViewById(R.id.ass_task);
            timeTask = itemView.findViewById(R.id.task_dateTime);
        }
    }
}
