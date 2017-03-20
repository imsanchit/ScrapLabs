package com.example.sanchit.scraplabs.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.sanchit.scraplabs.Adapter.customTabAdapter;

import com.example.sanchit.scraplabs.R;
import com.example.sanchit.scraplabs.SharedPrefrences.UserLocalStore;

public class Home extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    customTabAdapter customTabAdapter;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);


        userLocalStore = new UserLocalStore(Home.this);


        customTabAdapter = new customTabAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(customTabAdapter);

        final TabLayout.Tab page1 = tabLayout.newTab();
        final TabLayout.Tab page2 = tabLayout.newTab();


        page1.setText("Logs");
        page2.setText("Classes");
        //home.setIcon(R.drawable.home);
        //search.setIcon(R.drawable.search_black);
        tabLayout.addTab(page1,0);
        tabLayout.addTab(page2,1);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}