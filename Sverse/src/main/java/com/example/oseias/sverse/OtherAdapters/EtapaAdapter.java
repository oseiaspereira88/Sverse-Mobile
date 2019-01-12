package com.example.oseias.sverse.OtherAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.oseias.sverse.OthersClass.SubItemArea;

import java.util.ArrayList;

public class EtapaAdapter extends RecyclerView.Adapter<EtapaAdapter.MyViewHolder> {
    private Context ctx;
    private ArrayList<SubItemArea> etapas;
    private LayoutInflater myLayoutInflater;


    public EtapaAdapter(Context ctx, ArrayList<SubItemArea> etapas) {
        this.ctx = ctx;
        this.etapas = etapas;
        this.myLayoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titulo;
        public MyViewHolder(View itemView) {
            super(itemView);
            //titulo = itemView.findViewById()
        }
    }
}
