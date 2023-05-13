package com.example.btl_android.Main.Task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TaskViewPagerAdapter extends FragmentStatePagerAdapter {
    public TaskViewPagerAdapter(@NonNull FragmentManager fm, int behaviour) {
        super(fm, behaviour);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:return new FragmentNextTask();
            case 1:return new FragmentUncompletedTask();
            case 2:return new FragmentCompletedTask();
            default: return new FragmentUncompletedTask();
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
