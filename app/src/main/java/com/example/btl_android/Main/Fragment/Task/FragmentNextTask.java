package com.example.btl_android.Main.Fragment.Task;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.AddTaskActivity;
import com.example.btl_android.Main.Adapter.TaskAdapter;
import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Dao.TaskDao;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;
import com.example.btl_android.UpdateUserActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FragmentNextTask extends Fragment {
    private User currentUser;
    private TaskAdapter taskAdapter;
    private List<Task> list;
    private RecyclerView recyclerView;
    private TextView addTask;
    private View view;
    private ImageView all, choseDay;
    private String sortDate;

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
        view = inflater.inflate(R.layout.fragment_next_task, container, false);
        recyclerView = view.findViewById(R.id.nextRecycler);
        addTask = view.findViewById(R.id.addTask);
        all = view.findViewById(R.id.all);
        choseDay = view.findViewById(R.id.choseDay);

        getNextTask();

        addTaskActivity();
        sortTaskActivity();

        return view;
    }

    private void sortTaskActivity() {
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNextTask();
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
                        GetNextTaskInDay getNextTaskInDay = new GetNextTaskInDay();
                        getNextTaskInDay.execute();
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
    }

    public class GetNextTaskInDay extends AsyncTask<Void, Void, LiveData<List<Task>>> {
        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            return db.taskDao().getNextTaskInDay(currentUser.getId(), sortDate);
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

    private void getNextTask() {
        list = new ArrayList<>();
        GetNextTask getNextTask = new GetNextTask();
        getNextTask.execute();

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        taskAdapter = new TaskAdapter(view.getContext(), list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(taskAdapter);
    }

    public class GetNextTask extends AsyncTask<Void, Void, LiveData<List<Task>>> {
        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            return db.taskDao().getNextTask(currentUser.getId());
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

    private void addTaskActivity() {
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
        });
    }
}
