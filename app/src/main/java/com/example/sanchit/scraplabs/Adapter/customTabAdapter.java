package com.example.sanchit.scraplabs.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sanchit.scraplabs.Fragment.Fragment1;
import com.example.sanchit.scraplabs.Fragment.Fragment2;


public class customTabAdapter extends FragmentPagerAdapter {
    public customTabAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Page1";
            case 1:
                return "Page2";
        }
        return "Default";
    }
}