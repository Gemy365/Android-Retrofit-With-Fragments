package com.example.android.retrofitwithfragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

// ViewPagerAdapter Own Class extends FragmentPagerAdapter Class.
class ViewPagerAdapter extends FragmentPagerAdapter {

    // Make List Of Fragments & Titles.
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    // Constructor has FragmentManager as param.
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Get Fragment By Position.
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    // Get size of fragments.
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    // Get Title By Position.
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    // Method Take 2 Param (Fragment, Title).
    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        titles.add(title);
    }
}
