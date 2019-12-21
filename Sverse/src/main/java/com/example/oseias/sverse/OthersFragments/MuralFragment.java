package com.example.oseias.sverse.OthersFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oseias.sverse.OthersClass.MyCountDownTime;
import com.exemple.oseias.sverse.R;

/**
 * Created by Oseias on 10/01/2018.
 */

public class MuralFragment extends Fragment {
    View container;
    TextView tvTime, tvInfoTime;
    ImageView bPlay;
    MyCountDownTime time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.container = container;
        return inflater.inflate(R.layout.mural_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        inicializarVars();
    }

    private void inicializarVars() {
        time = new MyCountDownTime(container.getContext(), tvTime, 15*60*1000, 1000);
        tvTime = (TextView) container.findViewById(R.id.tvTime);
        tvInfoTime = (TextView) container.findViewById(R.id.tvInfoTime);
        bPlay = (ImageView) container.findViewById(R.id.bPlay);

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.start();
            }
        });
    }
}
