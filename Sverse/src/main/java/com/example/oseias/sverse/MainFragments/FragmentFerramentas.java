package com.example.oseias.sverse.MainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OthersActivitys.AreaDeTrabalhoEmGrupo;
import com.versaplications.prodesenvelopment.sverse.R;

/**
 * Created by Oseias on 10/01/2018.
 */

public class FragmentFerramentas extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ferramentas, container, false);
        Button button = (Button) view.findViewById(R.id.bTrabalhoEmGrupo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTrabalhosEmGrupo(v);
            }
        });
        return view;
    }

    public void abrirTrabalhosEmGrupo(View view){
        YoYo.with(Techniques.Wave)
                .duration(700)
                .repeat(0)
                .playOn(view);

        Toast.makeText(getContext(), "Abrindo Area de Trabalho em Grupo...", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(getContext(), AreaDeTrabalhoEmGrupo.class);
        startActivity(it);
    }
}
