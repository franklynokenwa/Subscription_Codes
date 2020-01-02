package com.example.subscriptioncodes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> headFragment = new ArrayList<>();
    private List<String> headTitle = new ArrayList<>();


    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
            return headFragment.get(position);
        }

    @Override
    public int getCount() {
        return headTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return headTitle.get(position);
    }

    public void AddFragment(Fragment fragment, String title) {
        headFragment.add(fragment);
        headTitle.add(title);
    }
}
