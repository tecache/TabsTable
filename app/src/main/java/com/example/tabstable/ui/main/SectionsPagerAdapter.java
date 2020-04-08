package com.example.tabstable.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabstable.coursefragments.FridayFragment;
import com.example.tabstable.coursefragments.MondayFragment;
import com.example.tabstable.coursefragments.SaturdayFragment;
import com.example.tabstable.coursefragments.ThursdayFragment;
import com.example.tabstable.coursefragments.TuesdayFragment;
import com.example.tabstable.coursefragments.WednesdayFragment;
import com.example.tabstable.database.TableDatabase;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {



    private static final int  NO_OF_TABS = 6;
    private final Context context;
    private TableDatabase db;

    private String [] tabTitle = {"Monday", "Tuesday","Wednesday","Thursday", "Friday", "Saturday"};

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        db = TableDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

       return tabTitle[position];

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MondayFragment();
            case 1:
                return new TuesdayFragment(context);
            case 2:
                return new WednesdayFragment(context);
            case 3:
                return new ThursdayFragment(context);
            case 4:
                return new FridayFragment(context);
            case 5:
                return new SaturdayFragment(context);
            default:
                return null;
        }

    }


    @Override
    public int getCount() {
        return NO_OF_TABS;
    }
}