package com.example.btl_android.Main.Fragment.Task;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.Main.Adapter.TaskAdapter;
import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentCompletedTask extends Fragment {
    private User currentUser;
    private TaskAdapter taskAdapter;
    private List<Task> list;
    private RecyclerView recyclerView;

    public FragmentCompletedTask() {
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
        View view = inflater.inflate(R.layout.fragment_completed_task, container, false);

        list = new ArrayList<>();
        FragmentCompletedTask.GetCompletedTask getCompletedTask = new FragmentCompletedTask.GetCompletedTask();
        getCompletedTask.execute();

        recyclerView = view.findViewById(R.id.completedRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        taskAdapter = new TaskAdapter(view.getContext(), list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(taskAdapter);

        return view;
    }

    public class GetCompletedTask extends AsyncTask<Void, Void, LiveData<List<Task>>> {
        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            return db.taskDao().getCompletedTask(currentUser.getId());
        }

        @Override
        protected void onPostExecute(LiveData<List<Task>> taskLiveData) {
            super.onPostExecute(taskLiveData);
            taskLiveData.observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
                @Override
                public void onChanged(List<Task> tasks) {
                    list.clear();
                    list.addAll(tasks);
                    taskAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
