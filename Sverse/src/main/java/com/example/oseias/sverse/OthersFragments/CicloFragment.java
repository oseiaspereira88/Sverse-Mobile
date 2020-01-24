package com.example.oseias.sverse.OthersFragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Adapters.CicloFragmentPagerAdapter;
import com.example.oseias.sverse.OtherAdapters.CicloItemAdapter;
import com.example.oseias.sverse.OthersActivitys.CicloItemCreator;
import com.example.oseias.sverse.SQLite.dao.CicloDAO;
import com.example.oseias.sverse.SQLite.dao.CicloItemDAO;
import com.example.oseias.sverse.SQLite.model.Ciclo;
import com.example.oseias.sverse.SQLite.model.CicloItem;
import com.exemple.oseias.sverse.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CicloFragment extends Fragment {
    private FloatingActionMenu fab;
    private ArrayList<CicloItem> cicloItens;
    private CicloItemAdapter cicloItemAdapter;
    private ListView lvItens;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ciclo, null);

        findAllViews(rootView);
        initAllViews();

        return rootView;
    }

    public void findAllViews(View rootView){
        fab = (FloatingActionMenu) getActivity().findViewById(R.id.fab);
        lvItens = rootView.findViewById(R.id.lvEstudos);
    }

    public void initAllViews() {
        cicloItens = new ArrayList<>();
        lvItens = new ListView(getContext());

        crieDados();
        //Buscar dados do banco via CicloItemDAO

        if (!cicloItens.isEmpty()) {
            ordenarLista(cicloItens);
            cicloItemAdapter = new CicloItemAdapter(getContext(), cicloItens);
            lvItens.setAdapter(cicloItemAdapter);
        }

        lvItens.setOnScrollListener(new ListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount){
                if (mLastFirstVisibleItem < firstVisibleItem) {
                    fab.hideMenuButton(true);
                }
                if (mLastFirstVisibleItem > firstVisibleItem) {
                    fab.showMenuButton(true);
                }
                mLastFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    public void crieDados() {
        cicloItens.add(new CicloItem(1, 1, 13, 45, 2, 25, 10, "Observação...", 1, 1, 1));
        cicloItens.add(new CicloItem(2, 2, 14, 00, 2, 15, 8, "Observação...", 2, 1, 1));
        cicloItens.add(new CicloItem(3, 3, 15, 00, 3, 20, 5, "Observação...", 3, 1, 1));
        cicloItens.add(new CicloItem(4, 4, 20, 20, 4, 10, 5, "Observação...", 4, 1, 1));
        cicloItens.add(new CicloItem(5, 5, 17, 00, 1, 30, 0, "Observação...", 5, 1, 1));
        cicloItens.add(new CicloItem(6, 6, 18, 00, 2, 15, 5, "Observação...", 6, 1, 1));
        cicloItens.add(new CicloItem(7, 7, 1, 00, 3, 10, 5, "Observação...", 7, 1, 1));
        cicloItens.add(new CicloItem(8, 1, 7, 30, 4, 15, 5, "Observação...", 2, 1, 1));
        cicloItens.add(new CicloItem(9, 2, 8, 10, 1, 25, 0, "Observação...", 1, 1, 1));
        cicloItens.get(0).setConcluido(true);
        cicloItens.get(7).setConcluido(true);
    }

    // ordena por dia, hora e minuto
    private static void ordenarLista(ArrayList<CicloItem> lista) {
        Collections.sort(lista, new Comparator<CicloItem>() {
            @Override
            public int compare(CicloItem o1, CicloItem o2) {
                return o1.getMinuto().compareTo(o2.getMinuto());
            }

        });
        Collections.sort(lista, new Comparator<CicloItem>() {
            @Override
            public int compare(CicloItem o1, CicloItem o2) {
                return o1.getHora().compareTo(o2.getHora());
            }

        });
        Collections.sort(lista, new Comparator<CicloItem>() {
            @Override
            public int compare(CicloItem o1, CicloItem o2) {
                return o1.getDiaDaSemana().compareTo(o2.getDiaDaSemana());
            }
        });
    }

    public void actionButton(View view){
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }
}
