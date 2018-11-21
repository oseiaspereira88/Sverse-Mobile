package com.example.oseias.sverse.OthersClass;

/**
 * Created by Oseias on 12/11/2017.
 */

import android.view.*;
import android.widget.ImageButton;

import java.util.Date;

public class Lembrete {
    private Date dataLembrete;
    private boolean isAtivo;

    public Date getDataLembrete() {
        return dataLembrete;
    }

    public void setDataLembrete(Date dataLembrete) {
        this.dataLembrete = dataLembrete;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }
}
