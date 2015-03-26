package com.igorgarcia.terremotos.Fragmentos;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.igorgarcia.terremotos.Model.Coordinate;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;

import com.igorgarcia.terremotos.Fragmentos.dummy.DummyContent;
import com.igorgarcia.terremotos.Tasks.DownloadEarthQuakeTask;
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


public class EarthQuakeFragment extends ListFragment implements DownloadEarthQuakeTask.AddEarthQuakeInterface {


    ArrayList<EarthQuake> earthQuakes ;
    String EARTHQUAKE = "EARTHQUAKE";
    private ArrayAdapter<EarthQuake> aa;
    private EarthquakeAdapter aa2;


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

        DownloadEarthQuakeTask task= new DownloadEarthQuakeTask(this);
        task.execute(getString(R.string.earth_quakes_url));
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
    public void AddEarthQuake(EarthQuake earthquake) {

        earthQuakes.add(0,earthquake);
        //aa.notifyDataSetChanged();

        aa2.notifyDataSetChanged();
    }

    @Override
    public void NotifyTotal(int Total) {
        String msg= getString(R.string.Mensaje,Total);
        Toast toast=  Toast.makeText( getActivity(),msg + Total, Toast.LENGTH_LONG);
    }
}
