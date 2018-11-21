package com.example.oseias.sverse.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oseias.sverse.MainFragments.BlocoDeNotasFragment;
import com.example.oseias.sverse.MainFragments.MuralFragment;
import com.example.oseias.sverse.MainFragments.NotifiFragment;
import com.example.oseias.sverse.MainFragments.ContainersFragment;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles;


    public MyFragmentPagerAdapter(FragmentManager fm, String[] tabTitles) {
        super(fm);
        this.tabTitles = tabTitles;
    }

    @Override
    public int getCount() {
        return this.tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MuralFragment();
            case 1:
                return new ContainersFragment();
            case 2:
                return new BlocoDeNotasFragment();
            case 3:
                return new NotifiFragment();
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabTitles[position];
    }
}
