package com.example.tabstable.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabstable.AssFragment;
import com.example.tabstable.ExamFragment;

public class AssPagerAdapter extends FragmentPagerAdapter {

    private String[] taskTitles = {"Task/Assignment", "Exam/Test"};

    public AssPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return taskTitles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return new AssFragment();
        }else{
            return new ExamFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
