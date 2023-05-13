package com.example.btl_android.Main.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl_android.Main.Fragment.FragmentTask;
import com.example.btl_android.Main.Fragment.FragmentUser;
import com.example.btl_android.Main.Fragment.FragmentStatistical;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int pageNum = 3;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println(position);
        switch (position) {
            case 0: return new FragmentTask();
            case 1: return new FragmentStatistical();
            case 2: return new FragmentUser();
            default: return new FragmentTask();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
