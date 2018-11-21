package com.example.oseias.sverse.OthersFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.versaplications.prodesenvelopment.sverse.R;

/**
 * Created by Oseias on 10/01/2018.
 */

public class FragmentFerramentas extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ferramentas, container, false);
    }
}
