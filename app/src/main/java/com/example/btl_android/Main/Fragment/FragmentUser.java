package com.example.btl_android.Main.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.btl_android.LoginActivity;
import com.example.btl_android.MainActivity;
import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.Entity.User;
import com.example.btl_android.UpdateUserActivity;
import com.google.android.material.tabs.TabLayout;

public class FragmentUser extends Fragment {
    private static final int REQUEST_CODE_UPDATE_USER = 0303;
    private static final int RESULT_OK = -1;
    private User currentUser;
    private TextView name, email, phone, dob, username, updateUser;

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
        updateUser = view.findViewById(R.id.updateProfile);
    }

    private void activity() {

        setCurrentUser(currentUser);
        updateUserProfile();
    }

    private void setCurrentUser(User user) {
        name.setText(user.getName());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());
        dob.setText(user.getBirthday());
        username.setText(user.getUserName());
    }

    private void updateUserProfile() {
        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateUserActivity.class);
                intent.putExtra("currentUser", currentUser);
                startActivityForResult(intent, REQUEST_CODE_UPDATE_USER);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_UPDATE_USER && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("updateUser")) {
                User updatedUser = (User) data.getSerializableExtra("updateUser");
                currentUser = updatedUser;
                setCurrentUser(currentUser);
            }
        }
    }
}
