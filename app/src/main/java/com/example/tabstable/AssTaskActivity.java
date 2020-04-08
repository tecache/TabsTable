package com.example.tabstable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tabstable.ui.main.AssPagerAdapter;
import com.example.tabstable.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class AssTaskActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Toolbar toolbar = findViewById(R.id.ass_toolbar);
        setSupportActionBar(toolbar);



        viewPager = findViewById(R.id.task_viewpager);
        AssPagerAdapter sectionsPagerAdapter = new AssPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout = findViewById(R.id.task_tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }
}
