package com.example.rcksuporte05.recycleractionmode.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rcksuporte05.recycleractionmode.Frag.Frag1;
import com.example.rcksuporte05.recycleractionmode.Frag.Frag2;

/**
 * Created by RCKSUPORTE05 on 26/12/2017.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private String[] titles = {"Frag 1", "Frag 2"};
    private Activity activity;

    public TabAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new Frag1();
                break;
            case 1:
                frag = new Frag2();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
