package com.igorgarcia.geolocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.igorgarcia.geolocation.Geolocaclizacion.LocationListenerX;


public class MainActivity extends ActionBarActivity implements LocationListenerX {

    //Hay que pedir permisos en el manifiesto, sino no funciona

    private TextView lblLatitud;
    private TextView lblLongitud;
    private TextView lblAltitud;
    private TextView lblVelocidad;

    private LocationManager locationManager = LocationManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lblLatitud = (TextView) findViewById(R.id.LblLatitud);
        lblLongitud = (TextView) findViewById(R.id.LblLongitud);
        lblAltitud = (TextView) findViewById(R.id.LblAltitud);
        lblVelocidad = (TextView) findViewById(R.id.LblVelocidad);


        getLocationProvider();
        listenLocationChanges();


    }

    private void getLocationProvider() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        Criteria criterios = new Criteria();

        criterios.setAccuracy(Criteria.ACCURACY_FINE);
        criterios.setSpeedRequired(true);
        criterios.setAltitudeRequired(true);


        String bestProvider = locationManager.getBestProvider(criterios, true);

        Log.d("GEO", bestProvider);
    }


    private void listenLocationChanges(){


        int t= 5000;
        int distance=5;

        LocationListenerX listener= new LocationListenerX (this);


        locationManager=requesLocationUpdates(provider,t,distance,listener);

    }



    @Override
    public void AddLocation(Location location){
        lblLatitud.setText(String.valueOf(location.getLatitude));

    }



}
