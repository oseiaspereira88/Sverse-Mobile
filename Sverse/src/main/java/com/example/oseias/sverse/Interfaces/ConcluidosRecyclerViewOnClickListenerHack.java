package com.example.oseias.sverse.Interfaces;

import android.view.DragEvent;
import android.view.View;

public interface ConcluidosRecyclerViewOnClickListenerHack {
    public void onClickListener(View view, int position);
    public void onLongClickListener(View view, int position);
    public boolean onDragListener(View view, DragEvent event);
}
