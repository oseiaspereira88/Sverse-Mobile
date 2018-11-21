package com.example.oseias.sverse.OthersClass;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.CalendarView;

/**
 * Created by IUser on 14/02/2018.
 */

public class BuilderSelector extends AlertDialog.Builder {
    View view;

    @Override
    public AlertDialog.Builder setView(View view) {
        this.view = view;
        super.setView(view);
        return this;
    }

    public BuilderSelector(Context context) {
        super(context);
    }

    public BuilderSelector(Context context, int themeResId) {
        super(context, themeResId);
    }

    public CalendarView getViewCalendario(){

        return (CalendarView) view;
    }
}
