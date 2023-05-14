package com.example.btl_android.Main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.Main.Model.Message;
import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.Entity.Task;

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
        holder.day.setText("test");
        holder.date.setText("test");
        holder.month.setText("test");
        holder.time.setText("test");
        holder.title.setText("test");
        holder.description.setText("test");
        holder.status.setText("test");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView day, date, month, time, title, description, status;
        public ViewHolder(@NonNull View view) {
            super(view);
            day = view.findViewById(R.id.day);
            date = view.findViewById(R.id.date);
            month = view.findViewById(R.id.month);
            time = view.findViewById(R.id.time);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            status = view.findViewById(R.id.status);
        }
    }
}
