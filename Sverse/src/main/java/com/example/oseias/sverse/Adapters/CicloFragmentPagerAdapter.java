package com.example.oseias.sverse.Adapters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oseias.sverse.OthersFragments.BlocoDeNotasFragment;
import com.example.oseias.sverse.OthersFragments.CicloFragment;
import com.example.oseias.sverse.OthersFragments.ContainersFragment;
import com.example.oseias.sverse.OthersFragments.MuralFragment;
import com.example.oseias.sverse.OthersFragments.NotificacoesFragment;

/**
 * Created by Oseias on 10/01/2018.
 */

public class CicloFragmentPagerAdapter extends FragmentPagerAdapter {
    private String aux;
    private int indexAux = 0;
    private String[] tabTitles;
    private TabLayout tabLayout;


    public CicloFragmentPagerAdapter(FragmentManager fm, String[] tabTitles, TabLayout tabLayout) {
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
        return new CicloFragment();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabTitles[position];
    }
}
