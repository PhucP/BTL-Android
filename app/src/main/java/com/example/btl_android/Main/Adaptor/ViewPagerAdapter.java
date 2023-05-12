package com.example.btl_android.Main.Adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl_android.Main.Fragment.FragmentCafe;
import com.example.btl_android.Main.Fragment.FragmentHome;
import com.example.btl_android.Main.Fragment.FragmentNotification;
import com.example.btl_android.Main.Fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int pageNum = 4;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println(position);
        switch (position) {
            case 0: return new FragmentHome();
            case 1: return new FragmentNotification();
            case 2: return new FragmentSearch();
            case 3: return new FragmentCafe();
            default: return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
