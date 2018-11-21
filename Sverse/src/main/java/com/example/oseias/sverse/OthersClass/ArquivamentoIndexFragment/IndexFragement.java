package com.example.oseias.sverse.OthersClass.ArquivamentoIndexFragment;

import android.content.Context;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IUser on 19/02/2018.
 */

public class IndexFragement {
    private static final String INDEX_FRAGEMENT_FILE = "indexFragmentFile.bup";
    private Context ctx;

    public IndexFragement(Context ctx) {
        this.ctx = ctx;
    }


    public void arquivarIdCurrentFragment(int id){

        if(new File(ctx.getFilesDir() + "/" + (INDEX_FRAGEMENT_FILE)).exists()) {
            new File(ctx.getFilesDir() + "/" + (INDEX_FRAGEMENT_FILE)).delete();
        }

        String file = "" + id;

            FileOutputStream fos = null;
            try {
                fos = ctx.openFileOutput((INDEX_FRAGEMENT_FILE), ctx.MODE_PRIVATE);
                fos.write(file.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public String lerIdCurrentFragment(){
        String backupId = "";

        if(new File(ctx.getFilesDir() + "/" + (INDEX_FRAGEMENT_FILE)).exists()) {
            FileInputStream fis = null;
            try {
                fis = ctx.openFileInput(INDEX_FRAGEMENT_FILE);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String text;

                while ((text = br.readLine()) != null) {
                    sb.append(text);
                }
                backupId = sb.toString();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return backupId;
    }

    public void deleteFileIdFragment(){
        if(new File(ctx.getFilesDir() + "/" + (INDEX_FRAGEMENT_FILE)).exists()) {
            new File(ctx.getFilesDir() + "/" + (INDEX_FRAGEMENT_FILE)).delete();
        }
    }


}
