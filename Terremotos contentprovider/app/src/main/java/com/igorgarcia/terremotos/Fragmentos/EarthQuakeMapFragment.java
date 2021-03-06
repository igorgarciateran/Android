package com.igorgarcia.terremotos.Fragmentos;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;
import com.igorgarcia.terremotos.Fragmentos.Abstractas.AbstractMapFragment;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EarthQuakeMapFragment extends AbstractMapFragment implements GoogleMap.OnMapLoadedCallback {

    //private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleMap map;
    private List<EarthQuake> earthQuakes;

    private SharedPreferences prefs=null;
    private EarthQuake earthQuake;
    String EARTHQUAKE_KEY = "EARTHQUAKE_KEY";


    //private EarthQuakeDB earthQuakeDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = super.onCreateView(inflater, container, savedInstanceState);
            map = getMap();
            map.setOnMapLoadedCallback(this);

        //earthQuakeDB = new EarthQuakeDB(getActivity());


        return layout;

           // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detalle_terremoto, container, false);

    }


   /* public void setEartQuakes(List<EarthQuake> earthQuakes) {
        this.earthQuakes = earthQuakes;
    }*/


   /* @Override
    public void onMapLoaded() {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        LatLng point=new LatLng(0,0);


        for (EarthQuake earthquake: earthQuakes){

           //añadimos todos los terremotos que le pasamos en la lista

            //EarthQuake terremoto = earthquake.get(i);
             point = new LatLng(
                    earthquake.getCoords().getLng(),
                    earthquake.getCoords().getLat());

            MarkerOptions marker = new MarkerOptions();
            //marker.snippet(terremoto.getPlace());
            marker.position(point);
            marker.title(earthquake.getMagnitudString().concat(" ") + earthquake.getPlace());

            map.addMarker(marker);
            builder.include(marker.getPosition());
        }


        CameraPosition camPos	=	new	CameraPosition.Builder().target(point)
                .zoom(3)
                .tilt(0)
                .bearing(0)
                .build();

        CameraUpdate camUpd	=	CameraUpdateFactory.newCameraPosition(camPos);
        map.animateCamera(camUpd);



       *//* //Pone la camara centrada en todos los terremotos que hemos pintado
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 0);
        map.moveCamera(cu);*//*

    }
*/


    @Override
    protected void getData() {

        String id = getActivity().getIntent().getStringExtra(EARTHQUAKE_KEY);

        earthQuake = earthQuakeDB.ListadoXID(id).get(0);

    }

    @Override
    protected void showMap() {

        MarkerOptions marker = createMarker(earthQuake);


        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        getMap().addMarker(marker);

        CameraPosition camPos = new CameraPosition.Builder().target(marker.getPosition())
                .zoom(5)
                .build();

        CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);

        getMap().animateCamera(camUpd);




      /*  LatLngBounds.Builder builder = new LatLngBounds.Builder();
        LatLng point;

        point = new LatLng(
                    earthQuake.getCoords().getLng(),
                    earthQuake.getCoords().getLat());

            MarkerOptions marker = createMarker(earthQuake);
            //marker.snippet(terremoto.getPlace());
            marker.position(point);
            marker.title(earthQuake.getMagnitudString().concat(" ") + earthQuake.getPlace());

            map.addMarker(marker);
            builder.include(marker.getPosition());


        LatLngBounds bounds =builder .build();
        CameraUpdate cu=CameraUpdateFactory.newLatLngBounds(bounds,10);
        getMap().animateCamera(cu);
*/
    }
}
