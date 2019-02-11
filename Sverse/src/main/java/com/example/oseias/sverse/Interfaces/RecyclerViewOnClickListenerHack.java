package com.example.oseias.sverse.Interfaces;

import android.view.DragEvent;
import android.view.View;

public interface RecyclerViewOnClickListenerHack {
    public void onClickListener1(View view, int position);
    public void onClickListener2(View view, int position);
    public void onClickListener3(View view, int position);
    public void onLongClickListener1(View view, int position);
    public void onLongClickListener2(View view, int position);
    public void onLongClickListener3(View view, int position);
    public boolean onDragListener1(View view, DragEvent event);
    public boolean onDragListener2(View view, DragEvent event);
    public boolean onDragListener3(View view, DragEvent event);

}
