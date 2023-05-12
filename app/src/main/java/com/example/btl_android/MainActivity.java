package com.example.btl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.btl_android.Main.Adaptor.ViewPagerAdapter;
import com.example.btl_android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);

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
                        navigationView.getMenu().findItem(R.id.mTask).setCheckable(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.mAddTask).setCheckable(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.mCompletedTask).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(R.id.mHome == item.getItemId())
                    viewPager.setCurrentItem(0);
                else if(R.id.mTask == item.getItemId())
                    viewPager.setCurrentItem(1);
                else if(R.id.mAddTask == item.getItemId())
                    viewPager.setCurrentItem(2);
                else if(R.id.mCompletedTask == item.getItemId())
                    viewPager.setCurrentItem(3);
                return true;
            }
        });
    }
}