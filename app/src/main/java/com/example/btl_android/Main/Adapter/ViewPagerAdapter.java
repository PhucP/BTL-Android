package com.example.btl_android.Main.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl_android.Main.Fragment.FragmentTask;
import com.example.btl_android.Main.Fragment.FragmentUser;
import com.example.btl_android.Main.Fragment.FragmentStatistical;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int pageNum = 3;
    private Bundle bundle;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, Bundle bundle) {
        super(fm, behavior);
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                FragmentTask fragmentTask = new FragmentTask();
                fragmentTask.setArguments(bundle);
                return fragmentTask;
            }
            case 1: {
                FragmentStatistical fragmentStatistical = new FragmentStatistical();
                fragmentStatistical.setArguments(bundle);
                return fragmentStatistical;
            }
            case 2: {
                FragmentUser fragmentUser = new FragmentUser();
                fragmentUser.setArguments(bundle);
                return fragmentUser;
            }
            default: {
                FragmentTask fragmentTask = new FragmentTask();
                fragmentTask.setArguments(bundle);
                return fragmentTask;
            }
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
