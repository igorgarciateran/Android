package com.igorgarcia.terremotos.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import com.igorgarcia.terremotos.Fragmentos.settingsfragment;
import com.igorgarcia.terremotos.R;

import java.util.List;

/**
 * Created by cursomovil on 26/03/15.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // addPreferencesFromResource(R.xml.userpreferences);
        //cambiamos esta linea porestas, porque esta deprecated addPreferencesFromResource

        getFragmentManager()
            .beginTransaction()
            .replace(android.R.id.content, new settingsfragment())
            .commit();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //	TODO	Check	the	shared	preference	and	key	parameters
        //	and	change	UI	or	behavior	as	appropriate.




    }
}
