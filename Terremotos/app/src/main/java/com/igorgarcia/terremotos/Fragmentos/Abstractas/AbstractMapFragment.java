package com.igorgarcia.terremotos.Fragmentos.Abstractas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.igorgarcia.terremotos.BD.EarthQuakeDB;
import com.igorgarcia.terremotos.Model.EarthQuake;

import java.util.List;

/**
 * Created by cursomovil on 13/04/15.
 */
public abstract class AbstractMapFragment extends MapFragment implements GoogleMap.OnMapLoadedCallback{

    protected EarthQuakeDB earthQuakeDB;
    protected List<EarthQuake> earthQuakes ;

    //protected GoogleMap map;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        earthQuakeDB = new EarthQuakeDB(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout= super.onCreateView(inflater, container, savedInstanceState);
        
        getMap().setOnMapLoadedCallback(this);
        
        return layout;
    }
 

    @Override
    public void onMapLoaded() {
        this.getData();
        this.showMap();
    }

    abstract protected void getData();
    abstract protected void showMap();
    
    
    
    
    
/*

    protected void cojeTerremotos (){

        //earthQuakeDB = new EarthQuakeDB(getActivity());

        this.setEartQuakes( earthQuakeDB.listado());
    }


    public void setEartQuakes(List<EarthQuake> earthQuakes) {
        this.earthQuakes = earthQuakes;
    }


    protected void pintaPunto(EarthQuake earthquake){

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        //añadimos todos los terremotos que le pasamos en la lista
        LatLng point=new LatLng(0,0);
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

    protected void pintaListaPuntos(){

        for (EarthQuake earthquake: earthQuakes){
            pintaPunto(earthquake);
        }

        LatLng point=new LatLng(0,0);

        CameraPosition camPos	=	new	CameraPosition.Builder().target(point)
                .zoom(3)
                .tilt(0)
                .bearing(0)
                .build();

        CameraUpdate camUpd	=	CameraUpdateFactory.newCameraPosition(camPos);
        map.animateCamera(camUpd);

    }

*/


}
