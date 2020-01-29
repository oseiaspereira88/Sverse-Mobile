package com.example.oseias.sverse.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oseias.sverse.MainFragments.MenuFragment;
import com.example.oseias.sverse.OthersFragments.NotasFragment;
import com.example.oseias.sverse.OthersFragments.MuralFragment;
import com.example.oseias.sverse.OthersFragments.NotificacoesFragment;
import com.example.oseias.sverse.OthersFragments.ContainersFragment;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private int nTabs;


    public MyFragmentPagerAdapter(FragmentManager fm, int nTabs) {
        super(fm);
        this.nTabs = nTabs;
    }

    @Override
    public int getCount() {
        return this.nTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MuralFragment();
            case 1:
                return new ContainersFragment();
            case 2:
                return new NotasFragment();
            case 3:
                return new NotificacoesFragment();
            case 4:
                return new NotificacoesFragment();
            case 5:
                return new MenuFragment();
            default:
                return new MenuFragment();
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
