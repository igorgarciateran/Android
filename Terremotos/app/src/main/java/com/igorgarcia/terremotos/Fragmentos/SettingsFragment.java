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
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private String EARTHQUAKE = "EARTHQUAKE";
    private ArrayList<EarthQuake> Terremotos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.userpreferences);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //SharedPreferences	prefs	=   PreferenceManager.getDefaultSharedPreferences(getActivity());


    }
}
