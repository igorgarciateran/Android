package com.igorgarcia.geolocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.igorgarcia.geolocation.Geolocaclizacion.GeoGoogleApi;
import com.igorgarcia.geolocation.Geolocaclizacion.Geolocalizacion2;



public class MainActivity extends ActionBarActivity implements Geolocalizacion2.AddLocationInterface,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    //Hay que pedir permisos en el manifiesto, sino no funciona

    private TextView lblLatitud;
    private TextView lblLongitud;
    private TextView lblAltitud;
    private TextView lblVelocidad;

    private LocationManager locationManager ;
    private String bestProvider;

    private boolean conectado=false;
    private GoogleApiClient googleapiClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager)getSystemService (Context.LOCATION_SERVICE);

        lblLatitud = (TextView) findViewById(R.id.LblLatitud);
        lblLongitud = (TextView) findViewById(R.id.LblLongitud);
        lblAltitud = (TextView) findViewById(R.id.LblAltitud);
        lblVelocidad = (TextView) findViewById(R.id.LblVelocidad);

        Log.d("GEO", "Entramos");

        getLocationProvider();
        listenLocationChanges();


    }

    private void getLocationProvider() {

        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Log.d("GEO", "Entramos get location");

        Criteria criterios = new Criteria();

        criterios.setAccuracy(Criteria.ACCURACY_COARSE);
        criterios.setSpeedRequired(true);
        criterios.setAltitudeRequired(true);

        //provider = locationManager.getBestProvider(criteria, true);
         bestProvider = locationManager.getBestProvider(criterios, true);

        Log.d("GEO", bestProvider);
    }


    private void listenLocationChanges(){
        int t= 5000;
        int distance=5;

        Log.d("GEO", "listenlocationchanges");

        Geolocalizacion2 listener= new Geolocalizacion2 (this);

        locationManager.requestLocationUpdates(bestProvider, t, distance, listener);
    }


    @Override
    public void AddLocation(Location location){
        Log.d("GEO", "addlocation");

        lblLatitud.setText(String.valueOf(location.getLatitude()));
        lblLongitud.setText(String.valueOf(location.getLongitude()));
        lblVelocidad.setText(String.valueOf(location.getSpeed()));
        lblAltitud.setText(String.valueOf(location.getAltitude()));
    }


    @Override
    public void onConnected(Bundle bundle) {

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


}
