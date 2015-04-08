package com.igorgarcia.terremotos.Fragmentos;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ListFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.igorgarcia.terremotos.BD.EarthQuakeDB;
import com.igorgarcia.terremotos.Model.Coordinate;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R ;

import com.igorgarcia.terremotos.Fragmentos.dummy.DummyContent;
import com.igorgarcia.terremotos.Tasks.DownloadEarthQuakeTask;
import com.igorgarcia.terremotos.activities.MainActivity;
import com.igorgarcia.terremotos.adapter.EarthquakeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class EarthQuakeFragment extends ListFragment {

    private List<EarthQuake> earthQuakes ;

    String EARTHQUAKE = "EARTHQUAKE";
    String EARTHQUAKE_KEY = "EARTHQUAKE_KEY";

    // private ArrayAdapter<EarthQuake> aa;
    private EarthquakeAdapter aa2;

    private SharedPreferences Prefs=null;
    private EarthQuakeDB earthQuakeDB;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EarthQuakeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        earthQuakes = new ArrayList<EarthQuake>();

        Prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        earthQuakeDB = new EarthQuakeDB(getActivity());

        //downloadEarthQuakes();


        //por las 2 lineas de abajo
        /*DownloadEarthQuakeTask task= new DownloadEarthQuakeTask(this);
        task.execute(getString(R.string.earth_quakes_url));*/
        //ponemos esta linea de parte de lo de abajo

       /*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                updateEarthQuake();
            }
        });
        t.start(); */
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=super.onCreateView(inflater,container,savedInstanceState);

       // aa=new ArrayAdapter<EarthQuake>( getActivity(), android.R.layout.simple_list_item_1,earthQuakes);
       // setListAdapter(aa);

        aa2=new EarthquakeAdapter(getActivity(),R.layout.layoutterremoto,earthQuakes);
        setListAdapter(aa2);

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();


        earthQuakes = new ArrayList<EarthQuake>();


        int minMag = Integer.parseInt(Prefs.getString(getString( R.string.opcion3Key), "0"));

        earthQuakes.clear();
        earthQuakes.addAll(earthQuakeDB.listadoXMagnitud(minMag));

        aa2.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        EarthQuake earthQuake = earthQuakes.get(position);

        //para abrir los datos de un terremoto cuando se pulsa sobre el
       // Intent detailIntent = new Intent(getActivity(),Detail.class);

        Intent nuevoIntent= new Intent (getActivity(),detalleTerremoto.class);
        nuevoIntent.putExtra(EARTHQUAKE_KEY,earthQuake.get_id());
        startActivity(nuevoIntent);
    }


}
