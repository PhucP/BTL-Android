package com.example.btl_android.Main.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.Main.Adaptor.MessageAdapter;
import com.example.btl_android.Main.Model.Message;
import com.example.btl_android.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentNotification extends Fragment {
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<Message> list;

    public FragmentNotification() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noti, container, false);
        list = new ArrayList<>();
        list.add(new Message(R.drawable.baseline_task_24, "Hoa", "Em an com chua?", "16:20"));
        list.add(new Message(R.drawable.baseline_task_24, "Hoa", "Em an com chua?", "16:20"));
        list.add(new Message(R.drawable.baseline_task_24, "Hoa", "Em an com chua?", "16:20"));
        list.add(new Message(R.drawable.baseline_task_24, "Hoa", "Em an com chua?", "16:20"));
        list.add(new Message(R.drawable.baseline_task_24, "Hoa", "Em an com chua?", "16:20"));
        list.add(new Message(R.drawable.baseline_task_24, "Hoa", "Em an com chua?", "16:20"));

        recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        messageAdapter = new MessageAdapter(view.getContext(),list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(messageAdapter);

        return view;
    }
}
