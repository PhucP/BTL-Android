package com.example.btl_android.Main.Fragment.Task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.Main.Adapter.StatisticalAdapter;
import com.example.btl_android.Main.Adapter.TaskAdapter;
import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentNextTask extends Fragment {
    private User currentUser;
    private TaskAdapter taskAdapter;
    private List<Task> list;
    private RecyclerView recyclerView;

    public FragmentNextTask() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            currentUser = (User) bundle.getSerializable("currentUser");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_next_task, container, false);
        list = new ArrayList<>();
        list.add(new Task());
        list.add(new Task());
        list.add(new Task());

        recyclerView = view.findViewById(R.id.nextRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        taskAdapter = new TaskAdapter(view.getContext(), list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(taskAdapter);

        return view;
    }
}
