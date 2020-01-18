package com.example.oseias.sverse.OthersFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import com.example.oseias.sverse.OtherAdapters.CicloAdapter;
import com.example.oseias.sverse.SQLite.dao.CicloDAO;
import com.example.oseias.sverse.SQLite.dao.EstudoDAO;
import com.example.oseias.sverse.SQLite.model.Ciclo;
import com.example.oseias.sverse.SQLite.model.CicloItem;
import com.exemple.oseias.sverse.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CicloFragment extends Fragment {
    ArrayList<CicloItem> lista;
    EstudoDAO estudoDAO;
    private FloatingActionMenu fab;
    CicloAdapter cicloAdapter;
    CicloDAO cicloDAO;
    ListView lv;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ciclo, null);
        initializeViews(container, rootView);
        return rootView;
    }

    public void initializeViews(ViewGroup container, View rootView) {
        cicloDAO = new CicloDAO(getActivity());
        lista = new ArrayList<>();
        lv = new ListView(getContext());
        lv = rootView.findViewById(R.id.lvEstudos);
        crieDados();
        ordenarLista(lista);
        cicloAdapter = new CicloAdapter(getContext(), lista);
        lv.setAdapter(cicloAdapter);
        findFabs();

        lv.setOnScrollListener(new ListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(mLastFirstVisibleItem<firstVisibleItem)
                {
                    fab.hideMenuButton(true);
                }
                if(mLastFirstVisibleItem>firstVisibleItem)
                {
                    fab.showMenuButton(true);
                }
                mLastFirstVisibleItem=firstVisibleItem;

            }
        });
    }

    public void findFabs(){
        fab = (FloatingActionMenu) getActivity().findViewById(R.id.fab);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                //Toast.makeText(AreaDeTrabalhoEmGrupo.this, "Is menu Opened: " + (opened? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) getActivity().findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) getActivity().findViewById(R.id.fab2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Receber input do usuario para criar novo ciclo
                openInputDialogCreator();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adicionar Participantes
            }
        });
    }

    public void crieDados(){
        lista.add(new CicloItem(1, 1, 13, 45, 2, 25, 10, "Observação...", 1, 1, 1));
        lista.add(new CicloItem(2, 2, 14, 00, 2, 15, 8, "Observação...", 2, 1, 1));
        lista.add(new CicloItem(3, 3, 15, 00, 3, 20, 5, "Observação...", 3, 1, 1));
        lista.add(new CicloItem(4, 4, 20, 20, 4, 10, 5, "Observação...", 4, 1, 1));
        lista.add(new CicloItem(5, 5, 17, 00, 1, 30, 0, "Observação...", 5, 1, 1));
        lista.add(new CicloItem(6, 6, 18, 00, 2, 15, 5, "Observação...", 6, 1, 1));
        lista.add(new CicloItem(7, 7, 1, 00, 3, 10, 5, "Observação...", 7, 1, 1));
        lista.add(new CicloItem(8, 1, 7, 30, 4, 15, 5, "Observação...", 2, 1, 1));
        lista.add(new CicloItem(9, 2, 8, 10, 1, 25, 0, "Observação...", 1, 1, 1));
        lista.get(0).setConcluido(true);
        lista.get(7).setConcluido(true);
    }

    // Para ordenar por dia, hora e minuto
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

    public void openInputDialogCreator(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Qual será o titulo do ciclo?");

        // Set up the input
        final EditText input = new EditText(getActivity());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Criando novo ciclo:
                cicloDAO.salvarCiclo(new Ciclo(input.getText().toString(), 1));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
