package com.example.btl_android.Main.Fragment.Task;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;

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
import java.util.Calendar;
import java.util.List;

public class FragmentUncompletedTask extends Fragment {
    private User currentUser;
    private TaskAdapter taskAdapter;
    private List<Task> list;
    private RecyclerView recyclerView;
    private ImageView all, choseDay;
    private View view;
    private String sortDate;

    public FragmentUncompletedTask() {
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
        view = inflater.inflate(R.layout.fragment_uncompleted_task, container, false);
        all = view.findViewById(R.id.all);
        choseDay = view.findViewById(R.id.choseDay);
        recyclerView = view.findViewById(R.id.uncompletedRecycler);

        getUnCompletedTask();

        sortTaskActivity();
        return view;
    }

    private void getUnCompletedTask() {
        list = new ArrayList<>();
        FragmentUncompletedTask.GetUnCompletedTask getUnCompletedTask = new FragmentUncompletedTask.GetUnCompletedTask();
        getUnCompletedTask.execute();

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        taskAdapter = new TaskAdapter(view.getContext(), list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(taskAdapter);
    }

    private void sortTaskActivity() {
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUnCompletedTask();
            }
        });

        choseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortDate = "";
                Calendar c = Calendar.getInstance();
                int d = c.get(Calendar.DAY_OF_MONTH);
                int m = c.get(Calendar.MONTH);
                int y = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int mm, int d) {
                        sortDate = (d + "/" + (mm+1) + "/" + y + "%");
                        GetUnCompletedTaskInDay getUnCompletedTaskInDay = new GetUnCompletedTaskInDay();
                        getUnCompletedTaskInDay.execute();
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
    }

    public class GetUnCompletedTaskInDay extends AsyncTask<Void, Void, LiveData<List<Task>>> {
        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            return db.taskDao().getUnCompletedTaskInDay(currentUser.getId(), sortDate);
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

    public class GetUnCompletedTask extends AsyncTask<Void, Void, LiveData<List<Task>>> {
        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            return db.taskDao().getUncompletedTask(currentUser.getId());
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
