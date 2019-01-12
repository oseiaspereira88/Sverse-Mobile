package com.example.oseias.sverse.MainFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OtherAdapters.AtividadeAdapter;
import com.example.oseias.sverse.OtherAdapters.ContainerGridAdapter;
import com.example.oseias.sverse.OtherAdapters.ContainerListAdapter;
import com.example.oseias.sverse.OtherAdapters.GrupAdapter;
import com.example.oseias.sverse.OtherAdapters.MateriaAdapter;
import com.example.oseias.sverse.OthersActivitys.CriadorDeTurmas;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.dao.ContainerDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;
import com.versaplications.prodesenvelopment.sverse.R;

public class EtapasFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_etapas, null); //->container, false
        initializeViews(container, rootView);
        return rootView;
    }

    public void initializeViews(ViewGroup container, View rootView) {
        View parent = (View) container.getParent();
        AppBarLayout barLayout = (AppBarLayout) parent.findViewById(R.id.appBarArea);
        TabLayout tabLayout = (TabLayout) barLayout.getChildAt(0);
        TextView textTitulo = (TextView) rootView.findViewById(R.id.tvTituloLista);
        textTitulo.setText(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText().toString());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                textTitulo.setText(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}