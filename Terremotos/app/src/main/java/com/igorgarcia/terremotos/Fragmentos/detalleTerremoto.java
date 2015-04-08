package com.igorgarcia.terremotos.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.igorgarcia.terremotos.BD.EarthQuakeDB;
import com.igorgarcia.terremotos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class detalleTerremoto extends Fragment {



    String EARTHQUAKE_KEY = "EARTHQUAKE_KEY";
    private EarthQuakeDB earthQuakeDB;



    public detalleTerremoto() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_terremoto, container, false);



    }








}
