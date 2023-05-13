package com.example.btl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.btl_android.Main.Adapter.ViewPagerAdapter;
import com.example.btl_android.RoomDatabase.Entity.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        activity();
    }

    private void init() {
        //get current user from login
        Intent intent = getIntent();
        User currentUser = (User) intent.getSerializableExtra("currentUser");

        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);

        //set adapter and send user for all fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("currentUser", currentUser);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 3, bundle);
        viewPager.setAdapter(adapter);
    }

    private void activity() {
        viewPagerActivity();
        navigationViewActivity();
    }

    private void viewPagerActivity() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.mHome).setCheckable(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.mStatistical).setCheckable(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.mUser).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void navigationViewActivity() {
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(R.id.mHome == item.getItemId())
                    viewPager.setCurrentItem(0);
                else if(R.id.mStatistical == item.getItemId())
                    viewPager.setCurrentItem(1);
                else if(R.id.mUser == item.getItemId())
                    viewPager.setCurrentItem(2);
                return true;
            }
        });
    }
}