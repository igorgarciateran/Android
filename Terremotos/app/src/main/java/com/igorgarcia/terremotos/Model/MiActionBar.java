package com.igorgarcia.terremotos.Model;

import android.app.ActionBar;

/**
 * Created by cursomovil on 10/04/15.
 */
public class MiActionBar {

    ActionBar miactionBar;


    public void enlazar ( ActionBar actionBar){
        miactionBar=actionBar;
    }

    public void Titulo (String titulo){
        miactionBar.setTitle(titulo);
    }

    public void SubTitulo (String subTitulo){
        miactionBar.setTitle(subTitulo);
    }






}
