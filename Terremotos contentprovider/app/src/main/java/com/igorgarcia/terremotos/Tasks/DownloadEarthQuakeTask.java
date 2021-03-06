package com.igorgarcia.terremotos.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.igorgarcia.terremotos.provider.EarthQuakeDB;
import com.igorgarcia.terremotos.Model.Coordinate;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;

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

/**
 * Created by cursomovil on 25/03/15.
 */
public class DownloadEarthQuakeTask extends AsyncTask<String, EarthQuake, Integer> {

    EarthQuakeDB earthQuakeDB=null;

    //creamos una interfaz para poder acceder desde fuera
    public interface AddEarthQuakeInterface {
        public void AddEarthQuake(EarthQuake earthquake);

        public void NotifyTotal(int Total);
    }


    private String EARTHQUAKE = "EARTHQUAKE";

    private AddEarthQuakeInterface target;



    public DownloadEarthQuakeTask(AddEarthQuakeInterface target,Context context) {

        this.target = target;
        //conectamos con la BD
        earthQuakeDB=new EarthQuakeDB(context);


    }

    @Override
    protected Integer doInBackground(String... urls) {

        //el trabajo que se tiene que ir haciendo => Descargar los datos
        int cont = 0;
        if (urls.length > 0) {
            //Descargar los datos
            cont = updateEarthQuake(urls[0]);
        }
        return cont;
    }


    @Override
    protected void onProgressUpdate(EarthQuake... earthQuakes) {
        super.onProgressUpdate(earthQuakes);
        //utilizamos una interfaz para poder pasar datos fuera
        // target.AddEarthQuake(earthQuakes[0]);

        Log.d(EARTHQUAKE,"Progress update");
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        //sacamos un total de los terremotos que hemos bajado
        target.NotifyTotal(integer);
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

            //Log.d(EARTHQUAKE, earthQuake.toString());

            //earthQuakes.add(0,earthQuake);
            //aa.notifyDataSetChanged();


            //es una llamada especial para que se ejecute el evento onprogress update
            publishProgress(earthQuake);

            //lo guardamos en la BD
            earthQuakeDB.insertNewEarthQuake(earthQuake);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
