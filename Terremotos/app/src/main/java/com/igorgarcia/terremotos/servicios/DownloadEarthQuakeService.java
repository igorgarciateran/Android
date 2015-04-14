package com.igorgarcia.terremotos.servicios;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.igorgarcia.terremotos.Alarmas.Alarma;
import com.igorgarcia.terremotos.BD.EarthQuakeDB;
import com.igorgarcia.terremotos.Model.Coordinate;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;
import com.igorgarcia.terremotos.activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadEarthQuakeService extends Service {


    private EarthQuakeDB earthQuakeDB = null;
    private static String EARTHQUAKE = "EARTHQUAKE";

    @Override
    public void onCreate() {
        super.onCreate();

        earthQuakeDB = new EarthQuakeDB(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                updateEarthQuake(getString(R.string.earth_quakes_url));
            }
        });
        t.start();

        Log.d("EARTHQUAKE", "se bajaron datos");
        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    private int updateEarthQuake(String earthquakeFeed) {
        //Devolvera el total de terremotos que nos pasan
        JSONObject json;

        //Devolvera el total de terremotos que nos pasan
        int contador = 0;

        // String earthquakeFeed = getString(R.string.earth_quakes_url);
        try {

            //conexion para descargar los datos de terremotos
            URL url = new URL(earthquakeFeed);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                json = new JSONObject(responseStrBuilder.toString());
                JSONArray earthquakes = json.getJSONArray("features");
                //Devolvera el total de terremotos que nos pasan
                contador = earthquakes.length();
                for (int i = earthquakes.length() - 1; i >= 0; i--) {
                    //Saca los datos del JSON y los pasa en un objeto para tratarlo en onProgressUpdate
                    processEarthQuakeTask(earthquakes.getJSONObject(i));
                }
                Log.d(EARTHQUAKE,"Actualizados " + contador );

                sendNotification(contador);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contador;
    }


    private void processEarthQuakeTask(JSONObject JsonObj) {
        //Saca los datos del JSON y los mete en un objeto
        try {
            //String id = JsonObj.getString("id");
            JSONArray JsonCoords = JsonObj.getJSONObject("geometry").getJSONArray("coordinates");
            Coordinate coords = new Coordinate(JsonCoords.getDouble(0), JsonCoords.getDouble(1), JsonCoords.getDouble(2));

            String id = JsonObj.getString("id");

            JSONObject properties = JsonObj.getJSONObject("properties");

            EarthQuake earthQuake = new EarthQuake();

            earthQuake.set_id(JsonObj.getString("id"));
            earthQuake.setPlace(properties.getString("place"));
            earthQuake.setMagnitud(properties.getDouble("mag"));
            earthQuake.setTime(properties.getLong("time"));
            earthQuake.setUrl(properties.getString("url"));
            earthQuake.setCoords(coords);

            //earthQuakes.add(0,earthQuake);
            //aa.notifyDataSetChanged();


            //lo guardamos en la BD
            earthQuakeDB.insertNewEarthQuake(earthQuake);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void sendNotification (int cont){

        //muestra un aviso en la barra

        Intent intentToFire=new Intent(this, MainActivity.class);
        PendingIntent activityIntent = PendingIntent.getActivity(this,0,intentToFire,0);

        Notification.Builder builder= new Notification.Builder(DownloadEarthQuakeService.this );

        builder.setSmallIcon (R.drawable.ic_launcher)
        .setContentTitle(getString(R.string.app_name))
                .setTicker("Notification")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Terremotos " + cont + " procesados")
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setContentIntent(activityIntent);





        //.setProgress(100, 75, false)
        //.setContent(myRemoteView)

        Notification	notification	=	builder.getNotification();
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        int NOTIFICATION_REF=1;
        notificationManager.notify(NOTIFICATION_REF,notification);



    }

}
