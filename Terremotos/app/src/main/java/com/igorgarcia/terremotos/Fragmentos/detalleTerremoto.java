package com.igorgarcia.terremotos.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
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
public class detalleTerremoto extends MapFragment implements GoogleMap.OnMapLoadedCallback{

    String EARTHQUAKE_KEY = "EARTHQUAKE_KEY";
    private EarthQuakeDB earthQuakeDB;

    //private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private GoogleMap map;
    private List<EarthQuake> earthQuakes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        map=getMap();
        map.setOnMapLoadedCallback(this);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_terremoto, container, false);

    }


    public void setEartQuakes(List<EarthQuake> earthQuakes){
        this.earthQuakes = earthQuakes;
    }



    @Override
    public void onMapLoaded() {

        map=this.getMap();
        LatLngBounds.Builder builder= new LatLngBounds.Builder();
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);


        for ( int i=0; i< this.earthQuakes.size();i++) {
            //añadimos todos los terremotos que le pasamos en la lista

            EarthQuake terremoto=this.earthQuakes.get(i);
            LatLng point = new LatLng( terremoto.getCoords().getLng(),terremoto.getCoords().getLat()  );

            MarkerOptions marker = new MarkerOptions();
            //marker.snippet(terremoto.getPlace());
            marker.position(point);
            marker.title(terremoto.getMagnitudString().concat(" ")+ terremoto.getPlace());

            map.addMarker(marker);
            builder.include(marker.getPosition());
        }

        //Pone la camara centrada en todos los terremotos que hemos pintado
        LatLngBounds bounds =builder.build();
        CameraUpdate cu= CameraUpdateFactory.newLatLngBounds(bounds,0);
        map.moveCamera(cu);



    }



}
