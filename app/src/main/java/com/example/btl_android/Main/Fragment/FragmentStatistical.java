package com.example.btl_android.Main.Fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.btl_android.Main.Fragment.Task.FragmentNextTask;
import com.example.btl_android.R;
import com.example.btl_android.RegisterActivity;
import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentStatistical extends Fragment {
    TextView nextTask, completedTask, unCompletedTask, totalTask, anim;
    PieChart pieChart;
    private User currentUser;
    private List<Task> listAllTask;

    public FragmentStatistical() {
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
        View view = inflater.inflate(R.layout.fragment_statistical, container, false);

        nextTask = view.findViewById(R.id.tvR);
        completedTask = view.findViewById(R.id.tvPython);
        unCompletedTask = view.findViewById(R.id.tvCPP);
        pieChart = view.findViewById(R.id.piechart);
        totalTask = view.findViewById(R.id.totalTask);
        anim = view.findViewById(R.id.anim);

        listAllTask = new ArrayList<>();
        FragmentStatistical.GetAllTask allTask = new FragmentStatistical.GetAllTask();
        allTask.execute();

        //setData();

        anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieChart.startAnimation();
            }
        });

        return view;
    }

    private void setData()
    {
        if(listAllTask.size() == 0) return;
        int numOfNext = 0;
        int numOfCompleted = 0;
        int numOfUnCompleted = 0;
        for(int i = 0; i < listAllTask.size(); i++) {
            if (listAllTask.get(i).getStats().equals("next_task")) {
                numOfNext += 1;
            } else if (listAllTask.get(i).getStats().equals("completed")) {
                numOfCompleted += 1;
            } else {
                numOfUnCompleted += 1;
            }
        }


        numOfNext = (int) (numOfNext*100/listAllTask.size());
        numOfCompleted = (int) (numOfCompleted*100/listAllTask.size());
        numOfUnCompleted = 100 - numOfNext - numOfCompleted;
        // Set the percentage of language used
        nextTask.setText(numOfNext + "%");
        completedTask.setText(numOfCompleted + "%");
        unCompletedTask.setText(numOfUnCompleted + "%");
        totalTask.setText(listAllTask.size() + "");

        // Set the data and color to the pie chart
        pieChart.clearChart();
        pieChart.addPieSlice(new PieModel("Next Task", numOfNext, Color.parseColor("#FFA726")));
        pieChart.addPieSlice(new PieModel("Completed Task", numOfCompleted, Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(new PieModel("UnCompleted Task", numOfUnCompleted, Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }

    public class GetAllTask extends AsyncTask<Void, Void, LiveData<List<Task>>> {
        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            return db.taskDao().getAllTask(currentUser.getId());
        }

        @Override
        protected void onPostExecute(LiveData<List<Task>> taskLiveData) {
            super.onPostExecute(taskLiveData);
            taskLiveData.observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
                @Override
                public void onChanged(List<Task> tasks) {
                    listAllTask.clear();
                    listAllTask.addAll(tasks);
                    setData();
                }
            });
        }
    }
}
