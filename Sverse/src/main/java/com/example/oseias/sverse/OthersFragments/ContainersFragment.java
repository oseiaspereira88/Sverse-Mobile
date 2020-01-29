package com.example.oseias.sverse.OthersFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OtherAdapters.ContainerGridAdapter;
import com.example.oseias.sverse.OtherAdapters.ContainerListAdapter;
import com.example.oseias.sverse.OthersActivitys.GroupCreator;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;
import com.exemple.oseias.sverse.R;
import com.example.oseias.sverse.OtherAdapters.GrupAdapter;
import com.example.oseias.sverse.OtherAdapters.MateriaAdapter;
import com.example.oseias.sverse.SQLite.dao.ContainerDAO;

/**
 * Created by Oseias on 10/01/2018.
 */

public class ContainersFragment extends Fragment {

    private String listMode;
    private GridView gvContainers;
    private ListView lvContainers;
    private ConstraintLayout clContainers;
    private FloatingActionButton fab;
    private ContainerGridAdapter containerGridAdapter;
    private ContainerListAdapter containerListAdapter;
    private ContainerDAO containerDAO;
    private MateriaAdapter adapterMaterias;
    private GrupAdapter adapterEstudoEmGrupo;
    private View rootView;
    private ImageView imgListMode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grupos, null); //->container, false
        lerConfigs(rootView);
        initializeViews(rootView);
        return rootView;
    }

    public void initializeViews(View rootView) {
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        imgListMode = (ImageView) rootView.findViewById(R.id.imgListGrid);
        containerDAO = new ContainerDAO(getContext());
        //clContainers = (ConstraintLayout) rootView.findViewById(R.id.clCotainers);
        gvContainers = (GridView) rootView.findViewById(R.id.gvContainers);
        lvContainers = (ListView) rootView.findViewById(R.id.lvContainers);

        if(listMode.equals("list")){
            if(containerDAO.listarContainers() != null){
                containerListAdapter = new ContainerListAdapter(getActivity(), rootView.getContext(), containerDAO.listarContainers());
                lvContainers.setAdapter(containerListAdapter);
                lvContainers.setVisibility(ListView.VISIBLE);
                gvContainers.setVisibility(GridView.INVISIBLE);
                imgListMode.setImageResource(R.mipmap.ic_grid_mode);

            }

        } else if(listMode.equals("grid")){
            if(containerDAO.listarContainers() != null){
                containerGridAdapter = new ContainerGridAdapter(getActivity(), rootView.getContext(), containerDAO.listarContainers());
                gvContainers.setAdapter(containerGridAdapter);
                gvContainers.setVisibility(GridView.VISIBLE);
                lvContainers.setVisibility(ListView.INVISIBLE);
                imgListMode.setImageResource(R.mipmap.ic_list_mode);
            }
        }

        this.rootView = rootView;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrindo Criador de Containers
                Intent it = new Intent(getActivity(), GroupCreator.class);
                startActivity(it);
            }
        });

        imgListMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alternarListMode(v);
            }
        });

        /*YoYo.with(Techniques.FadeIn)
                .duration(1200)
                .repeat(0)
                .playOn(gridView);*/
    }

    public void lerConfigs(View rootView){
        listMode = "grid";
        ////////Ler configs do DB Local:
        ConfiguracaoDAO config = new ConfiguracaoDAO(getContext());
        if(config.buscarConfig(Configuracao.LAST_CONTAINERS_LIST_MODE).getvalorConfig() == 0){
            listMode = "grid";
        } else {
            listMode = "list";
        }
    }

    public void alternarListMode(View view){
        ImageView img = (ImageView) view;
        if(listMode == "list"){
            listMode = "grid";
            containerGridAdapter = new ContainerGridAdapter(getActivity(), rootView.getContext(), containerDAO.listarContainers());
            gvContainers.setAdapter(containerGridAdapter);
            gvContainers.setVisibility(GridView.VISIBLE);
            lvContainers.setVisibility(ListView.INVISIBLE);

            //salvando Config
            ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO(getContext());
            configuracaoDAO.atualizarConfig(new Configuracao(Configuracao.LAST_CONTAINERS_LIST_MODE, 0));

            YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(gvContainers);

            YoYo.with(Techniques.RotateIn)
                    .duration(800)
                    .repeat(0)
                    .playOn(view);
            img.setImageResource(R.mipmap.ic_list_mode);

        } else{
            listMode = "list";
            containerListAdapter = new ContainerListAdapter(getActivity(), rootView.getContext(), containerDAO.listarContainers());
            lvContainers.setAdapter(containerListAdapter);
            lvContainers.setVisibility(ListView.VISIBLE);
            gvContainers.setVisibility(GridView.INVISIBLE);

            //salvando Config
            ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO(getContext());
            configuracaoDAO.atualizarConfig( new Configuracao(Configuracao.LAST_CONTAINERS_LIST_MODE, 1));

            YoYo.with(Techniques.FadeIn)
                    .duration(1000)
                    .repeat(0)
                    .playOn(lvContainers);

            YoYo.with(Techniques.RotateIn)
                    .duration(800)
                    .repeat(0)
                    .playOn(view);
            img.setImageResource(R.mipmap.ic_grid_mode);

        }
    }
}
