package com.example.oseias.sverse.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oseias.sverse.OthersFragments.EtapasFragment;
import com.example.oseias.sverse.OthersFragments.FragmentEtapasConcluidas;
import com.example.oseias.sverse.OthersFragments.FragmentEtapasEmAndamento;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MyFragmentPagerTrabalhoEmGrupoAdapter extends FragmentPagerAdapter {
    private String[] tabTitles;

    public MyFragmentPagerTrabalhoEmGrupoAdapter(FragmentManager fm, String[] tabTitles) {
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
                return new EtapasFragment();
            case 1:
                return new FragmentEtapasEmAndamento();
            case 2:
                return new FragmentEtapasConcluidas();
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabTitles[position];
    }
}
