package com.example.oseias.sverse.OthersFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.oseias.sverse.OthersClass.MyCountDownTime;
import com.exemple.oseias.sverse.R;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MuralFragment extends Fragment {
    View view;
    TextView tvTime, tvInfoTime;
    ImageView bAlternar, bVincular, bPlay, bRestart, bMarcar;
    ProgressBar progBar;
    MyCountDownTime time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mural, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        inicializarVars();
    }

    private void inicializarVars() {
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvInfoTime = (TextView) view.findViewById(R.id.tvInfoTime);
        bAlternar = (ImageView) view.findViewById(R.id.bAlternar);
        bVincular = (ImageView) view.findViewById(R.id.bVincular);
        bPlay = (ImageView) view.findViewById(R.id.bPlay);
        bRestart = (ImageView) view.findViewById(R.id.bRestart);
        bMarcar = (ImageView) view.findViewById(R.id.bMarcar);
        progBar = (ProgressBar) view.findViewById(R.id.progressBar);

        bAlternar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abri uma lista de seleção de itens do ciclo de estudos os 'avuço'
            }
        });
        bVincular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abri uma Lista de seleção de outros estudos ou afazeres que não estão no ciclo de estudos
                // e vincula ao tempo de estudo
            }
        });

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time==null) {
                    time = new MyCountDownTime(getContext(), tvTime, 15*60*1000, 1000, progBar);
                } else {
                    time.cancel();
                }
                progBar.setMax(15*60);
                time.start();
            }
        });
        bRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time!=null){
                    time.cancel();
                    tvTime.setText("00:00");
                }
            }
        });
        bMarcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Marca como item estudado na sequência correta do ciclo de estudos;
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.getMillisUntilFinished();

    }
}
