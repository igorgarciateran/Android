package com.igorgarcia.terremotos.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.igorgarcia.terremotos.BD.EarthQuakeDB;
import com.igorgarcia.terremotos.Fragmentos.EarthQuakeMapFragment;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends Activity {

    private String EARTHQUAKE = "EARTHQUAKE";
    String EARTHQUAKE_KEY = "EARTHQUAKE_KEY";
    private EarthQuakeDB earthQuakeDB;
    private EarthQuakeMapFragment mapFragment;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //cogemos el enlace al mapa
        mapFragment =(EarthQuakeMapFragment)getFragmentManager().findFragmentById(R.id.map_fragment);


        earthQuakeDB = new EarthQuakeDB(getApplicationContext());
//        private Intent nuevoIntent = getIntent();
//        private String id = nuevoIntent.getStringExtra(EARTHQUAKE_KEY);
//        private List<EarthQuake> resultados;
//        private  TextView TxtMagnitud = (TextView) findViewById(R.id.txtMagnitud);
//        private TextView TxtTexto = (TextView) findViewById(R.id.txtTexto);
//        private  TextView TxtEnlace = (TextView) findViewById(R.id.txtEnlace);
        //TextView  mMap = (TextView) findViewById(R.id.mapa);
            sacarDatos();

    }



    private void sacarDatos(){

         Intent nuevoIntent = getIntent();
         String id = nuevoIntent.getStringExtra(EARTHQUAKE_KEY);
         List<EarthQuake> resultados;
          TextView TxtMagnitud = (TextView) findViewById(R.id.txtMagnitud);
         TextView TxtTexto = (TextView) findViewById(R.id.txtTexto);
          TextView TxtEnlace = (TextView) findViewById(R.id.txtEnlace);

        resultados = earthQuakeDB.ListadoXID(id);
        if (resultados.size() > 0) {
            EarthQuake terre = resultados.get(0);
            // setUpMapIfNeeded(terre);
            try {
                TxtMagnitud.setText(terre.getMagnitudString());
                TxtTexto.setText(terre.getPlace());
                TxtEnlace.setText(terre.getPlace());

                mapFragment.setEartQuakes(resultados);

            } catch (Exception ex) {
                Log.d(EARTHQUAKE, ex.getMessage());
            }

        } else {
            Log.d(EARTHQUAKE, "Error, no hay resultados de la BD");
        }

    }



    private void showMap(EarthQuake earthQuake) {
        //muestra un punto en el mapa con la funcion que hemos creado en el fragmento, reutilizable
        List<EarthQuake> earthQuakes = new ArrayList<EarthQuake>();
        earthQuakes.add(earthQuake);

        //mapFragment.pintarTerremotos(earthQuakes);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    private void setUpMapIfNeeded(EarthQuake terremoto) {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapa))
//                    .getMap();
//            // Check if we were successful in obtaining the map.
//            if (mMap != null) {
//                setUpMap(terremoto);
//            }
//        }
//    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
//    private void setUpMap(EarthQuake terremoto) {
//
//        mMap.addMarker(new MarkerOptions().position(new LatLng(terremoto.getCoords().getLng(), terremoto.getCoords().getLat())).title("Marker"));
//    }


}
