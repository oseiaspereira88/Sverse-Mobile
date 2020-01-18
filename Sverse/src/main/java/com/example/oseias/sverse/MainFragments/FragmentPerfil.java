package com.example.oseias.sverse.MainFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oseias.sverse.SQLite.dao.UsuarioDAO;
import com.example.oseias.sverse.SQLite.model.Usuario;
import com.exemple.oseias.sverse.R;
/**
 * Created by Oseias on 10/01/2018.
 */

public class FragmentPerfil extends Fragment {
    UsuarioDAO userDAO;
    Usuario user;

    ListView lv;
    ConstraintLayout clPerfilConfig, clPerfilAtributes;
    TextView tvExplorar, tvAmigos, tvSolicitacoes;

    //cabeçalho
    ImageView imgBG, imgPerfil, imgCamera, imgConfig;
    TextView tvNomePerfil, tvEmail;

    //clPerfilAtributesShow
    EditText bioShow, cursoShow, hobbiesShow, trelloShow, githubShow;

    //clPerfilConfig
    EditText editNome, editBio, editCurso, editHobbies, editTrello, editGitgub, editUsername, editSenha;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        getUsuarioLogado();
        procurarViews(view);
        setValues();
        setOnClickButtons();
        return view;
    }

    private void getUsuarioLogado() {
        userDAO = new UsuarioDAO(getContext());
        user = userDAO.listarUsuarios().get(0);
    }

    private void procurarViews(View view) {
        lv = (ListView) view.findViewById(R.id.lvConteudo);
        clPerfilConfig = (ConstraintLayout) view.findViewById(R.id.clPerfilEdit);
        clPerfilAtributes = (ConstraintLayout) view.findViewById(R.id.clPerfilEdit);
        tvExplorar = (TextView) view.findViewById(R.id.tvExplorar);
        tvAmigos = (TextView) view.findViewById(R.id.tvAmigos);
        tvSolicitacoes = (TextView) view.findViewById(R.id.tvSolicitacoes);

        //cabeçalho

        imgBG = (ImageView) view.findViewById(R.id.imgBG);
        imgPerfil = (ImageView) view.findViewById(R.id.imgPerfil);
        imgCamera = (ImageView) view.findViewById(R.id.imgCamera);
        imgConfig = (ImageView) view.findViewById(R.id.imgExcluir);
        tvNomePerfil = (TextView) view.findViewById(R.id.tvNomePerfil);
        tvEmail = (TextView) view.findViewById(R.id.tvEmail);

        //clPerfilAtributesShow

        bioShow = (EditText) view.findViewById(R.id.editBioShow);
        cursoShow = (EditText) view.findViewById(R.id.editCursoShow);
        hobbiesShow = (EditText) view.findViewById(R.id.editHobbiesShow);
        trelloShow = (EditText) view.findViewById(R.id.editTrelloShow);
        githubShow = (EditText) view.findViewById(R.id.editGithubShow);

        //clPerfilConfig

        editNome = (EditText) view.findViewById(R.id.editNome);
        editBio = (EditText) view.findViewById(R.id.editBio);
        editCurso = (EditText) view.findViewById(R.id.editCurso);
        editHobbies = (EditText) view.findViewById(R.id.editHobbies);
        editTrello = (EditText) view.findViewById(R.id.editTrello);
        editGitgub = (EditText) view.findViewById(R.id.editGithub);
        editUsername = (EditText) view.findViewById(R.id.editUsername);
        editSenha = (EditText) view.findViewById(R.id.editPassword);
    }

    private void setValues() {
        //imgBG
        //imgPerfil
        tvNomePerfil.setText(user.getNome());
        //tvEmail
        //bioShow
        //cursoShow
        //hobbiesShow
        //trelloShow
        //githubShow
        editNome.setText(user.getNome());
        //editBio
        //editCurso
        //editHobbies
        //editTrello
        //editGitgub
        editUsername.setText(user.getLogin());
        editSenha.setText(user.getSenha());
    }

    private void setOnClickButtons() {
        tvExplorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set os buttons de explorar e o input de pesquisa como visiveis;
                //setAdapter uma lista de grupos ou pessoas;
            }
        });

        tvAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set o input de pesquisa como visivel;
                //set os buttons de explorar como invisiveis;
                //setAdapter uma lista de grupos ou pessoas;
            }
        });

        tvSolicitacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set os buttons de explorar e o input de pesquisa como visiveis;
                //setAdapter uma lista de grupos ou pessoas;
            }
        });

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abra seletor de Imagem na Galeria;
            }
        });

        imgConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Alterna entre as ConstraintLayouts (PerfilShow e PerfilEdite) setando a visibilidade;
            }
        });
    }
}
