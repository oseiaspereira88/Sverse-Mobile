package com.example.oseias.sverse.OtherAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OthersClass.ItemArea;
import com.exemple.oseias.sverse.R;
import java.util.ArrayList;

public class ItensAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<ItemArea> itens;
    boolean isLongeClick;

    public ItensAdapter(Context ctx, ArrayList<ItemArea> itens) {
        this.ctx = ctx;
        this.itens = itens;
        isLongeClick = false;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = LayoutInflater.from(ctx).inflate(R.layout.ciclo_item_model, null);

        //TextView tvItemNumber = (TextView) view.findViewById(R.id.tvItemNumber);
        //tvItemNumber.setText(i+1);
        final View finalView = view.findViewById(R.id.imgItem);
        int finalI = i;
        finalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    YoYo.with(Techniques.Landing)
                            .duration(700)
                            .repeat(0)
                            .playOn(finalView);
                    /*Intent it = new Intent(ctx, NotaCreator.class);
                    Bundle b = new Bundle();
                    b.putInt("id", finalI +1);
                    it.putExtra("id", b);
                    ctx.startActivity(it);*/
                }
            }
        });

        finalView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLongeClick = true;
                YoYo.with(Techniques.Wave)
                        .duration(700)
                        .repeat(0)
                        .playOn(finalView);
                return false;
            }
        });

        return view;
    }

    public void actionButton(View view){
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }
}
