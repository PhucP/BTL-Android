package com.example.btl_android.Main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.UpdateTaskActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    private Context context;
    private List<Task> list;

    public TaskAdapter(Context context, List<Task> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = list.get(position);
        String dateTime = task.getTime();
        String[] listTime = dateTime.split(" ");
        String[] dayTime = listTime[0].split("/");

        holder.day.setText(getDayInWeek(listTime[0]));
        holder.date.setText(dayTime[0]);
        holder.month.setText(getMonth(dayTime[1]));
        holder.time.setText(listTime[1]);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.status.setText(task.getStats());
    }

    private String getDayInWeek(String dayInWeek) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = dateFormat.parse(dayInWeek);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            switch (dayOfWeek) {
                case 1: return "Sun";
                case 2: return "Mon";
                case 3: return "Tue";
                case 4: return "Wed";
                case 5: return "Thu";
                case 6: return "Fri";
                case 7: return "Sat";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Nan";
    }

    private String getMonth(String monthTime) {
        int monthTime2 = Integer.parseInt(monthTime);
        switch (monthTime2) {
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
        }

        return "Nan";
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView day, date, month, time, title, description, status;
        private ImageView options;

        public ViewHolder(@NonNull View view) {
            super(view);
            day = view.findViewById(R.id.day);
            date = view.findViewById(R.id.date);
            month = view.findViewById(R.id.month);
            time = view.findViewById(R.id.time);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            status = view.findViewById(R.id.status);
            options = view.findViewById(R.id.options);

            options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Task currentTask = list.get(position);
                        Intent intent = new Intent(context, UpdateTaskActivity.class);
                        intent.putExtra("currentTask", currentTask);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
