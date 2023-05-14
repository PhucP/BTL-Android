package com.example.btl_android.Main.Fragment.Task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.Entity.User;

public class FragmentUncompletedTask extends Fragment {
    private User currentUser;

    public FragmentUncompletedTask() {
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
        View view = inflater.inflate(R.layout.fragment_uncompleted_task, container, false);
        return view;
    }
}
