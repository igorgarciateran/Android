package com.igorgarcia.terremotos.Fragmentos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.igorgarcia.terremotos.Fragmentos.Abstractas.AbstractMapFragment;
import com.igorgarcia.terremotos.Model.EarthQuake;


import java.util.List;

/**
 * Created by cursomovil on 13/04/15.
 */
public class EarthQuakesListMapFragment extends AbstractMapFragment {

    private SharedPreferences prefs;
    private List<EarthQuake> earthQuakes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setHasOptionsMenu(true);

    }


    @Override
    public void onResume() {
        super.onResume();

        getMap().setOnMapLoadedCallback(this);
    }

    @Override
    protected void getData() {

        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());
        //int minMap =2; Integer.parseInt(prefs.getString(R.string.opcion3Key,0));

        String Opcion3 = getString(R.string.opcion3Key);
        String magnitud =prefs.getString(Opcion3, "0");

        earthQuakes= earthQuakeDB.listadoXMagnitud(Integer.parseInt(magnitud));

    }

    @Override
    protected void showMap() {
        getMap().clear();

        //TODO poner otro preferencia con el tipo de visualizacion
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        LatLng point;

        for (EarthQuake earthQuake: earthQuakes) {

           MarkerOptions marker = createMarker(earthQuake);

            /*MarkerOptions marker = new LatLng(
                    earthQuake.getCoords().getLng(),
                    earthQuake.getCoords().getLat());
*/
            getMap().addMarker(marker);
            builder.include(marker.getPosition());
        }

        LatLngBounds bounds = builder.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 10);

        getMap().animateCamera(cu);

    }
}
