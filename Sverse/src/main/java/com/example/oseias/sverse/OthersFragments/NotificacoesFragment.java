package com.example.oseias.sverse.OthersFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OtherAdapters.NotifiAdapter;
import com.versaplications.prodesenvelopment.sverse.R;
import com.example.oseias.sverse.SQLite.dao.NotifiDAO;
import com.example.oseias.sverse.SQLite.model.NotifiModel;

import java.util.ArrayList;

/**
 * Created by Oseias on 10/01/2018.
 */

public class NotificacoesFragment extends Fragment {
    GridView gridView;
    NotifiAdapter adapterNotifi;
    View rootView;
    NotifiDAO notifiDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notifi_fragment, null); //->container, false
        initializeViews(rootView);
        return rootView;
    }

    public void initializeViews(View rootView){
        //notifiDAO = new NotifiDAO(rootView.getContext());
        gridView = (GridView) rootView.findViewById(R.id.notifiGrid);
        adapterNotifi = new NotifiAdapter(rootView.getContext(),createData());
        gridView.setAdapter(adapterNotifi);
        this.rootView = rootView;
        YoYo.with(Techniques.FadeIn)
                .duration(1200)
                .repeat(0)
                .playOn(gridView);
    }

    private ArrayList<NotifiModel> createData() {
        ArrayList<NotifiModel> models = new ArrayList<>();
        models.add(new NotifiModel(1,
                "Sugestão de Apendizado",
                "Sempre atualise o status das suas pendencias!",
                1,
                "",
                "0"));
        models.add(new NotifiModel(1,
                "Sugestão de Insentivo",
                "Seu desempenho está exelente. Continue assim, aplicado!",
                1,
                "",
                "0"));
        models.add(new NotifiModel(1,
                "Dica do Dia",
                "Adicione, sempre que necessário, uma nova nota ao bloco de notas.",
                1,
                "",
                "0"));
        models.add(new NotifiModel(1,
                "Aviso",
                "! Voce tem atividades pendentes !",
                1,
                "",
                "0"));
        models.add(new NotifiModel(1,
                "Sugestão do Sistema",
                "Sempre atualise o status das suas pendencias!",
                1,
                "",
                "0"));
        models.add(new NotifiModel(1,
                "Sugestão de Insentivo",
                "! Você esta deixando acumular pendências. Organize melhor seus estudos e tire um tempo para as resoluções.",
                1,
                "",
                "0"));
        models.add(new NotifiModel(1,
                "Sugestão de Insentivo",
                "Suas notas em PI estão ficando baixas. dedique mais tempo a estudar!",
                1,
                "",
                "0"));
        models.add(new NotifiModel(1,
                "Sugestão de Apendizado",
                "Sempre atualise o status das suas pendencias!",
                1,
                "",
                "0"));
        models.add(new NotifiModel(1,
                "Sugestão de Apendizado",
                "Sempre atualise o status das suas pendencias!",
                1,
                "",
                "0"));




        return models;
    }
}
