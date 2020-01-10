package com.example.oseias.sverse.Adapters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oseias.sverse.OthersFragments.BlocoDeNotasFragment;
import com.example.oseias.sverse.OthersFragments.MuralFragment;
import com.example.oseias.sverse.OthersFragments.NotificacoesFragment;
import com.example.oseias.sverse.OthersFragments.ContainersFragment;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String aux;
    private int indexAux = 0;
    private String[] tabTitles;
    private TabLayout tabLayout;


    public MyFragmentPagerAdapter(FragmentManager fm, String[] tabTitles, TabLayout tabLayout) {
        super(fm);
        this.tabTitles = tabTitles;
        this.tabLayout = tabLayout;
    }

    @Override
    public int getCount() {
        return this.tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).setText(":::");
                return new MuralFragment();
            case 1:
                tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).setText(":::");
                return new ContainersFragment();
            case 2:
                tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).setText(":::");
                return new BlocoDeNotasFragment();
            case 3:
                tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).setText(":::");
                return new NotificacoesFragment();
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabTitles[position];
    }
}
