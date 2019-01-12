package com.example.oseias.sverse.MainFragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.oseias.sverse.Adapters.MyFragmentPagerAdapter;
import com.example.oseias.sverse.OthersClass.ArquivamentoIndexFragment.IndexFragement;
import com.versaplications.prodesenvelopment.sverse.R;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MainFragment extends Fragment {
    private AppBarLayout appBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private IndexFragement indexFragement;
    private Context ctx;
    private int lastIndexFragment;
    private int defaultIndexPrimaryFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        View contenedor = (View) container.getParent();
        appBar = (AppBarLayout) container.findViewById(R.id.appBar2);
        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        appBar.addView(tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), getResources().getStringArray(R.array.titles_tabs));
        viewPager.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.WHITE,Color.DKGRAY);


        lerPredefinicoes();
        viewPager.setCurrentItem(lastIndexFragment);
        ctx = getActivity();
        salvarCofiguracoes();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabLayout);
        if(lastIndexFragment != defaultIndexPrimaryFragment){
            lastIndexFragment = defaultIndexPrimaryFragment;
        }
    }

    public void lerPredefinicoes(){
        ConfiguracaoDAO configDAO = new ConfiguracaoDAO(getActivity());
        lastIndexFragment = configDAO.buscarConfig(Configuracao.LAST_INDEX_FRAGMENT).getvalorConfig();
        defaultIndexPrimaryFragment = 0;
    }

    public void salvarCofiguracoes(){
        ConfiguracaoDAO configsDAO = new ConfiguracaoDAO(getActivity());
        configsDAO.atualizarConfig(new Configuracao(Configuracao.LAST_INDEX_FRAGMENT, 0));
        Toast.makeText(getContext(), "O LastIndexFragment foi salvo...", Toast.LENGTH_SHORT).show();
    }
}
