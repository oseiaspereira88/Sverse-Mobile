package com.example.oseias.sverse.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Oseias on 17/01/2018.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context ctx;
    private ArrayList<android.support.v7.widget.CardView> lista;

    public GridViewAdapter(Context ctx, ArrayList<android.support.v7.widget.CardView> lista){
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return lista.get(position);
    }
}
