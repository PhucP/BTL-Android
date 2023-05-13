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

import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.Entity.User;
import com.google.android.material.tabs.TabLayout;

public class FragmentUser extends Fragment {
    private User currentUser;
    private TextView name, email, phone, dob, username;

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
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        init(view);
        activity();
        return view;
    }

    private void init(@NonNull View view) {
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        dob = view.findViewById(R.id.dob);
        username = view.findViewById(R.id.userName);
    }

    private void activity() {
        setCurrentUser();
    }

    private void setCurrentUser() {
        name.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phone.setText(currentUser.getPhoneNumber());
        dob.setText(currentUser.getBirthday());
        username.setText(currentUser.getUserName());
    }
}
