package com.igorgarcia.terremotos.Fragmentos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ListFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.igorgarcia.terremotos.BD.EarthQuakeDB;
import com.igorgarcia.terremotos.Fragmentos.Abstractas.AbstractMapFragment;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R ;

import com.igorgarcia.terremotos.Tasks.DownloadEarthQuakeTask;
import com.igorgarcia.terremotos.activities.DetailActivity;
import com.igorgarcia.terremotos.adapter.EarthquakeAdapter;
import com.igorgarcia.terremotos.servicios.DownloadEarthQuakeService;

import java.util.ArrayList;
import java.util.List;


public class EarthQuakeListFragment extends ListFragment {

    private List<EarthQuake> earthQuakes ;

    String EARTHQUAKE = "EARTHQUAKE";
    String EARTHQUAKE_KEY = "EARTHQUAKE_KEY";

    // private ArrayAdapter<EarthQuake> aa;
    private EarthquakeAdapter aa2;

    private SharedPreferences prefs=null;
    private EarthQuakeDB earthQuakeDB;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EarthQuakeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        earthQuakes = new ArrayList<EarthQuake>();
        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());
        earthQuakeDB = new EarthQuakeDB(getActivity());


        setHasOptionsMenu(true);

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
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String Opcion3 = getString(R.string.opcion3Key);
        String magnitud =prefs.getString(Opcion3, "0");

        //earthQuakeDB = new EarthQuakeDB(getActivity());

        earthQuakes=earthQuakeDB.listadoXMagnitud(Integer.parseInt(magnitud) );

        aa2=new EarthquakeAdapter(getActivity(),R.layout.earthquake_list_row,earthQuakes);
        setListAdapter(aa2);
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();

        earthQuakes = new ArrayList<EarthQuake>();

        int minMag = Integer.parseInt(prefs.getString(getString( R.string.opcion3Key), "0"));

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

        Intent nuevoIntent= new Intent (getActivity(),DetailActivity.class);
        nuevoIntent.putExtra(EARTHQUAKE_KEY,earthQuake.get_id());
        startActivity(nuevoIntent);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        //para crear menus relacionados con el fragmento

        inflater.inflate(R.menu.menurefresh,menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.action_refresh){

            Intent download= new Intent (getActivity(),DownloadEarthQuakeService.class);
           getActivity().startService(download);
        }

        return super.onOptionsItemSelected(item);
    }






}
