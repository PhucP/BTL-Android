package com.example.btl_android.Main.Fragment.Task;

import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.btl_android.AddTaskActivity;
import com.example.btl_android.Main.Adapter.TaskAdapter;
import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;
import com.example.btl_android.UpdateUserActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentNextTask extends Fragment {
    private User currentUser;
    private TaskAdapter taskAdapter;
    private List<Task> list;
    private RecyclerView recyclerView;
    private TextView addTask;
    private static final int REQUEST_CODE_UPDATE_USER = 0303;
    private static final int RESULT_OK = -1;
    private View view;

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

        getNextTask();

        addTaskActivity();

        return view;
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

    public class GetNextTask extends AsyncTask<Void, Void, List<Task>> {
        @Override
        protected List<Task> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            return db.taskDao().getNextTask(currentUser.getId());
        }

        @Override
        protected void onPostExecute(List<Task> tasks) {
            super.onPostExecute(tasks);
            list.clear();
            list.addAll(tasks);
//            System.out.println("test" + list.size());
            taskAdapter.notifyDataSetChanged();
        }
    }

    private void addTaskActivity() {
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                intent.putExtra("currentUser", currentUser);
                startActivityForResult(intent, REQUEST_CODE_UPDATE_USER);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_UPDATE_USER && resultCode == RESULT_OK) {
            getNextTask();
        }
    }
}
