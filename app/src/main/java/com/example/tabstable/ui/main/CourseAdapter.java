package com.example.tabstable.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabstable.CourseActivity;
import com.example.tabstable.CourseTable;
import com.example.tabstable.R;
import com.example.tabstable.model.CourseTimeTable;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewModel> {



    public List<CourseTimeTable> mCourseTimetable;
    private Context context;

    public CourseAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new MyViewModel(view);
    }

    public void notifyView(List<CourseTimeTable> timetable){
        mCourseTimetable = timetable;
        notifyDataSetChanged();
    }
    public List<CourseTimeTable> gettable(){
        return mCourseTimetable;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewModel holder, int position) {
        CourseTimeTable courseTimeTable = mCourseTimetable.get(position);
        holder.coursetext.setText(courseTimeTable.getCourseTitle());
        holder.coursetitle.setText(courseTimeTable.getCourseName().toUpperCase());
        holder.timeSchedule.setText(courseTimeTable.getCourseTime());

    }

    @Override
    public int getItemCount() {
        if (mCourseTimetable == null) {
            return 0;
    }
        return mCourseTimetable.size();
}



    public class MyViewModel extends RecyclerView.ViewHolder {
    TextView coursetext;
    TextView coursetitle;
    TextView timeSchedule;


        public MyViewModel(@NonNull View itemView) {
            super(itemView);
            coursetext = itemView.findViewById(R.id.coursetext_textView);
            coursetitle = itemView.findViewById(R.id.coursetitle_textView);
            timeSchedule = itemView.findViewById(R.id.date_textView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CourseActivity.class);
                    context.startActivity(intent);

                }
            });

        }
    }
}
