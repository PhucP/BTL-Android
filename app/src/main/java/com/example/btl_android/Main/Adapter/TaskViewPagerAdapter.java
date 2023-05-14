package com.example.btl_android.Main.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl_android.Main.Fragment.Task.FragmentCompletedTask;
import com.example.btl_android.Main.Fragment.Task.FragmentNextTask;
import com.example.btl_android.Main.Fragment.Task.FragmentUncompletedTask;

public class TaskViewPagerAdapter extends FragmentStatePagerAdapter {
    private Bundle childBundle;
    public TaskViewPagerAdapter(@NonNull FragmentManager fm, int behaviour, Bundle childBundle) {
        super(fm, behaviour);
        this.childBundle = childBundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                FragmentNextTask fragmentNextTask = new FragmentNextTask();
                fragmentNextTask.setArguments(childBundle);
                return fragmentNextTask;
            }
            case 1: {
                FragmentUncompletedTask fragmentUncompletedTask = new FragmentUncompletedTask();
                fragmentUncompletedTask.setArguments(childBundle);
                return fragmentUncompletedTask;
            }
            case 2:{
                FragmentCompletedTask fragmentCompletedTask = new FragmentCompletedTask();
                fragmentCompletedTask.setArguments(childBundle);
                return fragmentCompletedTask;
            }
            default: {
                FragmentNextTask fragmentNextTask = new FragmentNextTask();
                fragmentNextTask.setArguments(childBundle);
                return fragmentNextTask;
            }
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Next Task";
            case 1: return "Uncompleted Task";
            case 2: return "Completed Task";
            default: return "Uncompleted Task";
        }
    }
}
