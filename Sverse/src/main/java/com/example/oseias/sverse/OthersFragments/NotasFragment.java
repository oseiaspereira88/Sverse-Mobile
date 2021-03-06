package com.example.oseias.sverse.OthersFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OtherAdapters.BlocoDeNotasAdapter;
import com.example.oseias.sverse.OthersActivitys.NotaCreator;
import com.exemple.oseias.sverse.R;
import com.example.oseias.sverse.SQLite.dao.NotaDAO;
import com.example.oseias.sverse.SQLite.model.NotaModel;

import java.util.ArrayList;

/**
 * Created by Oseias on 10/01/2018.
 */

public class NotasFragment extends Fragment {
    GridView gridView;
    BlocoDeNotasAdapter adapterNotas;
    View rootView;
    NotaDAO notaDAO;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bloco_de_notas, null); //->container, false
        initializeViews(rootView);
        return rootView;
    }

    public void initializeViews(View rootView){
        notaDAO = new NotaDAO(rootView.getContext());
        gridView = (GridView) rootView.findViewById(R.id.grid_de_notas);
        this.rootView = rootView;
        atualizarGridView();
        YoYo.with(Techniques.FadeIn)
                .duration(1200)
                .repeat(0)
                .playOn(gridView);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Abrindo Criador de Notas...", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getContext(), NotaCreator.class);
                startActivity(it);
                getActivity().finish();
            }
        });
    }

    public void atualizarGridView(){
        adapterNotas = new BlocoDeNotasAdapter(getActivity(), createContainersData());
        gridView.setAdapter(adapterNotas);
    }

    private ArrayList createContainersData()
    {
        ArrayList<NotaModel> notas = new ArrayList<>();
        notas = notaDAO.returnAllNotas();
        return notas;
    }
}