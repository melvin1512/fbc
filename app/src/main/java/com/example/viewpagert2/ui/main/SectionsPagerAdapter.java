package com.example.viewpagert2.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.viewpagert2.R;
import com.example.viewpagert2.Tab1;
import com.example.viewpagert2.Tab2;
import com.example.viewpagert2.Tab3;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    //private final Context mContext;

    private final List<Fragment> lstfragment = new ArrayList<>();
    private final List<String> lsttitle = new ArrayList<>();

    public SectionsPagerAdapter( FragmentManager fm) {
        super(fm);
        //mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
//        switch(position){
//            case 0:
//                Tab1 tab1 = new Tab1();
//                return tab1;
//            case 1:
//                Tab2 tab2 = new Tab2();
//                return tab2;
//            case 2:
//                Tab3 tab3 = new Tab3();
//                return tab3;
//        }
//        return null;
        return lstfragment.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return mContext.getResources().getString(TAB_TITLES[position]);
        return lsttitle.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        //return 3;
        return lsttitle.size();
    }

    public void AddFragments (Fragment fragment, String title)
    {
        lstfragment.add(fragment);
        lsttitle.add(title);
    }
}