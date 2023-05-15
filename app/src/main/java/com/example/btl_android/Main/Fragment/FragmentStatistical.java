package com.example.btl_android.Main.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.btl_android.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class FragmentStatistical extends Fragment {
    TextView nextTask, completedTask, unCompletedTask, totalTask;
    PieChart pieChart;

    public FragmentStatistical() {
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

        setData();

        return view;
    }

    private void setData()
    {
        int numOfNext, numOfCompleted, numOfUnCompleted;
        numOfNext = 40;
        numOfCompleted = 25;
        numOfUnCompleted = 35;
        // Set the percentage of language used
        nextTask.setText(numOfNext + "%");
        completedTask.setText(numOfCompleted + "%");
        unCompletedTask.setText(numOfUnCompleted + "%");

        // Set the data and color to the pie chart
        pieChart.addPieSlice(new PieModel("Next Task", numOfNext, Color.parseColor("#FFA726")));
        pieChart.addPieSlice(new PieModel("Completed Task", numOfCompleted, Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(new PieModel("UnCompleted Task", numOfUnCompleted, Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
