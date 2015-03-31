package com.igorgarcia.terremotos.Fragmentos;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class settingsfragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private String EARTHQUAKE = "EARTHQUAKE";
    private ArrayList<EarthQuake> Terremotos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.userpreferences);

        //	Register	this	OnSharedPreferenceChangeListener
        SharedPreferences	prefs	=   PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //SharedPreferences	prefs	=   PreferenceManager.getDefaultSharedPreferences(getActivity());


        String Opcion1=getString(R.string.opcion1Key);
        String Opcion2=getString(R.string.opcion2Key);
        String Opcion3=getString(R.string.opcion3Key);


        if (key.equals(Opcion1)){
            //Auto refresh
            Log.d(EARTHQUAKE, "Hemos cambiado: " + key + key + " => " + sharedPreferences.getBoolean(getString(R.string.opcion1Key), true));
        } else if(key.equals(Opcion2)){
            //Tiempo de refresco
            Log.d(EARTHQUAKE, "Hemos cambiado: " + key + key +" => "+  sharedPreferences.getString( getString(R.string.opcion1Key),"0"));
        } else if(key.equals(Opcion3)){
            //Filtrar por magnitud, no hay que hacer nada, se guarda solo
            Log.d(EARTHQUAKE, "Hemos cambiado: " + key +" => "+  sharedPreferences.getString( getString(R.string.opcion3Key),"0"));

        }
    }
}
