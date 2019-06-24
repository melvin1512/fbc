package com.example.viewpagert2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.viewpagert2.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter( getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);

        // add fragment
        sectionsPagerAdapter.AddFragments(new Tab1(),"Call");
        sectionsPagerAdapter.AddFragments(new Tab2(),"Contact");
        sectionsPagerAdapter.AddFragments(new Tab3(),"Fav");

        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setIcon(R.drawable.ic_call);
        tabs.getTabAt(1).setIcon(R.drawable.ic_group);
        tabs.getTabAt(2).setIcon(R.drawable.ic_star);
    }

}