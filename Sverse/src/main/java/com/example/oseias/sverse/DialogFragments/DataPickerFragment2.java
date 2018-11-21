package com.example.oseias.sverse.DialogFragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by IUser on 14/02/2018.
 */

public class DataPickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendario = Calendar.getInstance();


        return super.onCreateDialog(savedInstanceState);
    }
}
