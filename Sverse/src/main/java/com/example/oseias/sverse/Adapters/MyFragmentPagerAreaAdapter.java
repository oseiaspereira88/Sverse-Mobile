package com.example.oseias.sverse.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oseias.sverse.OthersFragments.AllObjetivoFragment;
import com.example.oseias.sverse.OthersFragments.ConcluidosObjetivoFragment;
import com.example.oseias.sverse.OthersFragments.EmAndamentoObjetivoFragment;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MyFragmentPagerAreaAdapter extends FragmentPagerAdapter {
    private String[] tabTitles;

    public MyFragmentPagerAreaAdapter(FragmentManager fm, String[] tabTitles) {
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
                return new AllObjetivoFragment();
            case 1:
                return new EmAndamentoObjetivoFragment();
            case 2:
                return new ConcluidosObjetivoFragment();
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabTitles[position];
    }
}
