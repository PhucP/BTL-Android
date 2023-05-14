package com.example.btl_android.Main.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.btl_android.Main.Adapter.TaskViewPagerAdapter;
import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.Entity.User;
import com.google.android.material.tabs.TabLayout;

public class FragmentTask extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private User currentUser;

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
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        Bundle childBundle = new Bundle();
        childBundle.putSerializable("currentUser", currentUser);

        TaskViewPagerAdapter adapter = new TaskViewPagerAdapter(getChildFragmentManager(), 3, childBundle);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
