package com.igorgarcia.geolocation.Geolocaclizacion;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by cursomovil on 7/04/15.
 */
public class GeoGoogleApi {

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;


    protected void createLocationRequest() {
         mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
    }







}
