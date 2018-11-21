package com.example.oseias.sverse.DialogFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.CalendarView;

import com.example.oseias.sverse.OthersClass.BuilderSelector;

/**
 * Created by IUser on 14/02/2018.
 */

public class AlertDialogFragment extends DialogFragment {
    long l;
    BuilderSelector builder;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new BuilderSelector(getActivity());
        builder.setMessage("Qual o dia?")
                .setTitle("Escolha um dia")
                .setView(new CalendarView(getActivity()))
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                l = builder.getViewCalendario().getDate();
                            }
                        })
                .setNegativeButton("Cancelar Seleção",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                l = 0;
                                getDialog().cancel();
                            }
                        });

        return builder.create();
    }

    public long getLong(){
        return l;
    }
}
