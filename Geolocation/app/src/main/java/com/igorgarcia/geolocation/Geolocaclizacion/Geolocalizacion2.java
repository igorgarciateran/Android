package com.igorgarcia.geolocation.Geolocaclizacion;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by cursomovil on 7/04/15.
 */
public class Geolocalizacion2 implements LocationListener {


    public interface AddLocationInterface {
        public void AddLocation(Location location);
    }


    private AddLocationInterface target;
    public Geolocalizacion2 (AddLocationInterface target) {
        this.target=target;
    }


    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        Log.d("GEO","Location Changed");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("GEO"," Status Changed");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("GEO","Provider Enabled");

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("GEO","Provider  disabled");
    }


}
