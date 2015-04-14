package com.igorgarcia.terremotos.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.igorgarcia.terremotos.Alarmas.Alarma;
import com.igorgarcia.terremotos.Fragmentos.EarthQuakeListFragment;
import com.igorgarcia.terremotos.Fragmentos.EarthQuakeMapFragment;
import com.igorgarcia.terremotos.Fragmentos.EarthQuakesListMapFragment;
import com.igorgarcia.terremotos.ListenerPestanyas.TabListener;
import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.Model.MiActionBar;
import com.igorgarcia.terremotos.R;
import com.igorgarcia.terremotos.Tasks.DownloadEarthQuakeTask;
import com.igorgarcia.terremotos.servicios.DownloadEarthQuakeService;


public class MainActivity extends Activity implements DownloadEarthQuakeTask.AddEarthQuakeInterface {

    private int PREFS_ACTIVITY = 1;
    private String EARTHQUAKE_PREFS = "Pref_Aplicacion";
    private String EARTHQUAKE = "EARTHQUAKE";

    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //downloadEarthQuakes();
        //cambiamos el de arriba por el servicio
        //downloadEarthQuakesService();


        actionBar = getActionBar();

        actionBar.setTitle("Terremotos");
        actionBar.setSubtitle("Mi aplicacion");
        actionBar.setDisplayShowTitleEnabled(true);

        actionBar.setHomeButtonEnabled(true);

        //modo con pestañas
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Creamos las pestañas
        ActionBar.Tab tabOne = actionBar.newTab();
        ActionBar.Tab tabtwo = actionBar.newTab();

        tabOne.setText("Lista")

                //.setIcon(R.drawable.ic_launcher)
                //.setContentDescription("Segunda”)
                .setTabListener(
                        new TabListener<EarthQuakeListFragment>
                                (this, R.id.fragmentContainer, EarthQuakeListFragment.class));
        actionBar.addTab(tabOne);

        tabtwo.setText("Mapa")

                .setTabListener(
                        new TabListener<EarthQuakesListMapFragment>
                                (this, R.id.fragmentContainer, EarthQuakesListMapFragment.class));
        actionBar.addTab(tabtwo);

        checkToSetAlarm();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case (android.R.id.home):

                Intent intent = new Intent(this, ActionBarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //return true;
                break;

            case (R.id.action_settings):
                //sacamos las preferencias
                Intent prefsIntent = new Intent(this, SettingsActivity.class);
                startActivityForResult(prefsIntent, PREFS_ACTIVITY);

                //return true;
                break;

            default:
                return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //aqui habria que poner el codigo si queremos hacer algo cuando se cierren las settings
    }


    private void checkToSetAlarm() {

        // Es para poner una alarma periodica para bajar los terremotos

        String KEY = "LAUNCHED_BEFORE";

        SharedPreferences prefs = getSharedPreferences(EARTHQUAKE_PREFS, Activity.MODE_PRIVATE);

        String Opcion2 = getString(R.string.opcion2Key);

        if (!prefs.getBoolean(KEY, false)) {
            //si entra => es la primera vez que entra en la aplicacion

            int tiempo = getResources().getInteger(R.integer.default_Interval);
            Alarma.setRepeatingAlarm(this, tiempo, true);

            prefs.edit().putBoolean(KEY, true).apply();
        }

    }


    @Override
    public void NotifyTotal(int Total) {

        String msg = getString(R.string.Mensaje, Total);
        //saca un mensaje
        Toast toast = Toast.makeText(this, msg + Total, Toast.LENGTH_LONG);
    }


    private void downloadEarthQuakesService() {
        //Lanzar servicio descarga de datos
        Intent download = new Intent(this, DownloadEarthQuakeService.class);
        startService(download);
    }


    private void downloadEarthQuakes() {
        //Tarea para descargamos los datos de terremotos
        DownloadEarthQuakeTask task = new DownloadEarthQuakeTask(this, this);
        task.execute(getString(R.string.earth_quakes_url));

    }


    @Override
    public void AddEarthQuake(EarthQuake earthquake) {

  /*      //Solo dibujamos los mayores que el filtro
        double minMag=Double.parseDouble(Prefs.getString(getString(R.string.opcion3Key), ""));

        if (earthquake.getMagnitud()>=minMag) {

            earthQuakes.add(0, earthquake);
            //aa.notifyDataSetChanged();

            aa2.notifyDataSetChanged();

        }*//*
        // esto ya no hace falta
 */

    }






}
