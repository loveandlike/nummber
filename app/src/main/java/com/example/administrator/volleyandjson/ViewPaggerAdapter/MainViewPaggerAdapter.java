package com.example.administrator.volleyandjson.ViewPaggerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2016/7/6.
 */
public class MainViewPaggerAdapter extends FragmentPagerAdapter {
    Fragment[] frags;
    public MainViewPaggerAdapter(FragmentManager fm, Fragment[] frags) {
        super(fm);
        this.frags=frags;
    }

    @Override
    public Fragment getItem(int position) {
        return frags[position];
    }

    @Override
    public int getCount() {
        return frags.length;
    }
}
