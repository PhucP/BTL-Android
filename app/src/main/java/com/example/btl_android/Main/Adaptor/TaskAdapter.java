package com.example.btl_android.Main.Adaptor;

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

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    private Context context;
    private List<Message> list;

    public TaskAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message s = list.get(position);
        holder.img.setImageResource(s.getImg());
        holder.name.setText(s.getName());
        holder.title.setText(s.getTitle());
        holder.gio.setText(s.getGio());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, title, gio;
        public ViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            name = view.findViewById(R.id.tname);
            title = view.findViewById(R.id.ttitle);
            gio = view.findViewById(R.id.tgio);
        }
    }
}
