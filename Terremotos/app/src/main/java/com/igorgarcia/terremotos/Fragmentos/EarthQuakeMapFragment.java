package com.igorgarcia.terremotos.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.igorgarcia.terremotos.BD.EarthQuakeDB;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EarthQuakeMapFragment extends MapFragment implements GoogleMap.OnMapLoadedCallback {

    //private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private GoogleMap map;
    private List<EarthQuake> earthQuakes;

    private EarthQuakeDB earthQuakeDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = super.onCreateView(inflater, container, savedInstanceState);
            map = getMap();
            map.setOnMapLoadedCallback(this);

        earthQuakeDB = new EarthQuakeDB(getActivity());

        return layout;

           // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detalle_terremoto, container, false);

    }


    public void setEartQuakes(List<EarthQuake> earthQuakes) {
        this.earthQuakes = earthQuakes;
    }


    @Override
    public void onMapLoaded() {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        LatLng point=new LatLng(0,0);

        if (earthQuakes.size()==0){
            cojeTerremotos();
        }

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



       /* //Pone la camara centrada en todos los terremotos que hemos pintado
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 0);
        map.moveCamera(cu);*/

    }


    public void cojeTerremotos (){

      this.setEartQuakes( earthQuakeDB.listado());
    }


}
