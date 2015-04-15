package com.igorgarcia.terremotos.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.igorgarcia.terremotos.Alarmas.Alarma;
import com.igorgarcia.terremotos.Fragmentos.SettingsFragment;
import com.igorgarcia.terremotos.R;

/**
 * Created by cursomovil on 26/03/15.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
//saca las preferencias

    private String EARTHQUAKE = "EARTHQUAKE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //	Register	this	OnSharedPreferenceChangeListener
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);


       // addPreferencesFromResource(R.xml.userpreferences);
        //cambiamos esta linea por estas, porque esta deprecated addPreferencesFromResource

        getFragmentManager()
            .beginTransaction()
            .replace(android.R.id.content, new SettingsFragment())
            .commit();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //	TODO	Check	the	shared	preference	and	key	parameters
        //	and	change	UI	or	behavior	as	appropriate.


        try {
            String Opcion1 = getString(R.string.opcion1Key);
            String Opcion2 = getString(R.string.opcion2Key);
            String Opcion3 = getString(R.string.opcion3Key);

            if (key.equals(Opcion1)) {
                //Auto refresh
                Log.d(EARTHQUAKE, "Hemos cambiado: "  + key + " => " + sharedPreferences.getBoolean(Opcion1, true));

                //Pongo la alarma para actualizacion automatica
                ActualizacionAutomatica();

            } else if (key.equals(Opcion2)) {
                //Tiempo de refresco
                Log.d(EARTHQUAKE, "Hemos cambiado: "  + key + " => " + sharedPreferences.getString(Opcion2, "0"));

                ActualizacionAutomatica();
            } else if (key.equals(Opcion3)) {
                //Filtrar por magnitud, no hay que hacer nada, se guarda solo
                Log.d(EARTHQUAKE, "Hemos cambiado: " + key + " => " + sharedPreferences.getString(Opcion3, "0"));

            }

        } catch (Exception e) {
            Log.d(EARTHQUAKE, e.getMessage());
        }

    }



    private void ActualizacionAutomatica(){

        //	Register	this	OnSharedPreferenceChangeListener
        SharedPreferences prefs	=   PreferenceManager.getDefaultSharedPreferences(this);

        // si actualizacion automatica
        if(prefs.getBoolean(getString(R.string.opcion1Key), true)){

            int tiempo=Integer.parseInt(prefs.getString(getString(R.string.opcion2Key), "0"))   ;
            Alarma.setRepeatingAlarm(this, tiempo, true);

        }else{

            //si no actualizacion automatica
            Alarma.desactivarAlarma(this);

        }
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Log.d(EARTHQUAKE,"Elemento pulsado");


        Intent detalle = new Intent (this,DetailActivity.class);
        startActivity(detalle);
    }
}
