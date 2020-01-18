package com.example.oseias.sverse.OthersFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Customizados.LockableScrollView;
import com.example.oseias.sverse.OtherAdapters.ItensPomodoroAdapter;
import com.example.oseias.sverse.OthersClass.MyCountDownTime;
import com.example.oseias.sverse.SQLite.model.CicloItem;
import com.exemple.oseias.sverse.R;

import java.util.ArrayList;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MuralFragment extends Fragment {
    View view;
    LockableScrollView scrollMural;
    TextView tvTime, tvInfoTime;
    ImageView bAlternar, bVincular, bPlay, bRestart, bMarcar;
    ListView lvItens, lvTimes;
    ProgressBar progBar;
    MyCountDownTime time;
    private boolean isTimesDown;
    private boolean isItensDown;

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

    @SuppressLint("WrongViewCast")
    private void inicializarVars() {
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvInfoTime = (TextView) view.findViewById(R.id.tvInfoTime);
        bAlternar = (ImageView) view.findViewById(R.id.bAlternar);
        bVincular = (ImageView) view.findViewById(R.id.bVincular);
        bPlay = (ImageView) view.findViewById(R.id.bPlay);
        bRestart = (ImageView) view.findViewById(R.id.bRestart);
        bMarcar = (ImageView) view.findViewById(R.id.bMarcar);
        progBar = (ProgressBar) view.findViewById(R.id.progressBar);
        scrollMural = (LockableScrollView) view.findViewById(R.id.scrollMural);
        lvItens = (ListView) view.findViewById(R.id.lvItens);
        lvTimes = (ListView) view.findViewById(R.id.lvTimes);

        bAlternar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abri uma lista de seleção de itens do ciclo de estudos ou 'avuço'
                if (lvItens.getVisibility() != View.VISIBLE || isItensDown){
                    lvItens.setVisibility(View.VISIBLE);
                    isItensDown = false;
                    scrollMural.setScrollingEnabled(false);
                    YoYo.with(Techniques.FadeInUp)
                            .duration(300)
                            .repeat(0)
                            .playOn(lvItens);
                    //setar o ListView e carregar os itens via adapter
                    ItensPomodoroAdapter itensAdapter = new ItensPomodoroAdapter(getActivity(), crieDados());
                    lvItens.setAdapter(itensAdapter);
                } else {
                    YoYo.with(Techniques.FadeOutDown)
                            .duration(300)
                            .repeat(0)
                            .playOn(lvItens);
                    isItensDown = true;
                    scrollMural.setScrollingEnabled(true);
                }
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
                //Aqui conterá a lógica de marcação das etapas do item em andamento;

                //Abri uma lista de times para seleção entre pomodoros e intervalos
                if (lvTimes.getVisibility() != View.VISIBLE || isTimesDown){
                    lvTimes.setVisibility(View.VISIBLE);
                    isTimesDown = false;
                    scrollMural.setScrollingEnabled(false);
                    YoYo.with(Techniques.FadeInUp)
                            .duration(300)
                            .repeat(0)
                            .playOn(lvTimes);

                } else {
                    scrollMural.setScrollingEnabled(true);
                    YoYo.with(Techniques.FadeOutDown)
                            .duration(300)
                            .repeat(0)
                            .playOn(lvTimes);
                    isTimesDown = true;
                }
            }
        });

    }

    public ArrayList<CicloItem> crieDados(){
        ArrayList<CicloItem> lista = new ArrayList<>();
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
        return lista;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.getMillisUntilFinished();
    }
}
